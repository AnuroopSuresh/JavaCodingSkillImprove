package com.capulus;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TrainSeatingArrangment {

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int totalTestCase = Integer.parseInt(br.readLine()), start;
            short seatNumber, end;
            String outPutValue = "";
            for (int i = 0; i < totalTestCase; i++) {
                seatNumber = Short.parseShort(br.readLine());
                if (seatNumber <= 48) {
                    start = 0;
                    end = 3;
                } else {
                    start = 4;
                    end = 8;
                }

                for (int n = start; n <= end; n++) {
                    if (((12 * n) + 1) == seatNumber) {
                        outPutValue = (12 * (n + 1)) + " WS";
                    } else if ((12 * (n + 1) == seatNumber)) {
                        outPutValue = ((12 * n) + 1) + " WS";
                    } else if ((((11 * n) + 2) + n) == seatNumber) {
                        outPutValue = ((11 * (n + 1)) + n) + " MS";
                    } else if (((11 * (n + 1)) + n) == seatNumber) {
                        outPutValue = (((11 * n) + 2) + n) + " MS";
                    } else if ((((10 * n) + 3) + 2 * n) == seatNumber) {
                        outPutValue = ((10 * (n + 1)) + (2 * n)) + " AS";
                    } else if (((10 * (n + 1)) + (2 * n)) == seatNumber) {
                        outPutValue = (((10 * n) + 3) + 2 * n) + " AS";
                    } else if ((((9 * n) + 4) + (3 * n) == seatNumber)) {
                        outPutValue = (9 * (n + 1) + (3 * n)) + " AS";
                    } else if ((9 * (n + 1) + (3 * n)) == seatNumber) {
                        outPutValue = ((9 * n) + 4) + (3 * n) + " AS";
                    } else if ((((8 * n) + 5) + (4 * n) == seatNumber)) {
                        outPutValue = (8 * (n + 1) + (4 * n)) + " MS";
                    } else if ((8 * (n + 1) + (4 * n)) == seatNumber) {
                        outPutValue = ((8 * n) + 5) + (4 * n) + " MS";
                    } else if ((((7 * n) + 6) + (5 * n) == seatNumber)) {
                        outPutValue = (7 * (n + 1) + (5 * n)) + " WS";
                    } else if ((7 * (n + 1) + (5 * n)) == seatNumber) {
                        outPutValue = ((7 * n) + 6) + (5 * n) + " WS";
                    }
                }
                System.out.println(outPutValue);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}