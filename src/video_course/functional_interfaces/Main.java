package video_course.functional_interfaces;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Predicate<Integer> isPrime = Main::isEven;
        // ссылка на функцию

        Consumer<Integer> messageNotification = System.out::println;

        Function<String, Department> strangeConstructor = Department::new;

        Function<Integer, Integer> applyTwo = a -> a + 2;

        System.out.println(applyTwo.apply(2));

        Supplier<Boolean> random = () -> ThreadLocalRandom.current().nextBoolean();

        Stream.of(1, 2, 3, 4, 5, 6).map(applyTwo).filter(isPrime).forEach(messageNotification);

        BiFunction<String, CharSequence, Boolean> contains = String::contains;

        System.out.println(contains.apply("Ukraine", "ine"));

        // composition
        List<Integer> fibonacciNumbers = Arrays.asList(1, 1, 2, 3, 5, 8, 13);

        Function<Integer, Integer> addTwo = e -> e + 2;
        Function<Integer, Integer> multipleTen = e -> e * 10;

        fibonacciNumbers.stream().map(addTwo.andThen(multipleTen)).forEach(System.out::println);

        fibonacciNumbers.stream().map(addTwo.compose(multipleTen)).forEach(System.out::println);


        CalculatePerDiem machine = new CalculatePerDiem();
        Function<Integer, Double> curriedFirstArgument = machine.curryFirstArgument(57.16);
        System.out.println(curriedFirstArgument.apply(5));

    }

    private static boolean isEven(Integer num) {
        return num % 2 == 0;
    }

}

@FunctionalInterface
interface CurriedBiFunction<T, U, R> extends BiFunction<T, U, R> {
    default Function<U, R> curryFirstArgument(T t) {
        return u -> apply(t, u);
    }

    default Function<T, R> currySecondArgument(U u) {
        return t -> apply(t, u);
    }
}

class CalculatePerDiem implements CurriedBiFunction<Double, Integer, Double> {

    private static final Double perDiemRate = 10.15;

    @Override
    public Double apply(Double dollarRate, Integer amountOfDays) {
        return perDiemRate * dollarRate * amountOfDays;
    }
}
