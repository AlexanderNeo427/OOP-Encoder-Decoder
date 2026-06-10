package com.dxc;

import com.dxc.exceptions.CharacterNotContainedInMappingException;
import com.dxc.reference_table.DxcReferenceTableMappingCreator;
import com.dxc.reference_table.ReferenceTable;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Setup input-related stuffs
        Map<Character, Action> actions = Map.of(
                'e', Action.ENCODE,
                'd', Action.DECODE,
                'q', Action.QUIT
        );
        var scanner = new Scanner(System.in);

        // Setup referenceTable, encoder/decoder
        // Swap with another impl for different mapping. This 'dxc' impl returns the one specified in the brief...
        var mappingCreator = new DxcReferenceTableMappingCreator();
        var referenceTable = new ReferenceTable(mappingCreator);
        var encoderDecoder = new EncoderDecoder(referenceTable);

        while (true) {
            System.out.print("Encode(e) | Decode(d) | Quit(q): ");
            String choiceInput = (scanner.nextLine()).toLowerCase().trim();
            if (choiceInput.length() != 1 || !actions.containsKey(choiceInput.charAt(0))) {
                System.out.println("Invalid input, try again...");
                System.out.println();
                continue;
            }

            Action action = actions.get(choiceInput.charAt(0));
            if (action == Action.QUIT) {
                System.out.println("Quitting...");
                break;
            }

            System.out.print("Enter string to " + (action == Action.ENCODE ? "encode" : "decode") + ": ");
            String input = (scanner.nextLine()).trim();
            if (input.isEmpty()) {
                System.out.println("Invalid input, try again...");
                System.out.println();
                continue;
            }

            try {
                String result = action == Action.ENCODE ? encoderDecoder.encode(input) : encoderDecoder.decode(input);
                System.out.println(action == Action.ENCODE ? "Encoding...." : "Decoding....");
                System.out.println((action == Action.ENCODE ? "Encoded" : "Decoded") + ": " + result);
                System.out.println();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }
}