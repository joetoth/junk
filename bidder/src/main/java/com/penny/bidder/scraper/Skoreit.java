package com.penny.bidder.scraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jtoth
 * Date: 12/15/10
 * Time: 3:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Skoreit implements PennyScraper {

    @Override
    public String domain() {
        return "skoreit.com";
    }

    @Override
    public List<PennyAuctionStatus> extractPennyAuctionStatuses(WebDriver browser) {

        List<PennyAuctionStatus> auctionStatuses = new ArrayList<PennyAuctionStatus>();

                List<WebElement> elements = browser.findElements(By.xpath("/html/body/div[3]/div/div/div[2]/div[3]/div/ul/li"));

                for (WebElement element : elements) {
                    String product = extractProduct(element);
                    String retailPrice = extractRetailPrice(element);
                    String highBidder = extractHighBidder(element);
                    String timeLeft = extractTimeLeft(element);

                    auctionStatuses.add(new PennyAuctionStatus(highBidder, product, retailPrice, timeLeft));
                }

        return auctionStatuses;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private static String extractProduct(WebElement element) {
        return element.findElement(By.xpath("a")).getText();
    }

    private static String extractRetailPrice(WebElement element) {
        return element.findElement(By.xpath("div[3]/div")).getText();
    }

    private static String extractHighBidder(WebElement element) {
        return element.findElement(By.xpath("div[4]/div")).getText();
    }

    private static String extractTimeLeft(WebElement element) {
        return element.findElement(By.xpath("div[5]/div")).getText();
    }

}
