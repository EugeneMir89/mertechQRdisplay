package com.company;


import static com.company.MertechQrDisplay.clearDisplay;
import static com.company.MertechQrDisplay.sendMertechQrDisplay;


public class Main {

    public static void main(String[] args) throws InterruptedException {

            sendMertechQrDisplay("�������������-����������� ����� - ������!!!");
            Thread.sleep(5000);
            clearDisplay();



    }

}
