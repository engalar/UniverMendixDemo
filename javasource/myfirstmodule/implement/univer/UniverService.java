package myfirstmodule.implement.univer;

import com.mendix.thirdparty.org.json.JSONObject;

import myfirstmodule.implement.spring.SpringUtil;
import myfirstmodule.implement.univer.plugins.IPlugin;

public class UniverService {
    public static JSONObject process(JSONObject input) {

        var result = new JSONObject();
        var plugins = SpringUtil.context.getBeansOfType(IPlugin.class);
        Boolean isSuccess = true;
        for (var plugin : plugins.values()) {
            isSuccess = plugin.init(input);
            // once failed, stop processing
            if (!isSuccess) {
                break;
            }
        }
        if (!isSuccess) {
            // TODO: handle failure
            return result;
        }
        for (var plugin : plugins.values()) {
            plugin.result(result);
        }
        return result;
    }
}
