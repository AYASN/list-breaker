import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

class ListBreaker {

    /**
     * Partition a list of integer values into sublists of subsize, then returns them as list
     * @param toBePartitioned the list to be partitioned
     * @param subsize the size to partition with
     * @return the result of partition, i.e.: list of sublists containing integers
     */

    static List<List<Integer>> partition(List<Integer> toBePartitioned, int subsize) {
        requireNonNull(toBePartitioned, "The List to be partitioned can't be null.");

        int size = toBePartitioned.size();
        validateSubsize(subsize, size);

        List<List<Integer>> result = new ArrayList<>();

        for (int cursor = 0; cursor < size; cursor += subsize){
            List<Integer> subPartitioned = toBePartitioned.subList(cursor, getLastIndex(subsize, cursor, size));
            result.add(subPartitioned);
        }

        return result;
    }

    private static void validateSubsize(int subsize, int size) {
        if (subsize < 0) {
            throw new IllegalArgumentException("The size of partitions can't be negative");
        } else if (subsize == 0) {
            throw new IllegalArgumentException("The size of partitions can't be zero.");
        } else if (subsize > size) {
            throw new IllegalArgumentException("The size of partitions can't be greater than global size.");
        }
    }

    static int getLastIndex(int subsize, int cursor, int size) {
        return subsize + cursor > size ? size : subsize + cursor;
    }
}