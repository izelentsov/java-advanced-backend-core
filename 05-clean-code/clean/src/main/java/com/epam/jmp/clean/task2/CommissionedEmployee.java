package com.epam.jmp.clean.task2;




public class CommissionedEmployee extends Employee {

    private static final String TYPE = "COMMISSIONED";

    private final Money grossSalary;
    private final double commissionRate;
    private final double bonusRate;


    public CommissionedEmployee(String name, Money grossSalary, double commissionRate, double bonusRate) {
        this(name, TYPE, grossSalary, commissionRate, bonusRate);
    }

    protected CommissionedEmployee(String name, String type, Money grossSalary, double commissionRate, double bonusRate) {
        super(name, type);
        this.grossSalary = grossSalary;
        this.commissionRate = commissionRate;
        this.bonusRate = bonusRate;
    }

    @Override
    public Money calculatePay() throws InvalidEmployeeType {
        return grossSalary.multiply(1 + commissionRate);
    }

    @Override
    public Money calculateBonus() throws InvalidEmployeeType {
        Money commissionEarnings = grossSalary.multiply(commissionRate);
        return commissionEarnings.multiply(bonusRate);
    }
}
