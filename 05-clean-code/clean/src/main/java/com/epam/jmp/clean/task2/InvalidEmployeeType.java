package com.epam.jmp.clean.task2;




public class InvalidEmployeeType extends Exception {

    public InvalidEmployeeType(String type, String operation) {
        super("Employee type '" + type + "' does not support operation: " + operation);
    }

}

