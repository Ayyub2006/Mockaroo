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

@Service
public class DataGenerationService {

    public List<Map<String, Object>> generateData(
            GenerateRequest request
    ) {

        List<Map<String, Object>> data = new ArrayList<>();

        for (int i = 0; i < request.getRows(); i++) {

            Map<String, Object> row = new LinkedHashMap<>();

            for (SchemaField field : request.getFields()) {

                row.put(
                        field.getName(),
                        generateValue(field.getType())
                );
            }

            data.add(row);
        }

        return data;
    }

    private Object generateValue(String type) {

        switch (type.toUpperCase()) {

            case "FIRST_NAME":
                return FakerUtil.faker.name().firstName();

            case "LAST_NAME":
                return FakerUtil.faker.name().lastName();

            case "FULL_NAME":
                return FakerUtil.faker.name().fullName();

            case "EMAIL":
                return FakerUtil.faker.internet().emailAddress();

            case "PHONE":
                return FakerUtil.faker.phoneNumber().cellPhone();

            case "CITY":
                return FakerUtil.faker.address().city();

            case "COUNTRY":
                return FakerUtil.faker.address().country();

            case "ADDRESS":
                return FakerUtil.faker.address().fullAddress();

            case "COMPANY":
                return FakerUtil.faker.company().name();

            case "UUID":
                return UUID.randomUUID().toString();

            case "BOOLEAN":
                return new Random().nextBoolean();

            case "DATE":
                return FakerUtil.faker.date().birthday();

            default:
                return "UNKNOWN_TYPE";
        }
    }
}
