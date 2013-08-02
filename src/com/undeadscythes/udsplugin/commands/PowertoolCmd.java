package com.undeadscythes.udsplugin.commands;

import com.undeadscythes.udsplugin.*;
import org.apache.commons.lang.*;

/**
 * Set up a powertool.
 * @author UndeadScythes
 */
public class PowertoolCmd extends CommandHandler {
    @Override
    public void playerExecute() {
        if(args.length >= 1 && isHoldingItem()) {
            player.setPowertoolID(player.getItemInHand().getTypeId());
            player.setPowertool(StringUtils.join(args, " ").replaceFirst("/", ""));
            player.sendNormal("Powertool set.");
        } else {
            player.setPowertoolID(0);
            player.setPowertool("");
            player.sendNormal("Powertool removed.");
        }
    }
}
