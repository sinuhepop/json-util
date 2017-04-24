package tk.spop.json.merge;

import java.util.Map;

import javax.json.Json;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;

import lombok.val;

public class JsonMerger {
	
	private Operation defaultOperation;
	private Map<String, Operation> operations;
	

	@SuppressWarnings("unchecked")
	public <T extends JsonValue> T merge(T a, T b) {
		if (b == null) {
			return a;
		} else if (a == null || a.getValueType() != ValueType.OBJECT || b.getValueType() != ValueType.OBJECT) {
			return b;
		}
		val oa = a.asJsonObject();
		val ob = b.asJsonObject();
		val oc = Json.createObjectBuilder();
		val keys = oa.keySet();
		keys.addAll(ob.keySet());
		for (val key : keys) {
			oc.add(key, merge(oa.get(key), ob.get(key)));
		}
		return (T) oc.build();
	}

}
