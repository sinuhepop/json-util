package tk.spop.json.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.ConversionService;

@Configuration
@PropertySource("config.properties")
public class AppConfig {

	@Bean
	public ConversionService conversionService() {
		return new JsonConversionService("json:", null, null);
	}

}
