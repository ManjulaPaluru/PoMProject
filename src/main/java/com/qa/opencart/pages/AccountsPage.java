package com.qa.opencart.pages;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AccountsPage {

    private WebDriver driver;
    private ElementUtil eleUtil;

//locators
    By  search =By.name("search");
    By header = By.cssSelector("div#logo a");
    By accSelList = By.cssSelector("div#content h2");
    By  searchBtn = By.cssSelector("button.btn.btn-default.btn-lg");

    public  AccountsPage(WebDriver driver){
        this.driver=driver;
        eleUtil= new ElementUtil(driver);
    }
    public String  getAccountPageTitle(){
        return eleUtil.waitForTitleIs(Constants.DEFAULT_TIME_OUT,Constants.ACCOUNTS_PAGE_TITLE);
    }
     public boolean isAccountsPageHeaderExist()
     {
         return eleUtil.doIsDisplayed(header);
           }

           public boolean isSearchExist(){
        return eleUtil.doIsDisplayed(search);
           }

           public SearchResultsPage doSearch(String productName){
        if(isSearchExist()){
            eleUtil.doSendKeys(search,productName);
            eleUtil.doClick(searchBtn);
            return new SearchResultsPage(driver);

        }
        return null;
           }


     public List<String>  getAccountsPageSectionsList(){
       List<WebElement> secList=  eleUtil.getElements(accSelList);
       List<String> accSecValList = new ArrayList<String>();
       for(WebElement e: secList){
         String text = e.getText();
           accSecValList.add(text);

       }
    return accSecValList;

     }
}
