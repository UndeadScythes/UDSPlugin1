package com.undeadscythes.udsplugin.commands;

import com.undeadscythes.udsplugin.*;

/**
 * @author UndeadScythes
 */
public class DayCmd extends CommandHandler {
    @Override
    public void playerExecute() {
        player.getWorld().setTime(0);
        player.sendNormal("Summoning the sun.");
    }
}