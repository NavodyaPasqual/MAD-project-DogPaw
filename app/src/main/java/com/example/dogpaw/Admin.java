package com.example.dogpaw;

public class Admin {

    private String adminName, adminUsername, adminPw;

    public Admin(String adminName, String adminUsername, String adminPw) {
        this.adminName = adminName;
        this.adminUsername = adminUsername;
        this.adminPw = adminPw;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {

        this.adminName = adminName;
    }

    public String getAdminUsername() {

        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {

        this.adminUsername = adminUsername;
    }

    public String getAdminPw() {
        return adminPw;
    }

    public void setAdminPw(String adminPw) {
        this.adminPw = adminPw;
    }
}
