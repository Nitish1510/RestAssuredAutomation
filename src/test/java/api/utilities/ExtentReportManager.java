package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class ExtentReportManager implements ITestListener {

    
    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;
    private static String repName;

    @Override
    public void onStart(ITestContext testContext) {
       
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            repName = "Test-Report-" + timeStamp + ".html";



            sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
            
            sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");
            sparkReporter.config().setReportName("Pet Store Users API");
            sparkReporter.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Application", "Pet Store Users API");
            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
            extent.setSystemInfo("User Name", System.getProperty("user.name"));
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("user", "Nitish");


        }
    

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = extent.createTest(result.getName());
       // extentTest.set(test);
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, result.getName() + " got successfully executed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = extent.createTest(result.getName());
       // extentTest.set(test);
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, result.getName() + " got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = extent.createTest(result.getName());
        //extentTest.set(test);
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName() + " got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext testContext) {
        extent.flush();

    }  

}
