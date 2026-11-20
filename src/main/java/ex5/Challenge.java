package ex5;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.UnaryOperator;

public class Challenge {
    public static void main(String[] args) {
        String[] names= {"aLiCe", "bOb", "aNNa", "sArAh", "jAmEs"};

        List<UnaryOperator<String>> operations= List.of(
                String::trim,
                String::toUpperCase,
                name -> name + " "
                        + (char) ('A' + ThreadLocalRandom.current().nextInt(26)) + ".",
                Challenge::addLastName,
                name -> name + " (CC student)",
                "Student - "::concat
        );

        transformNames(names, operations);
        Arrays.asList(names).forEach(System.out::println);
    }

    public static String addLastName(String name) {
        String firstName= name.split(" ")[0];
        String lastName= new StringBuilder(firstName).reverse().toString();
        return name + " " + lastName;
    }

    public static void transformNames(String[] names, List<UnaryOperator<String>> operations) {
        for (int i=0; i<names.length; i++) {
            for (UnaryOperator<String> operation : operations) {
                names[i]= names[i].transform(operation);
            }
        }
    }
}
