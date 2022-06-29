package Steps;

import Endpoints.Endpoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class UserTestSteps {

    public static createUser(Map<String, Object> data) {
        RequestSpecification httpRequest = RestAssured.given();
        ResponseBody<Response> response = httpRequest.contentType(ContentType.JSON)
                .body(data)
                .when()
                .post(Endpoints.user);

        return response.as(UserOutput.class);
    }

    public static generateToken(Map<String, Object> data) {
        RequestSpecification httpRequest = RestAssured.given();
        ResponseBody<Response> response = httpRequest.contentType(ContentType.JSON)
                .body(data)
                .when()
                .post(Endpoints.generateToken);
    }

    public static checkAuthorized(Map<String, Object> data) {
        RequestSpecification httpRequest = RestAssured.given();

        httpRequest.contentType(ContentType.JSON)
                .body(data)
                .when()
                .post(Endpoints.authorized)
                .then()
                .extract()
                .response()
                .as(Boolean.class);
    }
}
