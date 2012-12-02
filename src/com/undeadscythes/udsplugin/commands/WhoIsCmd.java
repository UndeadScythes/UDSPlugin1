package com.undeadscythes.udsplugin.commands;

import com.undeadscythes.udsplugin.*;

/**
 * Checks the identity of a player.
 * @author UndeadScythes
 */
public class WhoIsCmd extends AbstractPlayerCommand {
    /**
     * @inheritDocs
     */
    @Override
    public void playerExecute(SaveablePlayer player, String[] args) {
        SaveablePlayer target;
        if(numArgsHelp(1) && (target = getMatchingPlayer(args[0])) != null) {
            player.sendMessage(Color.MESSAGE + target.getNick() + " is " + target.getName() + ".");
        }
    }
}
