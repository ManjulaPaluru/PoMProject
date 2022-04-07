package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class ProductInfoPageTest extends BaseTest {

    @BeforeClass
    public void productInfoSetup(){
       accPage =  getLoginPage().doLogin(prop.getProperty("username"),prop.getProperty("password"));
    }

    @DataProvider
    public Object[][] getProductData(){
        return  new Object[][]{
                {"MacBook","MacBook Pro"},
                {"Macbook","MacBook Air"},
                {"Apple", "Apple Cinema 30\""}    //Escaping characters(escaping the inches)
        };
    }


   @Test(dataProvider = "getProductData")
    public void productInfoHeaderTest(String productName, String mainProductName){
        searchResultsPage = accPage.doSearch(productName);
       productInfoPage =  searchResultsPage.selectProduct(mainProductName);
       Assert.assertEquals(productInfoPage.getProductHeaderText(),mainProductName);
   }
@Test
public void productImageTest(){
    searchResultsPage = accPage.doSearch("MacBook");
    productInfoPage =  searchResultsPage.selectProduct("MakBook Air");
    Assert.assertEquals(productInfoPage.getProductImagesCount(), Constants.MACBOOK_IMAGES_COUNT);
}
@Test
    public void productInfoTest(){
        searchResultsPage= accPage.doSearch("MacBook");
       productInfoPage =  searchResultsPage.selectProduct("MacBook pro");
        Map<String,String>  actualProductInfoPage = productInfoPage.getProductInfo();
        actualProductInfoPage.forEach((k,v)-> System.out.println(k +" : " +v));

       /* Assert.assertEquals(actualProductInfoPage.get("productname"),"MacBook Pro");
        Assert.assertEquals(actualProductInfoPage.get("Brand"),"Apple");
        Assert.assertEquals(actualProductInfoPage.get("Reward Points"),"800");

//soft assert
//if we have semeler kind of more than one assert conditions , we can write soft assertion is good, soft assert is a class.
 we ned to write softAssert.assertAll()is mandatory
      softAssert.assertEquals(actualProductInfoPage.get("productname"),"MacBook Pro");
        softAssert.assertEquals(actualProductInfoPage.get("productname"),"MacBook Pro");
        softAssert.assertAll();
        */

}
}
