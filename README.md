# FetchRewardsProject

This project is for Fetch Rewards Coding Exercise on https://fetch-hiring.s3.amazonaws.com/SDET/FetchRewards_Coding_Exercise_SDET.pdf
@Author Ning Yuan - nyuan102500@gmail.com

## Installation

Before run this project, please make to go through the following installation process.
1. Please make sure [chrome](https://www.google.com/chrome/) is installed.
2. Please make sure either maven or Intellij is installed.


To Install Maven:
  a. If you have [Homebrew](https://brew.sh/), Please open terminal and enter
      ```bash
      brew install maven
      ```
  b. Or you can install maven following this [Install Maven](https://maven.apache.org/install.html)
  
  
To Install Intellij: [Download Intellij](https://www.jetbrains.com/idea/download/#section=mac)


## Usage
First clone this repo to you local.

1. To trigger any test by maven, please open terminal and locate to the root path of this project. And enter:    
      ```bash
      mvn clean test -Dtest=$testName
      ```
      
     
      For example, to run challenge 1 solution:
      ```bash
      mvn clean test -Dtest=FetchRewardsAlgorithmChallenge1
      ```
      
      Same, challenge 2 solution:
      ```bash
      mvn clean test -Dtest=FetchRewardsWebActionChallenge2
      ```
      
      Challenge 3 solution:
      ```bash
      mvn clean test -Dtest=FetchRewardsChallenge3
      ```
      
 2. To run any test in Intellij, take challenge 1(FetchRewardsAlgorithmChallenge1) as example
    a. Open this Project in Intellij.
    b. Click [Run] - > [Edit Configuration] -> [+] on the top right -> Select [TestNG] - > In Class section, choose the test name you want to run, 
    here is FetchRewardsAlgorithmChallenge1 -> Give a name in Name section -> click [Apply] then [OK]
    
    c.Click [Run] -> [Run].
      