package com.app.carapitest.models;

import io.realm.RealmObject;

public class CarItem extends RealmObject {
    private String name;
    private String makeNiceName;
    private String modelNiceName;
    private String year;
    private String styleId;
    private String photo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMakeNiceName() {
        return makeNiceName;
    }

    public void setMakeNiceName(String makeNiceName) {
        this.makeNiceName = makeNiceName;
    }

    public String getModelNiceName() {
        return modelNiceName;
    }

    public void setModelNiceName(String modelNiceName) {
        this.modelNiceName = modelNiceName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
