package com.github.enesusta;

import com.github.enesusta.generator.DataGenerator;
import com.github.enesusta.lookup.JsonReference;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LookupService {

    private final DataGenerator dataGenerator;

    public LookupService(DataGenerator dataGenerator) {
        this.dataGenerator = dataGenerator;
    }

    public String read(int n) {
        try (InputStream inputStream = new BufferedInputStream(Files.newInputStream(Paths.get("data.txt")))) {
            JsonReference jsonReference = dataGenerator.getN(n);
            int offset = jsonReference.getOffset();
            int length = jsonReference.getLength();

            inputStream.skip(offset + n);
            byte[] bytes = inputStream.readNBytes(length);

            return new String(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
