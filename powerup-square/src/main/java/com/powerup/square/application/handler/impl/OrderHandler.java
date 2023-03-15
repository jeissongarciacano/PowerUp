package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.order.OrderPlateResponse;
import com.powerup.square.application.dto.order.OrderRequest;
import com.powerup.square.application.dto.order.OrderResponse;
import com.powerup.square.application.dto.user.UserResponse;
import com.powerup.square.application.handler.IOrderHandler;
import com.powerup.square.application.mapper.IPlateResponseMapper;
import com.powerup.square.domain.api.IEmployeeServicePort;
import com.powerup.square.domain.api.IOrderPlatesServicePort;
import com.powerup.square.domain.api.IOrderServicePort;
import com.powerup.square.domain.api.IPlateServicePort;
import com.powerup.square.domain.exception.NoDataFoundException;
import com.powerup.square.domain.model.Employee;
import com.powerup.square.domain.model.Order;
import com.powerup.square.domain.model.OrderPlates;
import com.powerup.square.domain.model.Plate;
import com.powerup.square.infraestructure.configuration.TwilioClient;
import com.powerup.square.infraestructure.configuration.userclient.UserClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class OrderHandler implements IOrderHandler {
    private final IOrderServicePort orderServicePort;
    private final IOrderPlatesServicePort orderPlatesServicePort;
    private final RestaurantHandler restaurantHandler;
    private final IPlateServicePort plateServicePort;
    private final IPlateResponseMapper plateResponseMapper;
    private final IEmployeeServicePort employeeServicePort;
    private final UserClient userClient;
    private final TwilioClient twilioClient;

    public OrderHandler(IOrderServicePort orderServicePort, IOrderPlatesServicePort orderPlatesServicePort, RestaurantHandler restaurantHandler, IPlateServicePort plateServicePort, IPlateResponseMapper plateResponseMapper, IEmployeeServicePort employeeServicePort, UserClient userClient, TwilioClient twilioClient) {
        this.orderServicePort = orderServicePort;
        this.orderPlatesServicePort = orderPlatesServicePort;
        this.restaurantHandler = restaurantHandler;
        this.plateServicePort = plateServicePort;
        this.plateResponseMapper = plateResponseMapper;
        this.employeeServicePort = employeeServicePort;
        this.userClient = userClient;
        this.twilioClient = twilioClient;
    }

    @Override
    public void saveOrder(OrderRequest orderRequest) {
        if(!restaurantHandler.existById(orderRequest.getIdRestaurant())) throw new NoDataFoundException();
        else if (existsByIdClientAndState(orderRequest.getIdClient(), "PENDIENTE") ||
                existsByIdClientAndState(orderRequest.getIdClient(), "EN_PREPARACION") ||
                existsByIdClientAndState(orderRequest.getIdClient(), "LISTO")) throw new NoDataFoundException();
        else{
            Date date = new Date();
            Order order = new Order(-1L, orderRequest.getIdClient(), date, "PENDIENTE", null, orderRequest.getIdRestaurant());
            Order orderSaved = orderServicePort.saveOrder(order);
            List<OrderPlates> orderPlates = new ArrayList<>();
            for (int i = 0; i < orderRequest.getPlateOrderRequests().size(); i++) {
                Plate plate = plateServicePort.getPlate(orderRequest.getPlateOrderRequests().get(i).getIdPlates());
                if(Objects.equals(plate.getRestaurant().getId(), orderSaved.getIdRestaurant())){
                    OrderPlates orderPlatesAux = new OrderPlates(orderSaved, plate , orderRequest.getPlateOrderRequests().get(i).getAmountPlates());
                    orderPlates.add(orderPlatesAux);
                }
            }
            if(orderPlates.isEmpty()) throw new NoDataFoundException();
            else orderPlatesServicePort.saveOrderPlates(orderPlates);
        }
    }

    @Override
    public List<OrderResponse> getOrders(Long amount, Long page, String sort, Long idEmployee, String state) {
        Long idRestaurant = employeeServicePort.getEmployee(idEmployee).getRestaurant().getId();
        List<OrderResponse> orderResponses = new ArrayList<>();
        List<Order> orders = orderServicePort.getAllOrder(amount, page, sort, idRestaurant, state);
        for (int i = 0; i < orders.size(); i++) {
            List<OrderPlates> orderPlates = getOrderPlatesById(orders.get(i).getId());
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setId(orders.get(i).getId());
            orderResponse.setDate(orders.get(i).getDate());
            if(orders.get(i).getChef() == null) orderResponse.setIdChef(null);
            else orderResponse.setIdChef(orders.get(i).getChef().getIdUser());
            orderResponse.setIdClient(orders.get(i).getIdClient());
            orderResponse.setOrderPlateResponses(new ArrayList<>());
            for (int j = 0; j < orderPlates.size(); j++) {
                OrderPlateResponse orderPlateResponse = plateResponseMapper.toOrderPlateResponse(orderPlates.get(j).getPlate());
                orderPlateResponse.setAmount(orderPlates.get(j).getAmount());
                orderResponse.getOrderPlateResponses().add(orderPlateResponse);
            }
            orderResponses.add(orderResponse);
        }
        return orderResponses;
    }

    @Override
    public List<OrderResponse> takeOrder(Long idOrder, Long idEmployee, Long amount, Long page, String sort, String state) {
        Long idRestaurant = employeeServicePort.getEmployee(idEmployee).getRestaurant().getId();
        if (!existsByIdAndState(idOrder, "PENDIENTE")) throw new NoDataFoundException();
        Order order = orderServicePort.getOrder(idOrder);
        if(!Objects.equals(idRestaurant, order.getIdRestaurant())) throw new NoDataFoundException();
        Employee employee = employeeServicePort.getEmployee(idEmployee);
        order.setChef(employee);
        order.setState("EN_PREPARACION");
        orderServicePort.saveOrder(order);
        List<OrderResponse> orderResponses = new ArrayList<>();
        List<Order> orders = orderServicePort.getAllOrderByIdEmployee(employee.getId(), amount, page, sort, state);
        for (int i = 0; i < orders.size(); i++) {
            List<OrderPlates> orderPlates = getOrderPlatesById(orders.get(i).getId());
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setId(orders.get(i).getId());
            orderResponse.setDate(orders.get(i).getDate());
            orderResponse.setIdChef(orders.get(i).getChef().getIdUser());
            orderResponse.setIdClient(orders.get(i).getIdClient());
            orderResponse.setOrderPlateResponses(new ArrayList<>());
            for (int j = 0; j < orderPlates.size(); j++) {
                OrderPlateResponse orderPlateResponse = plateResponseMapper.toOrderPlateResponse(orderPlates.get(j).getPlate());
                orderPlateResponse.setAmount(orderPlates.get(j).getAmount());
                orderResponse.getOrderPlateResponses().add(orderPlateResponse);
            }
            orderResponses.add(orderResponse);
        }
        return orderResponses;
    }
    @Override
    public void cancelOrder(Long id, Long idClient) {
        if (!existsByIdAndState(id, "PENDIENTE")) throw new NoDataFoundException();
        else if (existsByIdAndState(id, "EN_PREPARACION") || existsByIdAndState(id, "LISTO")) twilioClient.sendSMS("su pedido ya esta en preparacion, no se puede cancelar");
        else if (existsByIdAndState(id, "CANCELADO")) throw new NoDataFoundException();
        else{
            Order order = orderServicePort.getOrder(id);
            if(!Objects.equals(order.getIdClient(), idClient)) throw new NoDataFoundException();
            order.setState("CANCELADO");
            orderServicePort.saveOrder(order);
        }
    }
    @Override
    public boolean existsByIdClientAndState(Long idClient, String state) {
        return orderServicePort.existsByIdClientAndState(idClient,state);
    }
    @Override
    public List<OrderPlates> getOrderPlatesById(Long id) {
        return orderPlatesServicePort.getAllOrderPlatesByOrderId(id);
    }

    @Override
    public boolean existsByIdAndState(Long id, String state) {
        return orderServicePort.existsByIdAndState(id, state);
    }

    @Override
    public List<Order> getAllOrderByIdEmployee(Long idEmployee, Long amount, Long page, String sort, String state) {
        return orderServicePort.getAllOrderByIdEmployee(idEmployee, amount, page, sort, state);
    }
    @Override
    public void readyToDeliver(Long idOrder, Long idEmployee) {
        if (!existsByIdAndState(idOrder, "EN_PREPARACION")) throw new NoDataFoundException();
        Order order = orderServicePort.getOrder(idOrder);
        if(!Objects.equals(order.getChef().getIdUser(), idEmployee)) throw new NoDataFoundException();
        order.setState("LISTO");
        orderServicePort.saveOrder(order);
        UserResponse userResponse = userClient.getUserById(order.getIdClient());
        String body = "Hola " + userResponse.getName() + "\uD83D\uDCDE!" + "\nTu pedido ya esta listo, recuerda el PIN: " + userResponse.getIdDocument();
        twilioClient.sendSMS(body);
    }

    @Override
    public void deliver(Long idOrder, Long idEmployee, String pin) {
        if (!existsByIdAndState(idOrder, "LISTO")) throw new NoDataFoundException();
        Order order = orderServicePort.getOrder(idOrder);
        if(!Objects.equals(order.getChef().getIdUser(), idEmployee)) throw new NoDataFoundException();
        if(userClient.getUserById(order.getIdClient()).getIdDocument().compareTo(pin) != 0) throw new NoDataFoundException();
        else{
            order.setState("ENTREGADO");
            orderServicePort.saveOrder(order);
        }

    }
}
