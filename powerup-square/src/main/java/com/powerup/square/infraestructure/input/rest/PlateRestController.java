package com.powerup.square.infraestructure.input.rest;


import com.powerup.square.application.dto.PlateRequest;
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
            @ApiResponse(responseCode = "409", description = "Plate already exists", content = @Content)
    })
    @PostMapping("/createPlate/")
    public ResponseEntity<Void> savePlateEntity(@RequestBody @Validated PlateRequest plateRequest){
        plateHandler.savePlate(plateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get plates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Plate gotten", content = @Content),
            @ApiResponse(responseCode = "409", description = "Plate doesn't exists", content = @Content)
    })
    @GetMapping("/getPlates/")
    public ResponseEntity<List<PlateEntity>> getAllPlates(){

        return null;
    }
    @Operation(summary = "change plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Plate edited successfully", content = @Content)
    })
    @PutMapping("/putPlate/")
    public ResponseEntity<Void> editPlate(@RequestBody PlateEntity plateEntity){
        return null;
    }

}
