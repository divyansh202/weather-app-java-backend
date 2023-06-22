package com.example.springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.springdemo.data.database;
import com.example.springdemo.repo.repo;

import java.util.Arrays;

@Service
public class testservice {

    @Autowired
    repo repository;

    public String service(String city) {
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);

        // RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        StringBuilder cityq = new StringBuilder();

        cityq.append("http://api.weatherapi.com/v1/current.json?key=28432a34394a4f24aea202609232006&q=").append(city);

        // Send request with GET method, and Headers.
        ResponseEntity<String> response = restTemplate.exchange(cityq.toString(), //
                HttpMethod.GET, entity, String.class);
  
        String result = response.getBody();

        // createGroceryItems();
        // findCountOfGroceryItems();
        
        return result;
    }

    void createGroceryItems() {
        System.out.println("Data creation started...");
        repository.save(new database("Whole Wheat Biscuit", "Whole Wheat Biscuit", 5, "snacks"));
        repository.save(new database("Kodo Millet", "XYZ Kodo Millet healthy", 2, "millets"));
        repository.save(new database("Dried Red Chilli", "Dried Whole Red Chilli", 2, "spices"));
        repository.save(new database("Pearl Millet", "Healthy Pearl Millet", 1, "millets"));
        repository.save(new database("Cheese Crackers", "Bonny Cheese Crackers Plain", 6, "snacks"));
        System.out.println("Data creation complete...");
    }

    public void findCountOfGroceryItems() {
         long count = repository.count();
         System.out.println("Number of documents in the collection: " + count);
     }


}
