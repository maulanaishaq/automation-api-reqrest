package api.endpoints;

public class Routes {

    public static String BASE_URL = "https://reqres.in";

    //CRUD USER
    public static String POST_URL = BASE_URL+"/api/users";
    public static String GET_URL = BASE_URL+"/api/users";
    public static String PUT_URL = BASE_URL+"/api/users/{id}";
    public static String DELETE_URL = BASE_URL+ "/api/users/{id}";

    // REGISTER USER
    public static String REGISTER_URL = BASE_URL+"/api/register";


    //LOGIN USER
    public static String LOGIN_URL = BASE_URL+"/api/login";


}
