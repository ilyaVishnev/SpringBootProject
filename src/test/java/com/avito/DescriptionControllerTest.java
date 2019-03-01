package com.avito;

import com.DAO.services.CarService;
import com.cars_annot.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.BDDMockito.*;


@RunWith(SpringRunner.class)
@WebMvcTest(DescriptionController.class)
public class DescriptionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    CarService carService;

    @Test
    public void whenPutCarIdThenPage() throws Exception {
        Car car = new Car();
        car.setId(1);
        car.setDescription("mycar");
        CarBody carBody = new CarBody();
        carBody.setDescription("cb");
        car.setCarBody(carBody);
        Engine engine = new Engine();
        engine.setDescription("en");
        car.setEngine(engine);
        Gearbox gearbox = new Gearbox();
        gearbox.setDescription("gb");
        car.setGearbox(gearbox);
        Holder holder = new Holder();
        holder.setId(1);
        car.setHolder(holder);
        car.setPrice(14);
        car.setStatus(true);
        car.setPhoto("pic");
        given(carService.findById(1)).willReturn(car);
        this.mvc.perform(post("/desc").param("carId", "1").with(csrf())
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("description"));
        mvc.perform(get("/desc")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("mycar"))
                .andExpect(jsonPath("$.carBody.description").value("cb"))
                .andExpect(jsonPath("$.engine.description").value("en"))
                .andExpect(jsonPath("$.gearbox.description").value("gb"))
                .andExpect(jsonPath("$.photo").value("pic"))
                .andExpect(jsonPath("$.price").value("14"))
                .andExpect(jsonPath("$.status").value("true"))
                .andExpect(jsonPath("$.holder.id").value("1"))
                .andExpect(jsonPath("$.id").value("1"));
    }
}
