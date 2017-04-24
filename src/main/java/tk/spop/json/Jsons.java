package tk.spop.json;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

import lombok.val;
import tk.spop.json.common.MapBuilder;

public class Jsons {

	public static Map<String, Object> toMap(JsonObject object) {
		return toJavaObject(object);
	}

	public static List<Object> toList(JsonArray array) {
		return toJavaObject(array);
	}

	@SuppressWarnings("unchecked")
	public static <T> T toJavaObject(JsonValue value) {
		Object[] result = new Object[1];
		val ctx = new MapBuilder.Context(x -> result[0] = x);
		new MapBuilder().visitValue(ctx, value);
		return (T) result[0];
	}

	public static Properties toProperties(JsonObject object) {
		val p = new Properties();
		p.setProperty(key, value);

		return p;
	}
}
