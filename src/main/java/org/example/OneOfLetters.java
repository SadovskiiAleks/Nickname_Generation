package org.example;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

//Словосостоит из одной буквы
public class OneOfLetters implements Callable {
    private AtomicInteger atomicInteger;
    private String text;
    OneOfLetters(AtomicInteger atomicInteger, String text) {
        this.atomicInteger = atomicInteger;
        this.text = text;
    }

    @Override
    public Object call() {
        char[] arrayOfChar = text.toCharArray();
        for (int i = 0; i < arrayOfChar.length - 1; i++) {
            if (arrayOfChar[i] == arrayOfChar[i+1]){
                if (i + 2 == arrayOfChar.length){
                    atomicInteger.incrementAndGet();
                }
            } else break;
        }
        return null;
    }
}
