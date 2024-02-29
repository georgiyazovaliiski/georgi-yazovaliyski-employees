package com.example.processing;

import java.io.InputStream;

import com.example.dto.CollaboratorDTO;

public interface CSVProcessor {
    CollaboratorDTO Process(InputStream FileInformation);
}
