package com.dxc.reference_table;

public class ReferenceTable {
    private final ReferenceTableMapping mapping;

    public ReferenceTable(ReferenceTableMapping mapping) {
        this.mapping = mapping;
    }

    public int getOffset(char ch) {
        return mapping.getIndexOfChar(ch);
    }

    public char offsetCharLeftBy(char ch, int offset) {
        int oldIndexOfCh = mapping.getIndexOfChar(ch);
        int newIndexOfCh = (oldIndexOfCh - offset);

        // Validate it is within the range 0 to countOfEntries()-1
        while (newIndexOfCh < 0) {
            newIndexOfCh += mapping.countOfEntries();
        }

        return mapping.getCharAtIndex(newIndexOfCh);
    }
}
