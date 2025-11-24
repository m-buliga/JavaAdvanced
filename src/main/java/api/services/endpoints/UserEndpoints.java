package api.services.endpoints;

public class UserEndpoints {

    // USERS - plural
    public static final String USERS_BASE = "/users";

    // USER - singular
    public static final String USER_BASE = "/users/";


    public static final String USER_CREATE = USER_BASE + "register";
    public static final String USER_LOGIN = USER_BASE + "login";
    public static final String USER_GET_BY_ID = USER_BASE;
    public static final String USER_DELETE_BY_ID = USER_BASE;
}
