package myfirstmodule.imps.plugins;

import org.springframework.stereotype.Service;

import com.mendix.thirdparty.org.json.JSONObject;

@Service
public class EntityManager implements IEnityManager, IPlugin {

    @Override
    public void init(JSONObject req) {
        System.out.println("Initializing Entity Manager");
    }

    @Override
    public <T> T execute(String id) {
        System.out.println("Executing Entity Manager");
        return null;
    }

    @Override
    public void result(JSONObject res) {
        System.out.println("Result Entity Manager");
        res.put("objects", 8);
    }

}