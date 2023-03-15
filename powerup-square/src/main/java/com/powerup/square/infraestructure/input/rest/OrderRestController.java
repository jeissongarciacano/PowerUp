package com.powerup.square.infraestructure.input.rest;


import com.powerup.square.application.dto.order.OrderListRequest;
import com.powerup.square.application.dto.order.OrderRequest;
import com.powerup.square.application.dto.order.OrderResponse;
import com.powerup.square.application.handler.IOrderHandler;
import com.powerup.square.infraestructure.configuration.userclient.UserClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderRestController {
    private final IOrderHandler orderHandler;
    private final UserClient userClient;

    @Operation(summary = "Add a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Order already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PostMapping("/client/make_order")
    public ResponseEntity<OrderResponse> makeOrder(@Validated @RequestBody OrderRequest orderRequest){
        orderRequest.setIdClient(userClient.getUserByEmail(userLoginApplication()).getId());
        orderHandler.saveOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @Operation(summary = "Take order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order taken", content = @Content),
            @ApiResponse(responseCode = "409", description = "Order doesn't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PutMapping("/employee/take_order/{idOrder}/{amount}/{page}/{sort}/{state}")
    public ResponseEntity<List<OrderResponse>> takeOrder(@PathVariable Long idOrder, @PathVariable Long amount, @PathVariable Long page,@PathVariable String sort, @PathVariable String state){
        if(idOrder <= 0L || amount <= 0L || page < 0L || sort.isBlank() || sort.isEmpty() || state.isBlank() || state.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        Long idEmployee =  userClient.getUserByEmail(userLoginApplication()).getId();
        return ResponseEntity.status(HttpStatus.OK).body(orderHandler.takeOrder(idOrder, idEmployee, amount, page, sort, state));
    }
    @Operation(summary = "get Orders By State")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Orders found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Orders don't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @GetMapping("/employee/getOrdersByState/{amount}/{page}/{sort}/{state}")
    public ResponseEntity<List<OrderResponse>> getAllOrderByState(@PathVariable Long amount, @PathVariable Long page,@PathVariable String sort, @PathVariable String state){
        if(amount <= 0L || page < 0L || sort.isBlank() || sort.isEmpty() || state.isBlank() || state.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        Long idEmployee =  userClient.getUserByEmail(userLoginApplication()).getId();
        return  ResponseEntity.status(HttpStatus.FOUND).body(orderHandler.getOrders(amount, page, sort, idEmployee, state));
    }
    @Operation(summary = "order ready to deliver")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order ready to deliver", content = @Content),
            @ApiResponse(responseCode = "409", description = "Order doesn't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PutMapping("/employee/order_ready/{idOrder}")
    public ResponseEntity<Void> orderReadyDeliver(@PathVariable Long idOrder){
        if(idOrder <= 0L)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        Long idEmployee =  userClient.getUserByEmail(userLoginApplication()).getId();
        orderHandler.readyToDeliver(idOrder, idEmployee);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @Operation(summary = "order deliver")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order deliver", content = @Content),
            @ApiResponse(responseCode = "409", description = "Order doesn't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PutMapping("/employee/order_deliver/{idOrder}/{pin}")
    public ResponseEntity<Void> orderDeliver(@PathVariable Long idOrder, @PathVariable String pin){
        if(idOrder <= 0L)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        Long idEmployee =  userClient.getUserByEmail(userLoginApplication()).getId();
        orderHandler.deliver(idOrder, idEmployee, pin);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @Operation(summary = "cancel Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order cancel", content = @Content),
            @ApiResponse(responseCode = "409", description = "Order doesn't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PutMapping("/client/cancel_order/{idOrder}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long idOrder){
        if(idOrder <= 0L)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        Long idClient =  userClient.getUserByEmail(userLoginApplication()).getId();
        orderHandler.cancelOrder(idOrder, idClient);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    public static String userLoginApplication() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }
        return userDetails.getUsername();
    }

}
