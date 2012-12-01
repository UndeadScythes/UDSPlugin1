package com.undeadscythes.udsplugin.eventhandlers;

import com.undeadscythes.udsplugin.*;
import java.util.*;
import org.bukkit.event.*;
import org.bukkit.event.block.*;

/**
 * Description.
 * @author UndeadScythes
 */
public class BlockFromTo extends ListenerWrapper implements Listener {
    @EventHandler
    public void onEvent(BlockFromToEvent event) {
        ArrayList<Region> regions = regionsHere(event.getToBlock().getLocation());
        if(!regions.isEmpty() && !regionsContain(regions, event.getBlock().getLocation())) {
            event.setCancelled(true);
        }
    }
}