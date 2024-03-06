package com.ge.proof_of_concept.catalog.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is a controller for the Product entity
 * It manages the Product entity in the database and provides methods for CRUD operations
 *
 * Author: Abdelah ESSORDO
 * Date: 06/03/2024
 */
@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController {
    /**
     * TODO:
     *   Return JSON response for all the endpoints [ ]
     */

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * GET /api/v1/products
     *
     * This method returns a list of all non-soft deleted products
     *
     * @param
     * @return List<Product> - a list of all products
     */
    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    /**
     * GET /api/v1/products/deleted
     *
     * This method returns a list of all soft deleted products
     *
     * @param
     * @return List<Product> - a list of all soft deleted products
     */
    @GetMapping(path = "/deleted")
    public List<Product> getSoftDeletedProducts() {
        return productService.getSoftDeletedProducts();
    }

    /**
     * POST /api/v1/product/add
     *
     * This method adds a new user to the database,
     * and also adds a new entry to the product_history table
     *
     * @param product: Product - the product to be added
     * @return void
     */
    @PostMapping(path = "/add")
    public void addNewProduct(@RequestBody Product product) {
        productService.addNewProduct(product);
    }
}