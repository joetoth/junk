package com.penny.bidder.scraper;

/**
 * Created by IntelliJ IDEA.
 * User: jtoth
 * Date: 12/15/10
 * Time: 4:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScraperMain {

    public static void main(String[] args) {

        Class[] scrapers = { Skoreit.class };

        ScraperController runner = new ScraperController("/home/joe/junk/", scrapers);
        runner.run();
    }

}
