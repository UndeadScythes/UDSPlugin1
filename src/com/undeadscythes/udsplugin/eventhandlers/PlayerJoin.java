package com.undeadscythes.udsplugin.eventhandlers;

import com.undeadscythes.udsplugin.SaveablePlayer.Rank;
import com.undeadscythes.udsplugin.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.*;

/**
 * Description
 * @author UndeadScythes
 */
public class PlayerJoin implements Listener {
    @EventHandler
    public void onEvent(PlayerJoinEvent event) {
        String playerName = event.getPlayer().getName();
        SaveablePlayer player;
        if(UDSPlugin.getPlayers().containsKey(playerName)) {
            player = UDSPlugin.getPlayers().get(playerName);
            player.wrapPlayer(event.getPlayer());
            UDSPlugin.getOnlinePlayers().put(playerName, player);
        } else {
            player = new SaveablePlayer(event.getPlayer());
            UDSPlugin.getPlayers().put(playerName, player);
            UDSPlugin.getOnlinePlayers().put(playerName, player);
            if(player.getName().equals(Config.SERVER_OWNER)) {
                player.setRank(Rank.OWNER);
                player.sendMessage(ChatColor.GOLD + "Welcome to your new server, I hope everything goes well.");
            } else {
                Bukkit.broadcastMessage(Color.BROADCAST + "A new player, free gifts for everyone!");
                for(SaveablePlayer onlinePlayer : UDSPlugin.getOnlinePlayers().values()) {
                    onlinePlayer.giveAndDrop(new ItemStack(Config.WELCOME_GIFT));
                }
            }
            player.quietTeleport(UDSPlugin.getWarps().get("spawn"));
        }
        if(UDSPlugin.serverInLockdown && !player.hasLockdownPass()) {
            player.kickPlayer("The server is currently in lockdown please check back later.");
        } else {
            player.sendMessage(Color.MESSAGE + Config.WELCOME);
            if(player.getRank().equals(Rank.DEFAULT)) {
                player.sendMessage(Color.MESSAGE + "Kill monsters or trade with players to earn " + Config.BUILD_COST + " credits then type /acceptrules in chat.");
            } else if(player.getRank().compareTo(Rank.MOD) >= 0) {
                player.sendMessage(Color.MESSAGE + "As a member of staff be polite helpful and lead by example.");
            }
            event.setJoinMessage(Color.BROADCAST + player.getDisplayName() + (player.isInClan() ? " of " + player.getClan() : "") + " has joined.");
        }
    }
}