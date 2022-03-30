package com.aca;

import java.util.*;

public class Contacts {
    public Map<String, String> numberName;
    public Map<String, PhoneType> numberType;
    public Map<String, String> emailName;
    public Map<String, EmailType> emailEmailType;
    public List<User> contacts;

    public Contacts() {
        this.contacts = new ArrayList<>();
        this.numberName = new TreeMap<>();
        this.numberType = new TreeMap<>();
        this.emailName = new TreeMap<>();
        this.emailEmailType = new TreeMap<>();
    }

    public String deleteSpace(String str){
        StringBuilder ret= new StringBuilder();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)!=' '){
                ret.append(str.charAt(i));
            }
        }
        return ret.toString();
    }
    public boolean contains(String str,Map<String,String> map){
        for(String s: map.keySet()){
            if(deleteSpace(str).equals( deleteSpace(s))){
                return true;
            }
        }
        return false;
    }
    public void creat() {
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        boolean flag;
        String email;
        System.out.println("Input Name");
        String name = scanner.nextLine();
        user.setName(name);
        System.out.println("Input Phone");
        String phone;
        do {
            phone = scanner.nextLine();
            if (contains(phone,numberName) ) {
                System.out.println("This phone number is buse. Try again.");
            }
            if (!Validator.isPhoneNumber(phone)) {
                System.out.println("Phone number is not valid");
            }
        } while (contains(phone,numberName) || !Validator.isPhoneNumber(phone));
        user.setPhoneNumber(phone);
        numberName.put(phone, name);
        System.out.println("Input Phone Type");
        String phoneTypeQuestion;
        do {
            flag = false;
            System.out.println("1.Mobile");
            System.out.println("2.Home");
            System.out.println("3.Work");
            System.out.println("4.School");
            System.out.println("5.Company");
            phoneTypeQuestion = scanner.nextLine();

            switch (phoneTypeQuestion) {
                case "1":
                    user.setPhoneType(PhoneType.MOBILE);
                    numberType.put(phone, PhoneType.MOBILE);
                    break;
                case "2":
                    user.setPhoneType(PhoneType.HOME);
                    numberType.put(phone, PhoneType.HOME);
                    break;
                case "3":
                    user.setPhoneType(PhoneType.WORK);
                    numberType.put(phone, PhoneType.WORK);
                    break;
                case "4":
                    user.setPhoneType(PhoneType.SCHOOL);
                    numberType.put(phone, PhoneType.SCHOOL);
                    break;
                case "5":
                    user.setPhoneType(PhoneType.COMPANY);
                    numberType.put(phone, PhoneType.COMPANY);
                    break;
                default:
                    System.out.println("Wrong input !");
                    System.out.println("Try again");
                    flag = true;
                    break;
            }
        } while (flag);
        System.out.println("You have email ? Y->Yes : Other input->No");
        String emailQuestion = scanner.nextLine();
        if (emailQuestion.equals("Y")) {
            System.out.println("Input Email");
            do {
                email = scanner.nextLine();
                if (contains(email,emailName)) {
                    System.out.println("This email is buse. Try again.");
                }
                if (!Validator.isMail(email)) {
                    System.out.println("This email is not valid");
                }
            } while (contains(email,emailName) || !Validator.isMail(email));
            user.setEmail(email);
            emailName.put(email, name);
            System.out.println("Input Email Type");
            do {
                flag = false;
                System.out.println("1.Gmail");
                System.out.println("2.Icloud");
                System.out.println("3.Other");
                phoneTypeQuestion = scanner.nextLine();
                switch (phoneTypeQuestion) {
                    case "1":
                        user.setEmailType(EmailType.GMAIL);
                        emailEmailType.put(email, EmailType.GMAIL);
                        break;
                    case "2":
                        user.setEmailType(EmailType.ICLOUD);
                        emailEmailType.put(email, EmailType.ICLOUD);
                        break;
                    case "3":
                        user.setEmailType(EmailType.OTHER);
                        emailEmailType.put(email, EmailType.OTHER);
                        break;
                    default:
                        System.out.println("Wrong input !");
                        System.out.println("Try again");
                        flag = true;
                        break;
                }
            } while (flag);
        }
        contacts.add(user);
    }

    public void read() {
        for (User us : contacts) {
            System.out.println(us);
        }
    }

    public void update() {
        Scanner scanner = new Scanner(System.in);
        String question;
        String name;
        String mail;
        String TypeQuestion;
        boolean flag;
        System.out.println("Input the phone number which to be updated");
        String number = scanner.nextLine();
        if (numberName.containsKey(number)) {
            System.out.println("Update name ? Y->Yes : Other input->No");
            question = scanner.nextLine();
            if (question.equals("Y")) {
                System.out.println("Input new name.This time name is -> " + numberName.get(number));
                name = scanner.nextLine();
                for (User user : contacts) {
                    if (user.getPhoneNumber().equals(number)) {
                        if(user.getEmail()!=null) {
                            user.setName(name);
                            emailName.put(user.getEmail(), name);
                            numberName.put(number, name);
                        }else{
                            user.setName(name);
                            numberName.put(number, name);
                        }
                    }
                }
            }
            System.out.println("Update phone type ? Y->Yes : Other input->No");
            question = scanner.nextLine();
            if (question.equals("Y")) {
                System.out.println("Input new phone type.This time phone type is -> " + numberType.get(number));
                do {
                    flag = false;
                    System.out.println("1.Mobile");
                    System.out.println("2.Home");
                    System.out.println("3.Work");
                    System.out.println("4.School");
                    System.out.println("5.Company");
                    TypeQuestion = scanner.nextLine();

                    switch (TypeQuestion) {
                        case "1":
                            for (User user : contacts) {
                                if (user.getPhoneNumber().equals(number)) {
                                    user.setPhoneType(PhoneType.MOBILE);
                                    numberType.put(number, PhoneType.MOBILE);
                                    break;
                                }
                            }
                            break;
                        case "2":
                            for (User user : contacts) {
                                if (user.getPhoneNumber().equals(number)) {
                                    user.setPhoneType(PhoneType.HOME);
                                    numberType.put(number, PhoneType.HOME);
                                    break;
                                }
                            }
                            break;
                        case "3":
                            for (User user : contacts) {
                                if (user.getPhoneNumber().equals(number)) {
                                    user.setPhoneType(PhoneType.WORK);
                                    numberType.put(number, PhoneType.WORK);
                                    break;
                                }
                            }
                            break;
                        case "4":
                            for (User user : contacts) {
                                if (user.getPhoneNumber().equals(number)) {
                                    user.setPhoneType(PhoneType.SCHOOL);
                                    numberType.put(number, PhoneType.SCHOOL);
                                    break;
                                }
                            }
                            break;
                        case "5":
                            for (User user : contacts) {
                                if (user.getPhoneNumber().equals(number)) {
                                    user.setPhoneType(PhoneType.COMPANY);
                                    numberType.put(number, PhoneType.COMPANY);
                                    break;
                                }
                            }
                            break;
                        default:
                            System.out.println("Wrong input !");
                            System.out.println("Try again");
                            flag = true;
                            break;
                    }
                } while (flag);

            }
            System.out.println("Update email ? Y->Yes : Other input->No");
            question = scanner.nextLine();

            if (question.equals("Y")) {
                for (User user : contacts) {
                    if (user.getPhoneNumber().equals(number)) {
                        System.out.println("Input new email.This time email is -> " + user.getEmail());
                        do {
                            flag = false;
                            mail = scanner.nextLine();
                            if (emailName.containsKey(mail)) {
                                System.out.println("This email is busy");
                                flag = true;
                            }
                            if (!Validator.isMail(mail)) {
                                System.out.println("Mail is not valid");
                            }
                        } while (flag || !Validator.isMail(mail));
                        if (user.getEmail() != null) {
                            emailName.remove(user.getEmail());
                            emailName.put(mail, user.getName());
                            emailEmailType.remove(user.getEmail());
                            emailEmailType.put(mail, user.getEmailType());
                            user.setEmail(mail);
                        } else {
                            emailName.put(mail, user.getName());
                            emailEmailType.put(mail, user.getEmailType());
                            user.setEmail(mail);
                        }

                        break;
                    }
                }
            }

            for (User user : contacts) {
                if (user.getPhoneNumber().equals(number) && user.getEmail() != null) {

                    System.out.println("Update email type ? Y->Yes : Other input->No");
                    question = scanner.nextLine();
                    if (question.equals("Y")) {
                        System.out.println("Input new email type.This time email type is -> " + user.getEmailType());
                        do {
                            flag = false;
                            System.out.println("1.Gmail");
                            System.out.println("2.Icloud");
                            System.out.println("3.Other");
                            TypeQuestion = scanner.nextLine();
                            switch (TypeQuestion) {
                                case "1":
                                    user.setEmailType(EmailType.GMAIL);
                                    emailEmailType.put(user.getEmail(), EmailType.GMAIL);
                                    break;
                                case "2":
                                    user.setEmailType(EmailType.ICLOUD);
                                    emailEmailType.put(user.getEmail(), EmailType.ICLOUD);
                                    break;
                                case "3":
                                    user.setEmailType(EmailType.OTHER);
                                    emailEmailType.put(user.getEmail(), EmailType.OTHER);
                                    break;
                                default:
                                    System.out.println("Wrong input !");
                                    System.out.println("Try again");
                                    flag = true;
                                    break;
                            }
                        } while (flag);
                        break;
                    }


                }
            }
        } else {
            System.out.println("Such a phone number does not exist");
        }

    }

    public void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input phone number which one want to delete");
        String number = scanner.nextLine();
        if (numberName.containsKey(number)) {
            for (User user : contacts) {
                if (user.getPhoneNumber().equals(number)) {
                    if (user.getEmail() != null) {
                        numberName.remove(user.getPhoneNumber());
                        numberType.remove(user.getPhoneNumber());
                        emailName.remove(user.getEmail());
                        emailEmailType.remove(user.getEmail());
                    } else {
                        numberName.remove(user.getPhoneNumber());
                        numberType.remove(user.getPhoneNumber());
                    }
                    contacts.remove(user);
                    break;
                }
            }
        } else {
            System.out.println("Such a phone number does not exist");
        }
    }

}
