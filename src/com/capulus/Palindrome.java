package com.capulus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

public class Palindrome {

    public static void main(String[] args) {
        try {
            String inputString = "abcsba";
            BufferedReader in = new BufferedReader(new StringReader(inputString));
            //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            char[] charArray = in.readLine().toCharArray();
            int lengthOfCharArray = charArray.length;
            int lengthOfHalfCharArray = lengthOfCharArray/2;
            boolean isPalindrome = true;

            lengthOfCharArray--;
            for(int i=0;i<lengthOfHalfCharArray;i++){
                if(charArray[i]!=charArray[lengthOfCharArray-i]){
                    isPalindrome = false;
                    break;
                }
            }

            if(isPalindrome){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
