package ru.geekbrains.lesson6.tests;

import ru.geekbrains.lesson6.TestedArrays;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ArrLastNumFourTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{5, 6, 2}, new int[]{3, 2, 3, 4, 5, 6, 2}},
                {new int[]{1, 7, 0, 7, 8}, new int[]{3, 4, 2, 5, 7, 8, 0, 1, 4, 1, 7, 0, 7, 8}},
                {new int[]{1, 1, 1, 1, 0}, new int[]{6, 5, 8, 0, 0, 6, 2, 4, 5, 1, 2, 4, 7, 4, 3, 5, 8, 0, 4, 1, 1, 1, 1, 0}},
                {new int[]{5, 5, 7, 8, 8, 1, 1, 3, 5, 6, 7}, new int[]{9, 0, 9, 1, 1, 4, 6, 4, 1, 9, 8, 4, 5, 5, 7, 8, 8, 1, 1, 3, 5, 6, 7}}

        });

    }

    int[] expected;
    int[] x;

    public ArrLastNumFourTest(int[] expected, int[] x) {
        this.expected = expected;
        this.x = x;
    }

    @Test
    public void LastNumFourTest() {
        Assert.assertArrayEquals(expected, TestedArrays.intArrLastNumFour(x));
    }
}
