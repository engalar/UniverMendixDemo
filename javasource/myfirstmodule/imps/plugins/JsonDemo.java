package myfirstmodule.imps.plugins;

import java.util.List;
import java.util.Map;

public class JsonDemo {
    private List<ObjectItem> objects;

    // Getters and setters
    public List<ObjectItem> getObjects() {
        return objects;
    }

    public void setObjects(List<ObjectItem> objects) {
        this.objects = objects;
    }

    public static class ObjectItem {
        private int id;
        private String hash;
        private Map<String, Object> columns;

        // Getters and setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public Map<String, Object> getColumns() {
            return columns;
        }

        public void setColumns(Map<String, Object> columns) {
            this.columns = columns;
        }
    }
}