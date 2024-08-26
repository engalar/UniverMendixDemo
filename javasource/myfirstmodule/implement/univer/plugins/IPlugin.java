package myfirstmodule.implement.univer.plugins;

import com.mendix.thirdparty.org.json.JSONObject;

public interface IPlugin{
    public void init(JSONObject req);
    public void result(JSONObject res);
}