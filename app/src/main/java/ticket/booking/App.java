package ticket.booking;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        // a stream in Java is a sequence of elements that can be processed in a pipeline
        List<Integer> l = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // first it is converting the array l to stream to apply filter method which takes lambda function
        // then applying collect method to change it back to list
        List<Integer> l1 = l.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
        System.out.println(l1);

        /*
            PREDICATE => is a functional interface that represents a boolean-valued function
            (condition checking function)
        */
    }
}
