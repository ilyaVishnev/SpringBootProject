package com.avito;

import com.DAO.services.BrandService;
import com.cars_annot.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(FilterForList.class)
public class FilterForListTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private BrandService brandService;

    @Test
    public void whenPutFilterThenPage() throws Exception {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("bmw");
        given(brandService.findAll()).willReturn(Arrays.asList(brand));
        this.mvc.perform(post("/filterFlist").param("brands", "2")
                .param("photo", "on")
                .param("today", "on").with(csrf())
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("list"));
        mvc.perform(get("/filterFlist")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.array[0].name").value("bmw"))
                .andExpect(jsonPath("$.array[0].id").value("1"))
                .andExpect(jsonPath("$.idBrand").value("2"))
                .andExpect(jsonPath("$.havePhoto").value("on"))
                .andExpect(jsonPath("$.today").value("on"));
    }
}
