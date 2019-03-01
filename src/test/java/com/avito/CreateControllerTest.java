package com.avito;

import com.DAO.services.CarService;
import com.DAO.services.HolderService;
import com.cars_annot.Car;
import com.cars_annot.CarForm;
import com.cars_annot.Holder;
import com.cars_annot.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.*;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(CreateController.class)
public class CreateControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarService carService;

    @MockBean
    private HolderService holderService;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenCreateThenSetSessionAtr() throws Exception {
        Role role = new Role();
        role.setId(1);
        role.setRole("ROLE_ADMIN");
        Holder holder = new Holder();
        holder.setLogin("user");
        holder.setPassword("password");
        holder.setRoles(Arrays.asList(role));
        given(holderService.findByLogin("user")).willReturn(holder);
        MvcResult mvcResult = this.mvc.perform(get("/create").accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("createCar")
        ).andReturn();
        MockHttpSession session = (MockHttpSession) mvcResult.getRequest().getSession();
        ;
        assertThat(holder, is(session.getAttribute("user")));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenSaveCarThenSaving() throws Exception {
        CarForm carForm = new CarForm();
        carForm.setPrice(12);
        this.mvc.perform(post("/create").flashAttr("car", carForm).with(csrf()))
                .andExpect(
                        status().isOk()
                ).andExpect(
                view().name("createCar")
        );
        Car car = new Car();
        car.setPrice(12);
        verify(this.carService, times(1)).save(car);
    }
}
