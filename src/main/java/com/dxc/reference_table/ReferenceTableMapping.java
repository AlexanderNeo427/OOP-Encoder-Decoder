package com.dxc.reference_table;

import java.util.HashMap;
import java.util.Map;

public class ReferenceTableMapping {
    /**
     * Main a 2-way mapping. More memory, but very fast lookup....
     * - Need to find what is the index of char 'ch'? indexToChar( ch )
     * - Need to find what is the char at index 'i'?  charToIndex( i )
     *
     * To be populated by the "ReferenceTable" class
     */
    private final Map<Character, Integer> charToIndex = new HashMap<>();
    private final Map<Integer, Character> indexToChar = new HashMap<>();

    /**
     * One function/operation to add to both at the same time
     */
    public void add(char ch, int index) {
        if (charToIndex.containsKey(ch)) {
            throw new RuntimeException("Trying to create charToIndex mapping for char '" + ch + "', already exists");
        }
        if (indexToChar.containsKey(index)) {
            throw new RuntimeException("Trying to create indexToChar mapping for index '" + index + "', already exists");
        }

        charToIndex.put(ch, index);
        indexToChar.put(index, ch);
    }

    public int getIndexOfChar(char ch) {
        if (!charToIndex.containsKey(ch)) {
            throw new RuntimeException("Character '" + ch + "' not found in the reference table.");
        }
        return charToIndex.get(ch);
    }

    public char getCharAtIndex(int index) {
        if (!indexToChar.containsKey(index)) {
            throw new RuntimeException("Index '" + index + "' not found in the reference table.");
        }
        return indexToChar.get(index);
    }

    public int countOfEntries() {
        return indexToChar.size();
    }
}
