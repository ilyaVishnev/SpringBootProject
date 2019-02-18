package com.avito;

import com.DAO.services.CarService;
import com.cars_annot.Car;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;
import org.json.simple.JSONArray;
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
    protected String sendList(@RequestBody(required = false) String text) {
        HashMap<String, String> map = new Gson().fromJson(text, new TypeToken<HashMap<String, String>>() {
        }.getType());
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
        JSONArray jsonArray = new JSONArray();
        JSONObject send = new JSONObject();
        Iterator<Car> iterator1 = cars.iterator();
        while (iterator1.hasNext()) {
            Car car = iterator1.next();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", car.getId());
            jsonObject.put("model", car.getGearbox().getModel().toString());
            jsonObject.put("price", car.getPrice());
            jsonObject.put("photo", car.getPhoto());
            jsonObject.put("status", car.getStatus());
            jsonObject.put("date", car.getDate());
            jsonObject.put("brandId", car.getGearbox().getModel().getBrand().getId());
            jsonArray.add(jsonObject);
        }
        send.put("array", jsonArray);
        return send.toString();
    }
}
