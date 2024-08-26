package myfirstmodule.implement.univer.plugins.query;

import org.springframework.stereotype.Service;

import com.mendix.thirdparty.org.json.JSONObject;

import myfirstmodule.implement.univer.plugins.IPlugin;

/** 
 * dependecy mxmodelreflection and oql module
 */
@Service
public class QueryPlugin implements IPlugin {

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
