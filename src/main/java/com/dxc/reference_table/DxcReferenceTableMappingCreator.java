package com.dxc.reference_table;

public final class DxcReferenceTableMappingCreator implements IReferenceTableMappingCreator {
    public ReferenceTableMapping createMappingInstance() {
        var mapping = new ReferenceTableMapping();

        // Adding indices for the alphabetic characters
        for (int i = 0; i <= 25; i++) {
            char ch = (char) ('A' + i);
            mapping.add(ch, i);
        }

        // Adding the indices for the numerical characters
        int START_AT = 26;
        int offset = '0' - START_AT;
        for (int i = START_AT; i <= 35; i++) {
            char ch = (char) (i + offset);
            mapping.add(ch, i);
        }

        // Hardcoded mappings for the special characters
        mapping.add('(', 36);
        mapping.add(')', 37);
        mapping.add('*', 38);
        mapping.add('+', 39);
        mapping.add(',', 40);
        mapping.add('-', 41);
        mapping.add('.', 42);
        mapping.add('/', 43);
        return mapping;
    }
}
