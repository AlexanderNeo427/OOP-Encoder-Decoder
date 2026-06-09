package com.dxc.reference_table;

public final class DxcReferenceTableMappingCreator implements IReferenceTableMappingCreator {
    public ReferenceTableMapping createMapping() {
        var mapping = new ReferenceTableMapping();

        // Adding indices for the '
        for (int i = 0; i <= 25; i++) {
            char ch = (char) ('A' + i);
            mapping.add(ch, i);
        }

        // Adding the indices for the 'numerical' characters
        for (int i = 26; i <= 35; i++) {
            char ch = (char) (i + (48 - 26));
            mapping.add(ch, i);
        }

        // Hardcoded mappings for the special characters...
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
