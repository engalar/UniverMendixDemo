package myfirstmodule.implement.univer.plugins.entity.model;

import java.util.Map;

public class EnityData extends HashProtectedObject {
    private Integer id;
    private String entity;
    private Map<String, Object> columns;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public Map<String, Object> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, Object> columns) {
        this.columns = columns;
    }
}