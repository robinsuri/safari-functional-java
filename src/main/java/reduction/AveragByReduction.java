package reduction;

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

class ImmutAverage {
    private final double sum;
    private final long count;

    public ImmutAverage(double sum, long count) {
        this.sum = sum;
        this.count = count;
    }

    public ImmutAverage include(double d) {
        return new ImmutAverage(this.sum + d, this.count + 1);
    }

    public ImmutAverage merge(ImmutAverage other) {
        System.out.println("merge...");
        return new ImmutAverage(this.sum + other.sum, this.count + other.count);
    }

    public Optional<Double> get() {
        if (count > 0) {
            return Optional.of(this.sum / this.count);
        } else {
            return Optional.empty();
        }
    }
}

public class AveragByReduction {
    public static void main(String[] args) {
        long start = System.nanoTime();

        ThreadLocalRandom.current().doubles(2_000_000_000, -Math.PI, +Math.PI)
                .parallel()
                .boxed()
                .reduce(new ImmutAverage(0, 0),
                        (a, d) -> a.include(d),
                        (a1, a2) -> a1.merge(a2))
                .get()
                .ifPresent(d -> System.out.println("Average is " + d));

        long time = System.nanoTime() - start;
        // print compilations with VM option: -XX:+PrintCompilation
        System.out.println("Total time was " + (time / 1_000_000_000.0));
    }
}
