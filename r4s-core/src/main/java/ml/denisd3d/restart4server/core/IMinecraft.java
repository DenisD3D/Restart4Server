package ml.denisd3d.restart4server.core;

import java.util.concurrent.Callable;

public interface IMinecraft {
    void registerCrashReporter(String name, Callable<String> reportGenerator);

    void initiateShutdown();
}
