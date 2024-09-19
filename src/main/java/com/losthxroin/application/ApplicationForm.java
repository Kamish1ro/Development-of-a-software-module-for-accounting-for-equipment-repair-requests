package com.losthxroin.application;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class ApplicationForm {
    private int id;
    private String date;
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 36, message = "Название устройства не должно быть слишком коротким и слишком длинным")
    private String device;
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(max = 30, message = "Слишком длинное сообщение max = 30")
    private String typeOfFault;
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(max = 200, message = "Слишком длинное сообщение max = 200")
    private String description;
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 5, max = 100, message = "ФИО не должно иметь менее 5 и более 100 символов")
    private String client;
    private String status;
    private String worker;
    private Date startDate;
    private Date completionDate;

    public ApplicationForm(){
        this.status = "В ожидании";
        this.worker = "Не назначен";
    }
    public ApplicationForm(int id, String date, String device, String typeOfFault, String description, String client, String status, String worker) {
        this.id = id;
        this.date = date;
        this.device = device;
        this.typeOfFault = typeOfFault;
        this.description = description;
        this.client = client;
        this.status = status;
        this.worker = worker;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getTypeOfFault() {
        return typeOfFault;
    }

    public void setTypeOfFault(String typeOfFault) {
        this.typeOfFault = typeOfFault;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
