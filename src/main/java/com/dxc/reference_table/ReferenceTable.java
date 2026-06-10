package com.dxc.reference_table;

import com.dxc.exceptions.CharacterNotContainedInMappingException;
import com.dxc.exceptions.IndexNotContainedInMappingException;

public class ReferenceTable {
    private final ReferenceTableMapping mapping;

    public ReferenceTable(IReferenceTableMappingCreator mappingCreator) {
        this.mapping = mappingCreator.createMappingInstance();
    }

    public int getIndexOfChar(char ch) throws CharacterNotContainedInMappingException {
        Integer indexOfChar = mapping.getIndexOfChar(ch);
        if (indexOfChar == null) {
            throw new CharacterNotContainedInMappingException(ch);
        }
        return indexOfChar;
    }

    public char shiftCharLeftByAmount(char ch, int shiftAmount) throws CharacterNotContainedInMappingException, IndexOutOfBoundsException {
        Integer oldIndexOfCh = mapping.getIndexOfChar(ch);
        if (oldIndexOfCh == null) {
            throw new CharacterNotContainedInMappingException(ch);
        }

        // Add the shift amount, wrap-around to ensure it is within the range 0 to countOfEntries-1
        int newIndexOfCh = (oldIndexOfCh - shiftAmount);
        while (newIndexOfCh < 0) {
            newIndexOfCh += mapping.countOfEntries();
        }
        newIndexOfCh %= mapping.countOfEntries();

        Character charAtNewIndex = mapping.getCharAtIndex(newIndexOfCh);
        if (charAtNewIndex == null) {
            throw new IndexNotContainedInMappingException(newIndexOfCh);
        }
        return charAtNewIndex;
    }
}
