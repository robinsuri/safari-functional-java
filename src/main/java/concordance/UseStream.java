package concordance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UseStream {
    private static final Pattern WORD_BOUNDARY = Pattern.compile("\\W+");

    public static void main(String[] args) {
        try (Stream<String> lines = Files.lines(Path.of("PrideAndPrejudice.txt"))) {
            lines
                    .map(String::toLowerCase)
//                    .map(s -> s.toLowerCase())
                    .flatMap(WORD_BOUNDARY::splitAsStream)
//                    .flatMap(s -> WORD_BOUNDARY.splitAsStream(s))
                    .filter(s -> !s.isBlank())
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//                    .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                    .entrySet().stream()
                    .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                    .limit(200)
                    .forEach(System.out::println);
//                    .forEach(s -> System.out.println(s));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
