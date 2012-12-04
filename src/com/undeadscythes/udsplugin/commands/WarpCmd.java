package com.undeadscythes.udsplugin.commands;

import com.undeadscythes.udsplugin.*;
import java.util.*;
import org.apache.commons.lang.*;

/**
 * Use warp points.
 * @author UndeadScythes
 */
public class WarpCmd extends AbstractPlayerCommand {
    @Override
    public void playerExecute() {
        Warp warp;
        if(args.length == 0) {
            final Set<String> warps = new TreeSet<String>();
            for(Warp test : UDSPlugin.getWarps().values()) {
                if(player.getRank().compareTo(test.getRank()) >= 0) {
                    warps.add(test.getName() + (test.getPrice() > 0 ? " (" + test.getPrice() + ")" : ""));
                }
            }
            if(warps.isEmpty()) {
                player.sendMessage(Color.MESSAGE + "You don't have access to any warps.");
            } else {
                player.sendMessage(Color.MESSAGE + "Available warps (with prices):");
                player.sendMessage(Color.TEXT + StringUtils.join(warps.toArray(), ", "));
            }
        } else if(numArgsHelp(1) && (warp = getWarp(args[0])) != null && hasRank(warp.getRank()) && canAfford(warp.getPrice())) {
            player.debit(warp.getPrice());
            player.teleport(warp.findSafePlace());
        }
    }
}
