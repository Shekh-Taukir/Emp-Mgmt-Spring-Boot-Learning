package com.learnSpringBootCode.Hospital_Mgmt_SB;

import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.Product;
import com.learnSpringBootCode.Hospital_Mgmt_SB.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

    @Test
    void testRepository(){
        Product product = Product.builder()
                .sku("nestle")
                .title("Dairy Milk")
                .quantity(2)
                .price(BigDecimal.valueOf(135))
                .build();

        Product product1 = productRepository.save(product);
        System.out.println(product1);
    }

    @Test
    void testGetRepository(){
        List<Product> product;
//        productList = productRepository.findAll();
//        System.out.println(productList);

//        product = productRepository.findByTitle("Nestle Pepsi");

//        product = productRepository.findByCreatedAtAfter(LocalDateTime.of(2026,4,20,19,26));
//        product = productRepository.findByQuantityAndPrice(12,BigDecimal.valueOf(35));
//        product = productRepository.findByQuantityGreaterThanOrPriceLessThan(11,BigDecimal.valueOf(36));

//        product = productRepository.findByTitleLike("%Pep%");

//        product = productRepository.findByTitleContaining("Pep");
        product = productRepository.findByTitleContainingIgnoreCase("pep");
        System.out.println(product);

    }

    @Test
    void testSingleEntity(){
        Optional<Product> product1;
//        Optional<Product> product1 = productRepository.findByTitleAndPrice("Dairy Milk1",135);

        product1 = productRepository.findByTitleAndPriceQuery("Dairy Milk", BigDecimal.valueOf(135));

        product1.ifPresent(System.out::println);
    }

}
