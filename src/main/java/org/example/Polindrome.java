package org.example;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

//Слово полидром
public class Polindrome implements Callable {
    private AtomicInteger atomicInteger;
    private String text;

    Polindrome(AtomicInteger atomicInteger, String text) {
        this.text = text;
        this.atomicInteger = atomicInteger;
    }

    @Override
    public Object call() throws Exception {
        char[] arrayOfChar = text.toCharArray();
        for (int i = 0; i < arrayOfChar.length; i++) {
            if (arrayOfChar[i] != arrayOfChar[arrayOfChar.length - 1 - i]) {
                break;
            }
            if ((i - (arrayOfChar.length - 1 - i)) >= 0) {
                atomicInteger.incrementAndGet();
                break;
            }
        }
        return null;
    }
}
