package com.laioffer.travelplanner.controllers;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.laioffer.travelplanner.antAlg.ACO;
import com.laioffer.travelplanner.entities.Place;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/plan")
public class PlanController {

    @PostMapping("/customized")
    public ResponseEntity<?> generateCustomizedPlan(@RequestBody JSONObject jsonObject) {
        JSONArray places = jsonObject.getJSONArray("place");
        List<Place> placeList = JSONObject.parseArray(places.toJSONString(), Place.class);
        ACO aco = new ACO(placeList);
        aco.iterator();
        System.out.println(aco.getOrder());

        Integer startDate = jsonObject.getJSONObject("settings").getInteger("startDate");
        Integer endDate = jsonObject.getJSONObject("settings").getInteger("endDate");

        Integer duration = (endDate - startDate) / (60 * 60 * 24);
        System.out.println(duration);

        JSONArray res = JSONArray.parseArray(JSON.toJSONString(aco.getOrder(), SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.IgnoreErrorGetter));


        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}