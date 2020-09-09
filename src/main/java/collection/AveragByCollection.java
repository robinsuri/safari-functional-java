package collection;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

class MutAverage {
    private double sum;
    private long count;

    public MutAverage(double sum, long count) {
        this.sum = sum;
        this.count = count;
    }

    public void include(double d) {
        this.sum += d;
        this.count++;
    }

    public void merge(MutAverage other) {
        System.out.println("merge...");
        this.sum += other.sum;
        this.count += other.count;
    }

    public Optional<Double> get() {
        if (count > 0) {
            return Optional.of(this.sum / this.count);
        } else {
            return Optional.empty();
        }
    }
}

public class AveragByCollection {
    public static void main(String[] args) {
        long start = System.nanoTime();

        ThreadLocalRandom.current().doubles(4_000_000_000L, -Math.PI, +Math.PI)
                .parallel()
                .boxed()
                .collect(() -> new MutAverage(0, 0),
                        (a, d) -> a.include(d),
                        (a1, a2) -> a1.merge(a2))
                .get()
                .ifPresent(d -> System.out.println("Average is " + d));

        long time = System.nanoTime() - start;
        // print compilations with VM option: -XX:+PrintCompilation
        System.out.println("Total time was " + (time / 1_000_000_000.0));
    }
}
