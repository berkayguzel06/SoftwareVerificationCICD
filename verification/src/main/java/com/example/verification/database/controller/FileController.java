package com.example.verification.database.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.verification.database.model.File;
import com.example.verification.database.services.FileService;

@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;
    
    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    // To get authenticated user files
    @GetMapping(value = "/api/auth/file", produces = "application/json")
    public ResponseEntity<List<File>> findFileByUsername(String username) {
        List<File> files = fileService.findByUsernameAndRepositoryAndBranch(username);
        return ResponseEntity.ok(files);
    }

    @GetMapping(produces="application/json")
    public ResponseEntity<List<File>> findAllFiles(){
        List<File> files = fileService.getAllFiles();
        return ResponseEntity.ok(files);
    }
    
    @GetMapping(value="/id/{id}", produces="application/json")
    public ResponseEntity<File> getFilebyId(@PathVariable("id") Integer id){
        File file = fileService.getFilebyId(id);
        return file != null ? ResponseEntity.ok(file) : ResponseEntity.notFound().build();
    }

    @PostMapping(consumes="application/json", produces="application/json")
    public ResponseEntity<File> createFile(@RequestBody File file){
        File createdFile = fileService.create(file);
        return ResponseEntity.ok(createdFile);
    }

    @PutMapping(consumes="application/json", produces="application/json")
    public ResponseEntity<File> updateFile(@RequestBody File newFileData){
        File updateFile = fileService.updateFile(newFileData);
        return ResponseEntity.ok(updateFile);
    }

    @DeleteMapping("/sha/{id}")
    public ResponseEntity<File> deleteFile(@PathVariable("id") Integer id) {
        File deletedFile = fileService.deleteFile(id);
        return ResponseEntity.ok(deletedFile);
    }
}
