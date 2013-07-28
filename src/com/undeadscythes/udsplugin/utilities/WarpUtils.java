package com.undeadscythes.udsplugin.utilities;

import com.undeadscythes.udsplugin.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author UndeadScythes
 */
public class WarpUtils {
    /**
     * File name of warp file.
     */
    public static final String PATH = "warps.csv";

    private static final SaveableHashMap WARPS = new SaveableHashMap();
    private static MatchableHashMap<Warp> MATCHABLE_WARPS;

    public static int numWarps() {
        return WARPS.size();
    }
    
    public static void saveWarps(final File path) throws IOException {
        WARPS.save(path + File.separator + PATH);
    }
    
    public static void loadWarps(final File path) throws IOException {
        try {
            final BufferedReader file = new BufferedReader(new FileReader(path + File.separator + PATH));
            String nextLine;
            while((nextLine = file.readLine()) != null) {
                WARPS.put(nextLine.split("\t")[0], new Warp(nextLine));
            }
            file.close();
        } catch (FileNotFoundException ex) {}
        MATCHABLE_WARPS = WARPS.toMatchableHashMap(Warp.class);
    }
    
    /**
     * Grab and cast the warps map.
     * @return Warps map.
     */
    public static Collection<Warp> getWarps() {
        return MATCHABLE_WARPS.values();
    }
    
    public static Warp getWarp(final String name) {
        return MATCHABLE_WARPS.get(name);
    }
    
    public static Warp matchWarp(final String name) {
        return MATCHABLE_WARPS.matchKey(name);
    }
    
    public static void addWarp(final Warp warp) {
        WARPS.put(warp.getName(), warp);
        MATCHABLE_WARPS = WARPS.toMatchableHashMap(Warp.class);
    }
    
    public static void removeWarp(final Warp warp) {
        WARPS.remove(warp.getName());
        MATCHABLE_WARPS = WARPS.toMatchableHashMap(Warp.class);
    }
    
    private WarpUtils() {}
}