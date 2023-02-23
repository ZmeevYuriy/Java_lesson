import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> listRandomValue = createListRandomValue(0, 100, 30);
        show(listRandomValue, true);

        Collections.sort(listRandomValue);
        show("min→ " + listRandomValue.get(0) + " | " + listRandomValue.get(listRandomValue.size() - 1) + " ←max", false);
        show("average→ " + getAverage(listRandomValue), true);

        listRandomValue.removeIf(item -> item % 2 == 0);
        show("only odd→ " + listRandomValue, true);

        // Try to sort merging
        ArrayList<Integer> listRandomValueForTestMergeSort = createListRandomValue(0, 100, 20);
        int[] arrayForTesting = listRandomValueForTestMergeSort.stream().mapToInt(integer -> integer).toArray();

        show(Arrays.toString(arrayForTesting) + " ← перед сортировкой слияния", false);
        MySort.mergeSort(arrayForTesting);
        show(Arrays.toString(arrayForTesting) + " ← после сортировки слияние", true);
    }

    public static ArrayList<Integer> createListRandomValue(int minValue, int maxValue, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        Random rnd = new Random();

        for (AtomicInteger i = new AtomicInteger(); i.get() < size; i.getAndIncrement()) {
            list.add(rnd.nextInt(minValue, maxValue));
        }

        return list;
    }

    public static float getAverage(List<Integer> list) {
        AtomicInteger summa = new AtomicInteger();
        list.forEach(summa::addAndGet);
        return (float) summa.get() / (float) list.size();

    }

    public static void show(Object something, boolean addSpase) {
        System.out.println(something);
        if (addSpase) System.out.println();
    }
}

abstract class MySort {


    public static void mergeSort(int[] arrayToSort) {
        if (arrayToSort.length < 2) {
            return;
        }
        int sizeLeftHalf = arrayToSort.length / 2;
        int sizeRightHalf = arrayToSort.length - sizeLeftHalf;

        int[] leftHalfArray = new int[sizeLeftHalf];
        int[] rightHalfArray = new int[sizeRightHalf];

        System.arraycopy(arrayToSort, 0, leftHalfArray, 0, sizeLeftHalf);
        System.arraycopy(arrayToSort, sizeLeftHalf, rightHalfArray, 0, sizeRightHalf);

        mergeSort(leftHalfArray);
        mergeSort(rightHalfArray);

        merge(arrayToSort, leftHalfArray, rightHalfArray);
    }

    private static void merge(int[] arrayToSort, int[] leftHalfArray, int[] rightHalfArray) {
        AtomicInteger indexRightHalfArray = new AtomicInteger();
        AtomicInteger indexBossArray = new AtomicInteger();
        AtomicInteger indexLeftHalfArray = new AtomicInteger();

        while (indexLeftHalfArray.get() < leftHalfArray.length && indexRightHalfArray.get() < rightHalfArray.length) {
            if (leftHalfArray[indexLeftHalfArray.get()] <= rightHalfArray[indexRightHalfArray.get()]) {
                arrayToSort[indexBossArray.getAndIncrement()] = leftHalfArray[indexLeftHalfArray.getAndIncrement()];
            } else {
                arrayToSort[indexBossArray.getAndIncrement()] = rightHalfArray[indexRightHalfArray.getAndIncrement()];
            }
        }

        while (indexLeftHalfArray.get() < leftHalfArray.length) {
            arrayToSort[indexBossArray.getAndIncrement()] = leftHalfArray[indexLeftHalfArray.getAndIncrement()];
        }

        while (indexRightHalfArray.get() < rightHalfArray.length) {
            arrayToSort[indexBossArray.getAndIncrement()] = rightHalfArray[indexRightHalfArray.getAndIncrement()];
        }
    }
}