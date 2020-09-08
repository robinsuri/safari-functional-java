package students;

import java.util.ArrayList;
import java.util.List;

interface StudentTest {
    boolean test(Student s);
}

class SmartStudentTest implements StudentTest {
    public boolean test(Student s) {
        return s.getGrade() > 70;
    }
}

class EnthusiasticStudentTest implements StudentTest {

    @Override
    public boolean test(Student s) {
        return s.getCourses().size() > 3;
    }
}
public class School {
    public static void showStudents(List<Student> ls) {
        for (Student s : ls) {
            System.out.println(s);
        }
        System.out.println("-----------------");
    }

    // Use "pointer to an object" for the behavior that object contains...
    // e.g. strategy, and this one, which is called "command"
    // In "functional programming" passing a "behavior" (i.e. a function :) is called "higher order function"
    public static List<Student> getStudents(List<Student> ls, StudentTest sst) {
        List<Student> rv = new ArrayList<>();
        for (Student s : ls) {
            if (sst.test(s)) {
                rv.add(s);
            }
        }
        return rv;
    }

//     public static List<Student> getSmartStudents(List<Student> ls, int threshold) {
//        List<Student> rv = new ArrayList<>();
//        for (Student s : ls) {
//            if (s.getGrade() > threshold) {
//                rv.add(s);
//            }
//        }
//        return rv;
//    }
//
//    public static List<Student> getEnthusiastcStudents(List<Student> ls, int threshold) {
//        List<Student> rv = new ArrayList<>();
//        for (Student s : ls) {
//            if (s.getCourses().size() > threshold) {
//                rv.add(s);
//            }
//        }
//        return rv;
//    }

    public static void main(String[] args) {
        List<Student> roster = List.of(
                Student.of("Fred", 75, "Math", "Physics"),
                Student.of("Jim", 58, "Art"),
                Student.of("Sheila", 92, "Math", "Physics", "Astrophysics", "Quantum Mechanics")
        );

        showStudents(getStudents(roster, new SmartStudentTest()));
//        showStudents(getSmartStudents(roster, 70));
        showStudents(getStudents(roster, new EnthusiasticStudentTest()));
    }
}
