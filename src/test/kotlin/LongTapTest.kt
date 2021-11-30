import io.appium.java_client.ios.IOSTouchAction
import io.appium.java_client.touch.LongPressOptions
import io.appium.java_client.touch.TapOptions
import io.appium.java_client.touch.offset.ElementOption.element
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import java.time.Duration

class LongTapTest: TestBase() {

    @BeforeClass
    fun beforeAll() {
        setupApp(TestApp.LONG_TAP)
    }

    @Test
    fun longTap() {
        val longTapElement = driver.findElementByName("Long tap")
        val touch = IOSTouchAction(driver)
        val longPressOptions = LongPressOptions()
            .withElement(element(longTapElement))
            .withDuration(Duration.ofSeconds(2))
        touch.longPress(longPressOptions).release().perform()

        val firstSwitch = driver.findElementByXPath("//XCUIElementTypeSwitch[1]")
        val tapOptions = TapOptions().withElement(element(firstSwitch))
        touch.tap(tapOptions).perform()
    }
}