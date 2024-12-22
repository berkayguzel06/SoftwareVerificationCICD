package com.example.verification.database.services;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.verification.database.model.File;
import com.example.verification.database.repository.FileCrud;

@Service
public class FileService {
    
    private final FileCrud fileRepository;

    @Autowired
    public FileService(FileCrud fileRepository){
        this.fileRepository = fileRepository;
    }

    @Transactional(readOnly = true)
    public List<File> getAllFiles(){
        Iterable<File> fileIterable = fileRepository.findAll();
        List<File> files = StreamSupport.stream(fileIterable.spliterator(), false)
                                             .toList();
        return files;
    }

    @Transactional(readOnly = true)
    public File getFilebyId(Integer id){
        File file = fileRepository.findById(id).orElse(null);
        return file;
    }

    @Transactional
    public File create(File file) {
        return fileRepository.save(file);
    }

    @Transactional
    public File deleteFile(Integer id){
        File file = fileRepository.findById(id).orElse(null);
        fileRepository.delete(file);
        return file;
    }

    @Transactional
    public File updateFileById(Integer id, File newFileData){
        File file = fileRepository.findById(id).orElse(null);
        file.setPath(newFileData.getPath());
        file.setContent(newFileData.getContent());
        return fileRepository.save(file);
    }

    @Transactional
    public File updateFile(File file){
        File updatedFile = fileRepository.findById(file.getId()).orElse(null);
        updatedFile.setContent(file.getContent());
        updatedFile.setPath(file.getPath());
        return fileRepository.save(updatedFile);
    }
}