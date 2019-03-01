package com.avito;

import com.DAO.services.CarService;
import com.cars_annot.Brand;
import com.cars_annot.Car;
import com.cars_annot.Gearbox;
import com.cars_annot.Model;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(ListController.class)
public class ListControllerTest {

    @MockBean
    private CarService carService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void whenFilterThenResultOnList() throws Exception {
        this.mvc.perform(get("/list")
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("list"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idBrand", "off");
        jsonObject.put("today", "off");
        jsonObject.put("photo", "off");
        Car car = new Car();
        car.setId(1);
        Model model = new Model();
        Brand brand = new Brand();
        brand.setId(1);
        model.setName("01");
        model.setBrand(brand);
        Gearbox gearbox = new Gearbox();
        gearbox.setModel(model);
        car.setGearbox(gearbox);
        car.setPrice(15);
        car.setPhoto("pic");
        car.setStatus(true);
        LocalDate date = LocalDate.of(1996, 12, 1);
        car.setDate(date);
        given(carService.findAll()).willReturn(Arrays.asList(car));
        mvc.perform(post("/list").content(jsonObject.toString()).with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.array[0].id").value("1"))
                .andExpect(jsonPath("$.array[0].gearbox.model.name").value("01"))
                .andExpect(jsonPath("$.array[0].price").value("15"))
                .andExpect(jsonPath("$.array[0].photo").value("pic"))
                .andExpect(jsonPath("$.array[0].status").value("true"))
                .andExpect(jsonPath("$.array[0].gearbox.model.brand.id").value("1"));
    }

    @Test
    public void whenBrandThenResultOnList() throws Exception {
        this.mvc.perform(get("/list")
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("list"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idBrand", "2");
        jsonObject.put("today", "off");
        jsonObject.put("photo", "off");
        Car car = new Car();
        car.setId(1);
        Model model = new Model();
        Brand brand = new Brand();
        brand.setId(1);
        model.setName("01");
        model.setBrand(brand);
        Gearbox gearbox = new Gearbox();
        gearbox.setModel(model);
        car.setGearbox(gearbox);
        car.setPrice(15);
        car.setPhoto("pic");
        car.setStatus(true);
        LocalDate date = LocalDate.of(1996, 12, 1);
        car.setDate(date);
        Car seconDcar = new Car();
        seconDcar.setId(1);
        Model smodel = new Model();
        Brand sbrand = new Brand();
        sbrand.setId(2);
        smodel.setName("01");
        smodel.setBrand(sbrand);
        Gearbox sgearbox = new Gearbox();
        sgearbox.setModel(smodel);
        seconDcar.setGearbox(sgearbox);
        seconDcar.setPrice(15);
        seconDcar.setPhoto("pic");
        seconDcar.setStatus(true);
        LocalDate sdate = LocalDate.of(1996, 12, 1);
        seconDcar.setDate(date);
        given(carService.findAllByBrand(2)).willReturn(Arrays.asList(seconDcar));
        mvc.perform(post("/list").content(jsonObject.toString()).with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.array[0].id").value("1"))
                .andExpect(jsonPath("$.array[0].gearbox.model.name").value("01"))
                .andExpect(jsonPath("$.array[0].price").value("15"))
                .andExpect(jsonPath("$.array[0].photo").value("pic"))
                .andExpect(jsonPath("$.array[0].status").value("true"))
                .andExpect(jsonPath("$.array[0].gearbox.model.brand.id").value("2"));
    }

}
