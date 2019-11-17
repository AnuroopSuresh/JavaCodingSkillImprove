package com.capulus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.StringReader;

public class FindProduct {
    public static void main(String[] args) {

        try {
            String inputString = "5\n" +
                    "1 2 3 4 5";
            BufferedReader in = new BufferedReader(new StringReader(inputString));
            //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            long moduloNumber = (long) (Math.pow(10, 9) + 7);
            int lengthOfArray = Integer.parseInt(in.readLine());
            long productOfAllNumber = 1;
            String[] inputArrayString = in.readLine().split(" ");
            for (int i = 0; i < lengthOfArray; i++) {
                productOfAllNumber = (productOfAllNumber * Integer.parseInt(inputArrayString[i])) % (moduloNumber);
            }

            System.out.println(productOfAllNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
