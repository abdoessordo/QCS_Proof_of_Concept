package com.ge.proof_of_concept.user.users.dto;

/**
 * This class is a Data Transfer Object (DTO) for User
 * It is used to transfer data between the User entity and the User controller
 *
 * Fields:
 *    SSO: Long
 *    name: String
 *
 * Methods:
 *  - Getters and Setters
 *  - toString
 *
 *  Author: Abdellah ESSORDO
 *  Date: 06/03/2024
 */
public class UserDto {
    private Long SSO;
    private String name;

    //==================================================================================================================
    // Getters and Setters
    //==================================================================================================================

    public Long getSSO() {
        return SSO;
    }

    public void setSSO(Long SSO) {
        this.SSO = SSO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //==================================================================================================================
    // toString
    //==================================================================================================================
    @Override
    public String toString() {
        return "CreateUserRequest{" +
                "SSO=" + SSO +
                ", name='" + name + '\'' +
                '}';
    }
}
