package tk.spop.json.merge;

import javax.json.JsonValue;

public interface Operation {
	
	JsonValue apply(JsonValue a, JsonValue b, MergeContext ctx);

}
