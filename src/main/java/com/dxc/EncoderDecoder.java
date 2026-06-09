package com.dxc;

import com.dxc.reference_table.ReferenceTable;

public class EncoderDecoder {
    private final ReferenceTable referenceTable;

    public EncoderDecoder(ReferenceTable referenceTable) {
        this.referenceTable = referenceTable;
    }

    public String encode(String plainText) {
        return encodeOrDecode(plainText, true);
    }

    public String decode(String encodedText) {
        return encodeOrDecode(encodedText, false);
    }

    private String encodeOrDecode(String text, boolean encode) {
        var result = new StringBuilder();

        int offset = referenceTable.getOffset(text.charAt(0));
        for  (int i = 0; i < text.length(); i++) {
            if (i == 0) {
                continue;
            }

            char ch = text.charAt(i);
            if (ch == ' ') {
                result.append(" ");
                continue;
            }

            char encodedOrDecoded = referenceTable.offsetCharLeftBy(ch, encode ? offset : -offset);
            result.append(encodedOrDecoded);
        }

        return result.toString();
    }
}
