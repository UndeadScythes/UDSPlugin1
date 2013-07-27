package com.undeadscythes.udsplugin.commands;

import com.undeadscythes.udsplugin.Color;
import com.undeadscythes.udsplugin.*;
import com.undeadscythes.udsplugin.utilities.*;
import java.util.*;
import org.bukkit.*;

/**
 * Place and check bounties on players.
 * @author UndeadScythes
 */
public class BountyCmd extends CommandWrapper {
    @Override
    public void playerExecute() {
        int bounty;

        SaveablePlayer target;
        if(args.length == 0) {
            sendPage(1, player);
        } else if(args.length == 1) {
            final int page = parseInt(args[0]);
            if(page > -1) {
                sendPage(page, player);
            }
        } else if(numArgsHelp(2) && (target = getMatchingOtherPlayer(args[0])) != null && (bounty = getAffordablePrice(args[1])) != -1) {
            player.debit(bounty);
            target.addBounty(bounty);
            UDSPlugin.sendBroadcast(player.getNick() + " placed a bounty on " + target.getNick() + ".");
        }
    }

    /**
     * Sends a full page of bounties to the player.
     * @param page Page to send.
     * @param player Player to send page to.
     */
    private void sendPage(final int page, final SaveablePlayer player) {
        final List<SaveablePlayer> bounties = new ArrayList<SaveablePlayer>();
        final List<SaveablePlayer> sortedPlayers = PlayerUtils.getSortedPlayers(new SortByBounty());
        for(SaveablePlayer test : sortedPlayers) {
            if(test.getBounty() > 0) {
                bounties.add(test);
            }
        }
        final int pages = (bounties.size() + 8) / 9;
        if(pages == 0) {
            player.sendNormal("There are no bounties to collect.");
        } else if(page > pages) {
            player.sendMessage(Message.NO_PAGE);
        } else {
            player.sendNormal("--- Current Bounties " + (pages > 1 ? "Page " + page + "/" + pages + " " : "") + "---");
            int posted = 0;
            int skipped = 1;
            for(SaveablePlayer bounty : bounties) {
                if(skipped > (page - 1) * 9 && posted < 9) {
                    player.sendListItem(bounty.getNick() + "'s reward: ", bounty.getBounty() + " " + UDSPlugin.getConfigString(ConfigRef.CURRENCIES));
                    posted++;
                } else {
                    skipped++;
                }
            }
        }
    }
}

class SortByBounty implements Comparator<SaveablePlayer> {
    @Override
    public int compare(final SaveablePlayer player1, final SaveablePlayer player2) {
        return player2.getBounty() - player1.getBounty();
    }
}
