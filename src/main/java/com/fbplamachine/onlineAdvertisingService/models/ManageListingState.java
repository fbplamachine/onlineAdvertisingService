package com.fbplamachine.onlineAdvertisingService.models;

import lombok.*;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@Builder
public class ManageListingState {

    @NotEmpty
    ListingState listingState;

    @NotEmpty
    boolean showException;
}
