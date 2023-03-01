package com.fbplamachine.onlineAdvertisingService.controllers;

import com.fbplamachine.onlineAdvertisingService.models.Listing;
import com.fbplamachine.onlineAdvertisingService.models.ManageListingState;
import com.fbplamachine.onlineAdvertisingService.services.ListingService;
import io.swagger.v3.oas.annotations.Operation;
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

import javax.validation.Valid;
import java.util.List;

@RestController
@Tag(name = "Listing API", description = "Api for managing listings")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Validated
@RequestMapping(path = "api/v1/listing")
public class ListingController {
    private final ListingService listingService;

    @Autowired
    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @Operation(summary = "Get all listings", description = "This endpoint is used to get all listings", tags = "Get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Listing.class))}),
            @ApiResponse(responseCode = "404", description = "Resource access does not exist"),
            @ApiResponse(responseCode = "400", description = " Bad Request - syntax error", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Listing> getListings() {
        return listingService.getListings();
    }

    @Operation(summary = "Create listing", description = "This endpoint is used to create listing", tags = "Post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success"),
            @ApiResponse(responseCode = "400", description = " Bad Request - syntax error", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Listing addListing(@RequestBody final Listing listing){return listingService.addListing(listing);}

    @Operation(summary = "Get listing detail", description = "This endpoint is used to get listing detail", tags = "Get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Listing.class))}),
            @ApiResponse(responseCode = "404", description = "Resource access does not exist", content = @Content),
            @ApiResponse(responseCode = "400", description = " Bad Request - syntax error", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(path = "{listingId}")
    @ResponseStatus(HttpStatus.OK)
    public Listing listingDetail(@Parameter(description = "listing id", required = true) @PathVariable("listingId") Long listingId){
        return listingService.listingDetail(listingId);
    }

    @Operation(summary = "Delete listing by id", description = "This endpoint is used to delete listing by id", tags = "Delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Resource access does not exist", content = @Content),
            @ApiResponse(responseCode = "400", description = " Bad Request - syntax error", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping(path = "{listingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteListing(@Parameter(description = "Listing id for deletion", required = true) @PathVariable("listingId") Long listingId){ listingService.deleteListing(listingId);}

    @Operation(summary = "Update Listing by id", description = "This endpoint is used to update listing by id", tags = "Put")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Resource access does not exist", content = @Content),
            @ApiResponse(responseCode = "400", description = " Bad Request - syntax error", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping(path = "{listingId}")
    @ResponseStatus(HttpStatus.OK)
    public Listing updateListing(@Parameter(description = "Listing id for the update", required = true) @PathVariable("listingId") Long listingId,
                                 @Valid @Parameter(description = "Listing body for the update", required = true) @RequestBody Listing listing){
        listing.setId(listingId);
        return listingService.updateListing(listing);
    }

    @Operation(summary = "Manage Listing State by id", description = "This endpoint is used to manage listing state by id", tags = "Put")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Resource access does not exist"),
            @ApiResponse(responseCode = "400", description = " Bad Request - syntax error", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping(path = "manage/{listingId}")
    @ResponseStatus(HttpStatus.OK)
    public Listing manageListingState(@Parameter(description = "Listing id for the manage listing state", required = true) @PathVariable("listingId") Long listingId,
                                      @Valid @Parameter(description = "Listing body for the manage listing state", required = true) @RequestBody ManageListingState manageListingState){
        return listingService.manageListingState(listingId, manageListingState);
    }
}
