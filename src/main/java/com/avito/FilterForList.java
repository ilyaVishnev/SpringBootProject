package com.avito;

import com.DAO.services.BrandService;
import com.cars_annot.Brand;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@Controller
@RequestMapping("/filterFlist")
public class FilterForList {

    @Autowired
    private BrandService brandService;
    private final String[] arrayParametrs = {"off", "off", "off"};

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    protected String getContextMenu() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject send = new JSONObject();
        mapper.configure(JsonParser.Feature.IGNORE_UNDEFINED, true);
        String brands = mapper.writerWithView(Views.Public.class).writeValueAsString(brandService.findAll());
        JSONParser parser = new JSONParser();
        JSONArray brandArray = (JSONArray) parser.parse(brands);
        send.put("idBrand", arrayParametrs[0]);
        send.put("havePhoto", arrayParametrs[1]);
        send.put("today", arrayParametrs[2]);
        send.put("array", brandArray);
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
