package ru.geekbrains.lesson6;

import java.util.ArrayList;
import java.util.Arrays;

public class TestedArrays {

    public static int[] intArrLastNumFour(int[] inputArr) {
        ArrayList<Integer> fourIndexesList = new ArrayList<>();

        for (int i = 0; i < inputArr.length; i++) {
            if (inputArr[i] == 4) {
                fourIndexesList.add(i);
            }
        }
        if (fourIndexesList.size() == 0) {
            throw new RuntimeException("Ни одной 4и во входящем массиве!");
        }

        int lastFourIndex = fourIndexesList.get(fourIndexesList.size() - 1);
        int resultArrLength = (inputArr.length - lastFourIndex - 1);
        int[] resultArr = new int[resultArrLength];
        for (int i = 0; i < resultArrLength; i++) {
            resultArr[i] = inputArr[lastFourIndex + i + 1];
        }
        return resultArr;
    }

    public static boolean intArrFoursAndOnes(int[] inputArr) {

        for (int value : inputArr) {
            if (value == 4) {
                return true;
            } else if (value == 1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        int[] testedArr1 = {4, 2, 2, 0, 0, 2, 0, 1, 0, 7};
        int[] testedArr2 = {4, 0};
        System.out.println(Arrays.toString(intArrLastNumFour(testedArr1)));
        System.out.println(intArrFoursAndOnes(testedArr2));
    }

}

