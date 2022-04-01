package pageObjects;

import Reports.Reporting;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import webPageObjects.Apply;
import webPageObjects.InternOpenings;
import webPageObjects.country;
import webPageObjects.landingPage;
import webUtilities.webActions;

public class webFunctions extends webActions {
    Reporting reportz = new Reporting();
    String fileName;

    public void landingPage(WebDriver driver, ExtentTest test) {
        landingPage landing = new landingPage(driver);
        country country1 = new country(driver);

        try {
            fileName = reportz.CaptureScreenShot(driver);
            clickObject(landing.CareerLink, driver);
            if (country1.CountryLink.isDisplayed()) {
                test.pass("SUCCESSFULLY CLICKED CAREER LINK", MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());
            } else {
                test.fail("UNABLE TO CLICK THE CAREER LINK", MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());
            }


        } catch (Exception e) {
        }
    }

    public void country(WebDriver driver,ExtentTest test) {
        country country1 = new country(driver);
        InternOpenings opening = new InternOpenings(driver);

        try {
            fileName = reportz.CaptureScreenShot(driver);
            clickObject(country1.CountryLink, driver);
            if (opening.InternLink.isDisplayed()) {
                test.pass("SUCCESSFULLY CLICKED OPENINGS LINK", MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());
            } else {
                test.fail("UNABLE TO CLICK THE OPENINGS LINK", MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());
            }


        } catch (Exception e) {
        }
    }

    public void InternOpenings(WebDriver driver, ExtentTest test) {
        InternOpenings opening = new InternOpenings(driver);
        Apply apply1 = new Apply(driver);

        try {
            fileName = reportz.CaptureScreenShot(driver);
            clickObject(opening.InternLink, driver);
            if (apply1.Apply.isDisplayed()) {
                test.pass("SUCCESSFULLY CLICKED INTERN LINK",MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());
            } else {
                test.fail("UNABLE TO CLICK THE INTERN LINK", MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());
            }


        } catch (Exception e) {
        }
    }

    public void Apply(WebDriver driver, ExtentTest test) {
        Apply apply1 = new Apply(driver);


        try {
            fileName = reportz.CaptureScreenShot(driver);
            clickObject(apply1.Apply, driver);
            passData(apply1.firstname, driver, "firstname");
            passData(apply1.email, driver, "email");
            passData(apply1.phone,driver,"phone");
            clickObject(apply1.Submit,driver);

            if (apply1.ErrorMessage.isDisplayed()) {
                test.pass("UNSUCCESSFUL",MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());
            } else {
               test.fail("APPLICATION SUBMISSION SUCCESSFUL", MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());
            }
        } catch (Exception e) {
        }
    }
}










