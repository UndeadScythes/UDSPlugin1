package com.undeadscythes.udsplugin.commands;

import com.undeadscythes.udsplugin.*;

/**
 * Allows players to use /chat help.
 * @author UndeadScythes
 */
public class ChatCmd extends CommandWrapper {
    @Override
    public void playerExecute() {
        if(args.length == 0) {
            sendHelp(1);
        } else {
            subCmdHelp();
        }
    }
}
