package com.sajal.spring_boot_querydsl.config;

import com.sajal.spring_boot_querydsl.entity.Category;
import com.sajal.spring_boot_querydsl.entity.Product;
import com.sajal.spring_boot_querydsl.entity.Review;
import com.sajal.spring_boot_querydsl.entity.Tag;
import com.sajal.spring_boot_querydsl.repository.CategoryRepository;
import com.sajal.spring_boot_querydsl.repository.ProductRepository;
import com.sajal.spring_boot_querydsl.repository.TagRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initializeDatabase(CategoryRepository categoryRepository,
                                                ProductRepository productRepository,
                                                TagRepository tagRepository) {
        return args -> {
            // Clean the database
            productRepository.deleteAll();
            categoryRepository.deleteAll();
            tagRepository.deleteAll();

            // Create dummy categories
            Category electronics = new Category();
            electronics.setName("Electronics");

            Category books = new Category();
            books.setName("Books");

            categoryRepository.saveAll(Set.of(electronics, books));

            // Create dummy tags
            Tag sale = new Tag();
            sale.setName("Sale");

            Tag newArrival = new Tag();
            newArrival.setName("New Arrival");

            tagRepository.saveAll(Set.of(sale, newArrival));

            // Create dummy products
            Product phone = new Product();
            phone.setName("Smartphone");
            phone.setDescription("Latest model smartphone");
            phone.setPrice(699.99);
            phone.setCategory(electronics);
            phone.setTags(Set.of(sale, newArrival));

            Product novel = new Product();
            novel.setName("Fiction Novel");
            novel.setDescription("Bestselling fiction novel");
            novel.setPrice(19.99);
            novel.setCategory(books);
            novel.setTags(Set.of(newArrival));

            productRepository.saveAll(Set.of(phone, novel));

            // Create dummy reviews
            Review phoneReview1 = new Review();
            phoneReview1.setComment("Excellent phone with great features!");
            phoneReview1.setRating(5);
            phoneReview1.setProduct(phone);

            Review phoneReview2 = new Review();
            phoneReview2.setComment("Good value for money.");
            phoneReview2.setRating(4);
            phoneReview2.setProduct(phone);

            Review novelReview = new Review();
            novelReview.setComment("Engaging story, a must-read!");
            novelReview.setRating(5);
            novelReview.setProduct(novel);

            // Add reviews to products
            phone.addReview(phoneReview1);
            phone.addReview(phoneReview2);
            novel.addReview(novelReview);

            // Save products with reviews
            productRepository.saveAll(Set.of(phone, novel));
        };
    }
}