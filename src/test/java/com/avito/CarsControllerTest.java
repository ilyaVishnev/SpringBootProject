package com.avito;

import com.DAO.services.*;
import com.cars_annot.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.persistence.Table;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(CarsController.class)
public class CarsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ModelService modelService;
    @MockBean
    private BrandService brandService;
    @MockBean
    private GearboxService gearboxService;
    @MockBean
    private EngineService engineService;
    @MockBean
    private CarBodyService carBodyService;
    @MockBean
    private YearService yearService;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenListThenMenu() throws Exception {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("bmw");
        List<Brand> brands = Arrays.asList(brand);
        given(brandService.findAll()).willReturn(brands);
        Model model = new Model();
        model.setId(1);
        model.setName("01");
        model.setBrand(brand);
        List<Model> models = Arrays.asList(model);
        given(modelService.findAll()).willReturn(models);
        Year year = new Year();
        year.setYear(1996);
        List<Year> years = Arrays.asList(year);
        given(yearService.findAll()).willReturn(years);
        Gearbox gearbox = new Gearbox();
        gearbox.setId(1);
        gearbox.setDescription("gb1");
        gearbox.setModel(model);
        gearbox.setYear(1996);
        List<Gearbox> gearboxes = Arrays.asList(gearbox);
        given(gearboxService.findAll()).willReturn(gearboxes);
        CarBody carBody = new CarBody();
        carBody.setId(1);
        carBody.setDescription("cb1");
        carBody.setModel(model);
        carBody.setYear(1996);
        List<CarBody> carBodies = Arrays.asList(carBody);
        given(carBodyService.findAll()).willReturn(carBodies);
        Engine engine = new Engine();
        engine.setId(1);
        engine.setDescription("eng1");
        engine.setModel(model);
        engine.setYear(1996);
        List<Engine> engines = Arrays.asList(engine);
        given(engineService.findAll()).willReturn(engines);
        mvc.perform(get("/cars")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandArray[0].id").value("1"))
                .andExpect(jsonPath("$.brandArray[0].name").value("bmw"))
                .andExpect(jsonPath("$.modelArray[0].id").value("1"))
                .andExpect(jsonPath("$.modelArray[0].name").value("01"))
                .andExpect(jsonPath("$.modelArray[0].brand.id").value("1"))
                .andExpect(jsonPath("$.gearboxArray[0].id").value("1"))
                .andExpect(jsonPath("$.gearboxArray[0].description").value("gb1"))
                .andExpect(jsonPath("$.gearboxArray[0].model.id").value("1"))
                .andExpect(jsonPath("$.gearboxArray[0].year").value("1996"))
                .andExpect(jsonPath("$.engineArray[0].id").value("1"))
                .andExpect(jsonPath("$.engineArray[0].description").value("eng1"))
                .andExpect(jsonPath("$.engineArray[0].model.id").value("1"))
                .andExpect(jsonPath("$.engineArray[0].year").value("1996"))
                .andExpect(jsonPath("$.carbodyArray[0].id").value("1"))
                .andExpect(jsonPath("$.carbodyArray[0].description").value("cb1"))
                .andExpect(jsonPath("$.carbodyArray[0].model.id").value("1"))
                .andExpect(jsonPath("$.carbodyArray[0].year").value("1996"));

    }

}
