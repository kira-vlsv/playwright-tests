package otus.extensions;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Tracing;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import otus.modules.GuiceModules;
import java.nio.file.Paths;

public class UIExtension implements BeforeEachCallback, AfterEachCallback {

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

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        injector.getProvider(BrowserContext.class).get().tracing().stop(new Tracing.StopOptions().setPath(Paths.get(extensionContext.getUniqueId() + "trace.zip")));
    }
}
