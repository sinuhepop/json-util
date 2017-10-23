package tk.spop.json.util;

import org.junit.Assert;
import org.junit.Test;

import lombok.val;

public class HolderTest {

	@Test
	public void test() {

		val h1 = Holder.ofType(Integer.class) //
				.setValue(5) //
				.replace(x -> x * 2) //
				.map(Integer::longValue);
		Assert.assertEquals(Holder.of(10L), h1);
	}

}
