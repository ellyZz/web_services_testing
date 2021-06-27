package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.User;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class WebServicesTest {

    @BeforeMethod
    public void initTest() {
        RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
    }

    @Test
    public void checkStatusCode() {
        SoftAssertions softAssertions = new SoftAssertions();
        Response rp = given().get("/users").andReturn();
        int actualStatusCode = rp.getStatusCode();
        System.out.println(actualStatusCode);
        softAssertions.assertThat(actualStatusCode).isEqualTo(200);
        softAssertions.assertAll();
    }

    @Test
    public void checkResponseHeader() {
        SoftAssertions softAssertions = new SoftAssertions();
        Response rp = given().get("/users").andReturn();
        String valueOfContentTypeHeader = rp.getHeader("content-type");
        softAssertions.assertThat(valueOfContentTypeHeader).containsIgnoringCase("application/json; charset=utf-8");
        softAssertions.assertAll();
    }

    @Test
    public void checkResponseBody() {
        SoftAssertions softAssertions = new SoftAssertions();
        Response rp = given().get("/users").andReturn();
        User[] users = rp.as(User[].class);
        softAssertions.assertThat(users.length).isEqualTo(10);
        softAssertions.assertAll();
    }

}
