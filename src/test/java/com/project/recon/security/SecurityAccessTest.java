package com.project.recon.security;

import com.project.recon.controller.BatchController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BatchController.class)
public class SecurityAccessTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "ADMIN")
    public void adminCanRunBatch() throws Exception {
        mockMvc.perform(post("/batch/run")
                        .contentType("application/json")
                        .content("{\"fileId\":1}"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "VIEWER")
    public void viewerCannotRunBatch() throws Exception {
        mockMvc.perform(post("/batch/run")
                        .contentType("application/json")
                        .content("{\"fileId\":1}"))
                .andExpect(status().isForbidden());
    }
}
