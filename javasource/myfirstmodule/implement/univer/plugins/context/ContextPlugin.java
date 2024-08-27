package myfirstmodule.implement.univer.plugins.context;

import org.springframework.stereotype.Service;

import com.mendix.thirdparty.org.json.JSONObject;

import myfirstmodule.implement.univer.plugins.IPlugin;

/**
 * e.g. Grid need dropdown selection value from context.
 */
@Service
public class ContextPlugin implements IPlugin {

    @Override
    public boolean init(JSONObject req) {
        return true;
    }

    @Override
    public void result(JSONObject res) {
    }
    
}
