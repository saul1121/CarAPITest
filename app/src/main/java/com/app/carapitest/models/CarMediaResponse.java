package com.app.carapitest.models;

import java.util.ArrayList;
import java.util.List;

public class CarMediaResponse {

    private List<String> photoSrcs = new ArrayList<String>();

    public List<String> getPhotoSrcs() {
        return photoSrcs;
    }

    public void setPhotoSrcs(List<String> photoSrcs) {
        this.photoSrcs = photoSrcs;
    }
}