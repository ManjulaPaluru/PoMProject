package com.qa.opencart.utils;

import edu.emory.mathcs.backport.java.util.Arrays;

import java.util.List;

public class Constants {

    public static final String LOGIN_PAGE_TITLE = "Account Login";
    public static final String LOGIN_PAGE_FRACTION_URL = "route=account/login";
    public static final String ACCOUNTS_PAGE_TITLE = "My Account";
    public static final List<String> ACCOUNTS_SECTIONS_LIST =
            List.of("My Account", "My Orders", "My Affiliate Account", "Newsletter");
    public static final int DEFAULT_TIME_OUT = 5;


    public static final int MACBOOK_PRODUCT_COUNT = 3;
    public static final int IMAC_PRODUCT_COUNT = 1;
    public static final int MACBOOK_IMAGES_COUNT = 3;
    public static final String REGISTER_SUCCESS_MESSAGE = "Your Account Has Been Created";
}

