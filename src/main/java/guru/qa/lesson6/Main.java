package guru.qa.lesson6;

public class Main {
    public static void main(String... args) {

        byte aByte = 15; // 8b  from -128 to 127
        short aShort = 32767; // 16b from -32768 to 32767
        int intMaxValue = Integer.MAX_VALUE;
        long aLong = 10000L; // 64b
        float aFloat = 0.123F;
        double aDouble = 9.3;
        Double doubleWrapper = 9.1;


        int aInt = 10; // 32b   (-2^32) .. (+2^32 -1)
        int bInt = 20; // from -2,147,483,648 to 2,147,483,647

        int incrementedA = 5; // from -2,147,483,648 to 2,147,483,647
        int decrementedB = 5; // from -2,147,483,648 to 2,147,483,647


        // Arithmetic operators
        System.out.println("addition: " + aInt + " + " + bInt + " = " + (aInt + bInt));
        System.out.println("subtraction: " + aInt + " - " + bInt + " = " + (aInt - bInt));
        System.out.println("multiplication: " + aInt + " * " + bInt + " = " + (aInt * bInt));
        System.out.println("division: " + bInt + " / " + aInt + " = " + (bInt / aInt));
        System.out.println("increment: " + incrementedA + " = " + ++incrementedA);
        System.out.println("decrement: " + decrementedB + " = " + --decrementedB);

        // Arithmetic mixing types
        System.out.println("double & int addition: " + aInt + " + " + aDouble + " = " + (aInt + aDouble));
        System.out.println("double & int subtraction: " + aDouble + " - " + bInt + " = " + (aDouble - bInt));
        System.out.println("double & int multiplication: " + aInt + " * " + aDouble + " = " + (aInt * aDouble));

        // Comparison operators
        System.out.println("aInt < bInt: " + aInt + " < " + bInt + " = " + (aInt < bInt));
        System.out.println("aInt > bInt: " + aInt + " > " + bInt + " = " + (aInt > bInt));
        System.out.println("aInt >= bInt: " + aInt + " >= " + bInt + " = " + (aInt >= bInt));
        System.out.println("bInt <= aInt: " + bInt + " <= " + aInt + " = " + (bInt <= aInt));
        System.out.println("bInt != aInt: " + bInt + " != " + aInt + " = " + (bInt != aInt));
        System.out.println("bInt == aInt: " + bInt + " == " + aInt + " = " + (bInt == aInt));

        // Overflow
        System.out.println("intMaxValue * aInt: " + intMaxValue + " * " + aInt + " = " + (intMaxValue * aInt));
        System.out.println("intMaxValue + aInt: " + intMaxValue + " * " + aInt + " = " + (intMaxValue + aInt));

        // Logical operators &, |, &&, ||, !
        System.out.println("true & false: " + true + " & " + false + " = " + (true & false));
        System.out.println("true | false: " + true + " | " + false + " = " + (true | false));
        System.out.println("true && false: " + true + " && " + false + " = " + (true && false));
        System.out.println("true || false: " + true + " || " + false + " = " + (true || false));
        System.out.println("!true: " + !true);
        System.out.println("!false: " + !false);
    }
}
