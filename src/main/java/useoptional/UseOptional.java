package useoptional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UseOptional {
    public static void main(String[] args) {
        Map<String, String> names = new HashMap<>();
        names.put("Fred", "Jones");

        String firstname = "Freddy";

//        String lastname = names.get(firstname);
//        String shout = lastname.toUpperCase();
//        String message = "Dear " + shout;
//        System.out.println(message);

        Optional.of(names)
                .map(m -> m.get(firstname)) // null return?? produces EMPTY optional
                .map(s -> s.toUpperCase())
                .map(s -> "Dear " + s)
                .ifPresent(s -> System.out.println(s));

    }
}
