package com.example.vlakna;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private TextField label_input;

    @FXML
    private TextArea faktorial_text;

    @FXML
    private TextArea mocnina_text;

    @FXML
    private TextArea fibonachi_text;

    @FXML
    private ProgressBar faktorial_bar;

    @FXML
    private ProgressBar mocnina_bar;

    @FXML
    private ProgressBar fibonachi_bar;

    @FXML
    protected void compute(){

        int n;

        try {
            n = Integer.parseInt(label_input.getText());

        } catch (NumberFormatException e) {
            label_input.setText("zadaj cislo");
            return;
        }

        faktorial_text.clear();
        mocnina_text.clear();
        fibonachi_text.clear();

        faktorial_bar.setProgress(0);
        mocnina_bar.setProgress(0);
        fibonachi_bar.setProgress(0);

        Runnable faktorialRun = new Runnable() {
            @Override
            public void run() {
                int i = 1;
                int sum = 1;

                while (i <= n){
                    try {
                        sum = sum * i;
                        faktorial_text.appendText(Integer.toString(sum) + "\n");

                        int pom = 1 + i;

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                faktorial_bar.setProgress(pom / (double) n);
                            }
                        });

                        i++;

                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }

            }
        };

        Runnable mocninaRun = new Runnable() {
            @Override
            public void run() {
                int i = 1;
                int sum = n;

                while (i <= n){
                    try {
                        mocnina_text.appendText(sum + "\n");
                        sum = sum * n;

                        int pom = 1 + i;

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                mocnina_bar.setProgress(pom / (double) n);
                            }
                        });

                        i++;

                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }

            }
        };

        Runnable fibonacciRun = new Runnable() {
            @Override
            public void run() {
                int a = 0;
                int b = 1;
                int i = 1;

                while (i <= n) {
                    int sum = a;
                    a = b;
                    b = sum + b;

                    int krok = i + 1;

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            fibonachi_text.appendText(sum + "\n");
                            fibonachi_bar.setProgress((double) krok / n);
                        }
                    });

                    i++;

                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        };

        Thread faktorialThread = new Thread(faktorialRun);
        Thread mocninaThread = new Thread(mocninaRun);
        Thread fibonacciThread = new Thread(fibonacciRun);

        faktorialThread.start();
        mocninaThread.start();
        fibonacciThread.start();
    }
}
