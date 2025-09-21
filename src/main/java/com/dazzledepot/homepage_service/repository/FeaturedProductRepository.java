package com.dazzledepot.homepage_service.repository;
import com.dazzledepot.homepage_service.model.FeaturedProduct;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeaturedProductRepository extends MongoRepository<FeaturedProduct, String> {
}
