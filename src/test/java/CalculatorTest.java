import org.junit.jupiter.api.Test;

import myfirstmodule.imps.SpringUtil;
import myfirstmodule.imps.UniverService;
import myfirstmodule.imps.plugins.IPlugin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mendix.thirdparty.org.json.JSONObject;
import com.mendix.thirdparty.org.json.JSONArray;
import myfirstmodule.imps.plugins.JsonDemo;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        assertEquals(5, calculator.add(2, 3));
        // throw new RuntimeException("Test failed");
        var plugins = SpringUtil.context.getBeansOfType(IPlugin.class);
        assertEquals(2, plugins.size());
    }

    @Test
    public void testSubtract() {
        String jsonString = "{\n" +
                "    \"objects\": [\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"hash\": \"abc123\",\n" +
                "            \"columns\": {\n" +
                "                \"name\": \"John Doe\",\n" +
                "                \"age\": 30,\n" +
                "                \"email\": \"johndoe@example.com\"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 2,\n" +
                "            \"hash\": \"def456\",\n" +
                "            \"columns\": {\n" +
                "                \"name\": \"Jane Smith\",\n" +
                "                \"age\": 25,\n" +
                "                \"email\": \"janesmith@example.com\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonString);

        // Convert JSONObject to POJO
        ObjectMapper objectMapper = new ObjectMapper();
        JsonDemo jsonDemo = null;
        try {
            jsonDemo = objectMapper.readValue(jsonObject.toString(), JsonDemo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Example assertions
        assertEquals(2, jsonDemo.getObjects().size());
        assertEquals(1, jsonDemo.getObjects().get(0).getId());
        assertEquals("abc123", jsonDemo.getObjects().get(0).getHash());
        assertEquals("John Doe", jsonDemo.getObjects().get(0).getColumns().get("name"));

        var result = UniverService.process(jsonObject);

        assertEquals(8, result.getInt("objects"));
        assertFalse(result.getBoolean("test"));
    }
}
