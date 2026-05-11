package com.example.mockaroo.service.export;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class CsvExportService {

    public String exportToCsv(
            List<Map<String, Object>> data
    ) {

        if (data.isEmpty()) {
            return "";
        }

        StringBuilder csv = new StringBuilder();

        // Header

        Map<String, Object> firstRow = data.get(0);

        for (String key : firstRow.keySet()) {
            csv.append(key).append(",");
        }

        csv.deleteCharAt(csv.length() - 1);

        csv.append("\n");

        // Data

        for (Map<String, Object> row : data) {

            for (Object value : row.values()) {

                csv.append(value).append(",");
            }

            csv.deleteCharAt(csv.length() - 1);

            csv.append("\n");
        }

        return csv.toString();
    }
}
