package com.files.api.controller;

import com.files.api.contoller.FilesController;
import com.files.api.model.FileDetails;
import com.files.api.services.FilesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class FilesControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private FilesController filesController;

    @Mock
    private FilesService filesService;


    @BeforeEach // For Junit5
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(filesController).build();

    }

    @Test
    void getFileDetails() throws Exception {
        List<FileDetails> fileDetailsList=new ArrayList<>();
        FileDetails fileDetails=new FileDetails();
        fileDetailsList.add(fileDetails);

        when(filesController.getFileDetails(null,null,null,null)).thenReturn(fileDetailsList);

        mockMvc.perform( MockMvcRequestBuilders
                        .get("/search")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
