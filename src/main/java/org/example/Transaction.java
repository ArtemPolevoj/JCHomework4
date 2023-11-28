package org.example;

import org.example.accounts.CreditAccount;
import org.example.accounts.DebitAccount;
import org.example.appexseptions.IllegalArgumentException;
import org.example.appexseptions.InsufficientFundsException;

public class Transaction {
    //region Methods

    /**
     * Метод перевода с кредитного счета на дебетовый
     * @param amount Сумма транзакции
     * @throws InsufficientFundsException Недостаточно средств на кредитном счете
     * @throws IllegalArgumentException Сумма для перевода должна быть положительной
     */
    public void transferFromCreditToDebit(double amount) throws InsufficientFundsException, IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма транзакции должна быть положительной.");
        } else if (creditAccount.getBalance() < amount) {
            throw new InsufficientFundsException("Недостаточно средств на кредитном счете. "
                    + "Текущий баланс: " + creditAccount.getBalance());
        } else {
            creditAccount.withdraw(amount);
            debitAccount.deposit(amount);
        }
    }

    /**
     * Метод перевода с дебетового счета на кредитный
     * @param amount Сумма транзакции
     * @throws IllegalArgumentException Сумма транзакции должна быть положительной
     * @throws InsufficientFundsException Недостаточно средств на дебетовом счете
     */
    public void transferFromDebitToCredit(double amount) throws IllegalArgumentException, InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма транзакции должна быть положительной.");
        } else if (debitAccount.getBalance() < amount) {
            throw new InsufficientFundsException("Недостаточно средств на дебетовом счете. "
                    + "Текущий баланс: " + debitAccount.getBalance());
        } else {
            debitAccount.withdraw(amount);
            creditAccount.deposit(amount);
        }
    }
    //endregion

    //region Constructors
    public Transaction(CreditAccount creditAccount, DebitAccount debitAccount) {
        this.creditAccount = creditAccount;
        this.debitAccount = debitAccount;
    }
    //endregion

    //region Fields
    private final CreditAccount creditAccount;
    private final DebitAccount debitAccount;
    //endregion
}
