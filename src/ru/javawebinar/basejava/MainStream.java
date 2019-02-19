package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {

    private static int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce(0, (s1, s2) -> s1 * 10 + s2);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
       int sum = integers.stream().reduce(0, (s1, s2) -> s1 + s2);
       return integers.stream().filter((sum % 2 != 0) ? o -> o % 2 == 0 : o -> o % 2 != 0)
              .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 3, 2, 3};
        int min = minValue(array);
        System.out.println(min);
        List<Integer> list = Arrays.asList(4, 8, 15, 16, 13, 23, 42, 11);
        System.out.println(oddOrEven(list));
    }
}
