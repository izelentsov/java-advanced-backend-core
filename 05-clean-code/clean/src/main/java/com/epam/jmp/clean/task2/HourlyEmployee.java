package com.epam.jmp.clean.task2;




public class HourlyEmployee extends Employee {

    private static final String TYPE = "HOURLY";

    private final Money hourlyRate;
    private final double hoursWorked;
    private final double bonusRate;


    public HourlyEmployee(String name, Money hourlyRate, double hoursWorked, double bonusRate) {
        this(name, TYPE, hourlyRate, hoursWorked, bonusRate);
    }

    protected HourlyEmployee(String name, String type, Money hourlyRate, double hoursWorked, double bonusRate) {
        super(name, type);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.bonusRate = bonusRate;
    }

    @Override
    public Money calculatePay() throws InvalidEmployeeType {
        return hourlyRate.multiply(hoursWorked);
    }

    @Override
    public Money calculateBonus() throws InvalidEmployeeType {
        return calculatePay().multiply(bonusRate);
    }
}

