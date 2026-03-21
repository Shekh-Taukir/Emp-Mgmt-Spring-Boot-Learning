package com.learnSpringBootCode.Hospital_Mgmt_SB.repositories;

import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.Product;
import org.aspectj.weaver.loadtime.Options;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitle(String title);

    List<Product> findByCreatedAtAfter(LocalDateTime afterDateTime);

    List<Product> findByQuantityAndPrice(int quantity, BigDecimal price);

    List<Product> findByQuantityGreaterThanOrPriceLessThan(int quantity, BigDecimal price);

    List<Product> findByTitleLike(String title);

    List<Product> findByTitleContaining(String title);

    List<Product> findByTitleContainingIgnoreCase(String title);

    Optional<Product> findByTitleAndPrice(String title, int price);

    @Query("select p from Product p where p.title = ?1 and p.price = ?2")
    Optional<Product> findByTitleAndPriceQuery(String title, BigDecimal price);

    List<Product> findByTitleOrderByPrice(String title);

    List<Product> findBySkuOrderByPriceDesc(String sku);

    List<Product> findByOrderByPriceDesc();

    List<Product> findBy(Sort sortBy);

    List<Product> findByTitle(String title, Pageable pageable);

    List<Product> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
