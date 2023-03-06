package org.example;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

//Поиск букв по возрастанию
public class LettersUp implements Callable {
    private AtomicInteger atomicInteger;
    private String text;

    LettersUp(AtomicInteger atomicInteger, String text) {
        this.text = text;
        this.atomicInteger = atomicInteger;
    }

    @Override
    public Object call() throws Exception {
        char[] arrayOfChar = text.toCharArray();
        int charNumber = arrayOfChar[0];

        for (int i = 0; i < arrayOfChar.length - 1; i++) {
            if (charNumber == (int) arrayOfChar[i + 1]) {
                if (i == arrayOfChar.length - 2) {
                    atomicInteger.incrementAndGet();
                }
            } else if (((int) arrayOfChar[i] + 1) == ((int) arrayOfChar[i + 1])) {
                if (i == arrayOfChar.length - 2) {
                    atomicInteger.incrementAndGet();
                }
                charNumber = (int) arrayOfChar[i + 1];
            } else {
                break;
            }
        }
        return null;
    }
}
