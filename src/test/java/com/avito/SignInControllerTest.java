package com.avito;

import com.DAO.services.HolderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(SignInController.class)
public class SignInControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    MockHttpServletRequest request;

    @Test
    public void whenSignThenGetView() throws Exception {
        this.mvc.perform(get("/sign").with(request -> {
                    return request;
                })
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("signIn"));
        this.mvc.perform(get("/sign").param("error", "error").with(request -> {
                    return request;
                })
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("signIn"));
        this.mvc.perform(get("/sign").param("logout", "logout").with(request -> {
                    return request;
                })
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("list"));
    }
}
