package com.ge.proof_of_concept.user.users.dto;

import com.ge.proof_of_concept.user.users.Users;

import java.util.List;

/**
 * This class is a Data Transfer Object (DTO) for User
 * It is used to transfer data between the User entity and the User controller
 *
 * Fields:
 * SSO: Long
 * name: String
 *
 * Methods:
 * - Getters and Setters
 * - toString
 *
 * Author: Abdellah ESSORDO
 * Date: 06/03/2024
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

    //==================================================================================================================
    // Methods
    //==================================================================================================================

    /**
     * This method returns a `UserDto` from a `User`
     *
     * @param user: User
     * @return UserDto
     */
    public static UserDto fromUser(Users user) {
        UserDto userDto = new UserDto();

        userDto.setSSO(user.get_SSO());
        userDto.setName(user.getName());

        return userDto;
    }

    /**
     * This method returns a `List<UserDto>` from a `List<User>`
     *
     * @param users: List<User>
     * @return List<UserDto>
     */
    public static List<UserDto> fromUsers(List<Users> users) {
        return users.stream()
                .map(UserDto::fromUser)
                .toList();
    }
}
