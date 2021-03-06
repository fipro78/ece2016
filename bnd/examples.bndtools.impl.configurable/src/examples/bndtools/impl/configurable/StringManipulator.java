package examples.bndtools.impl.configurable;

import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import examples.service.api.StringModifier;

@ObjectClassDefinition(name = "StringManipulator Configuration")
@interface StringManipulatorConfig {
	String prefix() default "";

	String suffix() default "";

	int repeat() default 1;

	boolean uppercase() default false;
}

@Component(configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = StringManipulatorConfig.class)
public class StringManipulator implements StringModifier {

	StringManipulatorConfig cfg;

	@Activate
	public void activate(StringManipulatorConfig cfg) {
		System.out.println(this.getClass() + " activated");
		this.cfg = cfg;
	}

	public void deactivate() {
		System.out.println(this.getClass() + " de-activated");
	}

	@Override
	public String modify(String message) {
		String rv = "";
		for (int i = 0; i < cfg.repeat(); i++) {
			rv += message;
		}
		return cfg.prefix() + rv + cfg.suffix();
	}

}
