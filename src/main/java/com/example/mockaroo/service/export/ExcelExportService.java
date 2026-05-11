package com.example.mockaroo.service.export;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class ExcelExportService {

    public byte[] exportToExcel(
            List<Map<String, Object>> data
    ) {

        try {

            XSSFWorkbook workbook = new XSSFWorkbook();

            Sheet sheet = workbook.createSheet("Mock Data");

            if (data.isEmpty()) {
                return new byte[0];
            }

            // Header

            Row header = sheet.createRow(0);

            int headerCell = 0;

            for (String key : data.get(0).keySet()) {

                header.createCell(headerCell++)
                        .setCellValue(key);
            }

            // Data

            int rowNum = 1;

            for (Map<String, Object> map : data) {

                Row row = sheet.createRow(rowNum++);

                int colNum = 0;

                for (Object value : map.values()) {

                    row.createCell(colNum++)
                            .setCellValue(
                                    String.valueOf(value)
                            );
                }
            }

            ByteArrayOutputStream outputStream =
                    new ByteArrayOutputStream();

            workbook.write(outputStream);

            workbook.close();

            return outputStream.toByteArray();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
