package com.epam.jmp.clean.task2.provided;


import com.epam.jmp.clean.task2.Money;



public class Employee {

    public static final String COMMISSIONED = "commissioned";
    public static final String HOURLY = "hourly";
    public static final String SALARIED = "salaried";


    private String name;
    private String type;


    public Employee(String name, String type) {
        this.name = name;
        this.type = type;
    }


    public Money calculatePay(Employee e) throws InvalidEmployeeType {
        switch (e.type) {
            case COMMISSIONED:
                return calculateCommissionedPay(e);
            case HOURLY:
                return calculateHourlyPay(e);
            case SALARIED:
                return calculateSalariedPay(e);
            default:
                throw new InvalidEmployeeType(e.type);
        }
    }

    public Money calculateBonus(Employee e) throws InvalidEmployeeType {
        switch (e.type) {
            case COMMISSIONED:
                return calculateCommissionedBonus(e);
            case HOURLY:
                return calculateHourlyBonus(e);
            case SALARIED:
                return calculateSalariedBonus(e);
            default:
                throw new InvalidEmployeeType(e.type);
        }
    }

    private Money calculateCommissionedPay(Employee e) {
        throw new IllegalStateException("unimplemented");
    }

    private Money calculateHourlyPay(Employee e) {
        throw new IllegalStateException("unimplemented");
    }

    private Money calculateSalariedPay(Employee e) {
        throw new IllegalStateException("unimplemented");
    }

    private Money calculateCommissionedBonus(Employee e) {
        throw new IllegalStateException("unimplemented");
    }

    private Money calculateHourlyBonus(Employee e) {
        throw new IllegalStateException("unimplemented");
    }

    private Money calculateSalariedBonus(Employee e) {
        throw new IllegalStateException("unimplemented");
    }




    public static class InvalidEmployeeType extends Exception {
        public InvalidEmployeeType(String employeeType) {
            super("Invalid employee type: " + employeeType);
        }
    }


}
