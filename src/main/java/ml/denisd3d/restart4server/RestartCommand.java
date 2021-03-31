package ml.denisd3d.restart4server;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

import java.io.File;
import java.io.IOException;

public class RestartCommand {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("restart").requires(commandSource -> commandSource.hasPermissionLevel(4)).executes(context -> {
            try {
                File file = new File(new File("").getAbsoluteFile().getParentFile(), "restart");
                if (file.createNewFile())
                {
                    context.getSource().sendFeedback(new StringTextComponent("Restarting the server"), true);
                    context.getSource().getServer().initiateShutdown(false);
                    return 1;
                }
                else {
                    context.getSource().sendErrorMessage(new StringTextComponent("An error happened. Perhaps the server is already set to restart ?"));
                    return 0;
                }
            } catch (IOException e) {
                context.getSource().sendErrorMessage(new StringTextComponent("An error happened. Please check the log"));
                e.printStackTrace();
                return -1;
            }
        }));
    }
}
