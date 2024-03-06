package com.ge.proof_of_concept.catalog.product;

import com.ge.proof_of_concept._backup.product_history.ProductHistory;
import com.ge.proof_of_concept._backup.product_history.ProductHistoryService;
import com.ge.proof_of_concept.user.users.Users;
import com.ge.proof_of_concept.user.users.UsersService;
import com.ge.proof_of_concept.util.FilterUtil;
import com.ge.proof_of_concept.util.HistoryAction;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class is a service for the Product entity
 * It manages the Product entity in the database and provides methods for CRUD operations
 *
 * Author: Abdelah ESSORDO
 * Date: 06/03/2024
 */
@Service
public class ProductService {
    /**
     * TODO:
     *   updated_by should be the user who is currently logged in, currently it is the user who is being restored [ ]
     */

    private final ProductRepository productRepository;
    private final EntityManager entityManager;
    private final ProductHistoryService productHistoryService;
    private final UsersService usersService;

    @Autowired
    public ProductService(
            ProductRepository productRepository,
            EntityManager entityManager,
            ProductHistoryService productHistoryService,
            UsersService usersService
    ) {
        this.productRepository = productRepository;
        this.entityManager = entityManager;
        this.productHistoryService = productHistoryService;
        this.usersService = usersService;
    }

    /**
     * This method returns a list of all non-soft deleted products
     *
     * @return List<Product>
     */
    public List<Product> getProducts() {
        // The two lines below are used to apply the deleted filter to the product table,
        // so that only the non-soft deleted products are returned
        Session session = entityManager.unwrap(Session.class);
        FilterUtil.applyDeletedFilter(session, Product.class);

        return productRepository.findAll();
    }

    /**
     * This method returns a list of soft deleted products
     *
     * @return List<Product>
     */
    public List<Product> getSoftDeletedProducts() {
        return productRepository.findByDeletedAtIsNotNull();
    }

    /**
     * This method adds a new product to the database
     *
     * @param product
     */
    public void addNewProduct(Product product) {
        /**
         * TODO:
         *  Check if the user exists in the database [ ]
         *  Add validation for the product fields [ ]
         *  updated_by should be the user who is currently logged in, currently it is the user who is being restored [ ]
         *  Add a check to see if the product already exists in the database [ ]
         *  Add a check to see if the user who is currently logged in has the permission to add a new product [ ]
         */

        // Add the product to the database
        productRepository.save(product);


        // Find User who is currently logged in
        Users user = usersService.getUserBySSO(223131678L); // This should be the user who is currently logged in


        ProductHistory productHistory = new ProductHistory(
                product,
                product.getName(),
                product.getPrice(),
                user, // updated_by, Should be the user who is currently logged in
                LocalDateTime.now(),
                HistoryAction.CREATED
        );

        // Add the product to the product_history table
        productHistoryService.recordActionToHistory(productHistory);

    }
}
