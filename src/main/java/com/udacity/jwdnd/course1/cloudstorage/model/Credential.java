package com.udacity.jwdnd.course1.cloudstorage.model;

//Credential class that is used by mapper to insert or fetch data
public class Credential {


    private Integer credentialId;
    private String URL;
    private String username;
    private String key;
    private String password;
    private Integer userid;

    public Credential(Integer credentialId, String URL, String username, String key, String password, Integer userid) {
        this.credentialId = credentialId;
        this.URL = URL;
        this.username = username;
        this.key = key;
        this.password = password;
        this.userid = userid;
    }

//    @Override
//    public String toString() {
//        return "Credential{" +
//                "credentialId=" + credentialId +
//                ", URL='" + URL + '\'' +
//                ", username='" + username + '\'' +
//                ", key='" + key + '\'' +
//                ", password='" + password + '\'' +
//                ", userid=" + userid +
//                '}';
//    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return URL;
    }

    public void setUrl(String URL) {
        this.URL = URL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userid;
    }

    public void setUserId(Integer userid) {
        this.userid = userid;
    }

}


