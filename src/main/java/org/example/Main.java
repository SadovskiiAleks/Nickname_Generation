package org.example;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static AtomicInteger withLengthThree = new AtomicInteger(0);
    public static AtomicInteger withLengthFour = new AtomicInteger(0);
    public static AtomicInteger withLengthFive = new AtomicInteger(0);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        //Определить пул потоков
        final ExecutorService threadPool = Executors.newFixedThreadPool(100);

        for (String text : texts) {
            switch (text.length()) {
                case (3):
                    submitPoll(threadPool, withLengthThree, text);
                    break;
                case (4):
                    submitPoll(threadPool, withLengthFour, text);
                    break;
                case (5):
                    submitPoll(threadPool, withLengthFive, text);
                    break;
            }
        }
        threadPool.shutdown();
        System.out.printf("Красивых слов с длиной 3: %d шт \n", withLengthThree.get());
        System.out.printf("Красивых слов с длиной 4: %d шт \n", withLengthFour.get());
        System.out.printf("Красивых слов с длиной 5: %d шт \n", withLengthFive.get());
    }

    public static void submitPoll(ExecutorService threadPool, AtomicInteger atomicInteger, String text) throws ExecutionException, InterruptedException {
        threadPool.submit(new LettersUp(atomicInteger, text)).get();
        threadPool.submit(new OneOfLetters(atomicInteger, text)).get();
        threadPool.submit(new Polindrome(atomicInteger, text)).get();
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }


}