import org.junit.jupiter.api.Test;

import myfirstmodule.implement.spring.SpringUtil;
import myfirstmodule.implement.univer.UniverService;
import myfirstmodule.implement.univer.plugins.IPlugin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mendix.thirdparty.org.json.JSONObject;
import com.mendix.thirdparty.org.json.JSONArray;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        assertEquals(5, calculator.add(2, 3));

        var plugins = SpringUtil.context.getBeansOfType(IPlugin.class);
        assertEquals(6, plugins.size());
    }

    @Test
    public void testSubtract() {
        String jsonString = """
                {
                    "entityPlugin": {
                        "objects": [
                            {
                                "id": 1,
                                "hash": "abc123",
                                "entity": "MyFirstModule.Entity",
                                "columns": {
                                    "name": "John Doe",
                                    "age": 30,
                                    "email": "johndoe@example.com"
                                }
                            },
                            {
                                "id": 2,
                                "hash": "def456",
                                "entity": "MyFirstModule.Entity",
                                "columns": {
                                    "name": "Jane Smith",
                                    "age": 25,
                                    "email": "janesmith@example.com"
                                }
                            }
                        ]
                    }
                }
                        """;

        JSONObject jsonObject = new JSONObject(jsonString);

        var result = UniverService.process(jsonObject);

        assertEquals(8, result.getInt("entityPlugin"));
    }
}
