package com.fetchrewards.automation;

import com.fetchrewards.automation.frontend.GoldBar;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class contains the solution for challenge 1.
 * It demonstrates the core algorithm that this project uses to find the fake gold bar: binary search.
 * Time Complexity: O(logN), N is the total number of gold bar.
 *
 * @author Ning Yuan.
 */
public class FetchRewardsAlgorithmChallenge1 {

    // input data that contains a fake gold between 0 ~ 3
    List<GoldBar> fakeAtLeft = new ArrayList<>(Arrays.asList(
            new GoldBar(0, 2),
            new GoldBar(1, 2),
            new GoldBar(2, 2),
            new GoldBar(3, 1),
            new GoldBar(4, 2),
            new GoldBar(5, 2),
            new GoldBar(6, 2),
            new GoldBar(7, 2),
            new GoldBar(8, 2)
    ));

    // input data that contains a fake gold between 5 ~ 8
    List<GoldBar> fakeAtRight = new ArrayList<>(Arrays.asList(
            new GoldBar(0, 2),
            new GoldBar(1, 2),
            new GoldBar(2, 2),
            new GoldBar(3, 2),
            new GoldBar(4, 2),
            new GoldBar(5, 1),
            new GoldBar(6, 2),
            new GoldBar(7, 2),
            new GoldBar(8, 2)
    ));

    // input data that contains a fake gold at 4
    List<GoldBar> fakeAtMid = new ArrayList<>(Arrays.asList(
            new GoldBar(0, 2),
            new GoldBar(1, 2),
            new GoldBar(2, 2),
            new GoldBar(3, 2),
            new GoldBar(4, 1),
            new GoldBar(5, 2),
            new GoldBar(6, 2),
            new GoldBar(7, 2),
            new GoldBar(8, 2)
    ));

    @Test
    public void algorithmTest(){
        FetchRewardsAlgorithmChallenge1 fetchRewardsAlgorithmChallenge1 = new FetchRewardsAlgorithmChallenge1();
        List<GoldBar> res = fetchRewardsAlgorithmChallenge1.findFakeBar(fetchRewardsAlgorithmChallenge1.fakeAtMid);
        List<GoldBar> res2 = fetchRewardsAlgorithmChallenge1.findFakeBar(fetchRewardsAlgorithmChallenge1.fakeAtLeft);
        List<GoldBar> res3 = fetchRewardsAlgorithmChallenge1.findFakeBar(fetchRewardsAlgorithmChallenge1.fakeAtRight);
        if(res.size() == 1){
            System.out.println("The expected fake gold Num is 4!");
            System.out.println("The actual fake gold Num is: " + res.get(0).num);
        }else{
            System.out.println("Something went wrong! ");
        }
        if(res2.size() == 1){
            System.out.println("The expected fake gold Num is 3!");
            System.out.println("The actual fake gold Num is: " + res2.get(0).num);
        }else{
            System.out.println("Something went wrong! ");
        }
        if(res3.size() == 1){
            System.out.println("The expected fake gold Num is 5!");
            System.out.println("The actual fake gold Num is: " + res3.get(0).num);
        }else{
            System.out.println("Something went wrong! ");
        }
    }

    /** This method is to find the fake gold bar
     * @param goldBars All input gold bars that needs to be scaled.
     * @return A list of fake gold bar. Here the list will only have one fake gold.
     * @author Ning Yuan.
     */
    public List<GoldBar> findFakeBar(List<GoldBar> goldBars){
        List<GoldBar> res = new ArrayList<>();
        List<GoldBar> leftBowls = goldBars.subList(0, goldBars.size() / 2);
        List<GoldBar> rightBowls = goldBars.subList(goldBars.size() / 2 + 1, goldBars.size());
        if(scalingBars(leftBowls, rightBowls).size() == 0){
            res = goldBars.subList(goldBars.size() / 2, goldBars.size() / 2 + 1);
            return res;
        }else{
            res = leftBowls;
            while(res.size() > 1){
                if(scalingBars(leftBowls, rightBowls) == leftBowls){
                    res = leftBowls;
                }else if(scalingBars(leftBowls, rightBowls) == rightBowls){
                    res = rightBowls;
                }else{
                    return new ArrayList<>();
                }
                leftBowls = res.subList(0, res.size() / 2);
                rightBowls = res.subList(res.size() / 2, res.size());
            }
        }
        return res;
    }

    /**This method simulates the process of how the scale get the measure
     * @Param lefBowls Gold bars which are in the left bowl.
     * @Param rightBowls Gold bars which are in the right bowl.
     *
     * @return The list of gold bars which weigh less.
     * @author Ning Yuan
     * */
    public List<GoldBar> scalingBars(List<GoldBar> leftBowls, List<GoldBar> rightBowls){
        List<GoldBar> res = new ArrayList<>();
        if(leftBowls == null || rightBowls == null || leftBowls.size() == 0 || rightBowls.size() == 0){
            return null;
        }

        int leftTotalWeight = 0;
        int rightTotalWeight = 0;

        for(GoldBar bar : leftBowls){
            leftTotalWeight += bar.weight;
        }

        for(GoldBar bar : rightBowls){
            rightTotalWeight += bar.weight;
        }

        if(leftTotalWeight < rightTotalWeight){
            return leftBowls;
        }else if(leftTotalWeight > rightTotalWeight){
            return rightBowls;
        }else{
            return res;
        }
    }
}
