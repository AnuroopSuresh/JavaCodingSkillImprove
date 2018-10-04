package com.capulus;

public class Expirement {
    int a;
    int b;

    @Override
    public String toString() {
        return "Expirement{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }

    public  Expirement(int a, int b){
        this.a=a;
        this.b=b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
