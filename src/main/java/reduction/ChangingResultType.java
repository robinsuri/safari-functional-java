package reduction;

import students.Student;
import java.util.List;

class Average {
    public long sum = 0;
    public long count = 0;

    public Average(long sum, long count) {
        this.sum = sum;
        this.count = count;
    }
    public Average() {}
}

public class ChangingResultType {
    public static void main(String[] args) {
        List<Student> roster = List.of(
                Student.of("Fred", 75, "Math", "Physics"),
                Student.of("Jim", 58, "Art"),
                Student.of("Sheila", 92, "Math", "Physics", "Astrophysics", "Quantum Mechanics")
        );

        Average avg = roster.stream()
                .filter(s -> s.getCourses().contains("Math"))
                .map(s -> new Average(s.getGrade(), 1))
                .reduce(new Average(), (a1, a2) -> new Average(a1.sum + a2.sum, a1.count + a2.count));
        System.out.println("Average is " + avg.sum / avg.count);
    }
}
