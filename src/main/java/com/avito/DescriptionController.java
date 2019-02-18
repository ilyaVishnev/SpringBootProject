package com.avito;

import com.DAO.services.CarService;
import com.cars_annot.Car;
import org.json.simple.JSONObject;
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
    final String[] parametrs = new String[1];

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    protected JSONObject getDesc() {
        Car car = carService.findById(Integer.parseInt(parametrs[0]));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("desc", car.getDescription());
        jsonObject.put("carbody", car.getCarBody().getDescription());
        jsonObject.put("engine", car.getEngine().getDescription());
        jsonObject.put("gearbox", car.getGearbox().getDescription());
        jsonObject.put("photo", car.getPhoto());
        jsonObject.put("price", car.getPrice());
        jsonObject.put("status", car.getStatus());
        jsonObject.put("idHolder", car.getHolder().getId());
        jsonObject.put("id", car.getId());
        return jsonObject;
    }

    @RequestMapping(method = RequestMethod.POST)
    protected String postDesc(@RequestParam("carId") String carId) throws IOException, ServletException {
        parametrs[0] = carId;
        return "description";
    }
}
