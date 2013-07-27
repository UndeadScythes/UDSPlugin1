package com.undeadscythes.udsplugin.commands;

import com.undeadscythes.udsplugin.*;
import org.bukkit.entity.*;

/**
 * Accept a request sent by another player.
 * @author UndeadScythes
 */
public class YCmd extends CommandWrapper {
    @Override
    public final void playerExecute() {
        Request request;
        SaveablePlayer sender;
        if((request = getRequest()) != null && (sender = request.getSender()) != null && sender.isOnline()) {
            int price;
            switch (request.getType()) {
                case TP:
                    sender.teleport(player);
                    break;
                case CLAN:
                    clanRequest(UDSPlugin.getClans().get(request.getData()));
                    break;
                case HOME:
                    if(canAfford(price = Integer.parseInt(request.getData())) && noHome()) {
                        final Region home = UDSPlugin.getRegions(RegionType.HOME).get(sender.getName() + "home");
                        home.clearMembers();
                        home.changeOwner(player);
                        player.debit(price);
                        sender.credit(price);
                    }
                    break;
                case PET:
                    if(canAfford(price = Integer.parseInt(request.getData()))) {
                        for(Entity entity : sender.getWorld().getEntities()) {
                            if(entity.getUniqueId().equals(sender.getSelectedPet())) {
                                player.debit(price);
                                sender.credit(price);
                                player.setPet((Tameable)entity);
                                player.teleportHere(entity);
                                player.sendNormal("Your bought " + sender.getNick() + "'s pet.");
                                sender.sendNormal(player.getNick() + " bought your pet.");
                            }
                        }
                    }
                    break;
                case PVP:
                    if(canAfford(price = Integer.parseInt(request.getData()))) {
                        sender.sendNormal(player.getNick() + " accepted your duel, may the best player win.");
                        player.sendNormal("Duel accepted, may the best player win.");
                        player.setChallenger(sender);
                        sender.setChallenger(sender);
                        player.setWager(price);
                        sender.setWager(price);
                    }
                    break;
                case SHOP:
                    if(canAfford(price = Integer.parseInt(request.getData())) && noShop()) {
                        final Region shop = UDSPlugin.getRegions(RegionType.SHOP).get(sender.getName() + "shop");
                        shop.clearMembers();
                        shop.changeOwner(player);
                        player.debit(price);
                        sender.credit(price);
                    }
                    break;
                default:
                    sender.sendNormal(player.getNick() + " was unable to accept your request.");
            }
        }
        UDSPlugin.getRequests().remove(player.getName());
    }

    private void clanRequest(final Clan clan) {
        clan.addMember(player);
        player.setClan(clan);
        clan.sendMessage(player.getNick() + " has joined the clan.");
        final Region base = UDSPlugin.getRegions(RegionType.BASE).get(clan.getName() + "base");
        if(base != null) {
            base.addMember(player);
        }
    }
}
