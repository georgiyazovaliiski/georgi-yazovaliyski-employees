package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.CollaboratorDTO;

public interface CollaborationController {
    ResponseEntity<CollaboratorDTO> ProcessCSV(@RequestParam("file") MultipartFile file);
}
