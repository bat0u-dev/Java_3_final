package ru.geekbarins.lesson7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TestRunner {
    static ArrayList<Method> beforeSuiteList;
    static ArrayList<Method> testsList;
    static ArrayList<Method> afterSuiteList;
    public static HashMap<Integer, ArrayList<Method>> testPriorityMap;

    static void start(Class clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        beforeSuiteList = new ArrayList<>();
        testsList = new ArrayList<>();
        afterSuiteList = new ArrayList<>();
        testPriorityMap = new HashMap<>();


        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                beforeSuiteList.add(method);
            } else if (method.isAnnotationPresent(MyTest.class)) {
                List<Method> priorityList = testPriorityMap.get(method.getAnnotation(MyTest.class).priority());
                if(priorityList==null){
                    testPriorityMap.put(method.getAnnotation(MyTest.class).priority(), new ArrayList<Method>());
                    testPriorityMap.get(method.getAnnotation(MyTest.class).priority()).add(method);
                } else {
                    priorityList.add(method);
                }
//                try {
//                    method.invoke(clazz.newInstance());//переделать через constructor!
//                } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    try {
//                        method.invoke(clazz.getConstructor().newInstance());
//                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                } catch (NoSuchMethodException e) {
//                    e.printStackTrace();
//
            } else if (method.isAnnotationPresent(AfterSuite.class)) {
                afterSuiteList.add(method);
            }
        }

        if (beforeSuiteList.size() >= 2) {
            throw new RuntimeException("Аннотация @BeforeSuite может быть использована " +
                    "единожды в рамках класса");
        }
        if (afterSuiteList.size() >= 2) {
            throw new RuntimeException("Аннотация @AfterSuite может быть использована " +
                    "единожды в рамках класса");
        }

        for (Method item : beforeSuiteList) {
            try {
                item.invoke(clazz.getConstructor().newInstance());
            } catch (IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        for (int i = 1; i < testPriorityMap.size() + 1; i++) {
            List<Method> list = testPriorityMap.get(i);
                for (Method method: list) {
                    method.invoke(clazz.getConstructor().newInstance());
            }
        }

        for (Method method : afterSuiteList) {
            try {
                method.invoke(clazz.getConstructor().newInstance());
            } catch (IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

//        for (Method method : clazz.getDeclaredMethods()) {
//            if (method.isAnnotationPresent(MyTest.class)) {
//                if (method.getParameterTypes().length != 0) {
//                    for (Parameter param :
//                            method.getParameters()) {
//                        System.out.println(param.getName());
//
//                    }
//                }
//            }
//        }
    }

    static void start(String classname) {
        try {
            Class clazz = Class.forName("ru.geekbarins.lesson7." + classname);
            start(clazz);
        } catch (ClassNotFoundException e) {
            System.out.println("Класса с указанным именем не существует");
            e.printStackTrace();
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}