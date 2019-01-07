package TestCases;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private static final int TIMEOUT = 30;
    public WebDriver driver;

    @Before
    public void before(){
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
    }

    @After
    public void after(){
        this.driver.manage().deleteAllCookies();
        this.driver.quit();
    }

    public List ReturnNofJsonObjectitems(JsonObject data, int n){
        /*
        Take a JsonObject and return n of its items
         */
        List<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();
        JsonArray items = data.get("items").getAsJsonArray();

        int cont = 0;
        for (JsonElement item : items) {
            JsonObject itemObj = item.getAsJsonObject();
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("name", itemObj.get("name").getAsString());
            map.put("price", itemObj.get("salePrice").getAsString());
            results.add(map);

            cont++;
            if (cont >= n){
                break;
            }
        }
        return results;
    }

    public boolean CheckPrice(String PriceAPI, String PriceUI){
        float APIPrice = Float.parseFloat(PriceAPI);
        float UIPriceMin = Float.parseFloat((PriceUI.replace("$","").split(" - ")[0]));
        float UIPriceMax = UIPriceMin;
        if (PriceUI.contains("-")){
            UIPriceMax = Float.parseFloat((PriceUI.replace("$","").split(" - ")[1]));
        }

        return UIPriceMin <= APIPrice && APIPrice <= UIPriceMax;
    }
}
