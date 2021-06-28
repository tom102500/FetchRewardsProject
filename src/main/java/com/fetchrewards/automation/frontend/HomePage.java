package com.fetchrewards.automation.frontend;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**This class represents the page object class which model the home page of the exercise
 * @author Ning Yuan
 **/
public class HomePage extends BaseClass{

    @FindBy(xpath = "//button[text()='Reset']")
    WebElement resetButton;

    @FindBy(xpath = "//button[text()='Weigh']")
    WebElement weighButton;

    @FindBy(xpath = "//div[@class='result']//button[@id='reset']")
    WebElement resultField;

    @FindBy(id = "left_0")
    WebElement leftBowlInput0;
    @FindBy(id = "left_1")
    WebElement leftBowlInput1;
    @FindBy(id = "left_2")
    WebElement leftBowlInput2;
    @FindBy(id = "left_3")
    WebElement leftBowlInput3;
    @FindBy(id = "left_4")
    WebElement leftBowlInput4;
    @FindBy(id = "left_5")
    WebElement leftBowlInput5;
    @FindBy(id = "left_6")
    WebElement leftBowlInput6;
    @FindBy(id = "left_7")
    WebElement leftBowlInput7;
    @FindBy(id = "left_8")
    WebElement leftBowlInput8;

    @FindBy(id = "right_0")
    WebElement rightBowlInput0;
    @FindBy(id = "right_1")
    WebElement rightBowlInput1;
    @FindBy(id = "right_2")
    WebElement rightBowlInput2;
    @FindBy(id = "right_3")
    WebElement rightBowlInput3;
    @FindBy(id = "right_4")
    WebElement rightBowlInput4;
    @FindBy(id = "right_5")
    WebElement rightBowlInput5;
    @FindBy(id = "right_6")
    WebElement rightBowlInput6;
    @FindBy(id = "right_7")
    WebElement rightBowlInput7;
    @FindBy(id = "right_8")
    WebElement rightBowlInput8;

    @FindBy(xpath = "//div[@class='game-info']/ol")
    WebElement weighingsResult;

    @FindBy(id = "coin_0")
    WebElement goldButton0;
    @FindBy(id = "coin_1")
    WebElement goldButton1;
    @FindBy(id = "coin_2")
    WebElement goldButton2;
    @FindBy(id = "coin_3")
    WebElement goldButton3;
    @FindBy(id = "coin_4")
    WebElement goldButton4;
    @FindBy(id = "coin_5")
    WebElement goldButton5;
    @FindBy(id = "coin_6")
    WebElement goldButton6;
    @FindBy(id = "coin_7")
    WebElement goldButton7;
    @FindBy(id = "coin_8")
    WebElement goldButton8;

    public HomePage(){
        PageFactory.initElements(driver, this);

    }

    //clicks on buttons (“Reset”)
    public HomePage clickResetButton(){
        wait.until(ExpectedConditions.elementToBeClickable(resetButton));
        resetButton.click();
        return this;
    }

    //clicks on buttons (“Weigh")
    public HomePage clickWeighButton(){
        wait.until(ExpectedConditions.elementToBeClickable(weighButton));
        weighButton.click();
        return this;
    }

    //filling out the bowls grids with bar numbers (0 to 8)
    //input list should only contains integers 0 - 8, and not longer than 9
    public HomePage fillLeftBowls(List<Integer> barNum){
        if(barNum.size() > 9){
            throw new IllegalStateException("The total amount of input numbers should be less than 10!");
        }

        for(Integer b : barNum){
            if(b < 0 || b > 8){
                throw new IllegalStateException("The input gold bar number should be between 0 ~ 8! ");
            }
        }

        List<WebElement> leftBowlList = new ArrayList<>(Arrays.asList(leftBowlInput0, leftBowlInput1, leftBowlInput2, leftBowlInput3,
                leftBowlInput4, leftBowlInput5, leftBowlInput6, leftBowlInput7, leftBowlInput8));

        for(int i = 0; i < barNum.size(); i++){
            leftBowlList.get(i).sendKeys(barNum.get(i).toString());
        }
        return this;
    }

    //fill out the right bowls section
    public HomePage fillRightBowls(List<Integer> barNum){
        if(barNum.size() > 9){
            throw new IllegalStateException("The total amount of input numbers should be less than 10!");
        }

        for(Integer b : barNum){
            if(b < 0 || b > 8){
                throw new IllegalStateException("The input gold bar number should be between 0 ~ 8! ");
            }
        }

        List<WebElement> rightBowlList = new ArrayList<>(Arrays.asList(rightBowlInput0, rightBowlInput1, rightBowlInput2, rightBowlInput3,
                rightBowlInput4, rightBowlInput5, rightBowlInput6, rightBowlInput7, rightBowlInput8));

        for(int i = 0; i < barNum.size(); i++){
            rightBowlList.get(i).sendKeys(barNum.get(i).toString());
        }
        return this;
    }

    //Clicking on gold bar number at the bottom of the website and checking alert message
    public HomePage clickOnGoldBar(int barNum){
        if(barNum < 0 || barNum > 8){
            throw new IllegalStateException("The input gold bar number should be between 0 ~ 8! ");
        }

        List<WebElement> goldButtonList = new ArrayList<>(Arrays.asList(goldButton0, goldButton1, goldButton2,
                goldButton3, goldButton4, goldButton5, goldButton6, goldButton7, goldButton8));
        goldButtonList.get(barNum).click();
        return this;
    }

    //click out the alert message
    public HomePage checkAlertMessage(){
        driver.switchTo().alert().accept();
        return this;
    }

    //get the measure result sign, <, >, =
    public String getMeasureResult(){
        return resultField.getText();
    }

    //get the weighing list
    public List<String> getWeighingList(){
        List<WebElement> weighingLiList = weighingsResult.findElements(By.tagName("li"));
        List<String> resList = new ArrayList<>();
        for(WebElement li : weighingLiList){
            resList.add(li.getText());
        }
        return resList;
    }


}
