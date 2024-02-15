package com.web.demoblaze;

import org.testng.annotations.Test;

public class TestProductList extends BaseTest{

    @Test
    public void testNonEmptyListOfProducts(){
        homePage.searchMonitors();
    }
}
