import org.junit.jupiter.api.Test;

import myfirstmodule.imps.SpringUtil;
import myfirstmodule.imps.UniverService;
import myfirstmodule.imps.plugins.IPlugin;

import com.mendix.thirdparty.org.json.JSONObject;

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
        var jsonObject = new JSONObject();
        jsonObject.put("operation", "subtract");
        var result = UniverService.process(jsonObject);

        assertEquals(8, result.getInt("objects"));
        assertFalse(result.getBoolean("test"));
    }
}
