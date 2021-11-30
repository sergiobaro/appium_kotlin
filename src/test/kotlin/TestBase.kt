import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.ios.IOSElement
import io.appium.java_client.remote.AutomationName
import io.appium.java_client.remote.IOSMobileCapabilityType
import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeClass
import org.testng.annotations.BeforeMethod
import java.net.URL
import java.nio.file.Paths
import java.util.*

open class TestBase {
    lateinit var driver: IOSDriver<IOSElement>

    fun setupApp(app: TestApp) {
        val testAppUrl = javaClass.classLoader.getResource(app.appName())
        val testAppFile = Paths.get(Objects.requireNonNull(testAppUrl).toURI()).toFile()
        val testAppPath = testAppFile.absolutePath

        val capabilities = DesiredCapabilities()
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 11 Pro")
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS")
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15.0")
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 500000);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 12000);
        capabilities.setCapability(MobileCapabilityType.APP, testAppPath)

        driver = IOSDriver(URL("http://127.0.0.1:4723/wd/hub"), capabilities)
        driver.closeApp()
    }

    @BeforeMethod
    fun testInit() {
        driver.launchApp()
    }

    @AfterMethod
    fun testCleanup() {
        driver.closeApp()
    }
}