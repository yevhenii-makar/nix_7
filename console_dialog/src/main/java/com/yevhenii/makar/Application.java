package com.yevhenii.makar;

public class Application {
    public static void run(Class<?> clazz){

        ConsoleDialog consoleDialog = new ConsoleDialog(clazz.getPackage().getName());
    }
}
