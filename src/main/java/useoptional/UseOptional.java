package useoptional;

import java.util.HashMap;
import java.util.Map;

public class UseOptional {
    public static void main(String[] args) {
        Map<String, String> names = new HashMap<>();
        names.put("Fred", "Jones");

        String firstname = "Freddy";

        String lastname = names.get(firstname);
        String shout = lastname.toUpperCase();
        String message = "Dear " + shout;
        System.out.println(message);
    }
}
