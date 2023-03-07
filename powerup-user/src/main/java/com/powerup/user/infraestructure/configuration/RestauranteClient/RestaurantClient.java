package com.powerup.user.infraestructure.configuration.RestauranteClient;

import com.powerup.user.application.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name="plazoleta-service",url = "http://localhost:8080/" ) //configuration = CustomFeignConfig.class)
public interface RestaurantClient {
    @RequestMapping(method = RequestMethod.POST, value = "restaurants/createRestaurant")
    public ResponseEntity<Void> saveRestaurant(@RequestBody RestaurantRequest restaurantRequest);
    @RequestMapping(method = RequestMethod.POST, value = "plates/createPlate")
    public ResponseEntity<Void> savePlate(@RequestBody PlateRequest plateRequest);
    @RequestMapping(method = RequestMethod.PUT, value = "plates/putPlate")
    public ResponseEntity<Void> editPlate(@RequestBody PlateUpdatingRequest plateUpdatingRequest);
    @RequestMapping(method = RequestMethod.POST, value = "employee/createEmployee")
    public ResponseEntity<Void> createEmployee(@RequestBody EmployeeRequest employeeRequest);
    @RequestMapping(method = RequestMethod.PUT, value = "plates/activatePlate")
    public ResponseEntity<Void> activatePlate(@RequestBody ActivatePlateRequest activatePlateRequest);
    @RequestMapping(method = RequestMethod.POST, value = "restaurants/getAllRestaurant")
    public ResponseEntity<List<RestaurantResponse>> getAllRestaurant(@RequestBody RestaurantListRequest restaurantListRequest);
    @RequestMapping(method = RequestMethod.POST, value = "plates/getPlates")
    public ResponseEntity<List<RestaurantResponse>> getRestaurantPlates(@RequestBody PlateListRequest plateListRequest);
    @RequestMapping(method = RequestMethod.POST, value = "order/makeOrder")
    public ResponseEntity<Void> makeOrder(@RequestBody OrderRequest orderRequest);
    @RequestMapping(method = RequestMethod.GET, value = "order/activatePlate/{state}/{id}")
    public ResponseEntity<List<OrderResponse>> getAllOrderByState(@PathVariable String state, @PathVariable Long id);
    @RequestMapping(method = RequestMethod.PUT, value = "order/activatePlate/{id}/{idEmployee}")
    public ResponseEntity<Void> takeOrder(@PathVariable Long id, @PathVariable Long idEmployee);
    @RequestMapping(method = RequestMethod.PUT, value = "order/activatePlate/{id}/{idEmployee}")
    public ResponseEntity<Void> readyToDeliver(@PathVariable Long id, @PathVariable Long idEmployee);
    @RequestMapping(method = RequestMethod.PUT, value = "order/activatePlate/{id}/{idEmployee}")
    public ResponseEntity<Void> deliverOrder(@PathVariable Long id, @PathVariable Long idEmployee);
    @RequestMapping(method = RequestMethod.PUT, value = "client/activatePlate/{id}/{idClient}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long id, @PathVariable Long idClient);

}
