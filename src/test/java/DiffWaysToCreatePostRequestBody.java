import api.payload.User;
import io.restassured.RestAssured;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class DiffWaysToCreatePostRequestBody {

    final String BASE_URI = "https://reqres.in";


    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URI;

    }


    @Test
    public void createUser() {

        String name =
                given()
                        .header("Content-Type", "application/json")
                        .body("{\n" +
                                "    \"name\": \"morpheus\",\n" +
                                "    \"job\": \"leader\"\n" +
                                "}")
                        .when()
                        .post("/api/users")

                        .then()
                        .assertThat()
                        .statusCode(201)  // Expecting a 200 status code for successful login
                        .body("id", notNullValue())  // Assuming the response contains a token
                        .body("name", equalTo("morpheus"))
                        .log().all()

                        .extract()
                        .path("name");  // Extract the token from the response

        System.out.println("Name: " + name);
    }

    @Test
    public void createUser2(){
        //using hash map & need to add maven gson for convert to json
        HashMap<String, String> data = new HashMap<>();
        data.put("name", "mauls");
        data.put("job", "qa engineer");

      String id =given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("/api/users")

                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo("mauls"))
                .body("job", equalTo("qa engineer"))
                .header("Content-Type", "application/json; charset=utf-8")
              //  .log().all()
              .extract().path("id");
        System.out.println(id);

    }

    @Test
    // using json object
    public void createUser3(){
        JSONObject data = new JSONObject();
        data.put("name", "ishaq");
        data.put("job", "backend");

           given().header("Content-Type", "application/json")
                .body(data.toString())
                .post("/api/users")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo("ishaq"))
                .body("job", equalTo("backend"));
    }

    @Test
    public void createUser4(){
        User users = new User();
        users.setName("Joko");
        users.setJob("Front End");

        given().header("Content-Type", "application/json")
                .body(users)
                .post("/api/users")
                .then().statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo("Joko"))
                .body("job", equalTo("Front End"));
    }


        @Test
        void createUser5() throws FileNotFoundException {
            File file = new File("test-data/user.json");
            FileReader reader = new FileReader(file);
            JSONTokener jsonTokener = new JSONTokener(reader);
            JSONObject data = new JSONObject(jsonTokener);

            given()
                    .contentType("application/json")
                    .body(data.toString())

                    .when()
                    .post("/api/users")

                    .then().statusCode(201)
                   .body("name", equalTo("morpheus"))
                   .body("job",equalTo("leader"))
                    .log().all();
    }



    @Test
    public void updateUser(){
        String name =
                given()
                        .header("Content-Type", "application/json")
                        .body("{\n" +
                                "    \"name\": \"morpheus\",\n" +
                                "    \"job\": \"zion resident\"\n" +
                                "}")
                        .when()
                        .put("/api/users/2")

                        .then()
                        .assertThat()
                        .statusCode(200)
                        .body("name", equalTo("morpheus"))
                        .log().all()

                        .extract()
                        .path("name");  // Extract the token from the response

        System.out.println("Name: " + name);
    }


    @Test // GET USERS BY ID
    public void getUserById() {
        given()
                .when()
                .get("/api/users/2")
                //expected
                .then().statusCode(200)
                .body("data.first_name", equalTo("Janet"))
                .log().all();  // Ensure that the semicolon is present here
    }




    @Test // GET ALL USERS
    public void findAllUsers() {
        Response response = given()
                .when()
                .get("/api/users")
                //expected
                .then().statusCode(200)
                .body("total", equalTo(12)) // Verify total users count
                .extract().response();

        // Extract the user data as a list
        List<Map<String, Object>> users = response.jsonPath().getList("data");

        // Verify each user's ID
        for (int i = 0; i < users.size(); i++) {
            int expectedId = i + 1; // Adjust expected ID to start from 1
            int actualId = (int) users.get(i).get("id");
            assertEquals(expectedId, actualId, "User ID does not match for user " + i);
        }
    }


    @Test
    public void deleteUserById() {
        given()
                .when().delete("/api/users/2")
                .then().statusCode(204);

    }



}




