package com.capulus;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class UtilsFunction {

    public static boolean gateTest;
    public UtilsFunction(){
        gateTest = true;
    }

    public static List<Integer> sieveOfEratosthenes(int max,int min) {
        boolean prime[] = new boolean[max + 1];
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= max; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= max; i += p) {
                    prime[i] = false;
                }
            }
        }
        List<Integer> primeNumbers = new LinkedList<>();
        for (int i = 2; i <= max; i++) {
            if (prime[i]) {
                primeNumbers.add(i);
            }
        }

        primeNumbers = primeNumbers.stream().filter(x->x>min).collect(Collectors.toList());

        return primeNumbers;
    }

    public static List<List<Character>> divideListBasedOnThreads(int numberOfthread,List<Character> characterArrayList,int size){
        int partitionSize = (int) size / numberOfthread;
        List<List<Character>> partitions = new ArrayList<>();
        for (int i = 0; i < size; i += partitionSize) {
            partitions.add(characterArrayList.subList(i, Math.min(i + partitionSize, size)));
        }

        return partitions;
    }

    public ArrayList<File> listFilesRecursively(String directoryName) {

        ArrayList<File> files = new ArrayList<>();
        File directory = new File(directoryName);
        // Get all the files from a directory.
        File[] fList = directory.listFiles();
        if(fList != null) {
            for (File file : fList) {
                if (file.isFile()) {
                    files.add(file);
                } else if (file.isDirectory()) {
                    files.addAll(listFilesRecursively(file.getAbsolutePath()));
                }
            }
        }
        return files;
    }

    public void listFilesReferences(String directoryName, List<File> files) {
        File directory = new File(directoryName);

        // Get all the files from a directory.
        File[] fList = directory.listFiles();
        if(fList != null){
            for (File file : fList) {
                if (file.isFile()) {
                    files.add(file);
                } else if (file.isDirectory()) {
                    listFilesReferences(file.getAbsolutePath(), files);
                }
            }
        }
    }

    public static String splitCamelCase(String s) {
        return s.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        );
    }


}
