package com.example.mockaroo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mockaroo.model.GenerateRequest;
import com.example.mockaroo.service.DataGenerationService;
import com.example.mockaroo.service.export.CsvExportService;
import com.example.mockaroo.service.export.ExcelExportService;
import com.example.mockaroo.service.export.JsonExportService;
import com.example.mockaroo.service.export.SqlExportService;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/data")
@CrossOrigin("*")
public class DataController {

    @Autowired
    private DataGenerationService dataGenerationService;

    @PostMapping("/generate")
    public List<Map<String, Object>> generateData(
            @RequestBody GenerateRequest request
    ) {

        return dataGenerationService.generateData(request);
    }
    
    @Autowired
    private CsvExportService csvExportService;

    @Autowired
    private JsonExportService jsonExportService;

    @Autowired
    private SqlExportService sqlExportService;

    @Autowired
    private ExcelExportService excelExportService;
    
    
    
    @PostMapping("/export")
    public ResponseEntity<?> exportData(
            @RequestBody GenerateRequest request
    ) {

        List<Map<String, Object>> data =
                dataGenerationService.generateData(request);

        String format = request.getFormat();

        switch (format.toUpperCase()) {

            case "CSV":

                String csv =
                        csvExportService.exportToCsv(data);

                return ResponseEntity.ok()
                        .header(
                                HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=data.csv"
                        )
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(csv);

            case "SQL":

                String sql =
                        sqlExportService.exportToSql(
                                data,
                                "users"
                        );

                return ResponseEntity.ok()
                        .header(
                                HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=data.sql"
                        )
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(sql);

            case "JSON":

                String json =
                        jsonExportService.exportToJson(data);

                return ResponseEntity.ok()
                        .header(
                                HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=data.json"
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(json);

            case "EXCEL":

                byte[] excel =
                        excelExportService.exportToExcel(data);

                return ResponseEntity.ok()
                        .header(
                                HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=data.xlsx"
                        )
                        .contentType(
                                MediaType.APPLICATION_OCTET_STREAM
                        )
                        .body(excel);

            default:

                return ResponseEntity.badRequest()
                        .body("Invalid Format");
        }
    }
}