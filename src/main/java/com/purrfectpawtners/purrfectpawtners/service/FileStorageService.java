package com.purrfectpawtners.purrfectpawtners.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

// handles file storage operations within a Spring application
@Service
public class FileStorageService {

    // Declaration of file storage location
    private final Path fileStorageLocation;

    // Constructor to initialize file storage location ensures the availability of the designed storage directory before any file storage operations take place 
    public FileStorageService() {
        // creates a Path object representing the intended directory to store files 
        this.fileStorageLocation = Paths.get("src/main/resources/static/public/uploads")
                // converts to an absolute path and normalises the path, removing redundant elements like // or ".."
                .toAbsolutePath().normalize();

        // Creating directory for file storage if needed
        try {
            // create the directory if it doesnt exist
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    // Method to store a file
    // MultipartFile as parameter - an uploaded file 
    public String storeFile(MultipartFile file) {
        // cleans up original file name to prevent potential path traversal attacks 
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // validates file name
        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
}
