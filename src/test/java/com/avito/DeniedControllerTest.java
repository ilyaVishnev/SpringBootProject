package com.avito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(DeniedController.class)
public class DeniedControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void whenDeniedThenPage() throws Exception {
        mvc.perform(post("/deniedPage").accept(MediaType.TEXT_HTML).with(csrf())
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("deniedPage"));
    }
}
