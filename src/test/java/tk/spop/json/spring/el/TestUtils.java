package tk.spop.json.spring.el;

import java.nio.charset.StandardCharsets;

import javax.json.JsonValue;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr353.JSR353Module;

import lombok.SneakyThrows;
import lombok.val;

public class TestUtils {

	@SneakyThrows
	public static JsonValue getJson(String name) {
		val rsc = new ClassPathResource("json/" + name + ".json");
		val content = StreamUtils.copyToString(rsc.getInputStream(), StandardCharsets.UTF_8);
		val mapper = new ObjectMapper();
		mapper.registerModule(new JSR353Module());
		val node = mapper.readTree(content);
		return mapper.treeToValue(node, JsonValue.class);
	}

}
