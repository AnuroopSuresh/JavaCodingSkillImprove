package com.capulus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class TestClass {
    public static void main(String[] args) throws IOException {

        BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = bi.readLine()) != null)
            System.out.println("TestClass:main: "+line);

        /*IncrementCounterRunnable incrementCounterRunnable = new IncrementCounterRunnable();
        DecrementCounterRunnable decrementCounterRunnable = new DecrementCounterRunnable();

        incrementCounterRunnable.largeNumber = 1000;
        decrementCounterRunnable.largeNumber = 1000;

        Thread t1 = new Thread(incrementCounterRunnable);
        Thread t2 = new Thread(decrementCounterRunnable);

        t1.start();
        t2.start();*/

        String braces = "[(([(])))]";
        char[] bracesArray = braces.toCharArray();
        int lenghtOfBraces = braces.length(), halfLenght = bracesArray.length / 2;

        try {
            if (lenghtOfBraces % 2 == 0) {
                int firstHalf, sencondHalf;
                for (int i = 0; i < halfLenght; i++) {
                    firstHalf = halfLenght - (i + 1);
                    sencondHalf = i + halfLenght;
                    if (bracesArray[firstHalf] == TestClass.returnOppositeValue(bracesArray[sencondHalf])) {
                        System.out.println("TestClass:main: true");
                        System.out.println(bracesArray[firstHalf] + " == " + bracesArray[sencondHalf]);
                    } else {
                        System.out.println("TestClass:main: false");
                        System.out.println(bracesArray[firstHalf] + " == " + bracesArray[sencondHalf]);
                    }
                }


            } else {
                System.err.println("TestClass:main: improper brackes length");
            }
        } catch (Error error) {
            error.printStackTrace();
        }
        System.out.println("TestClass:main: main function end, i.e main threaded ended");
    }

    private static char returnOppositeValue(char c) {
        switch (c) {
            case '(':
                return ')';

            case ')':
                return '(';

            case '[':
                return '[';

            case ']':
                return '[';

            case '{':
                return '}';

            case '}':
                return '{';

            default:
                return '0';
        }
    }
}

class IncrementCounterRunnable implements Runnable {

    public int largeNumber;

    @Override
    public void run() {

        for (int i = 0; i < largeNumber; i++)
            System.out.println("IncrementCounterRunnable:run: increment thread : i = " + i);

    }
}

class DecrementCounterRunnable implements Runnable {
    public int largeNumber;

    @Override
    public void run() {
        for (int i = largeNumber; i > 0; i--) {
            System.out.println("DecrementCounterRunnable:run: Decrement thread : i = " + i);
        }
    }
}


