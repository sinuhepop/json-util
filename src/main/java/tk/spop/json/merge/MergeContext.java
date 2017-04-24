package tk.spop.json.merge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class MergeContext {

	private final String key;
	private final List<String> args;
	private final Map<String, Object> attributes = new HashMap<>();
	private final JsonMerger merger;

}
