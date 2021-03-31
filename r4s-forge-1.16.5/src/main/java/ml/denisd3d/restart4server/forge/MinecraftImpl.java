package ml.denisd3d.restart4server.forge;

import ml.denisd3d.restart4server.core.IMinecraft;
import net.minecraftforge.fml.CrashReportExtender;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.util.concurrent.Callable;

public class MinecraftImpl implements IMinecraft {

    @Override
    public void registerCrashReporter(String name, Callable<String> reportGenerator) {
        CrashReportExtender.registerCrashCallable(name, reportGenerator);
    }

    @Override
    public void initiateShutdown() {
        ServerLifecycleHooks.getCurrentServer().initiateShutdown(false);
    }
}
