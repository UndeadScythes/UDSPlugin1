package com.undeadscythes.udsplugin.commands;

import com.undeadscythes.udsplugin.*;
import org.bukkit.*;



/**
 * Lets a player get build rights and promotes them to member.
 * @author UndeadScythes
 */
public class AcceptRulesCmd extends CommandWrapper {
    @Override
    public final void playerExecute() {
        if(canAfford(UDSPlugin.getConfigInt(ConfigRef.BUILD_COST))) {
            player.setRank(PlayerRank.MEMBER);
            player.debit(UDSPlugin.getConfigInt(ConfigRef.BUILD_COST));
            UDSPlugin.sendBroadcast(player.getNick() + " has accepted the rules.");
            player.sendNormal("Thanks for accepting the rules, enjoy your stay on " + Bukkit.getServerName() + ".");
        }
    }
}
