package com.learnSpringBootCode.Hospital_Mgmt_SB.controllers;

import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.Product;
import com.learnSpringBootCode.Hospital_Mgmt_SB.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;
    private final int PAGE_SIZE = 5;

    @GetMapping
    public List<Product> getAllProducts(@RequestParam(defaultValue = "id") String sortBy,
                                        @RequestParam(defaultValue = "asc") String sortOrder,
                                        @RequestParam(defaultValue = "0") Integer pageNumber,
                                        @RequestParam(defaultValue = "") String title)
    {
        List<Product> productList;
        /*productList = productRepository.findByOrderByPriceDesc();

        productList = productRepository.findBy(Sort.by(Sort.Direction.DESC, sortBy, "price", "quantity"));

        productList = productRepository.findBy(Sort.by(
                Sort.Order.asc(sortBy),
                Sort.Order.desc("title")
        ));*/

        //Dynamic sortBy and SortOrder in findAll method
        //eg API :http://localhost:8081/api/product?sortBy=sku&sortOrder=asc
//        productList = productRepository.findBy(
//                Sort.by(
//                        new Sort.Order(Sort.Direction.fromString(sortOrder),
//                        sortBy),
//                        Sort.Order.asc("id")
//        ));

        Pageable pageable = PageRequest.of(
                pageNumber,
                PAGE_SIZE,
                Sort.by(
                        new Sort.Order(Sort.Direction.fromString(sortOrder),
                        sortBy
                ))
        );

        //Dyanmic filter, Sorting & sort order, pagination
        //url : http://localhost:8081/api/product?title=par&pageNumber=0&sortBy=quantity&sortOrder=desc


        productList = productRepository.findByTitleContainingIgnoreCase(title,pageable);
        /*
        return productRepository.findAll(pageable).getContent();
         */
        return productList;
    }


}
