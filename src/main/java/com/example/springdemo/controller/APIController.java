package com.example.springdemo.controller;

import com.example.springdemo.dto.WeatherDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.springdemo.service.testservice;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
public class APIController {

    @Autowired
    private testservice testservicel;

    @GetMapping(value = "/temp", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "https://react-fgdy4ej5dq-uc.a.run.app")
    public Map< String, Object > temp(@RequestParam(name = "city", required = false) String city){

        JsonParser springParser = JsonParserFactory.getJsonParser();

        ObjectMapper objectMapper = new ObjectMapper();
        try{
            WeatherDTO data = objectMapper.readValue(testservicel.service(city), WeatherDTO.class);
            System.out.println(data.current.wind_dir);
        }
        catch (Exception e){

        }


        Map< String, Object > map = springParser.parseMap(testservicel.service(city));
//        Map<String,String> newMap = map.entrySet().stream()
//                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String)e.getValue()));
        String mapArray[] = new String[map.size()];

        WeatherDTO WeatherDTO = new WeatherDTO();
        BeanUtils.copyProperties(map, WeatherDTO);

        System.out.println("Items found: " + mapArray.length);
        System.out.println("Map Arr: " + WeatherDTO.equals(null));
//        int i = 0;
//        for (Map.Entry < String, String > entry: newMap.entrySet()) {
//            System.out.println(entry.getKey() + " = " + entry.getValue());
//            Map< String, Object > m = springParser.parseMap(entry.getValue());
//            System.out.println(m.toString());
//
//
//            i++;
//        }
        return map;
    }
}
