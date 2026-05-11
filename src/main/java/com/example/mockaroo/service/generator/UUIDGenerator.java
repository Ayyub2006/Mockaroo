package com.example.mockaroo.service.generator;

import com.example.mockaroo.model.FieldType;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDGenerator
        implements DataGenerator {

    @Override
    public FieldType getFieldType() {

        return FieldType.UUID;
    }

    @Override
    public Object generate() {

        return UUID.randomUUID().toString();
    }
}
