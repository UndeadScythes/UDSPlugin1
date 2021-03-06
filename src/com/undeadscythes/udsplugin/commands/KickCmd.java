package com.undeadscythes.udsplugin.commands;

import com.undeadscythes.udsplugin.*;

/**
 * Kick a player from the server.
 * 
 * @author UndeadScythes
 */
public class KickCmd extends CommandHandler {
    @Override
    public final void playerExecute() {
        SaveablePlayer target;
        if(minArgsHelp(1) && (target = matchOnlinePlayer(arg(0))) != null) {
            if(!target.hasPerm(Perm.UNKICKABLE)) {
                String message = "You have been kicked for breaking the rules.";
                if(argsLength() > 1) {
                    message = argsToMessage(1);
                }
                target.getWorld().strikeLightningEffect(target.getLocation());
                target.kickPlayer(message);
                UDSPlugin.sendBroadcast(target.getNick() + " has been kicked for breaking the rules.");
            } else {
                player().sendError("You cannot kick this player.");
            }
        }
    }
}
