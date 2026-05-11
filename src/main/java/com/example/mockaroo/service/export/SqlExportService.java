package com.example.mockaroo.service.export;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class SqlExportService {

    public String exportToSql(
            List<Map<String, Object>> data,
            String tableName
    ) {

        StringBuilder sql = new StringBuilder();

        for (Map<String, Object> row : data) {

            sql.append("INSERT INTO ")
                    .append(tableName)
                    .append(" (");

            for (String column : row.keySet()) {

                sql.append(column).append(",");
            }

            sql.deleteCharAt(sql.length() - 1);

            sql.append(") VALUES (");

            for (Object value : row.values()) {

                sql.append("'")
                        .append(value)
                        .append("',");
            }

            sql.deleteCharAt(sql.length() - 1);

            sql.append(");\n");
        }

        return sql.toString();
    }
}