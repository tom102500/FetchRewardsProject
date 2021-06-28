package com.fetchrewards.automation;

import com.fetchrewards.automation.frontend.BaseClass;
import com.fetchrewards.automation.frontend.HomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class contains the solution for challenge 2.
 * It performs following actions:
 * a. clicks on buttons (“Weigh”, “Reset”)
 * b. Getting the measure results (field between “bowls”)
 * c. filling out the bowls grids with bar numbers (0 to 8)
 * d. getting list of weighings
 * e. Clicking on gold bar number at the bottom of the website and checking alert message
 *
 *
 * @author Ning Yuan.
 */
public class FetchRewardsWebActionChallenge2 extends BaseClass{
    HomePage homePage;

    @BeforeClass
    public void setUp() {
        instantiateDriver();
        homePage = new HomePage();
    }

    //a.clicks on buttons (“Weigh”, “Reset”)
    @Test
    public void clickButtonTest(){
        homePage.clickWeighButton();
        homePage.clickResetButton();
    }

    //b. Getting the measure results (field between “bowls”)
    @Test(priority = 1)
    public void getMeasureResult(){
        System.out.println(homePage.getMeasureResult());
    }

    //c. filling out the left bowls grids with bar numbers (0 to 8)
    @Test(priority = 2)
    public void fillOutLeftBar(){
        List<Integer> numbers = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8)
        );
        homePage.fillLeftBowls(numbers);
    }

    //c. filling out the right bowls grids with bar numbers (0 to 8)
    @Test(priority = 2)
    public void fillOutRightBar(){
        List<Integer> numbers = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8)
        );
        homePage.fillRightBowls(numbers);
    }

    @Test(priority = 3)
    public void printOutWeighingList(){
        List<String> res = homePage.getWeighingList();
        System.out.println("Print out the list of weighings: ");
        for(String r : res){
            System.out.println(r);
        }
    }

    //d. getting list of weighings
    @DataProvider(name = "goldBarNumData")
    protected Object[][] goldBarNumData(){
        return new Object[][]{
                {0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}
        };
    }

    //e. Clicking on gold bar number at the bottom of the website and checking alert message
    @Test(dataProvider = "goldBarNumData", priority = 4)
    public void clickBottomBar(int barNum){
        try{
            homePage.clickOnGoldBar(barNum);
            Thread.sleep(1000);
            homePage.checkAlertMessage();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
