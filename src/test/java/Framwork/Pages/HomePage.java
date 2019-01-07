package Framwork.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomePage {
    private String url = "https://www.walmart.com/";

    public List SearchAndGetNamesAndPrices(WebDriver driver, String query){
        /*
           Return a list of maps of (name, price) value for each search result.
         */
        driver.get(url);
        driver.findElement(By.id("global-search-input")).clear();
        driver.findElement(By.id("global-search-input")).sendKeys(query);
        driver.findElement(By.id("global-search-form")).submit();

        List<WebElement> items = driver.findElements(By.className("search-result-listview-item"));
        List<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();

        for (WebElement item : items){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("name", item.findElement(By.className("product-title-link")).getText());
            map.put("price", item.findElement(By.className("search-result-productprice")).getText().substring(14));
            results.add(map);
        }
        return results;
    }
}
