package tk.spop.json.util;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import lombok.Data;
import lombok.val;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamUtils {

	@Data
	public static class ValueWithIndex<T> {
		private final int index;
		private final T value;
	}

	public static <T> Function<T, ValueWithIndex<T>> zipWithIndex() {
		val counter = new AtomicInteger(0);
		return x -> new ValueWithIndex<T>(counter.getAndIncrement(), x);
	}

}
