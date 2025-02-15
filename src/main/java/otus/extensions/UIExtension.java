package otus.extensions;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import otus.modules.GuiceModules;

public class UIExtension implements BeforeEachCallback {

    private Injector injector;

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        extensionContext.getTestInstance()
                .ifPresent(instance -> {
                            injector = Guice.createInjector(new GuiceModules());
                            injector.injectMembers(instance);
                        }
                );
    }
}
