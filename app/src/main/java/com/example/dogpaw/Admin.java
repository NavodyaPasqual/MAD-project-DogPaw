package com.example.dogpaw;

public class Admin {

    private String adminName, adminEmail, adminPassword;

    public Admin(String adminName, String adminEmail, String adminPassword) {
        this.adminName = adminName;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {

        this.adminName = adminName;
    }

    public String getAdminEmail() {

        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {

        this.adminEmail = adminEmail;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
}
