package com.undeadscythes.udsplugin.commands;

import com.undeadscythes.udsplugin.*;
import org.apache.commons.lang.*;
import org.bukkit.*;
import org.bukkit.inventory.*;

/**
 * Send another player a gift.
 * @author UndeadScythes
 */
public class GiftCmd extends PlayerCommandExecutor {
    /**
     * @inheritDocs
     */
    @Override
    public void playerExecute(SaveablePlayer player, String[] args) {
        SaveablePlayer target;
        if(argsMoreEq(1) && (target = matchesPlayer(args[0])) != null) {
            ItemStack gift = player.getItemInHand().clone();
            if(gift.getType() != Material.AIR) {
                String message = Color.MESSAGE + "[Gifting Service] You have recieved a free gift!";
                if(args.length > 1) {
                    message = Color.MESSAGE + "[Gifting Service] ".concat(StringUtils.join(args, " ", 1, args.length - 1));
                }
                target.sendMessage(message);
                target.giveAndDrop(gift);
                player.setItemInHand(new ItemStack(Material.AIR));
                player.sendMessage(Color.MESSAGE + "Gift sent.");
            } else {
                player.sendMessage(Color.ERROR + "You have no gift selected.");
            }
        }
    }
}