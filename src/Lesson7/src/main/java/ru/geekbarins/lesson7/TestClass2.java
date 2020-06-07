package ru.geekbarins.lesson7;

public class TestClass2 {

    @MyTest(priority = 2)
    void prepare(){};

    @MyTest(priority = 1)
    void runTest1(){};

    @MyTest(priority = 3)
    void runTest2(){};

    @MyTest(priority = 5)
    void runTest3(){};

    @MyTest(priority = 4)
    void complete(){};
}
