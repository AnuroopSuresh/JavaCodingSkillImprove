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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*public class BestIndex {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int lengthOfInput = Integer.parseInt(br.readLine());
        String[] inputNumberString = br.readLine().split("\\s+");
        Queue<Integer> inputNumbers = new LinkedList<>();
        for (String s : inputNumberString) {

                inputNumbers.add(Integer.parseInt(s));
        }

        //perform Best Index operation
        ArrayList<Integer> bestIndexList = new ArrayList<>();
        boolean done = true;
        Queue<Integer> tempInput;
        do {
            if (inputNumbers.size() > 0) {
                tempInput=new LinkedList<>(inputNumbers);
                boolean keepRemoving = true;
                int numberOfElementsToRemove = 1;
                int totalSum = 0;
                while (keepRemoving) {
                    if (tempInput.size() >= numberOfElementsToRemove) {
                        totalSum += removeNElementsAndSum(numberOfElementsToRemove, tempInput);
                    } else {
                        keepRemoving = false;
                    }
                    numberOfElementsToRemove++;
                }
                bestIndexList.add(totalSum);
                try {
                    inputNumbers.remove();
                } catch (NoSuchElementException n) {
                    done = false;
                }
            } else {
                done = false;
            }

        } while (done);


        System.out.println("BestIndex:main: Result List" + bestIndexList.toString());

    }

    public static int removeNElementsAndSum(int numberOfElementsToRemove, Queue<Integer> inputQueue) {
        int sum = 0, i;
        try {
            for (i = 0; i < numberOfElementsToRemove; i++) {
                sum += inputQueue.remove();
            }
        } catch (NoSuchElementException n) {
            return Integer.MIN_VALUE;
        }
        return sum;
    }

}*/

public class BestIndex {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long start,end;

        int lengthOfInput = Integer.parseInt(br.readLine());
        String[] inputNumberStringArray = br.readLine().split("\\s+");
        ArrayList<Integer> inputList = new ArrayList<>();
        for(String numberString:inputNumberStringArray){
            inputList.add(Integer.parseInt(numberString));
        }
        start = System.nanoTime();
        long sumofList = sumofList(inputList,lengthOfInput);
        long initialSum = sumofList;
        int maxIndexToAdd = getTheMaxIndexToAddWhenLengthIsKnown(lengthOfInput);
        int numberOfElementsToRemove = lengthOfInput-maxIndexToAdd;
        if(numberOfElementsToRemove>0){
            for(int i=maxIndexToAdd;i<lengthOfInput;i++){
                initialSum-=inputList.get(i);
            }
        }
        long max = initialSum,tempSum=0;
        int tempLength = lengthOfInput;

        for(int i=1;i<lengthOfInput;i++){
            tempLength--;
            sumofList=sumofList-inputList.get(i-1);
            tempSum=sumofList;
            maxIndexToAdd = getTheMaxIndexToAddWhenLengthIsKnown(tempLength);
            numberOfElementsToRemove = tempLength-maxIndexToAdd;
            if(numberOfElementsToRemove>0){
                int lastIndex = lengthOfInput-1;
                while(numberOfElementsToRemove>0){
                    tempSum-=inputList.get(lastIndex--);
                    numberOfElementsToRemove--;
                }
            }
            if(tempSum>max){
                max=tempSum;
            }
        }
        System.out.println(max);
        end =System.nanoTime();
        long timeTaken = (end-start)/1000000;
        System.out.println("TestClass:main: TimeTaken: "+Long.toString(timeTaken)+" ms");
    }


    public static int getMaxTermToAdd(int length){
        //this is simple quadratic equation
        //value of x = {-b +or- sqrt[(b*b)-(4*a*c)]}/(2*a)
        //since the pattren is 1,3,6,10,15 -> represents numberofdots or in our case length
        //this pattern is obtained using the formula Xn = {[n(n+1)]/2} and we should find n given Xn or length
        //Therefore n^2+n-2*length=0 is the equation

        int a=1,b=1;
        int c = (length*2)*-1;
        int result=1;

        //disc
        long discriminant = 1-(4*c);
        if(discriminant>0){
            //taking only the positive term
            result = (int)Math.floor((-1+Math.sqrt(discriminant))/2);
        }
        return result;
    }

    public static int getTheMaxIndexToAddWhenLengthIsKnown(int length){
        return sumOfNNaturalNumbers(getMaxTermToAdd(length));
    }

    public static int sumOfNNaturalNumbers(int n){
        int sum=0;
        for(int i=1;i<=n;i++){
            sum+=i;
        }
        return sum;
    }

    public static long sumofList(List<Integer> integerList,int toIndex){
        long sum=0;
        for(int i=0;i<toIndex;i++){
            sum+=integerList.get(i);
        }
        return sum;
    }
}
