package tk.spop.json.spring.config;

import java.util.Optional;

import javax.json.JsonValue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.convert.support.ConfigurableConversionService;

import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.experimental.Delegate;

@RequiredArgsConstructor
public class JsonConversionService implements ConfigurableConversionService {

	private final String prefix;
	private final ConversionService conversionService;

	@Delegate
	private final ConverterRegistry converterRegistry;

	@Override
	public boolean canConvert(TypeDescriptor sourceType, TypeDescriptor targetType) {
		return findPath(targetType).isPresent() || //
				(conversionService != null && conversionService.canConvert(sourceType, targetType));
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		val optPath = findPath(targetType);
		if (optPath.isPresent()) {
			return JsonValue.EMPTY_JSON_OBJECT;
		}
		return conversionService.convert(source, sourceType, targetType);
	}

	@Override
	public boolean canConvert(Class<?> sourceType, Class<?> targetType) {
		return conversionService != null && conversionService.canConvert(sourceType, targetType);
	}

	@Override
	public <T> T convert(Object source, Class<T> targetType) {
		return conversionService.convert(source, targetType);
	}

	protected Optional<String> findPath(TypeDescriptor targetType) {
		return Optional.ofNullable(targetType.getAnnotation(Value.class)) //
				.filter(x -> x.value().startsWith(prefix)) //
				.map(x -> x.value().substring(prefix.length()));
	}

}
