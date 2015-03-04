package com.penny.bidder.scraper;

import org.openqa.selenium.WebDriver;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jtoth
 * Date: 12/15/10
 * Time: 3:21 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PennyScraper {

        String domain();

        List<PennyAuctionStatus> extractPennyAuctionStatuses(WebDriver browser);

}
