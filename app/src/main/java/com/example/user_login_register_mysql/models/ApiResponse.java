package com.example.user_login_register_mysql.models;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("result_code")
    private int result_code;

    @SerializedName("name")
    private String name;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getResult_code() {
        return result_code;
    }

    public void setResult_code(int result_code) {
        this.result_code = result_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


