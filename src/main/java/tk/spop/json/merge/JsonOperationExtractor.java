package tk.spop.json.merge;

import javax.json.JsonValue;

public interface JsonOperationExtractor {

	OperationInfo extract(String key, JsonValue value);
}
