package com.yevheniimakar.beltcutting;

public class Routes {

    private Routes() {
        throw new AssertionError("non-instantiable class");
    }


    public static final String API_ROOT = "/api/v1";

    public static final String TASKS = API_ROOT + "/tasks";

    public static final String COMPLECTATION = API_ROOT + "/complectation";

    public static String task(long id){
        return  TASKS+ '/' + id;
    }


}
