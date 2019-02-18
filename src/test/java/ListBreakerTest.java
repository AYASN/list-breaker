import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ListBreakerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private List<Integer> toBePartitioned = asList(1, 2, 3, 4, 5);

    @Test
    public void should_break_a_list_of_five_into_sub_lists_of_two() {
        List<List<Integer>> result = ListBreaker.partition(toBePartitioned, 2);

        assertThat(result, hasSize(3));
        assertThat(result,
                contains(asList(1, 2), asList(3, 4), asList(5)));
    }

    @Test
    public void should_break_a_list_of_five_into_sub_lists_of_three() {
        List<List<Integer>> result = ListBreaker.partition(toBePartitioned, 3);

        assertThat(result, hasSize(2));
        assertThat(result,
                contains(asList(1, 2, 3), asList(4, 5)));
    }

    @Test
    public void should_break_a_list_of_five_into_sub_lists_of_one() {
        List<List<Integer>> result = ListBreaker.partition(toBePartitioned, 1);

        assertThat(result, hasSize(5));
        assertThat(result,
                contains(asList(1), asList(2), asList(3), asList(4), asList(5)));
    }

    @Test
    public void should_break_a_list_of_seven_into_sub_lists_of_three() {
        toBePartitioned = asList(1, 2, 3, 4, 5, 6, 7);
        List<List<Integer>> result = ListBreaker.partition(toBePartitioned, 3);

        assertThat(result, hasSize(3));
        assertThat(result,
                contains(asList(1,2,3), asList(4,5,6), asList(7)));
    }

    @Test
    public void should_break_a_list_of_seven_into_sub_lists_of_one() {
        toBePartitioned = asList(1, 2, 3, 4, 5, 6, 7);
        List<List<Integer>> result = ListBreaker.partition(toBePartitioned, 1);

        assertThat(result, hasSize(7));
        assertThat(result,
                contains(asList(1), asList(2), asList(3), asList(4), asList(5), asList(6), asList(7)));
    }

    @Test
    public void should_break_a_list_of_three_into_sub_lists_of_two() {
        toBePartitioned = asList(1, 2, 3);
        List<List<Integer>> result = ListBreaker.partition(toBePartitioned, 2);

        assertThat(result, hasSize(2));
        assertThat(result,
                contains(asList(1,2), asList(3)));
    }

    @Test
    public void should_throw_exception_when_subsize_is_greater_than_size_of_three() {
        toBePartitioned = asList(1, 2, 3);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("The size of partitions can't be greater than global size.");

        List<List<Integer>> result = ListBreaker.partition(toBePartitioned, 5);
    }

    @Test
    public void last_index_equal_size_when_cursor_is_greater_than_size() {
        int lastIndex = ListBreaker.getLastIndex(2, 4, 5);
        assertThat(lastIndex, equalTo(5));
    }

    @Test
    public void last_index_equal_sum_of_subsize_and_cursor_when_the_result_is_less_than_size() {
        int lastIndex = ListBreaker.getLastIndex(2, 1, 5);
        assertThat(lastIndex, equalTo(3));
    }

    @Test
    public void should_raise_exception_when_called_with_null_list() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("The List to be partitioned can't be null.");

        ListBreaker.partition(null, 2);
    }


    @Test
    public void should_raise_exception_when_called_with_subsize_negative() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("The size of partitions can't be negative");

        ListBreaker.partition(toBePartitioned, -1);
    }

    @Test
    public void should_raise_exception_when_called_with_empty_list_and_subsize_negative() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("The size of partitions can't be negative");

        ListBreaker.partition(emptyList(), -1);
    }

    @Test
    public void should_raise_exception_when_called_with_null_subsize() {
        thrown.expect(NullPointerException.class);

        ListBreaker.partition(toBePartitioned, (Integer) null);
    }

    @Test
    public void should_throw_exception_when_subsize_equals_zero() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("The size of partitions can't be zero.");

        ListBreaker.partition(toBePartitioned, 0);
    }

    @Test
    public void should_throw_exception_when_subsize_is_greater_than_size_of_five() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("The size of partitions can't be greater than global size.");

        ListBreaker.partition(toBePartitioned, 6);
    }
}
