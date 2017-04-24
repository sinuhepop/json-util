package tk.spop.json.spring.el;

import org.junit.Test;

import lombok.val;

public class ExpressionResolverTest {

	@Test
	public void test() {
		val resolver = new ExpressionResolver();
		val json = TestUtils.getJson("expressions");
		val mod = resolver.resolve(json);
		System.out.println(mod);
	}

}
