package ml.denisd3d.restart4server.forge;

import ml.denisd3d.restart4server.core.Restart4Server;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;

@Mod("restart4server")
@Mod.EventBusSubscriber(Dist.DEDICATED_SERVER)
public class Restart4ServerForge {
    public static Restart4Server instance = new Restart4Server(new MinecraftImpl());

    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        RestartCommandImpl.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onServerStarting(FMLServerAboutToStartEvent event) {
        instance.onServerStarting();
    }
}
