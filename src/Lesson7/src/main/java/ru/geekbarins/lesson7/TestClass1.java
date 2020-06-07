package ru.geekbarins.lesson7;

public class TestClass1 {

    @BeforeSuite
    void prepare(){
        System.out.println("Инициализация");
    };


    void runTest1(String string, int number){
        System.out.println(string + number);
    };


    @MyTest(priority = 2)
    void runTest2(){
        System.out.println("Тест 2 запущен");
    };

    @MyTest(priority = 1)
    void runTest3(){
        System.out.println("Тест 3 запущен");
    }

    @MyTest(priority = 4)
    void runTest4(){
        System.out.println("Тест 4 запущен");
    }
    @MyTest(priority = 3)
    void runTest5(){
        System.out.println("Тест 5 запущен");
    };

    @MyTest(priority = 6)
    void runTest6(){
        System.out.println("Тест 6 запущен");
    };

    @MyTest(priority = 5)
    void runTest7(){
        System.out.println("Тест 7 запущен");
    };

    @MyTest(priority = 5)
    void runTest8(){
        System.out.println("Тест 8 запущен");
    };


    @AfterSuite
    void complete(){
        System.out.println("Постобработка");
    };
}
