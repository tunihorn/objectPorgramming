package com.example.pristup_k_uctu;

public class BankAccount {

    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public synchronized void deposit(double sum) {
        balance = balance + sum;
    }

    public synchronized boolean withdraw(double sum) {

        if (balance >= sum) {
            balance = balance - sum;
            return true;
        }

        return false;
    }

    public synchronized double getBalance() {
        return balance;
    }
}