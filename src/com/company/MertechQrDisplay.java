package com.company;

import jssc.SerialPort;
import jssc.SerialPortException;
import java.util.Arrays;

public class MertechQrDisplay {

    public static String portName = "COM3";

    public static void sendMertechQrDisplay(String massage) {

        int length = massage.length();

        byte[] commandIn = {(byte)0x02,(byte)0xF2,(byte)0x02,(byte)((length>>8) & 0xFF),(byte) (length & 0xFF)};
        byte[] data = massage.getBytes();
        byte[] commandOut = {(byte)0x02,(byte)0xF2,(byte)0x03};

        byte[]sendMassage = new byte[commandIn.length + data.length + commandOut.length];

        int count = 0;

        for (int i = 0; i < commandIn.length; i++) {
            sendMassage[i] = commandIn[i];
            count++;
        }

        for (byte datum : data) {
            sendMassage[count++] = datum;
        }

        for (byte out : commandOut) {
            sendMassage[count++] = out;
        }

        System.out.println(Arrays.toString(sendMassage));

        SerialPort serialPort = portSetup(portName);

        if (serialPort.isOpened()){
            try {
                serialPort.writeBytes(sendMassage);
                serialPort.closePort();
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("COM порт не найден");
        }
    }

    private static SerialPort portSetup(String portName) {
        SerialPort serialPort = new SerialPort(portName);
        try {
            serialPort.openPort();
            serialPort.setParams(115200,8,1,0);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
        return serialPort;
    }

    public static void clearDisplay(){
        byte[] clearDisplay = {0x02,(byte) 0xF0,0x03,0x43,0x4C,0x53,0x03};
        SerialPort serialPort = new SerialPort(portName);
        try {
            serialPort.openPort();
            if (serialPort.isOpened()){
                serialPort.writeBytes(clearDisplay);
                serialPort.closePort();
            }else {
                System.out.println("COM порт не найден");
            }
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }
}
