package com.undeadscythes.udsplugin.commands;

import com.undeadscythes.udsplugin.*;

/**
 * Display server rules.
 * @author UndeadScythes
 */
public class RulesCmd extends AbstractPlayerCommand {
    @Override
    public void playerExecute() {
        player.sendMessage(Color.MESSAGE + "--- Server Rules ---");
        for(String rules : Config.serverRules) {
            player.sendMessage(Color.TEXT + "- " + rules);
        }
    }
}
