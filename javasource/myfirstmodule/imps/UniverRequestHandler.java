package myfirstmodule.imps;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;

import com.mendix.core.Core;
import com.mendix.externalinterface.connector.RequestHandler;
import com.mendix.m2ee.api.IMxRuntimeRequest;
import com.mendix.m2ee.api.IMxRuntimeResponse;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.thirdparty.org.json.JSONArray;
import com.mendix.thirdparty.org.json.JSONObject;

import communitycommons.XPath;

public class UniverRequestHandler extends RequestHandler {

    @Override
    protected void processRequest(IMxRuntimeRequest request, IMxRuntimeResponse response, String path)
            throws Exception {
        // 读取请求体并解析为JSON对象
        var req = request.getHttpServletRequest();
        String jsonString = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        var jsonObject = new JSONObject(jsonString);

        // 提取参数
        int limit = jsonObject.getInt("limit");
        int offset = jsonObject.getInt("offset");
        var columns = jsonObject.getJSONArray("columns");
        String[] sorts = jsonObject.getString("sort").split(",");
        String entity = jsonObject.getString("entity");

        // 创建系统上下文并构建XPath查询
        IContext context = Core.createSystemContext();
        var xpath = XPath.create(context, entity).offset(offset).limit(limit);
        for (String column : sorts) {
            String[] parts = column.trim().split("\\s+");
            String attribute = parts[0];
            String direction = parts.length > 1 ? parts[1] : "asc";
            if ("desc".equalsIgnoreCase(direction)) {
                xpath.addSortingDesc(attribute);
            } else {
                xpath.addSortingAsc(attribute);
            }
        }

        // 执行查询并构建响应
        var objects = xpath.allMendixObjects();
        var result = new JSONObject();
        var objectsArray = new JSONArray();
        for (var object : objects) {
            var jo2 = new JSONObject();
            var columns2 = new JSONObject();
            for (int i = 0; i < columns.length(); i++) {
                String column = columns.getString(i);
                var value = object.getValue(context, column);
                columns2.put(column, value);
            }
            jo2.put("id", object.getId().toLong());
            jo2.put("hash", JSONSummarizer.getMD5Summary(jo2, "someSecretPassword"));
            jo2.put("columns", columns2);
            objectsArray.put(jo2);
        }
        result.put("objects", objectsArray);

        // 设置响应内容
        response.setContentType("application/json");
        response.setStatus(response.OK);
        response.getOutputStream().write(result.toString().getBytes());
    }
}

class Test {
    public static void main(String[] args) {
        var jsonString = "{" +
                "limit: 2," +
                "offset: 1," +
                "sort: \"Attribute desc, Name asc\"," +
                "columns: [\"Name\", \"Attribute\", \"Value\"]," +
                "entity: \"Entity\"" +
                "}";
        var jo = new JSONObject(jsonString);
    }
}

class JSONSummarizer {

    public static void main(String[] args) {
        // 创建示例的 JSONObject
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "John Doe");
        jsonObject.put("age", 30);
        jsonObject.put("city", "New York");

        // 服务端密码
        String serverPassword = "mySecretPassword";

        // 获取 JSON 内容的摘要
        String summary = getMD5Summary(jsonObject, serverPassword);

        // 输出摘要
        System.out.println("JSON 摘要: " + summary);

        // 验证摘要
        boolean isValid = verifyMD5Summary(jsonObject, serverPassword, summary);
        System.out.println("摘要验证结果: " + isValid);
    }

    public static String getMD5Summary(JSONObject jsonObject, String serverPassword) {
        try {
            // 将 JSONObject 转为字符串
            String jsonString = jsonObject.toString();

            // 结合服务端密码
            String combinedString = jsonString + serverPassword;

            // 创建 MD5 哈希计算实例
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashInBytes = md.digest(combinedString.getBytes());

            // 转换 byte 数组为十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean verifyMD5Summary(JSONObject jsonObject, String serverPassword, String storedSummary) {
        // 重新计算摘要
        String recalculatedSummary = getMD5Summary(jsonObject, serverPassword);

        // 比较重新计算的摘要与存储的摘要
        return recalculatedSummary != null && recalculatedSummary.equals(storedSummary);
    }
}