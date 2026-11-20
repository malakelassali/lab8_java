package ex4;

import java.util.Locale;

public class Test {
    static StringManipulator upper= (String input) -> {
        return input.toUpperCase(Locale.ROOT);
    };

    static StringManipulator reverse= (String input) -> {
        StringBuilder sb= new StringBuilder(input);
        sb.reverse();
        return sb.toString();
    };

    public static String apply(StringManipulator sm, String input) {
        return sm.manipulate(input);
    }

    static void main(String[] args) {
        Test test= new Test();
        System.out.println(apply(upper, "Hello"));   // HELLO
        System.out.println(apply(reverse, "Hello")); // olleH
    }

}

@FunctionalInterface
interface StringManipulator {
    // Single abstract method that takes
    // a String and returns a modified String
    String manipulate(String input);
}


