package tk.spop.json.merge;

import java.util.List;

import lombok.Data;

@Data
public class OperationInfo {
	private final String key;
	private final List<String> args;
}
