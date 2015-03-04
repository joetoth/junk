package com.penny.bidder.scraper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA.
 * User: jtoth
 * Date: 12/15/10
 * Time: 5:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScraperController implements Runnable {

    String logDirectory;
    Class[] scraperClasses;

    public ScraperController(String logDirectory, Class[] scraperClasses) {
        this.logDirectory = logDirectory;
        this.scraperClasses = scraperClasses;
    }

    @Override
    public void run() {

        List<PennyScraper> pennyScrapers = new ArrayList<PennyScraper>();
        try {
            for (Class scraper : scraperClasses) {
                PennyScraper pennyScraper = (PennyScraper) scraper.newInstance();
                pennyScrapers.add(pennyScraper);

                ScraperRunner runner = new ScraperRunner(logDirectory, pennyScraper);
                Thread thread = Executors.defaultThreadFactory().newThread(runner);
                thread.start();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

}
