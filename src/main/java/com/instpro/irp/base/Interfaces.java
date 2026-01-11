package com.instpro.irp.base;

public class Interfaces {
    @FunctionalInterface
    interface IncrementDelegate {
        public long fnIncrement(long current);
}    
}
