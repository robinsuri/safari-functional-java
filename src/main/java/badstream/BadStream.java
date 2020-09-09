package badstream;

import java.util.stream.IntStream;

public class BadStream {
    public static void main(String[] args) {
        long [] counter = {0};
        IntStream.range(0, 1_000_000_000)
                .parallel()
                .peek(x -> counter[0]++) // DON'T TO THIS!!! Side-effects!!!
                .forEach(x -> {});
        System.out.println("Counter is " + counter[0]);
    }
}
