package com.packagePlayer;

public interface Account {

	public void deposit(double amount);

	public double withdraw(double amount);

	//TODO
	//Is it needed?
	public double getStatement();
}