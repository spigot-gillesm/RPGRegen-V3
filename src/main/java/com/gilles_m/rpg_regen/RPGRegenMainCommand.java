package com.gilles_m.rpg_regen;

import com.github.spigot_gillesm.command_lib.MainCommand;
import com.github.spigot_gillesm.command_lib.SimpleCommand;
import com.github.spigot_gillesm.format_lib.Formatter;
import org.bukkit.command.CommandSender;

import java.util.List;

@MainCommand
class RPGRegenMainCommand extends SimpleCommand {

    protected RPGRegenMainCommand() {
        super("rpgregen");

        setAliases(List.of("rpgreg", "rpgr", "rr"));
        setPlayerCommand(false);
        setDescription("RPGRegen main command");
        setPermission("rpgregen");

        new ReloadCommand(this);
    }

    @Override
    protected void run(final CommandSender commandSender, final String[] args) {
        if(args.length == 0) {
            displayHelp(commandSender);
        }
    }

    private static class ReloadCommand extends SimpleCommand {

        private ReloadCommand(final SimpleCommand parentCommand) {
            super(parentCommand, "reload");

            setAliases(List.of("rel", "r"));
            setPlayerCommand(false);
            setDescription("Reload the plugin");
            setPermission("rpgregen.reload");
        }

        @Override
        protected void run(final CommandSender commandSender, final String[] args) {
            if(args.length > 0) {
                Formatter.tell(commandSender, "&cThis command takes no arguments");
                return;
            }
            Formatter.tell(commandSender, "Reloading plugin...");
            RPGRegen.getInstance().loadObjects();
            RegeneratorManager.getInstance().reload();
            Formatter.tell(commandSender, "&aDone!");
        }

    }

}
