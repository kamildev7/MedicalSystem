package com.medicalsystem.controller;

import com.medicalsystem.excel.export.ExcelExporter;
import com.medicalsystem.excel.importer.ExcelImporter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@Log
public class ExcelController {

    private final String FILE_NAME = "data.xlsx";

    private final ExcelImporter importer;
    private final ExcelExporter exporter;
    @Autowired
    public ExcelController(ExcelImporter importer, ExcelExporter exporter) {
        this.importer = importer;
        this.exporter = exporter;
    }

    /**
     * Temporary method
     *
     * @return hard-coded excel file
     */
    @GetMapping("api/export")
    public ResponseEntity<?> exportToExcel() throws IOException {
        exporter.exportToExcel();
        File file = new File("bazaEskport.xlsx");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    @PostMapping("api/import/excel")
    public ResponseEntity<?> importToDB(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty())
            return new ResponseEntity<>("Error uploading file", HttpStatus.BAD_REQUEST);

        /* Save file */
        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(FILE_NAME)))) {
            byte[] bytes = file.getBytes();
            stream.write(bytes);
            log.info("File uploaded: " + FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Import data to db */
        importer.importToDB(FILE_NAME);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
