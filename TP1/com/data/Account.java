package com.data;

public interface Account {

	public void deposit(double amount);

	public double withdraw(double amount);

	public double getStatement();
}