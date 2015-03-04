package com.penny.bidder.scraper;

/**
 * Created by IntelliJ IDEA.
 * User: jtoth
 * Date: 12/15/10
 * Time: 3:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class PennyAuctionStatus {

    String product;

    String retailPrice;

    String highBidder;

    String timeLeft;

    public PennyAuctionStatus(String highBidder, String product, String retailPrice, String timeLeft) {
        this.highBidder = highBidder;
        this.product = product;
        this.retailPrice = retailPrice;
        this.timeLeft = timeLeft;
    }

    public String getHighBidder() {
        return highBidder;
    }

    public void setHighBidder(String highBidder) {
        this.highBidder = highBidder;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }
}
