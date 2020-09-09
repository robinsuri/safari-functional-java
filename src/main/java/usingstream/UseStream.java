package usingstream;

import students.Student;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UseStream {
    private static String getLetterGrade(Student s) {
        int grade = s.getGrade();
        if (grade > 80) return "A";
        if (grade > 70) return "B";
        else return "C";
    }
    public static void main(String[] args) {
        List<Student> roster = List.of(
                Student.of("Fred", 75, "Math", "Physics"),
                Student.of("Jim", 58, "Art"),
                Student.of("Jimmy", 59, "Art"),
                Student.of("Jim2", 57, "Art"),
                Student.of("Sheila", 92, "Math", "Physics", "Astrophysics", "Quantum Mechanics")
        );

        roster.stream()
                .filter(s -> s.getGrade() > 70) // "Intermediate" or "non-terminal" operations
                .map(s -> "Student called " + s.getName() + " is smart")
                .forEach(s -> System.out.println(s)); // any operation that does not return [SuperIterable] is "terminal"

        System.out.println("---------------------");
        var st = roster.stream()
                .peek(s -> System.out.println(">> " + s))
                .filter(s -> s.getGrade() > 70)
                .flatMap(s -> s.getCourses().stream())
                .peek(s -> System.out.println("++ " + s))
                .distinct();
        // build new "data set"
        st.forEach(s -> System.out.println(s));
        // Can't "reuse" a stream
//                st.map(s -> s.toUpperCase())
//                        .forEach(s -> System.out.println(s));

        System.out.println("----------------------");
        List.of("zebra", "ball", "apple")
                .stream()
                .peek(s -> System.out.println("> " + s))
                .sorted()
                .peek(s -> System.out.println(">> " + s))
                .forEach(s -> System.out.println(s));

        System.out.println("----------------------");

        boolean allMatch = IntStream.iterate(1, x -> x + 1)
//                .forEach(i -> System.out.println(i));
                .allMatch(i -> i < 1_000);
        System.out.println("all match? " + allMatch);

        System.out.println("----------------------");
        roster.stream()
                .collect(Collectors.groupingBy(s -> getLetterGrade(s)))
//                .entrySet().stream()
                .forEach((k, v) -> System.out.println("Students " + v + " have grade " + k));

        System.out.println("----------------------");
        roster.stream()
                .collect(Collectors.groupingBy(s -> getLetterGrade(s), Collectors.counting()))
                .forEach((k, v) -> System.out.println(v + " students " + " have grade " + k));
    }
}
