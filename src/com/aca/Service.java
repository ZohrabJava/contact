package com.aca;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Service {
    public static void write(Contacts contacts) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Contacts.txt"));
            for (User user : contacts.contacts) {
                if (user.getEmail() != null) {
                    writer.write(user.getName() + " " + user.getPhoneNumber() + " " +
                            user.getPhoneType() + " " + user.getEmail() + " " + user.getEmailType() + "\n");
                } else {
                    writer.write(user.getName() + " " + user.getPhoneNumber() + " " + user.getPhoneType() + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static PhoneType getPhoneType(String[] arr){
        switch (arr[2]) {
            case "MOBILE":
                return PhoneType.MOBILE;
            case "HOME":
                return PhoneType.HOME;
            case "WORK":
                return PhoneType.WORK;
            case "SCHOOL":
                return PhoneType.SCHOOL;
            case "COMPANY":
                return PhoneType.COMPANY;
            default:
                break;
        }
        return null;
    }
    private static EmailType getEmailType(String[] arr){
        switch (arr[4]) {
            case "GMAIL":
               return EmailType.GMAIL;
            case "ICLOUD":
                return EmailType.ICLOUD;
            case "OTHER":
                return EmailType.OTHER;
            default:
                break;
        }
        return null;
    }
    private static void setNumberNameNumberType(String[] arr,Contacts contacts,User user){
        user.setName(arr[0]);
        user.setPhoneNumber(arr[1]);
        user.setPhoneType(getPhoneType(arr));
        contacts.numberName.put(user.getPhoneNumber(), user.getName());
        contacts.numberType.put(user.getPhoneNumber(), user.getPhoneType());
    }
    private static void setEmailEmailType(String[] arr,Contacts contacts,User user){
        setNumberNameNumberType(arr,contacts,user);
        user.setEmail(arr[3]);
        user.setEmailType(getEmailType(arr));
        contacts.emailName.put((user.getEmail()), user.getName());
        contacts.emailEmailType.put(user.getEmail(), user.getEmailType());
    }

    public static void read(Contacts contacts) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Contacts.txt"));
            String element;
            String[] arr;
            while ((element = reader.readLine()) != null) {
                User user = new User();
                arr = element.split(" ");
                if (arr.length == 3) {
                    setNumberNameNumberType( arr, contacts, user);
                } else if (arr.length == 5) {
                    setEmailEmailType(arr,contacts,user);
                }
                contacts.contacts.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void start() {
        Contacts contacts = new Contacts();
        Scanner scanner = new Scanner(System.in);
        read(contacts);
        String stepOne;
        boolean flagOne;
        do {
            System.out.println("1. Creat contact");
            System.out.println("2. Read contact");
            System.out.println("3. Update contact");
            System.out.println("4. Delete contact");
            System.out.println("5. Search from number");
            System.out.println("6. Search from name");
            System.out.println("7. Exit");
            flagOne = true;
            stepOne = scanner.nextLine();
            switch (stepOne) {
                case "1":
                    contacts.creat();
                    break;
                case "2":
                    contacts.read();
                    break;
                case "3":
                    contacts.update();
                    break;
                case "4":
                    contacts.delete();
                    break;
                case "5":
                    contacts.searchFromNumber();
                    break;
                case "6":
                    contacts.searchFromName();
                    break;
                case "7":
                    write(contacts);
                    flagOne = false;
                    break;
                default:
                    System.out.println("Wrong Input !");
                    System.out.println("Try Again");
                    break;
            }
        } while (flagOne);
    }
}
