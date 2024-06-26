package com.ge.proof_of_concept.user.users.dto;

/**
 * This class is the Response Class for the CreateUser endpoint.
 * All user creation response should be made using this class and its fields.
 *
 * Author: Abdellah ESSORDO
 * Date: 07/03/2024
 */
public class CreateUserResponse {
    private Long _SSO;
    private String name;

    //==================================================================================================================
    // Getters and Setters
    //==================================================================================================================

    public Long get_SSO() {
        return _SSO;
    }

    public void set_SSO(Long _SSO) {
        this._SSO = _SSO;
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
        return "CreateUserResponse{" +
                "SSO=" + _SSO +
                ", name='" + name + '\'' +
                '}';
    }
}
