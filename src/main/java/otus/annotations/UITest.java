package otus.annotations;

import org.junit.jupiter.api.extension.ExtendWith;
import otus.extensions.UIExtension;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ExtendWith(UIExtension.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UITest {
}
