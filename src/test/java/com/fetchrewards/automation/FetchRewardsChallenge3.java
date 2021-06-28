package com.fetchrewards.automation;

import com.fetchrewards.automation.frontend.BaseClass;
import com.fetchrewards.automation.frontend.HomePage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class contains the solution for challenge 3.
 * It use binary search to find the fake gold bar on the website: http://ec2-54-208-152-154.compute-1.amazonaws.com/
 *
 * @author Ning Yuan.
 */
public class FetchRewardsChallenge3 extends BaseClass {
     List<Integer> inputData = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));
    HomePage homePage;

    @BeforeClass
    public void setUp() {
        instantiateDriver();
        homePage = new HomePage();
    }

    @Test
    public void challenge3Test(){
        try {
            List<Integer> res = findFakeBar(inputData);
            if(res.size() == 1){
                homePage.clickOnGoldBar(res.get(0));
                Thread.sleep(4000);
                homePage.checkAlertMessage();
            }else{
                System.out.println("Something went wrong! ");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    /**This method is for find the fake gold bar among 0 ~ 8
     * @Param goldBars The list of gold bar numbers.
     * @return The list contains only fake gold number, size is and only is 1.
     * */
    public List<Integer> findFakeBar(List<Integer> goldBars) throws Exception{
        List<Integer> res = new ArrayList<>();
        String currSign = "";
        List<Integer> leftBowls = goldBars.subList(0, goldBars.size() / 2);
        List<Integer> rightBowls = goldBars.subList(goldBars.size() / 2 + 1, goldBars.size());
        currSign = scalingBars(leftBowls, rightBowls);
        if(currSign.equals("=")){
            res = goldBars.subList(goldBars.size() / 2, goldBars.size() / 2 + 1);
            return res;
        }else if(currSign.equals("<")){
            res = leftBowls;
        }else if(currSign.equals(">")){
            res = rightBowls;
        }
        while(res.size() > 1){
            leftBowls = res.subList(0, res.size() / 2);
            rightBowls = res.subList(res.size() / 2, res.size());
            currSign = scalingBars(leftBowls, rightBowls);
            if(currSign.equals("<")){
                res = leftBowls;
            }else if(currSign.equals(">")){
                res = rightBowls;
            }else{
                return new ArrayList<>();
            }
        }
        return res;
    }

    /**This method performs single round of scaling.
     * @Param The list of gold bar numbers which are on the left bowl
     * @Param The list of gold bar numbers which are on the right bowl
     * @return A string that contains the measure result sign, "<", ">", "="
     * */
    public String scalingBars(List<Integer> leftBowls, List<Integer> rightBowls) throws Exception{
        String res = "";
        if(leftBowls == null || rightBowls == null || leftBowls.size() == 0 || rightBowls.size() == 0){
            return null;
        }
        //1. put bars to scale
        //2. click weigh button
        //3. get measure result sign
        //4. click reset button
        //5. based on 3 to return
        homePage.fillLeftBowls(leftBowls);
        homePage.fillRightBowls(rightBowls);
        homePage.clickWeighButton();
        Thread.sleep(3000);
        res = homePage.getMeasureResult();
        homePage.clickResetButton();
        Thread.sleep(1000);
        return res;
    }

    @AfterClass
    public void tearDown(){
        driver.close();
    }
}
