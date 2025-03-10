package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;

public class Encryptor {
    
    public static int determineColumns(int messageLen, int rows){
        if(messageLen == 0 || rows == 0) {
            return 1;
        }
        int col = messageLen / rows;
        if(col * rows < messageLen) {
            col ++;
        }
        return col;
    }
    
    public static String[][] generateEncryptArray(String message, int rows) {
        String[][] originalStrings = new String[rows][determineColumns(message.length(), rows)];
        int currentIdx = 0;
        for(int i = 0; i < originalStrings.length; i ++) {
            for(int j = 0 ; j < originalStrings[i].length; j ++) {
                if(currentIdx < message.length()) {
                    originalStrings[i][j] = message.substring(currentIdx, currentIdx + 1);
                    currentIdx ++;
                }
                else {
                    originalStrings[i][j] = "=";
                }
            }
        }
        return originalStrings;
    }

    public static String encryptMessage(String message, int rows){
        String[][] originalString = generateEncryptArray(message, rows);
        String encrypted = "";
        for(int i = originalString[0].length - 1; i >= 0; i --) {
            for(int j = 0; j < originalString.length; j ++) {
                encrypted += originalString[j][i];
            }
        }
        return encrypted;
    }

    public static String decryptMessage(String encryptedMessage, int rows) {
        
        int col = encryptedMessage.length() / rows;
        String[][] encrypted = new String[rows][col];
        int currentIdx = 0;
        for(int i = col - 1; i >= 0 ; i --) {
            for(int j = 0; j < rows; j ++) {
                encrypted[j][i] = encryptedMessage.substring(currentIdx, currentIdx + 1);
                currentIdx ++;
            }
        }
        String out = "";
        for(String[] texts : encrypted) {
            for(String letter : texts) {
                if(!letter.equals("=")) {
                    out += letter;
                }
            }
        }
        return out;
    }

    public static void main(String[] args) {
        String message = "SOmeone ? ";
        String ab = encryptMessage(message, 6);
        System.out.println(ab);
        System.out.println(decryptMessage(ab, 6));
    }
}