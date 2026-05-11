package com.example.mockaroo.service.resolver;

import com.example.mockaroo.model.FieldType;
import com.example.mockaroo.service.generator.DataGenerator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GeneratorResolver {

    private final Map<FieldType, DataGenerator>
            generatorMap = new HashMap<>();

    public GeneratorResolver(
            List<DataGenerator> generators
    ) {

        for (DataGenerator generator : generators) {

            generatorMap.put(
                    generator.getFieldType(),
                    generator
            );
        }
    }

    public DataGenerator resolve(
            FieldType type
    ) {

        return generatorMap.get(type);
    }
}
