package com.capulus;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.LongAdder;

public class TestClass {
    public static void main(String[] args){

        int length = 5;
        int maxPossibleTermsToAdd = 1;
        //System.out.println("TestClass:main: length: "+length+" max Index: "+getTheMaxIndexToAddWhenLengthIsKnown(length));
        List<Integer> numberList = new ArrayList<>();
        int lengthOfList = 100000;
        for(int i=0;i<lengthOfList;i++){
            numberList.add(100000);
        }
        long start,end;
        start = System.nanoTime();
        List<Integer> integerSubList;
        for(int i=0;i<lengthOfList;i++){
            integerSubList = numberList.subList(i,lengthOfList);
        }
        end =System.nanoTime();
        long timeTaken = (end-start)/1000000;
        System.out.println("TestClass:main: TimeTaken: "+Long.toString(timeTaken)+" ms");
    }
}
