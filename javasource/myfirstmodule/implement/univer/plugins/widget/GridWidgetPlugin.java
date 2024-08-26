package myfirstmodule.implement.univer.plugins.widget;

import org.springframework.stereotype.Service;

import com.mendix.thirdparty.org.json.JSONObject;

import myfirstmodule.implement.univer.plugins.IPlugin;

@Service
public class GridWidgetPlugin implements IPlugin{

    @Override
    public boolean init(JSONObject req) {
        return true;
    }

    @Override
    public void result(JSONObject res) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'result'");
    }
    
}
