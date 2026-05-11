package com.example.mockaroo.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.mockaroo.model.GenerateRequest;
import com.example.mockaroo.model.SchemaField;
import com.example.mockaroo.util.FakerUtil;

import com.example.mockaroo.model.GenerateRequest;
import com.example.mockaroo.model.SchemaField;
import com.example.mockaroo.service.generator.DataGenerator;
import com.example.mockaroo.service.resolver.GeneratorResolver;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DataGenerationService {

    private final GeneratorResolver resolver;

    public DataGenerationService(
            GeneratorResolver resolver
    ) {

        this.resolver = resolver;
    }

    public List<Map<String, Object>>
    generateData(GenerateRequest request) {

        List<Map<String, Object>> result =
                new ArrayList<>();

        for (int i = 0; i < request.getRows(); i++) {

            Map<String, Object> row =
                    new LinkedHashMap<>();

            for (SchemaField field :
                    request.getFields()) {

                DataGenerator generator =
                        resolver.resolve(
                                field.getType()
                        );

                Object value =
                        generator.generate();

                row.put(
                        field.getName(),
                        value
                );
            }

            result.add(row);
        }

        return result;
    }
}
