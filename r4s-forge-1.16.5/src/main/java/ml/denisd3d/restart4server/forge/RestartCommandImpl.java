package ml.denisd3d.restart4server.forge;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

public class RestartCommandImpl {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("restart")
                .requires(commandSource -> commandSource.hasPermissionLevel(3))
                .executes(context -> {
                    context.getSource().sendFeedback(new StringTextComponent(Restart4ServerForge.instance.setRestart()), true);
                    return 1;
                })
        );
    }
}
