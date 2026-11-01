package com.example.pristup_k_uctu;

public class Employee {

    private int id;
    private BankAccount account;

    public Employee(int id, BankAccount account) {
        this.id = id;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public BankAccount getAccount() {
        return account;
    }

    public void deposit(double sum) {
        account.deposit(sum);
    }

    public boolean withdraw(double sum) {
        return account.withdraw(sum);
    }
}