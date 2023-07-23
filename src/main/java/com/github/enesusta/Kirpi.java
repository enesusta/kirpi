package com.github.enesusta;


import com.github.enesusta.generator.DataGenerator;
import io.javalin.Javalin;

public class Kirpi {
    public static void main(String[] args) {
        DataGenerator dataGenerator = new DataGenerator();
        dataGenerator.generate();

        LookupService lookupService = new LookupService(dataGenerator);

        var app = Javalin.create(/*config*/)
                .get("/{id}", ctx -> {
                    int id = Integer.parseInt(ctx.pathParam("id"));
                    String[] arr = new String[5];

                    for (int i = 0; i < 5; i++) {
                        arr[i] = lookupService.read(i);
                    }
//                    String read = lookupService.read(id);
                    ctx.json(arr);
                })
                .start(8080);
    }
}
