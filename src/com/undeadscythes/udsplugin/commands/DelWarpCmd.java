package com.undeadscythes.udsplugin.commands;

import com.undeadscythes.udsplugin.*;
import com.undeadscythes.udsplugin.utilities.*;

/**
 * Delete a warp point.
 * @author UndeadScythes
 */
public class DelWarpCmd extends CommandWrapper {
    @Override
    public void playerExecute() {
        Warp warp;
        if(numArgsHelp(1) && (warp = getWarp(args[0])) != null) {
            WarpUtils.removeWarp(warp);
            player.sendNormal("Warp removed.");
        }
    }

}
