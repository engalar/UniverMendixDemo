package myfirstmodule.implement.univer.plugins.save;

import org.springframework.stereotype.Service;

import com.mendix.thirdparty.org.json.JSONObject;

import myfirstmodule.implement.univer.plugins.IPlugin;

/**
 * This is the implementation of the SavePlugin with replication.jar.
 */
@Service
public class SavePlugin implements IPlugin {

    @Override
    public boolean init(JSONObject req) {
        return true;
    }

    @Override
    public void result(JSONObject res) {
    }

}
