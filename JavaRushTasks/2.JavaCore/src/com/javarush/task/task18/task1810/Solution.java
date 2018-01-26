package com.javarush.task.task18.task1810;

/* 
DownloadException
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws DownloadException, IOException {
        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            FileInputStream input_file = new FileInputStream(reader.readLine());
            if (input_file.available() < 1000) {
                reader.close();
                input_file.close();
                throw new DownloadException();
            }
        }

    }

    public static class DownloadException extends Exception {

    }
}
