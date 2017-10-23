package tk.spop.json;

import org.junit.Assert;
import org.junit.Test;

import lombok.val;

public class JsonsTest {

	@Test
	public void toProperties() {
		val json = TestUtils.getJson("test").asJsonArray().get(0).asJsonObject();
		val p = Jsons.toProperties(json);
		System.out.println(p);
		Assert.assertEquals("b", p.getProperty("vaca[1]"));
		Assert.assertEquals("3.323", p.getProperty("peix"));
		Assert.assertNull(p.getProperty("ou"));
	}

}
