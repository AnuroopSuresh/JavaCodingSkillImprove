package com.capulus;

/*Given a single line of string 'S' containing alpha, numeric and symbols, followed by a number '0<=N<=1000'. Encrypt and print the resulting string.
        Note: The cipher only encrypts Alpha and Numeric. (A-Z, a-z, and 0-9) . All Symbols, such as - , ; %, remain unencrypted.

        SAMPLE INPUT
        All-convoYs-9-be:Alert1.
        4
        SAMPLE OUTPUT
        Epp-gsrzsCw-3-fi:Epivx5.

        Explanation
        First line contains the string to convert. S

        Second line contains the number, encrypt key. K

        A becomes E, Y becomes C, 9 becomes 3,

        -, . unchanged.*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Cipher {

    public static void main(String[] args)throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputString = br.readLine();
        char[] inputCharArray = inputString.toCharArray();

        ArrayList<Character> cList = new ArrayList<Character>();
        for (char ch : inputCharArray) {
            cList.add(ch);
        }

        int sizeOfInput = cList.size();
        int key = Integer.parseInt(br.readLine());
        int conversionNumberAlpha = key%27;
        int conversionNumberNumeric = key%10;
        ExecutorService service;
        int numberOfThreads = 4;

        if(sizeOfInput>12){
            service = Executors.newFixedThreadPool(numberOfThreads);
        }else{
           service = Executors.newSingleThreadExecutor();
           numberOfThreads=1;
        }

        List<List<Character>> partition = divideListBasedOnThreads(numberOfThreads, cList, sizeOfInput);

        ArrayList<Callable<ArrayList<Character>>> callableArrayList = new ArrayList<>();
        for (List<Character> characterList : partition) {
            callableArrayList.add(new CipherWorker(characterList,conversionNumberAlpha,conversionNumberNumeric));
        }
        List<Future<ArrayList<Character>>> futureList = service.invokeAll(callableArrayList);
        StringBuilder outPutString = new StringBuilder();
        for (Future<ArrayList<Character>> result : futureList) {
            for (char c : result.get()) {
                outPutString.append(c);
            }
        }
        System.out.println(outPutString);
        service.shutdown();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    service.shutdown();
                    if (!service.awaitTermination(100, TimeUnit.MILLISECONDS)) { //optional *
                        System.err.println("Executor did not terminate in the specified time."); //optional *
                        List<Runnable> droppedTasks = service.shutdownNow(); //optional **
                        System.err.println("Executor was abruptly shut down. " + droppedTasks.size() + " tasks will not be executed."); //optional **
                    }
                } catch (InterruptedException i) {
                    i.printStackTrace();
                }
            }
        });
    }

    public static List<List<Character>> divideListBasedOnThreads(int numberOfthread,List<Character> characterArrayList,int size){
        int partitionSize = (int) size / numberOfthread;
        List<List<Character>> partitions = new ArrayList<>();
        for (int i = 0; i < size; i += partitionSize) {
            partitions.add(characterArrayList.subList(i, Math.min(i + partitionSize, size)));
        }

        return partitions;
    }
}

class CipherWorker implements Callable<ArrayList<Character>>{
    private List<Character> characterArrayList;
    private int conversionNumberAlpha;
    private int conversionNumberNumeric;

    public CipherWorker(List<Character> characterSubList,int conversionNumberAlpha,int conversionNumberNumeric){
        this.characterArrayList = characterSubList;
        this.conversionNumberAlpha = conversionNumberAlpha;
        this.conversionNumberNumeric = conversionNumberNumeric;
    }

    @Override
    public ArrayList<Character> call() throws Exception {
        ArrayList<Character> conversionList = new ArrayList<>();

        int beforeConversion,afterConversion;
        StringBuilder resultString = new StringBuilder();

        for(char c:characterArrayList){
            beforeConversion = (int)c;
            if(Character.isAlphabetic(c)){
                afterConversion = beforeConversion+conversionNumberAlpha;
                if(Character.isLowerCase(c)){
                    //lowerCase conversion
                    if(afterConversion>122) {
                        afterConversion = (afterConversion - 122) + 96;
                    }
                }else {
                    //lowerCase Conversion
                    if(afterConversion>90) {
                        afterConversion = (afterConversion - 90) + 64;
                    }
                }
            }else if(Character.isDigit(c)){
                //numericConversion
                afterConversion = beforeConversion+conversionNumberNumeric;
                if(afterConversion>57){
                    afterConversion = (afterConversion-57)+47;
                }
            }else{
                //ignore special char
                afterConversion = beforeConversion;
            }
            conversionList.add((char)afterConversion);
        }

        return conversionList;
    }
}
