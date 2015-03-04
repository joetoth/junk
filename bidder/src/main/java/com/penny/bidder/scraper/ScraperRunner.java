package com.penny.bidder.scraper;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jtoth
 * Date: 12/14/10
 * Time: 12:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScraperRunner implements Runnable {

    String logDirectory;
    PennyScraper pennyScraper;

    HtmlUnitDriver driver;
    long lastWritten = System.currentTimeMillis();

    public ScraperRunner(String logDirectory, PennyScraper pennyScraper) {
        if (!logDirectory.endsWith("/")) {
            logDirectory = logDirectory + "/";
        }

        this.logDirectory = logDirectory;
        this.pennyScraper = pennyScraper;
    }

    @Override
    public void run() {

        restartBrowser();

        try {

//            waitToLoad(driver);

            while (true) {

                Long time = System.currentTimeMillis();

                List<PennyAuctionStatus> auctions = pennyScraper.extractPennyAuctionStatuses(driver);
                writeLog(time, auctions);

                if (!isAlive()) {
                    restartBrowser();
                    resetAlive();
                } else {
                    Thread.sleep(500l);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    private void restartBrowser() {
        driver = new HtmlUnitDriver();
        driver.setJavascriptEnabled(true);
        driver.get("http://" + pennyScraper.domain());
    }

    private void resetAlive() {
        lastWritten = System.currentTimeMillis();
    }

  /**
     * Returns true if data has been written to log within a minute
     * @return
     */
    public boolean isAlive() {
        return System.currentTimeMillis() - lastWritten < 60000;
    }

//    private static void waitToLoad(WebDriver browser) throws InterruptedException {
//        if (!isPageLoaded(browser)) {
//            Thread.sleep(1000l);
//            waitToLoad(browser);
//        }
//    }

//    private static boolean isPageLoaded(WebDriver browser) {
//        // Browsers which render content (such as Firefox and IE) return "RenderedWebElements"
//        RenderedWebElement resultsDiv = (RenderedWebElement) browser.findElement(By.id("footer"));
//
//        // If results have been returned, the results are displayed in a drop down.
//        return resultsDiv.isDisplayed();
//    }

    private void writeLog(Long time, List<PennyAuctionStatus> auctions)
            throws Exception {

        File file = new File(logDirectory + pennyScraper.domain() + ".log");
        file.createNewFile();
        FileWriter writer = new FileWriter(file, true);

        for (PennyAuctionStatus auction : auctions) {
            writer.write(time + "\t");
            writer.write(auction.getProduct() + "\t");
            writer.write(auction.getRetailPrice() + "\t");
            writer.write(auction.getHighBidder() + "\t");
            writer.write(auction.getTimeLeft() + "\t\n");
        }

        writer.flush();
        writer.close();

        if (!auctions.isEmpty()) {
            resetAlive();
        }
    }

}
