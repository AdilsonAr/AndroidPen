package com.example.pen.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class KeyValue {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private long idApunte;
    private String key;
    private String value;

    public KeyValue() {
    }

    public KeyValue(long id, long idApunte, String key, String value) {
        this.id = id;
        this.idApunte = idApunte;
        this.key = key;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdApunte() {
        return idApunte;
    }

    public void setIdApunte(long idApunte) {
        this.idApunte = idApunte;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
