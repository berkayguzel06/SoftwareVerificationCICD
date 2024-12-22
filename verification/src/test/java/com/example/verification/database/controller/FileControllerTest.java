package com.example.verification.database.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.verification.database.model.File;
import com.example.verification.database.services.FileService;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class FileControllerTest {

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    private FileController fileController;

    @Mock
    private FileService fileService;

    @Mock
    private File file;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(fileController).build();
    }

    @Test
    public void testFindAllFiles() throws Exception {
        File file1 = new File("path/to/file1", "File 1");
        File file2 = new File("path/to/file2", "File 2");
        file1.setId(1);
        file2.setId(2);
        
        when(fileService.getAllFiles()).thenReturn(Arrays.asList(file1, file2));

        mockMvc.perform(get("/file"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].content").value("File 2"));
    }

    @Test
    public void testGetFileById() throws Exception {
        File file = new File("path/to/file1", "File 1");
        file.setId(1);
        
        when(fileService.getFilebyId(1)).thenReturn(file);

        mockMvc.perform(get("/file/id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.content").value("File 1"));
    }
/*
 
@Test
public void testCreateFile() throws Exception {
    File file = new File("path/to/newfile", "New File");
    file.setId(1);
    
    when(fileService.create(any(File.class))).thenReturn(file);
    
    mockMvc.perform(post("/file")
    .contentType("application/json")
    .content("{\"content\": \"New File\", \"path\": \"path/to/newfile\"}"))
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.content").value("New File"));
    }
    
    @Test
    public void testUpdateFile() throws Exception {
        File file = new File("path/to/updatedfile", "Updated File");
        file.setId(1);
        
        when(fileService.updateFile(any(File.class))).thenReturn(file);
        
        mockMvc.perform(put("/file")
        .contentType("application/json")
        .content("{\"id\": 1, \"content\": \"Updated File\", \"path\": \"path/to/updatedfile\"}"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content").value("Updated File"));
    }
    
    */
    @Test
    public void testDeleteFile() throws Exception {
        File file = new File("path/to/filetodelete", "File to Delete");
        file.setId(1);
                
                when(fileService.deleteFile(1)).thenReturn(file);
                
        mockMvc.perform(delete("/file/sha/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.content").value("File to Delete"));
    }
}
