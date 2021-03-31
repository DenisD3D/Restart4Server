package ml.denisd3d.restart4server.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class Restart4Server {
    private static final Logger LOGGER = LogManager.getLogger();
    private final IMinecraft iMinecraft;

    public Restart4Server(IMinecraft iMinecraft) {
        this.iMinecraft = iMinecraft;
    }

    public void onServerStarting() {
        File file = new File(new File("").getAbsoluteFile().getParentFile(), "restart");
        if (file.exists()) {
            if (file.delete()) {
                LOGGER.info("Removed restart file");
            } else {
                LOGGER.error("Can't remove restart file");
            }
        }

        this.iMinecraft.registerCrashReporter("Server Restart", () -> {
            if (file.createNewFile()) {
                LOGGER.info("Crash detected, restart file created");
                return "Server set to restart";
            }
            else {
                LOGGER.error("Crash detected, but can't create restart file");
                return "Can't auto restart the server";
            }
        });
    }

    public String setRestart() {
        try {
            File file = new File(new File("").getAbsoluteFile().getParentFile(), "restart");
            if (file.createNewFile())
            {
                iMinecraft.initiateShutdown();
                return "Restarting the server";
            }
            else {
                return "An error happened. Perhaps the server is already set to restart ?";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "An error happened. Please check the log";
        }
    }
}
