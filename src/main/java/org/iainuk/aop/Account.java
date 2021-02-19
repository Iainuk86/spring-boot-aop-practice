package org.iainuk.aop;

public class Account {

    private String accountNumber;
    private String name;

    public Account(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name: " + name + ". Account number: " + accountNumber;
    }
}
