package video_course.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("usa", " Ukraine", "JaPaN");

        // forEach - default method in Iterable interface
        list.forEach(System.out::println);

        // forEach from Stream interface
        list.stream().forEach(System.out::println);

        list.stream()
                .map(item -> item.toLowerCase().trim())
                .filter(item -> !item.contains("p"))
                .forEach(System.out::println);


        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5)
                .stream()
                .filter(i -> i > 4)
                .map(bb -> 42);
        stream.count();


        // generators, infinite loop
        IntStream.iterate(0, i -> i + 2)
                .limit(10)
                .forEach(e -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    System.out.println(e);
                });

        System.out.println("It wont be printed");


        Stream.of(2, 4, 2.332, 4, -4, 9.9)
                .sorted()
                .forEach(System.out::println);

    }
}
