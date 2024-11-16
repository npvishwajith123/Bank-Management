package com.np.bank.management.utilities;

import org.springframework.stereotype.Component;

@Component
public class CommonUtils {

    public static void createProgramBanner() {
        System.out.println("\n\n\t\t\t\t\t\t\t\t\t#############################################");
        System.out.println("\t\t\t\t\t\t\t\t\t# Bank Management Web Application started #");
        System.out.println("\t\t\t\t\t\t\t\t\t#############################################\n\n");
    }
}

