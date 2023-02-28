package Lesson_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        int[] nums1 = {1, 3, 2, 1, 7};
        int[] nums2 = {3, 2, 1, 4, 7};
        System.out.println(Array.findEqualsSubarray(nums1, nums2));

        int[] nums3 = {0, 0, 0, 0, 0};
        int[] nums4 = {0, 0, 0, 0, 0};
        System.out.println(Array.findEqualsSubarray(nums3, nums4));

        int[] nums5 = {1, 2, 4, 3, 2};
        int[] nums6 = {2, 3, 1, 3, 5};
        System.out.println(Array.findEqualsSubarray(nums5, nums6));
    }
}

abstract class Array {

    private static final int MIN_LENGTH_SUBARRAY = 2;

    public static String findEqualsSubarray(int[] arrayOne, int[] arrayTwo) {
        List<Integer> result = new ArrayList<>();
        int counter = 0;

        long timeStart = System.currentTimeMillis();

        for (int startIndex = 0; startIndex <= arrayOne.length; startIndex++) {
            for (int endIndex = startIndex + 1; endIndex <= arrayOne.length; endIndex++) {
                int[] tempSubarray = Arrays.copyOfRange(arrayOne, startIndex, endIndex);
                if (tempSubarray.length >= MIN_LENGTH_SUBARRAY && counter < tempSubarray.length && isContains(tempSubarray, arrayTwo)) {
                    result.clear();
                    for (int item : tempSubarray) result.add(item);
                    counter = tempSubarray.length;
                }
            }
        }
        System.out.printf("Время выполнения %s миллисек.\n", System.currentTimeMillis() - timeStart);

        if (result.size() < MIN_LENGTH_SUBARRAY) {
            return "Чёт ничего не нашлось :( ";
        } else {
            return "Повторяющийся подмассив с максимальной длинной " + counter + " → " + result;
        }
    }

    private static boolean isContains(int[] arrayOne, int[] arrayTwo) {
        for (int startIndex = 0; startIndex <= arrayTwo.length; startIndex++) {
            for (int endIndex = startIndex + 1; endIndex <= arrayTwo.length; endIndex++) {
                int[] tmp = Arrays.copyOfRange(arrayTwo, startIndex, endIndex);
                if (arrayOne.length == tmp.length && Arrays.equals(arrayOne, tmp)) {
                    return true;
                }
            }
        }
        return false;
    }
}