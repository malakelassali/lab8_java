package ex3;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class CH1 {
    // ch1
    Consumer<String> printTheParts= sentence -> {
        String[] parts= sentence.split(" ");
        for (String part : parts) {
            System.out.println(part);
        }
    };

    public static String everySecondChar(String source) {
        StringBuilder returnVal= new StringBuilder();
        for (int i= 0; i<source.length(); i++) {
            if (i%2==1) {
                returnVal.append(source.charAt(i));
            }
        }
        return returnVal.toString();
    }

    // ch2
    UnaryOperator<String> everySecondChar= source -> {
        StringBuilder returnVal= new StringBuilder();
        for (int i=0; i<source.length(); i++) {
            if (i%2==1) {
                returnVal.append(source.charAt(i));
            }
        }
        return returnVal.toString();
    };

    // ch4
    public String everySecondCharacter(UnaryOperator<String> func, String source) {
        return func.apply(source);
    }

    public void main(String[] args) {
        printTheParts.accept("I love Java");

        // ch3
        System.out.println(everySecondChar.apply("1234567890"));
        // ch5
        System.out.println(everySecondCharacter(everySecondChar, "1234567890"));
        // ch6
        Supplier<String> iLoveJava= () -> "I love Java";
        // ch7
        String supplierResult= iLoveJava.get();
        System.out.println(supplierResult);
    }
}

