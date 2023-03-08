package com.powerup.square.infraestructure.input.rest;


import com.powerup.square.application.dto.*;
import com.powerup.square.application.handler.IPlateHandler;
import com.powerup.square.infraestructure.out.jpa.entity.PlateEntity;
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
@RequestMapping("/plates")
@RequiredArgsConstructor
public class PlateRestController {

    private final IPlateHandler plateHandler;

    @Operation(summary = "Add a new plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Plate created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Plate already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PostMapping("/createPlate")
    public ResponseEntity<Void> savePlateEntity(@Validated @RequestBody  PlateRequest plateRequest){
        plateHandler.savePlate(plateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @Operation(summary = "Get plates by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Plate gotten", content = @Content),
            @ApiResponse(responseCode = "409", description = "Plate doesn't exists", content = @Content)
    })
    @GetMapping("/getPlate/{id}")
    public PlateResponse getAllPlateById(@PathVariable Long id){
        return plateHandler.getPlate(id);
    }
    @Operation(summary = "change plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plate modified", content = @Content),
            @ApiResponse(responseCode = "409", description = "Plate doesn't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PutMapping("/putPlate")
    public ResponseEntity<Void> editPlate(@RequestBody PlateUpdatingRequest plateUpdatingRequest){
        plateHandler.updatePlate(plateUpdatingRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @Operation(summary = "activate plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plate disable or active", content = @Content),
            @ApiResponse(responseCode = "409", description = "Plate doesn't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PutMapping("/activatePlate")
    public ResponseEntity<Void> activatePlate(@Validated @RequestBody ActivatePlateRequest activatePlateRequest){
        plateHandler.activePlate(activatePlateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @Operation(summary = "Get plates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Plates found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Plates don't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PostMapping("/getPlates")
    public ResponseEntity<List<PlateResponse>> getAllPlates(@RequestBody PlateListRequest plateListRequest){
        return ResponseEntity.status(HttpStatus.FOUND).body(plateHandler.getPlates(plateListRequest));
    }

}
