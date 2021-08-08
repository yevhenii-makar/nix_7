package com.yevhenii.makar;

import com.yevhenii.makar.annotation.RunTask;
import com.yevhenii.makar.annotation.Task;

import javassist.bytecode.analysis.Executor;
import org.reflections.Reflections;
import org.reflections.scanners.*;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.Executors;

public class ConsoleDialog {

    private Reflections reflections;

    public ConsoleDialog(String appPackage) {
        reflections = new Reflections(ConfigurationBuilder
                .build(appPackage)
                .setScanners(new ResourcesScanner(), new TypeAnnotationsScanner(), new SubTypesScanner(false)));

    }

    public void run() {
        String startMassage = "";
        boolean isContinue = true;

        Set<Class<?>> setClass = reflections.getTypesAnnotatedWith(Task.class);


        List<Class<?>> listClass = new ArrayList<>();
        listClass.addAll(setClass);
        listClass.sort((Class<?> o1, Class<?> o2) -> o1.getAnnotation(Task.class).order()-o1.getAnnotation(Task.class).order());
        for (int i = 0; i < listClass.size(); i++) {
            startMassage = startMassage + (i + 1) + " - " + listClass.get(i).getAnnotation(Task.class).taskName() + "\n";
        }
        startMassage += (listClass.size() + 1) + " - to exit\nYour choice: ";
        while (isContinue) {
            System.out.println(startMassage);
            int choice = ConsoleReader.integerReader();
            if (choice <= 0 || choice > (listClass.size()+1)) {
                System.out.println("Wrong data, try again");
                continue;
            }

            if (choice > 0 && choice >= listClass.size()) {
                Object o = null;
                try {
                    o = ((Class)listClass.get(choice)).getDeclaredConstructor().newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                List<Method> methodList = getMethodList(listClass.get(choice-1));
                if (methodList.size() == 1){
                    try {
                        methodList.get(0).invoke(o);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    continue;

                }
                if (methodList.size() > 1){
                    try {
                        changeMethod(methodList).invoke(o);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }


            }
            if (choice == (listClass.size() + 1)) {
                System.out.println("Bay!");
                isContinue = false;
                continue;
            }

        }

    }

    private List<Method> getMethodList(Class<?> clazz){
        List<Method> methodList = new ArrayList<>();
        for (Method method : clazz.getMethods()) {
           if (method.isAnnotationPresent(RunTask.class)){
               methodList.add(method);
           }

        }
        return methodList;


    }
    private Method changeMethod(List<Method> methodsList){
        boolean isContinue = true;
        String methodMessage = "";

        for (int i = 0; i < methodsList.size(); i++) {
            methodMessage = methodMessage + (i + 1) + " - " + methodsList.get(i).getAnnotation(RunTask.class).runTaskName() + "\n";
        }

        while (isContinue){
            int change = ConsoleReader.integerReader();
            if (change <= 0 || change > methodsList.size()){
                System.out.println("Wrong data, try again");
                continue;
            }
            if (change>0 && change <= methodsList.size()){
                return methodsList.get(change-1);

            }

        }
        return null;

    }


}
