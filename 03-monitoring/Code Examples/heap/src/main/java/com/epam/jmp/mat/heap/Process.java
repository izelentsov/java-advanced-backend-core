package com.epam.jmp.mat.heap;

import java.io.IOException;

class Process {

    private Object field;

    Process() {
        field = new java.util.ArrayList<>();
    }

    void waitKeyPress() {
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void execute() {

        long i = 0;
        while (true) {
            for (int j = 0; j < 100; j++) {
                ((java.util.Collection)field).add(i);
                ((java.util.Collection)field).add((int)i);
                ((java.util.Collection)field).add((byte)i);
                ((java.util.Collection)field).add((short)i);
                ((java.util.Collection)field).add(Long.toString(i));
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
	    //            System.out.println("iter " + i);
            i++;
        }
    }

}
