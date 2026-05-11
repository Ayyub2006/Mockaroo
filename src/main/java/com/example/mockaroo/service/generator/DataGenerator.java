package com.example.mockaroo.service.generator;

import com.example.mockaroo.model.FieldType;

public interface DataGenerator {

    FieldType getFieldType();

    Object generate();
}
