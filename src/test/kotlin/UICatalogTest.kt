import org.testng.Assert.assertEquals
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

class UICatalogTest: TestBase() {

    @BeforeClass
    fun beforeAll() {
        setupApp(TestApp.UI_CATALOG)
    }

    @Test
    fun click() {
        driver.findElementByAccessibilityId("Alert Views").click()
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Text Entry']").click()
        driver.findElementByXPath("//XCUIElementTypeCell").sendKeys("Hello")
        driver.findElementByAccessibilityId("OK").click()
        driver.findElementByAccessibilityId("Confirm / Cancel").click()
        val message = driver.findElementByXPath("//*[contains(@name,'message')]").text

        assertEquals(message, "A message should be a short, complete sentence.")

        driver.findElementByAccessibilityId("Confirm").click()
    }

    @Test
    fun scroll() {
        val scrollArgs = hashMapOf<String, String>(
            "direction" to "down",
            "name" to "Web View"
        )
        driver.executeScript("mobile:scroll", scrollArgs)
        driver.findElementByAccessibilityId("Web View").click()
    }

    @Test
    fun picker() {
        driver.findElementByAccessibilityId("Picker View").click()
        driver.findElementByAccessibilityId("Red color component value").sendKeys("80")
        driver.findElementByAccessibilityId("Green color component value").sendKeys("220")
        driver.findElementByAccessibilityId("Blue color component value").sendKeys("220")
    }

    @Test
    fun sliders() {
        driver.findElementByAccessibilityId("Sliders").click()
        driver.findElementByXPath("//XCUIElementTypeSlider").setValue("1")
    }
}