package myfirstmodule.imps.plugins.entity;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mendix.thirdparty.org.json.JSONException;
import com.mendix.thirdparty.org.json.JSONObject;

import myfirstmodule.imps.plugins.IPlugin;

abstract class HashProtectedObject {
    private String hash;
}

class EnityMetadata extends HashProtectedObject {
    private String name;
    private String description;
    private String entityName;
    private String entityType;
    private String[] attributes;
    private String[] associations;
    private String[] permissions;
}

@Service
public class EntityManager implements IEnityManager, IPlugin {

    @Override
    public void init(JSONObject req) {
        // 获取补哈希保护的实体对象列表和权限配置模型数据
        // descerialize 对象列表和权限配置模型数据
        var objectMapper = new ObjectMapper();
        try {
            var entityMetadata = objectMapper.readValue(req.getJSONObject("entityPlugin").toString(),
                    EnityMetadata.class);
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void result(JSONObject res) {
        // 哈希内容防止篡改
        // 实体对象列的可读性和可修改性
        // 需要有模型配置支撑
        res.put("entityPlugin", 8);
    }

    @Override
    public boolean add(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public boolean remove(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public boolean update(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean exists(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exists'");
    }

    @Override
    public Object get(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

}