package com.avito;

import com.DAO.services.BrandService;
import com.cars_annot.Brand;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@Controller
@RequestMapping("/filterFlist")
public class FilterForList {

    @Autowired
    private BrandService brandService;
    private final String[] arrayParametrs = {"off","off","off"};

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    protected String getContextMenu() {
        JSONObject send = new JSONObject();
        JSONArray array = new JSONArray();
        Iterator<Brand> iterator = brandService.findAll().iterator();
        while (iterator.hasNext()) {
            JSONObject jsonObject = new JSONObject();
            Brand brand = iterator.next();
            jsonObject.put("id", brand.getId());
            jsonObject.put("brand", brand.getName());
            array.add(jsonObject);
        }
        send.put("idBrand", arrayParametrs[0]);
        send.put("havePhoto", arrayParametrs[1]);
        send.put("today", arrayParametrs[2]);
        send.put("array", array);
        return send.toString();
    }

    @RequestMapping(method = RequestMethod.POST)
    protected String goToList(@RequestParam(required = false, name = "brands") String brands, @RequestParam(required = false, name = "photo") String photo, @RequestParam(required = false, name = "today") String today) {
        arrayParametrs[0] = brands;
        arrayParametrs[1] = photo == null ? "off" : photo;
        arrayParametrs[2] = today == null ? "off" : today;
        return "list";
    }
}
