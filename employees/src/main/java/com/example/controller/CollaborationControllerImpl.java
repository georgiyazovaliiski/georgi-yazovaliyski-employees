package com.example.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.CollaboratorDTO;
import com.example.processing.CSVProcessor;

@RestController
public class CollaborationControllerImpl implements CollaborationController {
    private final CSVProcessor CSVProcessor;

    @Autowired
    public CollaborationControllerImpl(CSVProcessor CSVProcessor) {
        this.CSVProcessor = CSVProcessor;
    }

    @GetMapping("/employees")
    public String ProcessCSV(Model model) {
        return "employees";
    }

    @PostMapping("/employees")
    public ResponseEntity<CollaboratorDTO> ProcessCSV(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String[] fileNameParts = fileName.split("\\.");

        if (fileNameParts.length != 2 || !fileNameParts[fileNameParts.length - 1].equals("csv") || file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            return ResponseEntity.ok(CSVProcessor.Process(file.getInputStream()));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
