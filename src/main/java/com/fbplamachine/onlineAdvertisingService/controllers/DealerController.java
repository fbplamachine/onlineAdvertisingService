package com.fbplamachine.onlineAdvertisingService.controllers;


import com.fbplamachine.onlineAdvertisingService.models.Dealer;
import com.fbplamachine.onlineAdvertisingService.services.DealerService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import javax.validation.Valid;
import java.util.List;

@RestController
@Tag(name = "Dealer Controller", description = "Api for managing dealers")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Validated
@RequestMapping(path = "api/v1/dealer")
public class DealerController {
    private final DealerService dealerService;


    @Autowired
    public DealerController(DealerService dealerService) {
        this.dealerService = dealerService;
    }

    @Operation(summary = "Get all dealers", description = "This endpoint is used to get all dealers", tags = "Get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Dealer.class))}),
            @ApiResponse(responseCode = "404", description = "Resource access does not exist", content = @Content),
            @ApiResponse(responseCode = "400", description = " Bad Request - syntax error", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Dealer> getDealers() {
        return dealerService.getDealers();
    }

    @Operation(summary = "Create Dealer", description = "This endpoint is used to create dealer", tags = "Post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success"),
            @ApiResponse(responseCode = "400", description = " Bad Request - syntax error", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dealer addDealer(@RequestBody final Dealer dealer){
        return dealerService.addDealer(dealer);
    }

    @Operation(summary = "Get dealer detail", description = "This endpoint is used to get dealer detail", tags = "Get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Dealer.class))}),
            @ApiResponse(responseCode = "404", description = "Resource access does not exist", content = @Content),
            @ApiResponse(responseCode = "400", description = " Bad Request - syntax error", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(path = "{dealerId}")
    @ResponseStatus(HttpStatus.OK)
    public Dealer dealerDetail(@Parameter(description = "Dealer id for detail", required = true) @PathVariable("dealerId") Long dealerId){
        return dealerService.dealerDetail(dealerId);
    }

    @Operation(summary = "Delete dealer by id", description = "This endpoint is used to delete dealer by id", tags = "Delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Resource access does not exist", content = @Content),
            @ApiResponse(responseCode = "400", description = " Bad Request - syntax error", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping(path = "{dealerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDealer(@Parameter(description = "Dealer id for deletion", required = true) @PathVariable("dealerId") Long dealerId){
        dealerService.deleteDealer(dealerId);
    }

    @Operation(summary = "Update Dealer by id", description = "This endpoint is used to update dealer by id", tags = "Put")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Resource access does not exist", content = @Content),
            @ApiResponse(responseCode = "400", description = " Bad Request - syntax error", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping(path = "{dealerId}")
    @ResponseStatus(HttpStatus.OK)
    public Dealer updateDealer(@Parameter(description = "Dealer id for the update", required = true) @PathVariable("dealerId") Long dealerId,
                               @Valid @Parameter(description = "Dealer body for the update", required = true) @RequestBody Dealer dealer){
        dealer.setId(dealerId);
        return dealerService.updateDealer(dealer);
    }
}
