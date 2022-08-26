package com.company;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

 class PortReader implements SerialPortEventListener {

    public void serialEvent(SerialPortEvent event) {
        SerialPort serialPort = new SerialPort("COM3");
        try {
                String data = serialPort.readString(event.getEventValue());
                System.out.println(data + " ответ");
                System.out.println(event.getEventValue());
            }
            catch (SerialPortException ex) {
                System.out.println(ex);
            }
    }
}