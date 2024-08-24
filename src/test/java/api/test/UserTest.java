package api.test;

import api.endpoints.Routes;
import api.endpoints.UsersEndpoint;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class UserTest {

    Faker faker = new Faker();

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = Routes.BASE_URL;
    }



    @Test
    public void registerUserTest(){
        User user = new User();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("cityslicka");

        Response response = UsersEndpoint.registerUser(user);
        Assert.assertEquals(response.statusCode(), 200);
        response.then().body("token", equalTo("QpwL5tke4Pnpja7X4"));
    }


    @Test
    public void loginUserTest(){
        User user = new User();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("cityslicka");

        Response response = UsersEndpoint.loginUser(user);
        Assert.assertEquals(response.getStatusCode(), 200);
        response.then().body("token", equalTo("QpwL5tke4Pnpja7X4"));

    }

    @Test
    public void createUserTest(){
        User user = new User();
        user.setName(faker.name().fullName());
        user.setJob(faker.job().title());

        Response response = UsersEndpoint.createUser(user);
        Assert.assertEquals(response.getStatusCode(), 201);
        System.out.println("User Created Successfully");
        System.out.println(response.prettyPrint());

    }


    @Test
    public void getUserTest(){
        Response response = UsersEndpoint.getAllUser();
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println(response.prettyPrint());
    }


    @Test
    public void updateUserTest(){
        User user = new User();
        user.setName(faker.name().fullName());
        user.setJob(faker.job().title());

        Response response = UsersEndpoint.updateUser("2", user);
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println(response.prettyPrint());
    }


    @Test
    public void deleteUserTest(){
        Response response = UsersEndpoint.deleteUser("2");
            Assert.assertEquals(response.statusCode(), 204);
        }



}
