package ru.geekbrains.lesson6.tests;

import ru.geekbrains.lesson6.TestedArrays;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ArrFoursAndOnesTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {true, new int[]{1, 2, 3, 4, 5, 6, 2}},
                {false, new int[]{3, 2, 6, 7, 5}},
                {false, new int[]{8, 9, 8, 9, 8, 9, 8, 9}},
                {true, new int[]{1, 4, 4, 1, 1, 4, 1}}

        });

    }

    boolean expected;
    int[] x;

    public ArrFoursAndOnesTest(boolean expected, int[] x) {
        this.expected = expected;
        this.x = x;
    }


    @Test
    public void FoursAndOnesTest() {

        Assert.assertEquals(expected, TestedArrays.intArrFoursAndOnes(x));
    }
}
