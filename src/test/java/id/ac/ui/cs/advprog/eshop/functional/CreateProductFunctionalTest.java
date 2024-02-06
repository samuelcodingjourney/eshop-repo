package id.ac.ui.cs.advprog.eshop.functional;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import org.openqa.selenium.*;
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;
    private String baseProductListUrl;
    @BeforeEach
    void setupTest() {
        baseUrl = String.format("http://localhost:8080/product/create");
        baseProductListUrl = String.format("http://localhost:8080/product/list");
        System.out.println(baseUrl);
    }

    @Test
    void productsCreation_isCorrect(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);

        WebElement nameField = driver.findElement(By.id("nameInput"));
        nameField.sendKeys("Apple1");

        WebElement ageField = driver.findElement(By.id("quantityInput"));
        ageField.sendKeys("30");

        WebElement submitButton = driver.findElement(By.tagName("button"));
        submitButton.click();

        driver.get(baseProductListUrl);
        //assuming all product names are unique in this program
        String productName = "Apple1";
        WebElement productNameWeb = driver.findElement(By.xpath("//td[text()='" + productName + "']"));
        assertEquals("Apple1", productNameWeb.getText());

        driver.get(baseUrl);

        WebElement nameField2 = driver.findElement(By.id("nameInput"));
        nameField2.sendKeys("Banana2");

        WebElement ageField2 = driver.findElement(By.id("quantityInput"));
        ageField2.sendKeys("30");

        WebElement submitButton2 = driver.findElement(By.tagName("button"));
        submitButton2.click();

        driver.get(baseProductListUrl);
        String productName2 = "Banana2";
        WebElement productNameWeb2 = driver.findElement(By.xpath("//td[text()='" + productName2 + "']"));
        assertEquals("Banana2", productNameWeb2.getText());
    }



}
