package com.capulus;

/*You are given an array  of  elements. Now you need to choose the best index of this array . An index of the array is called best if the special sum of this index is maximum across the special sum of all the other indices.

        To calculate the special sum for any index  , you pick the first element that is  and add it to your sum. Now you pick next two elements i.e.  and  and add both of them to your sum. Now you will pick the next  elements and this continues till the index for which it is possible to pick the elements. For example:

        If our array contains  elements and you choose index to be  then your special sum is denoted by -
        , beyond this its not possible to add further elements as the index value will cross the value .

        Find the best index and in the output print its corresponding special sum. Note that there may be more than one best indices but you need to only print the maximum special sum.

        Input
        First line contains an integer  as input. Next line contains  space separated integers denoting the elements of the array .
        Output
        In the output you have to print an integer that denotes the maximum special sum

        Constraints

        Sample Inputs

        Input	Output
        5
        1 3 1 2 5

        8
        10
        2 1 3 9 2 4 -10 -9 1 3

        9


        SAMPLE INPUT
        6
        -3 2 3 -4 3 1
        SAMPLE OUTPUT
        3
        Explanation
        Let us calculate the special value for each index :

        For index 1:
        For index 2:
        For index 3:
        For index 4:
        For index 5:
        For index 6:
        So the maximum value among all the special values is 3 hence it is the output.
*/


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BestIndex {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int lengthOfInput = Integer.parseInt(br.readLine());
        String[] inputNumberString = br.readLine().split("\\s");
        Queue<Integer> inputNumbers = new LinkedList<>();
        for (String s : inputNumberString) {
            inputNumbers.add(Integer.parseInt(s));
        }

        //perform Best Index operation
        ArrayList<Integer> bestIndexList = new ArrayList<>();
        boolean done = true;
        int loopCounter = 0, lengthOfInputTemp = lengthOfInput, i, numberOfElementsToPoll = 1, pollCounter = 1, pollLoop;
        Queue<Integer> tempInput;
        do {
            boolean possibleToPoll = true;
            loopCounter++;
            tempInput = new LinkedList<>(inputNumbers);
            System.out.println("BestIndex:main: Length of Queue: " + tempInput.size() + " length of temp Input " + lengthOfInputTemp);

        } while (done);

    }

    private static boolean possibleToRemove(int length, int counter) {
        return false;
    }

    public static int sumOfQueue(Queue<Integer> integerQueue, int numberOfElements) {
        int sum = 0;
        try {
            for (int i = 0; i < numberOfElements; i++) {
                sum = sum + integerQueue.remove();
            }
        } catch (NoSuchElementException n) {
            return Integer.MIN_VALUE;
        }
        return sum;
    }

}
