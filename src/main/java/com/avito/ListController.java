package com.avito;

import com.DAO.services.CarService;
import com.cars_annot.Car;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/list")
public class ListController {


    @Autowired
    private CarService carService;

    @RequestMapping(method = RequestMethod.GET)
    protected String sendToList() {
        return "list";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    protected JSONObject sendList(@RequestBody(required = false) String text) throws Exception {
        HashMap<String, String> map = new Gson().fromJson(text, new TypeToken<HashMap<String, String>>() {
        }.getType());
        JSONObject send = new JSONObject();
        List<Car> cars = new ArrayList<>();
        String filter = "nothing";
        filter += !map.get("idBrand").equals("off") ? "brand" : "";
        filter += !map.get("photo").equals("off") ? "photo" : "";
        filter += !map.get("today").equals("off") ? "today" : "";
        switch (filter) {
            case ("nothing"):
                cars = carService.findAll();
                break;
            case ("nothingbrandphototoday"):
                cars = carService.findAllByDateAndPhotoAndBrand(Integer.parseInt(map.get("idBrand")));
                break;
            case ("nothingbrandphoto"):
                cars = carService.findAllByBrandAndPhoto(Integer.parseInt(map.get("idBrand")));
                break;
            case ("nothingbrandtoday"):
                cars = carService.findAllByBrandAndDate(Integer.parseInt(map.get("idBrand")));
                break;
            case ("nothingphototoday"):
                cars = carService.findAllByDateAndPhoto();
                break;
            case ("nothingbrand"):
                cars = carService.findAllByBrand(Integer.parseInt(map.get("idBrand")));
                break;
            case ("nothingphoto"):
                cars = carService.findAllByPhoto();
                break;
            case ("nothingtoday"):
                cars = carService.findAllByDate();
                break;
        }
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonSend = new JSONObject();
        mapper.configure(JsonParser.Feature.IGNORE_UNDEFINED, true);
        String Strcars = mapper.writerWithView(Views.Public.class).writeValueAsString(cars);
        JSONParser parser = new JSONParser();
        JSONArray JSONcars = (JSONArray) parser.parse(Strcars);
        send.put("array", JSONcars);
        return send;
    }
}
