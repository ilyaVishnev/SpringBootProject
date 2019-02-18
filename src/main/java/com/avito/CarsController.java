package com.avito;


import com.DAO.services.*;
import com.cars_annot.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.web.bind.annotation.GetMapping;*/
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;

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

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    protected JSONObject doGet() {
        final JSONArray brandArray = new JSONArray();
        final JSONArray modelArray = new JSONArray();
        final JSONArray gearboxArray = new JSONArray();
        final JSONArray engineArray = new JSONArray();
        final JSONArray carbodyArray = new JSONArray();
        final JSONArray yearsArray = new JSONArray();
        JSONObject jsonSend = new JSONObject();
        Iterator<Brand> iterator = brandService.findAll().iterator();
        while (iterator.hasNext()) {
            JSONObject jsonObj = new JSONObject();
            Brand brand = iterator.next();
            jsonObj.put("id", brand.getId());
            jsonObj.put("name", brand.getName());
            brandArray.add(jsonObj);
        }
        jsonSend.put("brandArray", brandArray);
        Iterator<Model> iterator1 = modelService.findAll().iterator();
        while (iterator1.hasNext()) {
            JSONObject jsonObj = new JSONObject();
            Model model = iterator1.next();
            jsonObj.put("id", model.getId());
            jsonObj.put("name", model.getName());
            jsonObj.put("IdBrand", model.getBrand().getId());
            modelArray.add(jsonObj);
        }
        jsonSend.put("modelArray", modelArray);
        Iterator<Gearbox> iterator2 = gearboxService.findAll().iterator();
        while (iterator2.hasNext()) {
            JSONObject jsonObj = new JSONObject();
            Gearbox gearboxA = iterator2.next();
            jsonObj.put("id", gearboxA.getId());
            jsonObj.put("desc", gearboxA.getDescription());
            jsonObj.put("IdM", gearboxA.getModel().getId());
            jsonObj.put("year", gearboxA.getYear());
            gearboxArray.add(jsonObj);
        }
        jsonSend.put("gearboxArray", gearboxArray);
        Iterator<Engine> iterator3 =engineService.findAll().iterator();
        while (iterator3.hasNext()) {
            JSONObject jsonObj = new JSONObject();
            Engine engineA = iterator3.next();
            jsonObj.put("id", engineA.getId());
            jsonObj.put("desc", engineA.getDescription());
            jsonObj.put("IdM", engineA.getModel().getId());
            jsonObj.put("year", engineA.getYear());
            engineArray.add(jsonObj);
        }
        jsonSend.put("engineArray", engineArray);
        Iterator<CarBody> iterator4 = carBodyService.findAll().iterator();
        while (iterator4.hasNext()) {
            JSONObject jsonObj = new JSONObject();
            CarBody carBodyA = iterator4.next();
            jsonObj.put("id", carBodyA.getId());
            jsonObj.put("desc", carBodyA.getDescription());
            jsonObj.put("IdM", carBodyA.getModel().getId());
            jsonObj.put("year", carBodyA.getYear());
            carbodyArray.add(jsonObj);
        }
        jsonSend.put("carbodyArray", carbodyArray);
        Iterator<Year> iterator5 = yearService.findAll().iterator();
        while (iterator5.hasNext()) {
            JSONObject jsonObj = new JSONObject();
            Year year = iterator5.next();
            jsonObj.put("year", year.getYear());
            yearsArray.add(jsonObj);
        }
        jsonSend.put("yearsArray", yearsArray);
        return jsonSend;
    }
}
