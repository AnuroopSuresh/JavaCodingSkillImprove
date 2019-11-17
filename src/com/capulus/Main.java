package com.capulus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.*;

public class Main {

    TestClass testClass = new TestClass();

    public static final HashMap<Integer,Character> primeASCIIAlpha = createMap();
    public static final List<Integer> onlyValues = Arrays.asList(67,71,73,79,83,89,97,101,103,107,109,113);

    private static HashMap<Integer,Character> createMap() {
        HashMap<Integer,Character> primeASCIIA = new HashMap<>();
        primeASCIIA.put(67, (char) 67);
        primeASCIIA.put(71, (char) 71);
        primeASCIIA.put(73, (char) 73);
        primeASCIIA.put(79, (char) 79);
        primeASCIIA.put(83, (char) 83);
        primeASCIIA.put(89, (char) 89);
        primeASCIIA.put(97, (char) 97);
        primeASCIIA.put(101, (char) 101);
        primeASCIIA.put(103, (char) 103);
        primeASCIIA.put(107, (char) 107);
        primeASCIIA.put(109, (char) 109);
        primeASCIIA.put(113, (char) 113);
        return primeASCIIA;
    }

    public static void main(String[] args) {

        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int testCases = Integer.parseInt(br.readLine());
            if(testCases>=1&&testCases<=100){
                ExecutorService testCaseExecutors = Executors.newFixedThreadPool(4);
                while(testCases>0) {
                    int sizeOfInput = Integer.parseInt(br.readLine());
                    if (sizeOfInput >= 1 && sizeOfInput <= 500) {
                        String inputString = br.readLine();
                        char[] inputArray = inputString.toCharArray();
                        ArrayList<Character> cList = new ArrayList<Character>();
                        for (char ch : inputArray) {
                            cList.add(ch);
                        }
                        ExecutorService service;
                        final int numberOfThreads = 4;
                        if (sizeOfInput > 12) {
                            service = Executors.newFixedThreadPool(numberOfThreads);
                        } else {
                            service = Executors.newSingleThreadExecutor();
                        }

                        List<List<Character>> partition = divideListBasedOnThreads(numberOfThreads, cList, sizeOfInput);
                        ArrayList<Callable<ArrayList<Character>>> callableArrayList = new ArrayList<>();

                        for (List<Character> characterList : partition) {
                            callableArrayList.add(new WorkerThread(characterList));
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
                    } else {
                        System.err.println("Out of Range input size");
                    }
                    testCases--;
                }
            }else{
                System.err.println("OUT OF Range Test Cases");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

class WorkerThread implements Callable<ArrayList<Character>> {
    private List<Character> characterArrayList;

    public WorkerThread(List<Character> characters){
        this.characterArrayList = characters;
    }

    @Override
    public ArrayList<Character> call() throws Exception {
        int value;
        ArrayList<Character> convertedList = new ArrayList<>();
        for(char c:characterArrayList){
            value = (int)c;
            int nearestASCIIValue = nearestElement(value);
            convertedList.add(Main.primeASCIIAlpha.get(nearestASCIIValue));
        }
        return convertedList;
    }

    private static int nearestElement(int value){
        int c = Main.onlyValues.stream()
                .min(Comparator.comparingInt(i -> Math.abs(i - value)))
                .orElseThrow(() -> new NoSuchElementException("No value present"));
        return c;
    }
}
