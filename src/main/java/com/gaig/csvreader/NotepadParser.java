/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaig.csvreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author cjohannemann
 */
public class NotepadParser {

    private static ArrayList<String> firstName = new ArrayList<>();
    private static ArrayList<String> lastName = new ArrayList<>();

    public static String notepadParse() throws IOException {
        String fileName = "C:\\Development\\UpdatedTextFile.txt";
        String gettingNames = null;
        Scanner file = new Scanner(new File(fileName)).useDelimiter(",\\s*");
        StringBuilder sb = new StringBuilder();
        ArrayList<String> arrayNames = new ArrayList<>();

        try {
            while (file.hasNext()) {
                gettingNames = file.nextLine();
                arrayNames.add(gettingNames);
            }
            for (String names : arrayNames) {
                System.out.println(lastName.size());
                System.out.println(names);

                if ((!names.contains(" ") && !names.contains(".") && !names.contains(","))) {
                    int idx = names.length();
                    String lName = names.substring(0, idx);
                    String fName = names.substring(0, idx);
                    lastName.add(lName);
                    firstName.add(fName);
                } else if (!names.contains(" ") && names.contains(".")) {
                    int idx = names.length();
                    String lName = names.substring(0, idx);
                    String fName = names.substring(0, idx);
                    lastName.add(lName);
                    firstName.add(fName);
                } else if (!names.contains(" ") && names.contains(",")) {
                    int idx = names.length();
                    String lName = names.substring(0, idx);
                    String fName = names.substring(0, idx);
                    lastName.add(lName);
                    firstName.add(fName);
                } else if (names.startsWith(".", 1)) {
                    for (int i = 0; i < names.length(); i++) {
                        int idx = names.indexOf(" ");
                        int endIdx = names.lastIndexOf(" ");
                        String fName = names.substring(idx, endIdx);
                        String lName = names.substring(endIdx + 1);
                        lastName.add(lName);
                        firstName.add(fName);
                        break;
                    }
                } //Name contains a coma only
                else if (names.contains(",") && !names.contains(".")) {
                    for (int i = 0; i < names.length(); i++) {
                        int idx = names.lastIndexOf(',');
                        String lName = names.substring(0, idx);
                        String fName = names.substring(idx + 2);
                        lastName.add(lName);
                        firstName.add(fName);
                        break;
                    }
                } //Name contains a period only                
                else if (names.contains(".") && !names.contains(",") && !names.endsWith("Jr.")) {
                    //if(!names.endsWith("Jr.")){
                    for (int i = 0; i < names.length(); i++) {
                        int idx = names.lastIndexOf('.');

                        String fName = names.substring(0, idx - 2);
                        String lName = names.substring(idx + 2);

                        lastName.add(lName);
                        firstName.add(fName);
                        break;
                        //}
                    }
                } //Just FirstName and LastName
                else if (!names.contains(",") && !names.contains(".")) {
                    if (names.contains(" ")) {
                        for (int i = 0; i < names.length(); i++) {
                            int idx = names.indexOf(" ");
                            String fName = names.substring(0, idx);
                            String lName = names.substring(idx + 1);
                            lastName.add(lName);
                            firstName.add(fName);
                            break;
                        }
                    }
                } else if (names.endsWith("Jr.")) {
                    for (int i = 0; i < names.length(); i++) {
                        int idx = names.indexOf('.');

                        String fName = names.substring(0, idx - 2);
                        String lName = names.substring(idx + 2);

                        lastName.add(lName);
                        firstName.add(fName);
                        break;
                    }
                } //Name contains coma and period
                else if (names.contains(".") && names.contains(",")) {
                    for (int i = 0; i < names.length(); i++) {
                        if (names.endsWith(".")) {
                            names = names.substring(0, names.length() - 3);
                        }
                        int idx = names.lastIndexOf(',');
                        String lName = names.substring(0, idx);
                        String fName = names.substring(idx + 2);
                        lastName.add(lName);
                        firstName.add(fName);
                        break;
                    }
                } else {
                    int idx = names.length();
                    String lName = names.substring(0, idx);
                    String fName = names.substring(0, idx);
                    lastName.add(lName);
                    lastName.add(fName);
                    System.out.println(lName);
                }
            }
            readArray();
            return sb.toString();
        } finally {
            file.close();
        }
    }

    private static void readArray() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writeFirstName = new PrintWriter("S:\\Shared\\Information Management\\BI\\PhishMeApp\\firstName.txt", "UTF-8");
        PrintWriter writeLastName = new PrintWriter("S:\\Shared\\Information Management\\BI\\PhishMeApp\\lastName.txt", "UTF-8");
//        PrintWriter writeFirstName = new PrintWriter("C:\\Development\\firstName.txt", "UTF-8");
//        PrintWriter writeLastName = new PrintWriter("C:\\Development\\lastName.txt", "UTF-8");
        String first = "";
        String last = "";

        for (String fn : firstName) {
            first = fn;
            System.out.println(first);
            try {
                writeFirstName.println(first);
            } catch (Exception e) {
            }
        }
        for (String ln : lastName) {
            last = ln;
            try {
                writeLastName.println(last);
            } catch (Exception e) {
            }
        }
        writeFirstName.close();
        writeLastName.close();
        System.exit(0);
    }

}
