package TestCases.BasicTestCases;

import Framwork.APIs.APICoverage;
import Framwork.Pages.HomePage;
import TestCases.BaseTest;
import com.google.gson.JsonObject;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.List;


public class TestSearch extends BaseTest {
    private String key = "v7cm36gekf7bj7ydvuwxe5zs";
    private String query = "Shell Scripting";


    @Test
    public void TestSearchAPIUI() {
        APICoverage apiCoverage = new APICoverage();
        JsonObject response = apiCoverage.Search(key, query);
        List APIResults = ReturnNofJsonObjectitems(response, 3);
        System.out.println(APIResults);

        HomePage homePage = new HomePage();
        List UIResults = homePage.SearchAndGetNamesAndPrices(driver, query);
        System.out.println(UIResults);

        for (int i=0; i < APIResults.size(); i++){
            HashMap APIResult = (HashMap) APIResults.get(i);
            HashMap UIResult = (HashMap) UIResults.get(i);

            assertEquals(APIResult.get("name"), UIResult.get("name"));
            assertTrue(CheckPrice((String) APIResult.get("price"), (String) UIResult.get("price")));

        }
    }
}
