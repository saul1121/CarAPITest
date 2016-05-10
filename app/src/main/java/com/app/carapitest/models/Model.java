
package com.app.carapitest.models;

import java.util.ArrayList;
import java.util.List;

public class Model{

    private String id;
    private String name;
    private String niceName;
    private List<Year> years = new ArrayList<Year>();

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The niceName
     */
    public String getNiceName() {
        return niceName;
    }

    /**
     * @param niceName The niceName
     */
    public void setNiceName(String niceName) {
        this.niceName = niceName;
    }

    /**
     * @return The years
     */
    public List<Year> getYears() {
        return years;
    }

    /**
     * @param years The years
     */
    public void setYears(List<Year> years) {
        this.years = years;
    }

}
