package com.kacimiamine.velvety.command;

import java.io.File;
import java.util.Collections;
import java.util.List;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import static net.kyori.adventure.text.Component.text;

public class VelvetyCommand extends Command {

    public VelvetyCommand(String name) {
        super(name);
        this.description = "Velvety related commands";
        this.usageMessage = "/velvety reload";
        this.setPermission("velvety.command");
    }

    @Override
    public boolean execute(@NonNull CommandSender sender, @NonNull String commandLabel, String @NonNull [] args) {
        if (!this.testPermission(sender)) return true;

        if (args.length != 1 || !args[0].equals("reload")) {
            sender.sendMessage(text("Usage: " + this.usageMessage, NamedTextColor.RED));
            return false;
        }


        Command.broadcastCommandMessage(sender, text().color(NamedTextColor.RED)
            .append(text("Please note that this command is not supported and may cause issues."))
            .appendNewline()
            .append(text("If you encounter any issues please use the /stop command to restart your server."))
            .build()
        );

        MinecraftServer console = MinecraftServer.getServer();
        com.kacimiamine.velvety.config.VelvetyConfig.init((File) console.options.valueOf("velvety-settings-directory"));
        for (ServerLevel world : console.getAllLevels()) {
            world.velvetyConfig.init();
        }
        console.server.reloadCount++;

        Command.broadcastCommandMessage(sender, text("Reload complete.", NamedTextColor.GREEN));


        return true;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull final CommandSender sender, @NotNull final String alias, final @NotNull String @NotNull [] args) throws IllegalArgumentException {
        if (args.length == 1) {
            return Collections.singletonList("reload");
        }

        return Collections.emptyList();
    }
}
