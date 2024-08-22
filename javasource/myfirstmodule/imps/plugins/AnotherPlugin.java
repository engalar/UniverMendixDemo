package myfirstmodule.imps.plugins;

import org.springframework.stereotype.Service;

import com.mendix.thirdparty.org.json.JSONObject;

@Service
public class AnotherPlugin implements IPlugin {

    @Override
    public void init(JSONObject req) {
        System.out.println("AnotherPlugin initialized");
    }

    @Override
    public void result(JSONObject res) {
        System.out.println("AnotherPlugin result received");
        res.put("test", false);
    }

    
}