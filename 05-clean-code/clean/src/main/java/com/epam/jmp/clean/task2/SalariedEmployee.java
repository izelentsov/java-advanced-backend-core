package com.epam.jmp.clean.task2;




public class SalariedEmployee extends Employee {

    private static final String TYPE = "SALARIED";

    private final Money salary;
    private final double bonusRate;


    public SalariedEmployee(String name, Money salary, double bonusRate) {
        this(name, TYPE, salary, bonusRate);
    }

    protected SalariedEmployee(String name, String type, Money salary, double bonusRate) {
        super(name, type);
        this.salary = salary;
        this.bonusRate = bonusRate;
    }

    @Override
    public Money calculatePay() throws InvalidEmployeeType {
        return salary;
    }

    @Override
    public Money calculateBonus() throws InvalidEmployeeType {
        return salary.multiply(bonusRate);
    }
}

