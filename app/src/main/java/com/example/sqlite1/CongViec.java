package com.example.sqlite1;

public class CongViec {
    private int id;
    private String tenTask;

    public CongViec(int id, String tenTask) {
        this.id = id;
        this.tenTask = tenTask;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenTask() {
        return tenTask;
    }

    public void setTenTask(String tenTask) {
        this.tenTask = tenTask;
    }
}
