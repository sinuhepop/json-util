package tk.spop.json.spring.config;

import javax.json.JsonValue;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AppTest {

	@Value("json:xx")
	private JsonValue peix;

	@Value("${cuc}")
	private String cuc;

	@Test
	public void test() {
		Assert.assertEquals(JsonValue.EMPTY_JSON_OBJECT, peix);
	}

}
