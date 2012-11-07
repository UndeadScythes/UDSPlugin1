package com.undeadscythes.udsplugin;

import java.util.*;

/**
 * A LinkedHashMap whose contents can be matched with partials.
 * @param <Object>
 * @author UndeadScythes
 */
public class MatchableHashMap<Object> extends HashMap<String, Object> {
    /**
     * Find all matches for a given partial key.
     * @param partial Partial key to search the map with.
     * @return A list of objects corresponding to matches of the partial key.
     */
    public ArrayList<Object> getKeyMatches(String partial) {
        String lowPartial = partial.toLowerCase();
        ArrayList<Object> matches = new ArrayList<Object>();
        for(Map.Entry<String, Object> entry : super.entrySet()) {
            if(entry.getKey().toLowerCase().contains(lowPartial)) {
                matches.add(entry.getValue());
            }
        }
        return matches;
    }

    /**
     * Finds the first match for a partial key.
     * @param partial Partial key to search the map with.
     * @return The first match or <code>null</code> if there are no matches.
     */
    public Object matchKey(String partial) {
        String lowPartial = partial.toLowerCase();
        for(Map.Entry<String, Object> entry : super.entrySet()) {
            if(entry.getKey().toLowerCase().contains(lowPartial)) {
                return entry.getValue();
            }
        }
        return null;

    }

    /**
     * Add an object to the map, converting the key to lower case.
     */
    @Override
    public Object put(String key, Object object) {
        return super.put(key.toLowerCase(), object);
    }

    /**
     * Get an object from the map, using the lower case key.
     * @param key The key to search for.
     * @return The object to which the key relates.
     */
    public Object get(String key) {
        return super.get(key.toLowerCase());
    }

    /**
     * Remove an object from the map, using the lower case key.
     * @param key The key to remove.
     * @return The object the key used to match or <code>null</code> if the key didn't exist.
     */
    public Object remove(String key) {
        return super.remove(key.toLowerCase());
    }

    public boolean containsKey(String key) {
        return super.containsKey(key.toLowerCase());
    }

    /**
     * A shortcut to get a sorted list of the map values.
     * @param comp Comparator to define sort priorities.
     * @return Sorted array of objects.
     */
    public ArrayList<Object> getSortedValues(Comparator comp) {
        ArrayList<Object> values = new ArrayList<Object>(this.values());
        Collections.sort(values, comp);
        return values;
    }

    public void replace(String oldKey, String newKey, Object object) {
        remove(oldKey);
        put(newKey, object);
    }
}
