package com.dxc.exceptions;

public class CharacterNotContainedInMappingException extends RuntimeException {
    public CharacterNotContainedInMappingException(Character ch) {
        super("Character " + ch + " not found in the current mapping");
    }
}
