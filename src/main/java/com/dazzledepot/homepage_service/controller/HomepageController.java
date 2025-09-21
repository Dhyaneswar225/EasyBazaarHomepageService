package com.dazzledepot.homepage_service.controller;

import com.dazzledepot.homepage_service.model.FeaturedProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.dazzledepot.homepage_service.repository.FeaturedProductRepository;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/homepage")
public class HomepageController {
    @Autowired
    private FeaturedProductRepository repo;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/featured")
    public List<Object> getFeaturedProducts() {
        try {
            List<FeaturedProduct> featured = repo.findAll();
            return featured.stream()
                    .sorted((a, b) -> Integer.compare(a.getDisplayOrder(), b.getDisplayOrder()))
                    .map(fp -> {
                        try {
                            return restTemplate.getForObject(
                                    "http://localhost:8082/api/products/" + fp.getProductId(), Object.class);
                        } catch (Exception e) {
                            return null; // Handle failed requests gracefully
                        }
                    })
                    .filter(product -> product != null)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return List.of(); // Return empty list on error
        }
    }
}
