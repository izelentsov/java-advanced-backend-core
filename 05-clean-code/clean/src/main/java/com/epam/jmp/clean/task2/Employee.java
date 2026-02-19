package com.epam.jmp.clean.task2;



public abstract class Employee {


    private final String name;
    private final String type;


    protected Employee(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }


    public Money calculatePay() throws InvalidEmployeeType {
        throw new InvalidEmployeeType(type, "calculatePay()");
    }

    public Money calculateBonus() throws InvalidEmployeeType {
        throw new InvalidEmployeeType(type, "calculateBonus()");
    }
}
