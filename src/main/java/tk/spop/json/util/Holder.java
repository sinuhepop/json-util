package tk.spop.json.util;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Holder<T> implements Supplier<T>, Consumer<T> {

	private T value;

	public static <X> Holder<X> of(X value) {
		return new Holder<X>().setValue(value);
	}

	public static <X> Holder<X> ofType(Class<X> type) {
		return empty();
	}

	public static <X> Holder<X> empty() {
		return new Holder<>();
	}

	@Override
	public void accept(T t) {
		setValue(t);
	}

	@Override
	public T get() {
		return getValue();
	}

	public Holder<T> replace(Function<T, T> function) {
		return setValue(function.apply(getValue()));
	}

	public <S> Holder<S> map(Function<T, S> function) {
		return of(function.apply(getValue()));
	}

}
