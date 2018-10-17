package com.capulus;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TestClass {
    public static void main(String[] args){

    }

    public static int findSum(List<Integer> sumList){
        int total=Integer.MIN_VALUE;
        for(int number:sumList){
            total = total+number;
        }
        return total;
    }
}
