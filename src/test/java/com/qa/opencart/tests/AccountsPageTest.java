package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class AccountsPageTest extends BaseTest {

    @BeforeClass
    public void accPageSetup() {
        System.out.println("@BeforeClass");
        accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    @Test
    public void accountsPageTitleTest() {
        String actAccountPageTitle = accpage.getAccountPageTitle();
        System.out.println("Acc page Title" + actAccountPageTitle);
        Assert.assertEquals(actAccountPageTitle, Constants.ACCOUNTS_PAGE_TITLE);
    }

    @Test
    public void accpageHeaderTest() {
        Assert.assertTrue(accpage.isAccountsPageHeaderExist());
    }
    @Test
    public void searchExistTest() {
        Assert.assertTrue(accpage.isSearchExist());
    }
    @Test
    public void accSectionsTest(){
       List<String> actSelList= accpage.getAccountsPageSectionsList();
        System.out.println("Account section list" +actSelList);
       Assert.assertEquals(actSelList,Constants.ACCOUNTS_SECTIONS_LIST);
    }
    @Test
    public void searchHeaderTest(){
       searchResultsPage= accpage.doSearch("MacBook");
       String  actSearchHearder= searchResultsPage.getResultsPageHeaderValue();
       Assert.assertTrue(actSearchHearder.contains("MacBook"));
    }
     @Test
    public void searchProductTest(){
        searchResultsPage = accpage.doSearch("iMac");
        int actProductCount = searchResultsPage.getProductSearchCount();
        Assert.assertEquals(actProductCount,Constants.IMAC_PRODUCT_COUNT);
     }
    @Test
    public void getSearchProductListTest(){
        searchResultsPage = accpage.doSearch("iMac");
      List<String> actualProductList= searchResultsPage.getProductResultsList();
        System.out.println(actualProductList);
    }
}
