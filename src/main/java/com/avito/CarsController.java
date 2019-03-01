package com.avito;


import com.DAO.services.*;
import com.cars_annot.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.annotation.JsonView;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializationConfig;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.web.bind.annotation.GetMapping;*/
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;


@RestController
@RequestMapping("/cars")
public class CarsController {

    @Autowired
    private ModelService modelService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private GearboxService gearboxService;
    @Autowired
    private EngineService engineService;
    @Autowired
    private CarBodyService carBodyService;
    @Autowired
    private YearService yearService;

    @JsonView(Views.Public.class)
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    protected JSONObject doGet() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonSend = new JSONObject();
        mapper.configure(JsonParser.Feature.IGNORE_UNDEFINED, true);
        String brands = mapper.writerWithView(Views.Public.class).writeValueAsString(brandService.findAll());
        JSONParser parser = new JSONParser();
        JSONArray brandArray = (JSONArray) parser.parse(brands);
        jsonSend.put("brandArray", brandArray);
        String models = mapper.writerWithView(Views.Public.class).writeValueAsString(modelService.findAll());
        JSONArray modelArray = (JSONArray) parser.parse(models);
        jsonSend.put("modelArray", modelArray);
        String gearboxs = mapper.writerWithView(Views.Public.class).writeValueAsString(gearboxService.findAll());
        JSONArray gearboxArray = (JSONArray) parser.parse(gearboxs);
        jsonSend.put("gearboxArray", gearboxArray);
        String engines = mapper.writerWithView(Views.Public.class).writeValueAsString(engineService.findAll());
        JSONArray engineArray = (JSONArray) parser.parse(engines);
        jsonSend.put("engineArray", engineArray);
        String carbodies = mapper.writerWithView(Views.Public.class).writeValueAsString(carBodyService.findAll());
        JSONArray carbodyArray = (JSONArray) parser.parse(carbodies);
        jsonSend.put("carbodyArray", carbodyArray);
        String years = mapper.writerWithView(Views.Public.class).writeValueAsString(yearService.findAll());
        JSONArray yearsArray = (JSONArray) parser.parse(years);
        jsonSend.put("yearsArray", yearsArray);
        return jsonSend;
    }
}
