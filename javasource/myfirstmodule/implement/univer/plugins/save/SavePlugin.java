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
    public void init(JSONObject req) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'init'");
    }

    @Override
    public void result(JSONObject res) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'result'");
    }

}
