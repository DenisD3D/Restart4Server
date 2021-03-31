package ml.denisd3d.restart4server;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.CrashReportExtender;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppedEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

@Mod("restart4server")
public class Restart4Server {

    private static final Logger LOGGER = LogManager.getLogger();

    public Restart4Server() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerCommand(RegisterCommandsEvent event) {
        if (FMLLoader.getDist().isDedicatedServer()) {
            RestartCommand.register(event.getDispatcher());
        }
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerAboutToStartEvent event) {
        File file = new File(new File("").getAbsoluteFile().getParentFile(), "restart");
        if (file.exists()) {
            if (file.delete()) {
                LOGGER.info("Removed restart file");
            } else {
                LOGGER.error("Can't remove restart file");
            }
        }

        CrashReportExtender.registerCrashCallable("Server Restart", () -> {
            if (file.createNewFile()) {
                System.out.println("Crash detected, restart file created");
            }
            else {
                System.out.println("Crash detected, but can't create restart file");
            }
            return "Server set to restart";
        });
    }
}
