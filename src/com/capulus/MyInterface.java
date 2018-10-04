package com.capulus;

public interface MyInterface {
    int age =12;
    default void defaultSystemPrint(String text,String anotherText){
        System.out.println("MyInterface:defaultSystemPrint: Testing defaultMeethodInInterface "+text+" "+anotherText);
    }

    public static void testingStaticMethod(String parameter1,String paameter2){
        System.out.println("MyInterface:testingStaticMethod: Testing static method "+parameter1+" "+paameter2+" Age: "+age);
    }

    abstract int printIt(int parameter1,int paraeter2);
}
