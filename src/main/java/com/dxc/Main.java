package com.dxc;

import com.dxc.reference_table.DxcReferenceTableMappingCreator;
import com.dxc.reference_table.ReferenceTable;

import java.util.Map;
import java.util.Scanner;

/**
 * Look into the 'EncoderDecoder' class for the 'encode()' and 'decode()' methods
 *
 * On a high-level...
 * > The class that maintains the 2-way mapping of a char to its index (and vice-versa) is the 'ReferenceTableMapping'
 * > The 'ReferenceTable' class will be injected with an instance of the mapping...As the mappings can be arbitrary
 *   (e.g maybe in the future, I want to map 'A' to 7 instead of 0)
 * > We have an interface 'IReferenceTableMappingCreator', which has a method to return a 'mapping' instance.
 * > A few lines below, see that there is a concrete implementation 'DxcReferenceTableMappingCreator' to
 *   create mapping the one stipulated in the assignment. (A -> 0, B -> 1, etc...)
 *
 * Note: In a real project, I would dial-back on the excessive OOP, as it makes code harder to reason about.
 *
 * Assumptions Made
 * - During encoding/decoding, the first char will NOT be involved...only used as the shift amount
 * - E.g
 *   [Encode] BHELLO WORLD -> GDKKN VNQKC
 *   [Decode] BGDKKN VNQKC -> HELLO WORLD
 */
public class Main {
    public static void main(String[] args) {

        // Set input-related
        Map<Character, Action> actions = Map.of(
                'e', Action.ENCODE,
                'd', Action.DECODE,
                'q', Action.QUIT
        );
        var scanner = new Scanner(System.in);

        // Setup referenceTable, encoder/decoder
        var mappingCreator = new DxcReferenceTableMappingCreator(); // Swap with another impl for different mapping
        var referenceTable = new ReferenceTable(mappingCreator.createMapping());
        var encoderDecoder = new EncoderDecoder(referenceTable);

        while (true) {
            System.out.print("Encode(e) | Decode(d) | Quit(q): ");
            String choiceInput = (scanner.nextLine()).trim();

            if (choiceInput.length() != 1 || !actions.containsKey(choiceInput.charAt(0))) {
                System.out.println("Invalid input");
                System.out.println();
                continue;
            }

            Action action = actions.get(choiceInput.charAt(0));
            if (action == Action.QUIT) {
                System.out.println("Quitting...");
                break;
            }

            System.out.print("Input to " + (action == Action.ENCODE ? "encode" : "decode") + ": ");
            String input = scanner.nextLine();

            String result = action == Action.ENCODE ? encoderDecoder.encode(input) : encoderDecoder.decode(input);
            System.out.println(action == Action.ENCODE ? "Encoding...." : "Decoding....");
            System.out.println((action == Action.ENCODE ? "Encoded" : "Decoded") + ": " + result);
            System.out.println();
        }
    }
}