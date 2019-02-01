package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {

    private static int minValue(int[] values) {
        int[] array = Arrays.stream(values).distinct().sorted().toArray();
        int min = 0;
        for (int i = 0; i < array.length; i++) {
            min = min + array[i] * (int) Math.floor(Math.pow(10, array.length - i - 1));
        }
        return min;
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream().reduce(0, (s1, s2) -> s1 + s2);
        if (sum % 2 != 0) {
            return integers.stream().filter(o -> o % 2 == 0).collect(Collectors.toList());
        } else {
            return integers.stream().filter(o -> o % 2 != 0).collect(Collectors.toList());
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 3, 2, 3};
        int min = minValue(array);
        System.out.println(min);
        List<Integer> list = Arrays.asList(4, 8, 15, 16, 23, 42, 11);
        System.out.println(oddOrEven(list));
    }
}
