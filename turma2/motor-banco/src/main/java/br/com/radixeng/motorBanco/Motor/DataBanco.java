package br.com.radixeng.motorBanco.Motor;

import java.util.Date;

public class DataBanco {
    private static Date _mockDate;

    public static void mock(Date date) {
        _mockDate = date;
    }
    
    public static Date agora() {
        if(_mockDate != null) {
            return _mockDate;
        } else {
            return new Date();
        }
    }
}