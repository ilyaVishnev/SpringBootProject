package com.avito;

import com.DAO.services.CarService;
import com.cars_annot.Car;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.IOException;

@Controller
@RequestMapping("/desc")
public class DescriptionController {

    @Autowired
    CarService carService;

    private Car car;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    protected JSONObject getDesc() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonSend = new JSONObject();
        mapper.configure(JsonParser.Feature.IGNORE_UNDEFINED, true);
        String Strcar = mapper.writerWithView(Views.Public.class).writeValueAsString(carService.findById(car.getId()));
        JSONParser parser = new JSONParser();
        JSONObject JSONcar = (JSONObject) parser.parse(Strcar);
        return JSONcar;
    }

    @RequestMapping(method = RequestMethod.POST)
    protected String postDesc(@RequestParam("carId") String carId) throws IOException, ServletException {
        car = carService.findById(Integer.parseInt(carId));
        return "description";
    }
}
