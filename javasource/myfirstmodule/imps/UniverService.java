package myfirstmodule.imps;

import com.mendix.thirdparty.org.json.JSONObject;

import myfirstmodule.imps.plugins.IPlugin;

public class UniverService {
    public static JSONObject process(JSONObject input) {

        var result = new JSONObject();
        var plugins = SpringUtil.context.getBeansOfType(IPlugin.class);
        for (var plugin : plugins.values()) {
            plugin.init(input);
        }
        for (var plugin : plugins.values()) {
            plugin.result(result);
        }
        return result;
    }
}
