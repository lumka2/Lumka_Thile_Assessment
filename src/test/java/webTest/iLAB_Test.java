package webTest;

import Database.Database_Connection;
import Reports.Reporting;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.webFunctions;
import webUtilities.webUtilities;

import java.sql.ResultSet;

public class iLAB_Test {
    webUtilities webUtil = new webUtilities();
    webFunctions webFun = new webFunctions();
    String sURL, sBroswer;
    Reporting reportz = new Reporting();
    ExtentReports repo;
    Database_Connection data = new Database_Connection();

    @Parameters({"ILABURL", "Browser"})
    @BeforeTest
    public void initialiseBrowser(String suRL, String browser) {
        sURL = suRL;
        sBroswer = browser;
        webUtil.setWebDriver(webUtil.initializeWebDriver(sBroswer));
        repo = reportz.initializeExtentReports("report/iLABReports.html");
    }

    @Test
    public void lab() throws InterruptedException {
        ExtentTest test = repo.createTest("iLABReports");
        test.assignAuthor("Lumka Thile");
        ExtentTest Node;
        ResultSet results;
        try {
            results = data.ConnectAndQuerySQL("jdbc:mysql://localhost:3306/ilab_info",
                    "root", "Mangci1950@", "Select * from lab ");
            int iRow = data.rowCount(results);
            webUtil.navigate(sURL);
            for (int i = 1; i <= iRow; i++) {
                if (results.next()) {
                    webUtil.navigate(sURL);
                    webFun.landingPage(webUtil.getWebDriver(), test);
                    webFun.country(webUtil.getWebDriver(), test);
                    webFun.InternOpenings(webUtil.getWebDriver(), test);
                    webFun.Apply(webUtil.getWebDriver(), test);
                }
            }
            results.close();
        } catch (Exception e) {
        }
    }

    @AfterTest
    public void closeBrowser() {
        webUtilities.getWebDriver().quit();
    }
}


