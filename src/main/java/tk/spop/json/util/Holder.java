package tk.spop.json.util;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Holder<T> implements Supplier<T>, Consumer<T> {

	private T value;

	public static <X> Holder<X> of(X value) {
		return new Holder<X>().set(value);
	}

	public static <X> Holder<X> ofType(Class<X> type) {
		return empty();
	}

	public static <X> Holder<X> empty() {
		return new Holder<>();
	}

	public Holder<T> set(T value) {
		this.value = value;
		return this;
	}

	@Override
	public T get() {
		return value;
	}

	@Override
	public void accept(T t) {
		set(t);
	}

	public Holder<T> replace(Function<T, T> function) {
		return set(function.apply(get()));
	}

	public <S> Holder<S> map(Function<T, S> function) {
		return of(function.apply(get()));
	}

	@Override
	public String toString() {
		return "<" + value + ">";
	}

}
