package myfirstmodule.imps.plugins.entity;

public interface IEnityManager {
    public boolean add(String id);
    public boolean remove(String id);
    public boolean update(String id);
    public boolean exists(String id);
    public Object get(String id);
}