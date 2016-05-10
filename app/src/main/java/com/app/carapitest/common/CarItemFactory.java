package com.app.carapitest.common;

import com.app.carapitest.models.CarItem;
import com.app.carapitest.models.Make;
import com.app.carapitest.models.Model;

import io.realm.Realm;

public class CarItemFactory {

    public static CarItem getItem(Realm realm, Make make, Model model) {
        realm.beginTransaction();
        CarItem item = realm.createObject(CarItem.class);
        item.setName(make.getName());
        item.setMakeNiceName(make.getNiceName());
        item.setModelNiceName(model.getNiceName());
        item.setYear(model.getYears().get(0).getYear() + "");
        realm.commitTransaction();
        return item;
    }
}
