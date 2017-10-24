package tk.spop.json;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;

import lombok.val;
import lombok.experimental.UtilityClass;
import tk.spop.json.common.MapBuilder;
import tk.spop.json.util.Holder;
import tk.spop.json.util.StreamUtils;

@UtilityClass
public class Jsons {

	public static Map<String, Object> toMap(JsonObject object) {
		return toJavaObject(object);
	}

	public static List<Object> toList(JsonArray array) {
		return toJavaObject(array);
	}

	@SuppressWarnings("unchecked")
	public static <T> T toJavaObject(JsonValue value) {
		Holder<Object> holder = Holder.empty();
		val ctx = new MapBuilder.Context(holder);
		new MapBuilder().visitValue(ctx, value);
		return (T) holder.get();
	}

	public static Properties toProperties(JsonObject object) {
		val p = new Properties();
		toProperties(p, "", object);
		return p;
	}

	private static void toProperties(Properties p, String path, JsonValue json) {

		if (json.getValueType() == ValueType.OBJECT) {
			val prefix = path.isEmpty() ? "" : path + '.';
			json.asJsonObject().forEach((k, v) -> toProperties(p, prefix + k, v));

		} else if (json.getValueType() == ValueType.ARRAY) {
			json.asJsonArray().stream() //
					.map(StreamUtils.zipWithIndex()) //
					.forEach(x -> toProperties(p, path + '[' + x.getIndex() + ']', x.getValue()));

		} else if (json.getValueType() != ValueType.NULL) {
			if (json instanceof JsonString) {
				p.setProperty(path, ((JsonString) json).getString());
			} else {
				p.setProperty(path, json.toString());
			}
		}
	}
}
