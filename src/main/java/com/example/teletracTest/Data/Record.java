package com.example.teletracTest.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Record {

    private @Id @GeneratedValue Long id;
    @NotNull
    private String recordType;
    @NotNull
    private String deviceId;
    @NotNull
    private String eventDateTime; // Instant?
    @NotNull
    private int fieldA;
    @NotNull
    private String fieldB;
    @NotNull
    private Double fieldC;

    public Record() {}

    public Record(String recordType, String deviceId, String eventDateTime, int fieldA, String fieldB, Double fieldC) {
        this.recordType = recordType;
        this.deviceId = deviceId;
        this.eventDateTime = eventDateTime;
        this.fieldA = fieldA;
        this.fieldB = fieldB;
        this.fieldC = fieldC;
    }

    public Long getId() {
        return id;
    }

    public String getRecordType() {
        return recordType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getEventDateTime() {
        return eventDateTime;
    }

    public int getFieldA() {
        return fieldA;
    }

    public String getFieldB() {
        return fieldB;
    }

    public Double getFieldC() {
        return fieldC;
    }

    @Override
    public String toString() {
        return "Record{" +
                "recordType='" + recordType + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", eventDateTime='" + eventDateTime + '\'' +
                ", fieldA=" + fieldA +
                ", fieldB='" + fieldB + '\'' +
                ", fieldC=" + fieldC +
                '}';
    }
}
