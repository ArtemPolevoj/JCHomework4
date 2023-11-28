package org.example;

import org.example.accounts.CreditAccount;
import org.example.accounts.DebitAccount;
import org.example.appexseptions.IllegalArgumentException;
import org.example.appexseptions.InsufficientFundsException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CreditAccount creditAccount = new CreditAccount();
        DebitAccount debitAccount = new DebitAccount();
        Transaction transaction = new Transaction(creditAccount, debitAccount);
        Scanner scanner = new Scanner(System.in);
        int operation;
        double amount;
        System.out.println("***** МОЙ БАНК *****");

        do {
            System.out.println("Введите начальные остатки счетов.");
            System.out.print("Дебетовый счет: ");
            try {
                debitAccount.initial(scanner.nextDouble());
                System.out.print("Кредитный счет: ");
                creditAccount.initial(scanner.nextDouble());
                System.out.printf("На кредитном счете: %.2f руб.\n", creditAccount.getBalance());
                System.out.printf("На дебетовом счете: %.2f руб.\n\n", debitAccount.getBalance());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("\n" + e.getMessage() + "\n");
            }
        } while (true);

        do {
            do {
                System.out.println("Выберете операцию:");
                System.out.println("0 - ВЫХОД");
                System.out.println("1 - пополнить дебетовый счет");
                System.out.println("2 - снять с дебетового счета");
                System.out.println("3 - пополнить кредитный счет");
                System.out.println("4 - снять с кредитного счета");
                System.out.println("5 - перевести с кредитного на дебетовый");
                System.out.println("6 - перевести с дебетового на кредитный");
                try {
                    operation = scanner.nextInt();
                    if (operation == 0){
                        System.out.println("ВЫХОД");
                        System.exit(0);
                    }else if (operation > 0 && operation <= 6) {
                        System.out.print("Введите сумму операции: ");
                        amount = scanner.nextDouble();
                        break;
                    } else {
                        System.out.println("\nНе верная операция.\n");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\nОшибка ввода числа при выборе операции.\n");
                    scanner.next();
                }
            }while (true) ;

                try {
                    switch (operation) {
                         case 1 -> {
                            debitAccount.deposit(amount);
                            System.out.printf("На дебетовом счете: %.2f руб.\n", debitAccount.getBalance());
                        }
                        case 2 -> {
                            debitAccount.withdraw(amount);
                            System.out.printf("На дебетовом счете: %.2f руб.\n", debitAccount.getBalance());
                        }
                        case 3 -> {
                            creditAccount.deposit(amount);
                            System.out.printf("На кредитном счете: %.2f руб.\n", creditAccount.getBalance());
                        }
                        case 4 -> {
                            creditAccount.withdraw(amount);
                            System.out.printf("На кредитном счете: %.2f руб.\n", creditAccount.getBalance());
                        }
                        case 5 -> {
                            transaction.transferFromCreditToDebit(amount);
                            System.out.printf("На кредитном счете: %.2f руб.\n", creditAccount.getBalance());
                            System.out.printf("На дебетовом счете: %.2f руб.\n", debitAccount.getBalance());
                        }
                        case 6 -> {
                            transaction.transferFromDebitToCredit(amount);
                            System.out.printf("На кредитном счете: %.2f руб.\n", creditAccount.getBalance());
                            System.out.printf("На дебетовом счете: %.2f руб.\n", debitAccount.getBalance());
                        }
                    }
                } catch (IllegalArgumentException |
                         InsufficientFundsException e) {
                    System.out.println("\n" + e.getMessage() +"\n");
                } catch (Exception e) {
                    System.out.println("Не обработанное исключение: " + e.getClass() + ", " + e.getMessage());
                }
        }while (true);
    }
}
