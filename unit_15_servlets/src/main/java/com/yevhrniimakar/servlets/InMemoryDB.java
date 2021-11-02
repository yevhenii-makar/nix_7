package com.yevhrniimakar.servlets;

import java.util.concurrent.ConcurrentHashMap;

public class InMemoryDB {

    private static ConcurrentHashMap<String,String> uniqueIP = new ConcurrentHashMap<>();

    public ConcurrentHashMap<String,String> getMap(){
        return uniqueIP;
    }
}
