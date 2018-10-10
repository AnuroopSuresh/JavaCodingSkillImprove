package com.capulus;

import java.io.File;
import java.util.*;

public class GenerateFeatures {

    private static final String dirLocation = "E:\\projects\\PoliceOnePlatform\\grails-app\\domain\\policeoneplatform";
    private static final String[] nameSuffix = {"ADD","VIEW","EDIT","DELETE"};
    private static final String[] descriptionPrefix = {"Add ","View ","Edit ","Delete "};

    public static void main(String[] args) {
        List<File> fileReferenceList = new ArrayList<>();
        UtilsFunction utils= new UtilsFunction();
        utils.listFilesReferences(dirLocation,fileReferenceList);

        String fileName;
        StringBuilder featureName = new StringBuilder("");
        StringBuilder featureDescription = new StringBuilder("");
        int i;

        for (File file : fileReferenceList) {
            fileName = UtilsFunction.splitCamelCase(file.getName().replaceAll(".groovy",""));
            for(i=0;i<4;i++){
                featureDescription = new StringBuilder();
                featureName = new StringBuilder();
                featureName.append(fileName.toUpperCase().replace(' ','_'));
                featureName.append("_");
                featureName.append(nameSuffix[i]);

                featureDescription.append(descriptionPrefix[i]);
                featureDescription.append(fileName);

                System.out.println("Name: "+featureName.toString()+" :: Description: "+featureDescription.toString());
            }
            System.out.println("");
        }
    }
}
