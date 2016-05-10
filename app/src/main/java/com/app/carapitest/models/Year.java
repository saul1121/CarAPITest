
package com.app.carapitest.models;

import java.util.ArrayList;
import java.util.List;

public class Year {

    private Integer id;
    private Integer year;
    private List<Style> styles = new ArrayList<Style>();

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * @param year The year
     */
    public void setYear(Integer year) {
        this.year = year;
    }
    /**
     *
     * @return
     * The styles
     */
    public List<Style> getStyles() {
        return styles;
    }

    /**
     *
     * @param styles
     * The styles
     */
    public void setStyles(List<Style> styles) {
        this.styles = styles;
    }
}
