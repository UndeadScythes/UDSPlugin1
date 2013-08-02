package com.undeadscythes.udsplugin.commands;

import com.undeadscythes.udsplugin.Color;
import com.undeadscythes.udsplugin.*;
import org.bukkit.*;

/**
 * Toggles a players AFK mode.
 * @author UndeadScythes
 */
public class AfkCmd extends CommandHandler {
    @Override
    public final void playerExecute() {
        if(notPinned() && isNotNearMobs()) {
            if(player.toggleAfk()) {
                Bukkit.broadcastMessage(Color.TEXT + "*" + player.getNick() + " is now afk.");
            } else {
                Bukkit.broadcastMessage(Color.TEXT + "*" + player.getNick() + " is no longer afk.");
            }
        }
    }
}
