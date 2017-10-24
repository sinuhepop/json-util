package tk.spop.json.util;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import lombok.val;

public class StreamUtilsTest {

	@Test
	public void test() {
		val sum = Arrays.asList("a", "bb", "ccc").stream() //
				.map(StreamUtils.zipWithIndex()) //
				.map(x -> x.withValue(x.getValue().length())) //
				.mapToInt(x -> x.getIndex() * x.getValue()) //
				.sum();
		Assert.assertEquals(8, sum);
	}

}
