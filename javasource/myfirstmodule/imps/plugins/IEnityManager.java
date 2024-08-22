package myfirstmodule.imps.plugins;

public interface IEnityManager {
    public <T extends Object> T execute(String id);
}