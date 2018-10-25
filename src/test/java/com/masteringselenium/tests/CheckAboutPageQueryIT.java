//package com.masteringselenium.tests;
//
//import org.assertj.core.api.Assertions;
//import org.testng.annotations.Ignore;
//import org.testng.annotations.Test;
//
//import com.masteringselenium.DriverBase;
//import com.masteringselenium.page_objects_query.AboutPage;
//import com.masteringselenium.page_objects_query.IndexPage;
//import com.masteringselenium.page_objects_query.PageFooter;
//
//public class CheckAboutPageQueryIT {
//
//    @Test
//    @Ignore
//    public void checkThatAboutPageHasText() {
//        DriverBase.getDriver().get("http://web.masteringselenium.com/index.html");
//
//        IndexPage indexPage = new IndexPage();
//        Assertions.assertThat(indexPage.mainTextIsDisplayed()).isEqualTo(true);
//        Assertions.assertThat(indexPage.mainPageButtonIsDisplayed()).isEqualTo(true);
//
//        PageFooter footer = new PageFooter();
//        footer.goToTheAboutUsPage();
//        
//        AboutPage aboutPage = new AboutPage();
//        Assertions.assertThat(aboutPage.aboutUsTextIsDisplayed()).isEqualTo(true);
//    }
//    
//    @Test
//    @Ignore
//    public void checkThatAboutPageHasText1() {
//        DriverBase.getDriver().get("http://web.masteringselenium.com/index.html");
//        
//        IndexPage indexPage = new IndexPage();
//        
//        Assertions.assertThat(indexPage.mainPageButtonIsDisplayed()).isEqualTo(true);
//        Assertions.assertThat(indexPage.mainTextIsDisplayed()).isEqualTo(true);
//        
//        indexPage.footer.goToTheAboutUsPage();
//        
//        AboutPage aboutPage = new AboutPage();
//        
//        Assertions.assertThat(aboutPage.aboutUsTextIsDisplayed()).isEqualTo(true);
//    }
//    
//    @Test
//    @Ignore
//    public void checkThatAboutPageHasText2() {
//        DriverBase.getDriver().get("http://web.masteringselenium.com/index.html");
//
//        IndexPage indexPage = new IndexPage();
//
//        Assertions.assertThat(indexPage.mainPageButtonIsDisplayed()).isEqualTo(true);
//        Assertions.assertThat(indexPage.mainTextIsDisplayed()).isEqualTo(true);
//        
//        AboutPage aboutPage = indexPage.footer.goToTheAboutUsPageSecond();
//        
//        Assertions.assertThat(aboutPage.aboutUsTextIsDisplayed()).isEqualTo(true);
//    }
//
//}
