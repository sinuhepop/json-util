package tk.spop.json.util;

import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import lombok.val;

public class HolderTest {

	@Test
	public void test1() {
		val h1 = Holder.ofType(Integer.class) //
				.set(5) //
				.replace(x -> x * 2) //
				.map(Integer::longValue);
		Assert.assertEquals(Holder.of(10L), h1);
	}

	@Test
	public void test2() {
		val dst = Holder.ofType(String.class);
		Stream.generate(Holder.of("x")) //
				.limit(1) //
				.forEach(dst);
		Assert.assertEquals("<x>", dst.toString());
	}

}
