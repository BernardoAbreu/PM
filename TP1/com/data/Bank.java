package com.data;

/**
 * Created by math on 4/1/17.
 */
public class Bank  implements Account{

    private double statement;

    @Override
    public void deposit(double amount){ statement += amount; }

    @Override
    public double withdraw(double amount){
        if(amount > statement) return 0;
        else {
            this.statement -= amount;
            if(this.statement == 0) return 0;
            else return amount;
        }
    }
    @Override
    public double getStatement() {
        return statement;
    }
}
