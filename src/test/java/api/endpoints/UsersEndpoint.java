package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UsersEndpoint {


    public static Response registerUser(User payload){

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.REGISTER_URL);
        return response;

    }


    public static Response loginUser(User payload){

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.LOGIN_URL);
        return response;
    }

    public static Response createUser(User payload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.POST_URL);
        return response;

    }


    public static Response getAllUser(){
       Response response = given()
               .contentType(ContentType.JSON).accept(ContentType.JSON)
               .when()
               .get(Routes.GET_URL);
        return response;
    }

    public static Response getUserByID(String id){
        return given()
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .get(Routes.GET_BY_ID_URL);
    }


    public static Response updateUser(String id, User payload){
        Response response = given()
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .pathParam("id", id)
                .body(payload)
                .when()
                .put(Routes.PUT_URL);
        return response;
    }


    public static Response deleteUser(String id){
        Response response = given()
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .delete(Routes.DELETE_URL);
        return response;
    }


}
