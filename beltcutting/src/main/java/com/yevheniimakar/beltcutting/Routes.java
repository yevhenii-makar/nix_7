package com.yevheniimakar.beltcutting;

public class Routes {

    private Routes() {
        throw new AssertionError("non-instantiable class");
    }






    public static final String API_ROOT = "/api/v1";

    public static final String ADMIN = API_ROOT + "/admin";

    public static final String TASKS = API_ROOT + "/tasks";

    public static final String COMPLECTATIONS = API_ROOT + "/complectation";

    public static final String USERS = API_ROOT + "/users";

    public static final String PIECES = API_ROOT + "/pieces" ;

    public static final String MANUFACTURERS = API_ROOT+"/manufacturers";

    public static final String CARDS = API_ROOT+"/cards" ;

    public static final String UNITS = API_ROOT+"/units" ;

    public static final String TOKEN = API_ROOT + "/token";

    public static String task(long id){
        return  TASKS+ '/' + id;
    }


}
