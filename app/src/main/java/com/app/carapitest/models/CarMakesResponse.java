
package com.app.carapitest.models;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CarMakesResponse {

    private List<Make> makes = new ArrayList<Make>();
    private Integer makesCount;

    /**
     * @return The makes
     */
    public List<Make> getMakes() {
        return makes;
    }

    /**
     * @param makes The makes
     */
    public void setMakes(List<Make> makes) {
        this.makes = makes;
    }

    /**
     * @return The makesCount
     */
    public Integer getMakesCount() {
        return makesCount;
    }

    /**
     * @param makesCount The makesCount
     */
    public void setMakesCount(Integer makesCount) {
        this.makesCount = makesCount;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
