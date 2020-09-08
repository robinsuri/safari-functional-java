package students;

import java.util.List;

public final class Student {
    private final String name;
    private final int grade;
    private final List<String> courses;

    private Student(String name, int grade, List<String> courses) {
        this.name = name;
        this.grade = grade;
        this.courses = courses;
    }

    public static Student of(String name, int grade, String ... courses) {
        return new Student(name, grade, List.of(courses));
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public List<String> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                ", courses=" + courses +
                '}';
    }
}
