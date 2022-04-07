package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

@Slf4j
public class AccountsPageTest extends BaseTest {

    @BeforeClass
    public void accPageSetup() {
        log.info("@BeforeClass");
        accPage = getLoginPage().doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    @Test
    public void accountsPageTitleTest() {
        String actAccountPageTitle = accPage.getAccountPageTitle();
        System.out.println("Acc page Title" + actAccountPageTitle);
        Assert.assertEquals(actAccountPageTitle, Constants.ACCOUNTS_PAGE_TITLE);
    }

    @Test
    public void accpageHeaderTest() {
        Assert.assertTrue(accPage.isAccountsPageHeaderExist());
    }

    @Test
    public void searchExistTest() {
        Assert.assertTrue(accPage.isSearchExist());
    }

    @Test
    public void accSectionsTest(){
       List<String> actSelList= accPage.getAccountsPageSectionsList();
        System.out.println("Account section list" +actSelList);
       Assert.assertEquals(actSelList,Constants.ACCOUNTS_SECTIONS_LIST);
    }
    @Test
    public void searchHeaderTest(){
       searchResultsPage= accPage.doSearch("MacBook");
       String  actSearchHearder= searchResultsPage.getResultsPageHeaderValue();
       Assert.assertTrue(actSearchHearder.contains("MacBook"));
    }
     @Test
    public void searchProductTest(){
        searchResultsPage = accPage.doSearch("iMac");
        int actProductCount = searchResultsPage.getProductSearchCount();
        Assert.assertEquals(actProductCount,Constants.IMAC_PRODUCT_COUNT);
     }
    @Test
    public void getSearchProductListTest(){
        searchResultsPage = accPage.doSearch("iMac");
      List<String> actualProductList= searchResultsPage.getProductResultsList();
        System.out.println(actualProductList);
    }
}
