package com.github.enesusta;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Kirpi {
    public static void main(String[] args) throws InterruptedException {

        try (InputStream inputStream = new BufferedInputStream(Files.newInputStream(Paths.get("data.txt")))) {

            byte[] bytes = inputStream.readNBytes(61);
            System.out.println("bytes = " + new String(bytes));

            inputStream.skip(1);

            byte[] bytes1 = inputStream.readNBytes(91);
            System.out.println("bytes1 = " + new String(bytes1));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Thread.currentThread().join();
    }
}
