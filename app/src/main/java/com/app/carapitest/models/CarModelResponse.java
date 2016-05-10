package com.app.carapitest.models;

import java.util.ArrayList;
import java.util.List;

public class CarModelResponse {


    private List<Model> models = new ArrayList<Model>();
    private Integer modelsCount;

    /**
     *
     * @return
     * The models
     */
    public List<Model> getModels() {
        return models;
    }

    /**
     *
     * @param models
     * The models
     */
    public void setModels(List<Model> models) {
        this.models = models;
    }

    /**
     *
     * @return
     * The modelsCount
     */
    public Integer getModelsCount() {
        return modelsCount;
    }

    /**
     *
     * @param modelsCount
     * The modelsCount
     */
    public void setModelsCount(Integer modelsCount) {
        this.modelsCount = modelsCount;
    }

}
