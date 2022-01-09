package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationITests {

    @Autowired
    transient MockMvc mockMvc;

    @Test
    void getAllUsers() throws Exception {
        MvcResult response = mockMvc.perform(get("/")).andReturn();

        assertEquals("Hello World!", response.getResponse().getContentAsString());
    }
}
