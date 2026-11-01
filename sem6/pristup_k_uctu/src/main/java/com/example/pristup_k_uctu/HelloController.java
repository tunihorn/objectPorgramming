package com.example.pristup_k_uctu;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Random;

public class HelloController {

    @FXML
    private TextField balance_text;

    @FXML
    private Label balance_label;

    @FXML
    private ComboBox<String> type_combo;

    @FXML
    private TextField sum_text;

    @FXML
    private Button run_client;

    @FXML
    private Button run_five;

    @FXML
    private TextArea transactions_text;

    private BankAccount account;

    private int employeeId = 1;


    @FXML
    public void initialize() {

        type_combo.getItems().addAll("Deposit", "Withdraw");
        type_combo.setValue("Deposit");

        run_client.setOnAction(event -> runOne());
        run_five.setOnAction(event -> runFive());
    }


    @FXML
    protected void create_account() {

        double balance;

        try {
            balance = Double.parseDouble(balance_text.getText());
        } catch (NumberFormatException e) {
            balance_text.setText("zadaj cislo");
            return;
        }

        account = new BankAccount(balance);

        balance_label.setText(String.format("%.2f €", account.getBalance()));

        transactions_text.clear();
        transactions_text.appendText(
                "Account created. Balance: "
                        + String.format("%.2f €", balance)
                        + "\n"
        );
    }


    public void runOne() {

        if (account == null) {
            transactions_text.appendText("Najprv vytvor ucet.\n");
            return;
        }

        double sum;

        try {
            sum = Double.parseDouble(sum_text.getText());
        } catch (NumberFormatException e) {
            sum_text.setText("cislo");
            return;
        }

        if (sum <= 0) {
            transactions_text.appendText("Suma musi byt vacsia ako 0.\n");
            return;
        }

        String type = type_combo.getValue();

        Employee employee = new Employee(employeeId++, account);

        Runnable clientRun = new Runnable() {

            @Override
            public void run() {

                if (type.equals("Deposit")) {

                    employee.deposit(sum);

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                            transactions_text.appendText(
                                    "Employee " + employee.getId()
                                            + " deposited "
                                            + String.format("%.2f €", sum)
                                            + "\n"
                            );

                            updateBalance();
                        }
                    });

                } else {

                    boolean success = employee.withdraw(sum);

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                            if (success) {
                                transactions_text.appendText(
                                        "Employee " + employee.getId()
                                                + " withdrew "
                                                + String.format("%.2f €", sum)
                                                + "\n"
                                );
                            } else {
                                transactions_text.appendText(
                                        "Employee " + employee.getId()
                                                + " - not enough money.\n"
                                );
                            }

                            updateBalance();
                        }
                    });
                }
            }
        };

        Thread clientThread = new Thread(clientRun);
        clientThread.start();
    }


    public void runFive() {

        if (account == null) {
            transactions_text.appendText("Najprv vytvor ucet.\n");
            return;
        }

        Random random = new Random();

        for (int i = 0; i < 5; i++) {

            Employee employee = new Employee(employeeId++, account);

            Runnable clientRun = new Runnable() {

                @Override
                public void run() {

                    try {
                        Thread.sleep(random.nextInt(1000));
                    } catch (InterruptedException e) {
                        return;
                    }

                    double sum = random.nextInt(100) + 1;

                    boolean deposit = random.nextBoolean();

                    if (deposit) {

                        employee.deposit(sum);

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {

                                transactions_text.appendText(
                                        "Employee " + employee.getId()
                                                + " deposited "
                                                + String.format("%.2f €", sum)
                                                + "\n"
                                );

                                updateBalance();
                            }
                        });

                    } else {

                        boolean success = employee.withdraw(sum);

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {

                                if (success) {
                                    transactions_text.appendText(
                                            "Employee " + employee.getId()
                                                    + " withdrew "
                                                    + String.format("%.2f €", sum)
                                                    + "\n"
                                    );
                                } else {
                                    transactions_text.appendText(
                                            "Employee " + employee.getId()
                                                    + " tried to withdraw "
                                                    + String.format("%.2f €", sum)
                                                    + " - not enough money.\n"
                                    );
                                }

                                updateBalance();
                            }
                        });
                    }
                }
            };

            Thread clientThread = new Thread(clientRun);
            clientThread.start();
        }
    }


    private void updateBalance() {
        balance_label.setText(
                String.format("%.2f €", account.getBalance())
        );
    }
}