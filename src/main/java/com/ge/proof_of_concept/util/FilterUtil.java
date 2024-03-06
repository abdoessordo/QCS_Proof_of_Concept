package com.ge.proof_of_concept.util;

import org.hibernate.Filter;
import org.hibernate.Session;

/**
 * This class provides a generic `applyDeletedFilter` method to manage filters for any entity extending `BaseEntity`.
 * The method takes the session and entity class as parameters.
 * It dynamically builds the filter name based on the entity class name (e.g., "ProductDeletedFilter", "UserDeletedFilter").
 * It enables the filter and sets the `isDeleted` parameter to `true` (assuming filtering for deleted entities). Adjust this as needed.
 *
 * Author: Abdellah ESSORDO
 * Date: 06/03/2024
 */

public class FilterUtil {

    public static <T extends BaseEntity> void applyDeletedFilter(Session session, Class<T> entityClass) {
        String filterName = buildFilterName(entityClass);
        System.out.println("===================================");
        System.out.println("Filter name: " + filterName);
        System.out.println("===================================");
        if (session != null) {
            Filter filter = session.enableFilter(filterName);
            if (filter != null) {
                filter.setParameter("includeDeleted", true); // Assuming filtering for deleted entities
            }
        }
    }

    private static <T extends BaseEntity> String buildFilterName(Class<T> entityClass) {
        return "deleted" + entityClass.getSimpleName() + "Filter";
    }
}
