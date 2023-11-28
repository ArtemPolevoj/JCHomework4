package org.example.accounts;

import org.example.appexseptions.IllegalArgumentException;
import org.example.appexseptions.InsufficientFundsException;

public abstract class Account {

    //region Methods
    public double getBalance() {
        return balance;
    }
    /**
     * Метод начальной инициализации баланса
     * @param initialBalance Сумма начального остатка
     * @throws IllegalArgumentException Начальный баланс не может быть отрицательным
     */
    public void initial(double initialBalance) throws IllegalArgumentException {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Начальный баланс не может быть отрицательным");
        } else {
            this.balance = initialBalance;
        }
    }

    /**
     * Метод пополнения счета
     * @param depositAmount Сумма пополнения
     * @throws IllegalArgumentException Сумма депозита не может быть отрицательной
     */
    public void deposit(double depositAmount) throws IllegalArgumentException {
        if (depositAmount < 0) {
            throw new IllegalArgumentException("Сумма депозита не может быть отрицательной");
        } else if (depositAmount > 0) {
            balance += depositAmount;
        }
    }

    /**
     * Метод списания со счета
     * @param withdrawalAmount Сумма списания
     * @throws InsufficientFundsException Недостаточно средств на счете
     * @throws IllegalArgumentException Сумма для снятия должна быть положительной
     */
    public void withdraw(double withdrawalAmount)throws InsufficientFundsException, IllegalArgumentException {
        if (withdrawalAmount > balance) {
            throw new InsufficientFundsException("Недостаточно средств на счете. "
                    + "Текущий баланс: " + balance);
        } else if (withdrawalAmount <= 0) {
            throw new IllegalArgumentException("Сумма для снятия должна быть положительной.");
        } else {
            balance -= withdrawalAmount;
        }
    }
    //endregion

    //region Fields
    protected double balance;
    //endregion
}
