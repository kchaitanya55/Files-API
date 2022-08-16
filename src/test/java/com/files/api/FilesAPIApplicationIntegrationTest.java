package com.files.api;

import com.files.api.contoller.FilesController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class FilesAPIApplicationIntegrationTest {
    private MockMvc mockMvc;

    @Autowired
    private FilesController filesController;

    @Test
    void filesIntegrationTest() throws Exception {

        mockMvc= MockMvcBuilders.standaloneSetup(filesController).build();

        mockMvc.perform( MockMvcRequestBuilders
                        .get("/search")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
