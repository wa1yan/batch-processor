package dev.waiyanhtet.batch_processor.controller;

import dev.waiyanhtet.batch_processor.service.DataImportingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/data")
@RequiredArgsConstructor
public class DataImportingController {

    private final DataImportingService dataImportingService;

    @PostMapping(value = "/import")
    public ResponseEntity<?> importData() {
        return new ResponseEntity<>(dataImportingService.importData(""), HttpStatus.OK);
    }
}
