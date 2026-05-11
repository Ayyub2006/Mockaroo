package com.example.mockaroo.service.generator;

import com.example.mockaroo.model.FieldType;
import com.example.mockaroo.util.FakerUtil;
import org.springframework.stereotype.Component;

@Component
public class LastNameGenerator
        implements DataGenerator {

    @Override
    public FieldType getFieldType() {

        return FieldType.LAST_NAME;
    }

    @Override
    public Object generate() {

        return FakerUtil.faker.name().lastName();
    }
}