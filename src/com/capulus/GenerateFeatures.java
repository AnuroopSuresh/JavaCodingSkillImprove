package com.capulus;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenerateFeatures {

    private static final String dirLocation = "E:\\projects\\PoliceOnePlatform\\grails-app\\domain\\policeoneplatform";


    public static void main(String[] args) {
        List<File> fileReferenceList = new ArrayList<>();
        new UtilsFunction().listFilesReferences(dirLocation,fileReferenceList);

        for (File file : fileReferenceList) {
            System.out.println("GenerateFeatures:main: " + file.getName());
        }


    }


}
