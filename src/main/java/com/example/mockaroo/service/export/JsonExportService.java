package com.example.mockaroo.service.export;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import tools.jackson.databind.ObjectMapper;

@Service
public class JsonExportService {

    public String exportToJson(
            List<Map<String, Object>> data
    ) {

        try {

            ObjectMapper mapper = new ObjectMapper();

            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(data);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}