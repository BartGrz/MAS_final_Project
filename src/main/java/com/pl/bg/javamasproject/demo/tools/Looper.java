package com.pl.bg.javamasproject.demo.tools;

public class Looper {
    /**
     * simple loop (for) created with functional interface. Method looperFor have parameter of Integer type which represents i in normal loop
     * @param beginIndex -> loop starts at
     * @param collectionSize -> length of loop
     * @param looperFor -> functional interface
     */
    public static void forLoop(int beginIndex,int collectionSize, LooperFor looperFor){

        for (int i = beginIndex;i<collectionSize;i++) {
            looperFor.looperFor(i);
        }

    }

}
