package reduction;

import java.util.stream.IntStream;

public class SimpleReduce {
    public static void main(String[] args) {
        var res = IntStream.range(1, 11)
                .peek(n -> System.out.println(">> " + n))
                .reduce((a, b) -> a + b);
        res.ifPresent(s -> System.out.println("Sum is " + s));
        var res2 = IntStream.range(1, 11)
                .reduce(0, (a, b) -> a + b);
    }
}
