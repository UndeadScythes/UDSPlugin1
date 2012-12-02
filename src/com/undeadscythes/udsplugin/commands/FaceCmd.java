package com.undeadscythes.udsplugin.commands;

import com.undeadscythes.udsplugin.*;
import org.bukkit.*;

/**
 * Face a player in a certain direction or get the direction a player is facing.
 * @author UndeadScythes
 */
public class FaceCmd extends AbstractPlayerCommand {
    /**
     * @inheritDocs
     */
    @Override
    public void playerExecute(final SaveablePlayer player, final String[] args) {
        Direction direction;
        if(args.length == 0) {
            player.sendMessage(Color.MESSAGE + "You are facing " + Direction.valueOf(player.getLocation()).toString() + ".");
        } else if(numArgsHelp(1) && (direction = getDirection(args[0])) != null) {
            final Location location = player.getLocation();
            location.setYaw(direction.getYaw());
            player.teleport(location);
        }
    }
}
