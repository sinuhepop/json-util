package tk.spop.json.util;

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

		public <S> ValueWithIndex<S> withValue(S value) {
			return new ValueWithIndex<>(index, value);
		}
	}

	public static <T> Function<T, ValueWithIndex<T>> zipWithIndex() {
		val counter = Holder.of(0);
		return x -> {
			val current = counter.get();
			counter.set(current + 1);
			return new ValueWithIndex<>(current, x);
		};
	}

}
