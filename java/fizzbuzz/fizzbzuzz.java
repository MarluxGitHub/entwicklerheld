package de.entwicklerheld.fizzbuzz.challenge.stage1;

public class Fizzbuzz {

    static String fizzbuzz(int number) {

        String fizzbuzz = "";

        fizzbuzz += number % 3 == 0 ? "fizz" : "";
        fizzbuzz += number % 5 == 0 ? "buzz" : "";

        return fizzbuzz.isEmpty() ? String.valueOf(number) : fizzbuzz;
    }

}