package com.powerup.square.infraestructure.input.rest;


import com.powerup.square.application.dto.EmployeeRequest;
import com.powerup.square.application.dto.OrderRequest;
import com.powerup.square.application.handler.IOrderHandler;
import com.powerup.square.domain.model.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderRestController {
    private final IOrderHandler orderHandler;

    @Operation(summary = "Add a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Order already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PostMapping("/makeOrder")
    public ResponseEntity<Void> makeOrder(@Validated @RequestBody OrderRequest orderRequest){
        orderHandler.saveOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @Operation(summary = "Take order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order taken", content = @Content),
            @ApiResponse(responseCode = "409", description = "Order doesn't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PutMapping("/takeOrder")
    public ResponseEntity<Void> takeOrder(){
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @Operation(summary = "get Orders By State")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Orders found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Orders don't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PostMapping("/getOrdersByState")
    public ResponseEntity<List<Order>> getAllOrderByState(){
        return ResponseEntity.status(HttpStatus.FOUND).body(orderHandler.getOrders());
    }
    @Operation(summary = "order ready to deliver")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order ready to deliver", content = @Content),
            @ApiResponse(responseCode = "409", description = "Order doesn't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PutMapping("/orderReadyDeliver")
    public ResponseEntity<Void> orderReadyDeliver(){
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @Operation(summary = "order deliver")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order deliver", content = @Content),
            @ApiResponse(responseCode = "409", description = "Order doesn't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PutMapping("/orderDeliver")
    public ResponseEntity<Void> orderDeliver(){
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @Operation(summary = "cancel Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order cancel", content = @Content),
            @ApiResponse(responseCode = "409", description = "Order doesn't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PutMapping("/cancelOrder")
    public ResponseEntity<Void> cancelOrder(){
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
