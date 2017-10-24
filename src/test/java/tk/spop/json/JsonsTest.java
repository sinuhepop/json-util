package tk.spop.json;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import lombok.val;

public class JsonsTest {

	@Test
	public void toJavaObject() {
		val json = TestUtils.getJson("test");

		val map = Jsons.toMap(json.asJsonArray().get(0).asJsonObject());
		Assert.assertEquals(Arrays.asList("a", "b"), map.get("vaca"));

		val list = Jsons.toList(json.asJsonArray());
		val object = Jsons.toJavaObject(json);
		Assert.assertEquals(list, object);
		Assert.assertEquals(map, list.get(0));
	}

	@Test
	public void toProperties() {
		val json = TestUtils.getJson("test").asJsonArray().get(0).asJsonObject();
		val p = Jsons.toProperties(json);
		Assert.assertEquals("b", p.getProperty("vaca[1]"));
		Assert.assertEquals("3.323", p.getProperty("peix"));
		Assert.assertNull(p.getProperty("ou.missing"));
	}

}
