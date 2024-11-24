package whatsapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
	@PropertySource("classpath:dateTwilioAuth.properties")
})
public class ConfigDateTwilioAuth {

}
