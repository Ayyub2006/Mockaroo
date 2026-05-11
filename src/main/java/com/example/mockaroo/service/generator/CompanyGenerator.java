package com.example.mockaroo.service.generator;

import com.example.mockaroo.model.FieldType;
import com.example.mockaroo.util.FakerUtil;
import org.springframework.stereotype.Component;

@Component
public class CompanyGenerator
        implements DataGenerator {

    @Override
    public FieldType getFieldType() {

        return FieldType.COMPANY;
    }

    @Override
    public Object generate() {

        return FakerUtil.faker.company()
                .name();
    }
}
