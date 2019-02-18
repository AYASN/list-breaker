import java.util.List;

import static java.util.Arrays.asList;

public class ListBreakerDemo {

    public static void main(String[] args) {

        List<Integer> toBePartitioned = asList(1, 2, 3, 4, 5);

        List<List<Integer>> result = ListBreaker.partition(toBePartitioned, 2);
        System.out.println("The result is : " + result);

        result = ListBreaker.partition(toBePartitioned, 3);
        System.out.println("The result is : " + result);

        result = ListBreaker.partition(toBePartitioned, 1);
        System.out.println("The result is : " + result);

    }
}