package com.powerup.square.infraestructure.out.jpa.adapter;

import com.powerup.square.domain.model.Order;
import com.powerup.square.domain.spi.IOrderPersistencePort;
import com.powerup.square.infraestructure.out.jpa.entity.OrderEntity;
import com.powerup.square.infraestructure.out.jpa.mapper.IOrderMapper;
import com.powerup.square.infraestructure.out.jpa.repository.IEmployeeRepository;
import com.powerup.square.infraestructure.out.jpa.repository.IOrderRepository;
import com.powerup.square.infraestructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderJpaAdapter implements IOrderPersistencePort {
    private final IOrderRepository orderRepository;
    private final IOrderMapper orderMapper;
    private final IEmployeeRepository employeeRepository;
    private final IRestaurantRepository restaurantRepository;

    @Override
    public Order saveOrder(Order order) {
        OrderEntity orderEntity = orderMapper.toEntity(order);
        orderEntity.setRestaurant(restaurantRepository.findById(order.getIdRestaurant()).get());
        return orderMapper.toOrder(orderRepository.save(orderEntity));
    }

    @Override
    public List<Order> getAllOrder() {
        return null;
    }

    @Override
    public Order getOrder(Long id) {
        return null;
    }

    @Override
    public void cancelOrder(Long id) {

    }
    @Override
    public void updateOrder(Order order) {

    }

    @Override
    public Order getOrderByClientId(Long idClient) {
        return null;
    }

}
