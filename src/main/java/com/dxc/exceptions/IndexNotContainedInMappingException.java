package com.dxc.exceptions;

public class IndexNotContainedInMappingException extends RuntimeException {
    public IndexNotContainedInMappingException(int index) {
        super("Index " + index + " not found in the current mapping");
    }
}
