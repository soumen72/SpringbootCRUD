package com.udacity.jwdnd.course1.cloudstorage.model;

public class User {

    private Integer userId;
    private String username;
    private String salt;
    private String password;
    private String firstName;
    private String lastName;

    public User(Integer userId, String username, String salt, String password, String firstName, String lastName) {
        this.userId = userId;
        this.username = username;
        this.salt = salt;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "userId=" + userId +
//                ", username='" + username + '\'' +
//                ", salt='" + salt + '\'' +
//                ", password='" + password + '\'' +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                '}';
//    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


//    private Integer user_Id;
//    private String user_name;
//    private String password;
//    private String salt;
//    private String first_Name;
//    private String last_Name;
//
//
//    public User(Integer user_id, String user_name, String password, String salt, String first_name, String last_name) {
//        this.user_Id = user_id;
//        this.user_name = user_name;
//        this.password = password;
//        this.salt = salt;
//        this.first_Name = first_name;
//        this.last_Name = last_name;
//    }
//
//    public Integer getUser_Id() {
//        return user_Id;
//    }
//
//    public void setUser_Id(Integer user_Id) {
//        this.user_Id = user_Id;
//    }
//
//    public String getUser_name() {
//        return user_name;
//    }
//
//    public void setUser_name(String user_name) {
//        this.user_name = user_name;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getSalt() {
//        return salt;
//    }
//
//    public void setSalt(String salt) {
//        this.salt = salt;
//    }
//
//    public String getFirst_Name() {
//        return first_Name;
//    }
//
//    public void setFirst_Name(String first_Name) {
//        this.first_Name = first_Name;
//    }
//
//    public String getLast_Name() {
//        return last_Name;
//    }
//
//    public void setLast_Name(String last_Name) {
//        this.last_Name = last_Name;
//    }
//
//

}
