package com.capulus;

public class InnerClass {
    int testData=20;
    static class InnerClassLevel1Example1{
        class InnerClassLevel2Example2{

        }
        void staticDoIt(){
            System.out.println("InnerClassLevel1Example1:staticDoIt: Hello");
        }
    }
    class InnerClassLeve1Example2{
        class InnerClassLevel2Example2{
            void doIt(){
                System.out.println("InnerClassLevel2Example2:doIt: Testing inner Class Before"+testData);
                testData=30;
                System.out.println("InnerClassLevel2Example2:doIt: Testing inner Class After"+testData);
            }
        }

        void outDoIt(){
            System.out.println("InnerClassLeve1Example2:outDoIt: Testing outer "+testData);
        }
    }
}
