package com.github.enesusta;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.enesusta.model.Employee;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Kirpi {
    public static void main(String[] args) throws InterruptedException, IOException {

        EasyRandomParameters parameters = new EasyRandomParameters();
        EasyRandom generator = new EasyRandom(parameters);
        ObjectMapper objectMapper = new ObjectMapper();

        try (OutputStream outputStream = new FileOutputStream("data.txt")) {
            for (int i = 0; i < 15; i++) {
                Employee employee = generator.nextObject(Employee.class);
                String s = objectMapper.writeValueAsString(employee);
                outputStream.write((s + "\n").getBytes());
            }
        }

//        try (InputStream inputStream = new BufferedInputStream(Files.newInputStream(Paths.get("data.txt")))) {
//            byte[] bytes = inputStream.readNBytes(61);
//            System.out.println("bytes = " + new String(bytes));
//
//            inputStream.skip(1);
//
//            byte[] bytes1 = inputStream.readNBytes(91);
//            System.out.println("bytes1 = " + new String(bytes1));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        Thread.currentThread().join();
    }
}
