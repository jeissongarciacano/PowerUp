package com.powerup.user.infraestructure.security;

import com.powerup.user.application.dto.UserRequest;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class securityTest {
    @Autowired
    private MockMvc mvc;

    private UserRequest userRequest(){
    return null;
    }

    @WithMockUser(username = "tets@gmail.com", roles = "ROLE_ADMIN")
    @Test
    public void testAdminEndpoint() throws Exception {
        mvc.perform(get("/api/v1/admin/createOwner").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

}
