package uk.co.atomicmedia.boilerplate.data.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import uk.co.atomicmedia.boilerplate.data.entities.Auction;

public class ApiResultSearch {

    @SerializedName("auctions")
    @Expose
    public List<Auction> auctions;
}
