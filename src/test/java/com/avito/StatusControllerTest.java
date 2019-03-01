package com.avito;

import com.DAO.services.CarService;
import com.cars_annot.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(StatusController.class)
public class StatusControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    CarService carService;

    @Test
    public void whenSetStatusThenChange() throws Exception {
        Car car = new Car();
        car.setId(1);
        car.setStatus(false);
        car.setPrice(17);
        given(carService.findById(1)).willReturn(car);
        this.mvc.perform(post("/status").with(csrf())
                .param("id", "1")
                .param("status", "true")
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("description"));
        Car mycar = new Car();
        mycar.setId(1);
        mycar.setStatus(true);
        mycar.setPrice(17);
        verify(this.carService, times(1)).save(mycar);
    }
}
