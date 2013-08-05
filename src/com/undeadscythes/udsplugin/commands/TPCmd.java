package com.undeadscythes.udsplugin.commands;

import com.undeadscythes.udsplugin.*;

/**
 * Teleport players to each other.
 * @author UndeadScythes
 */
public class TPCmd extends CommandHandler {
    @Override
    public void playerExecute() {
        SaveablePlayer targetFrom;
        SaveablePlayer targetTo;
        if(argsLength() == 1) {
            if((targetTo = matchesPlayer(arg(0))) != null && isOnline(targetTo) && notSelf(targetTo)) {
                player().setBackPoint();
                player().teleport(targetTo);
            }
        } else if(numArgsHelp(2) && (targetFrom = matchesPlayer(arg(0))) != null && isOnline(targetFrom) && (targetTo = matchesPlayer(arg(1))) != null && isOnline(targetTo)) {
            targetFrom.setBackPoint();
            targetFrom.teleport(targetTo);
        }
    }
}
