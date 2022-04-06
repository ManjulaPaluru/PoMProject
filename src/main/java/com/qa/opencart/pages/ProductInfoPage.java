package com.qa.opencart.pages;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProductInfoPage {
    private WebDriver driver;
    private ElementUtil eleUtil;
    private  Map<String, String> productInfoMap;

    private By productHeader = By.cssSelector("div#content h1");
    private By productImages = By.cssSelector("div#content img");
    private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
    private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
    private By productQuantity = By.id("input-quantity");
    private By addToCart = By.id("button-cart");
    private By sucessMessg = By.cssSelector("div.alert.alert-success.alert-dismissible");

    public ProductInfoPage(WebDriver driver) {
        this.driver = driver;
        eleUtil= new ElementUtil(driver);
    }

    public String getProductHeaderText() {
        return eleUtil.doElementGetText(productHeader);
    }

    public int getProductImagesCount() {
        return eleUtil.waitForElementsToBeVisible(productImages, Constants.DEFAULT_TIME_OUT).size();
    }

    public Map<String,String> getProductInfo() {
        productInfoMap = new LinkedHashMap<String,String>();
        productInfoMap.put("productName",getProductHeaderText());
        productInfoMap.put("productImagesCount",String.valueOf(getProductImagesCount()));
        getProductMetaData();
        //getProductPriceData();
        return productInfoMap;
    }

    public void getProductMetaData() {
        //here we are using Hash Map  concept
        List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
   /* Brand: Apple
    Product Code: Product 18
    Reward Points: 800
    Availability: Out Of Sto
    */
        for (WebElement e : metaDataList) {
            String text = e.getText().trim();
            String meta[] = text.split(":");
            String metaKey = meta[0].trim();
            String metaValue = meta[1].trim();
            productInfoMap.put(metaKey, metaValue);

        }

    }

    private void getProductPriceData(){
        List<WebElement> metaPriceList = eleUtil.getElements(productPriceData);
       String price= metaPriceList.get(0).getText().trim();
       String exPrice= metaPriceList.get(1).getText().trim();
       /*
       $2,000.00
Ex Tax: $2,000.00
       */
        productInfoMap.put("price",price);
        productInfoMap.put("exPrice",exPrice);
    }

}
