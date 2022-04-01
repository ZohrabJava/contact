package com.aca;

import java.util.HashMap;
import java.util.Map;

public enum EmailType {
    MAIL(1,"@mail.ru"),
    GMAIL(2,"@gmail.com"),
    ICLOUD(3,"@iCloud.com"),
    YAHOO(4,"@yahoo.com"),
    YANDEX(5,"@yandex.com"),
    ACA(6,"@aca.am"),
    OTHER(7,"☺");


    private final static Map<Integer, EmailType> mailTypesMap = new HashMap<>();
    private final int code;
    private final String name;


    static {
        for (EmailType type : EmailType.values()) {
            mailTypesMap.put(type.getCode(), type);
        }
    }

    EmailType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static EmailType getMailType(String mail) {
        for (EmailType c: mailTypesMap.values()) {
            if (c.name.equalsIgnoreCase(mail)){
                return c;
            }
        }
        return OTHER;
    }
}
