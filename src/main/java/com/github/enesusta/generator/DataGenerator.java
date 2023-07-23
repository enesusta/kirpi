package com.github.enesusta.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.enesusta.lookup.JsonReference;
import com.github.enesusta.model.Employee;
import org.atpfivt.ljv.LJV;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import vlsi.utils.CompactHashMap;

import java.awt.Desktop;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URLEncoder;

public class DataGenerator {

    private final CompactHashMap<Integer, JsonReference> map = new CompactHashMap<>();

    public void generate() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        EasyRandom generator = new EasyRandom(parameters);
        ObjectMapper objectMapper = new ObjectMapper();

        StringBuilder stringBuilder = new StringBuilder();
        int offset = 0;

        try (OutputStream outputStream = new FileOutputStream("data.txt")) {
            for (int i = 0; i < 15; i++) {
                Employee employee = generator.nextObject(Employee.class);

                String s = objectMapper.writeValueAsString(employee);
                int length = s.length();
                stringBuilder.append(s);
                stringBuilder.append("\n");

                JsonReference jsonReference = new JsonReference();
                jsonReference.setLength(length);
                jsonReference.setOffset(offset);

                map.put(i, jsonReference);
                offset += length;
                outputStream.write((s + "\n").getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public JsonReference getN(int n) {
        return map.get(n);
    }

}
