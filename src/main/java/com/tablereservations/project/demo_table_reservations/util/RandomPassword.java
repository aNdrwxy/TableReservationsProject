package com.tablereservations.project.demo_table_reservations.util;
import java.util.Random;

public class RandomPassword{

    public static String generateRandomPassword(String apellidos) {
        String inicial = (apellidos != null && !apellidos.isEmpty())
                ? apellidos.substring(0, 1).toUpperCase()
                : "X";

        String numeros = String.format("%08d", new Random().nextInt(100_000_000));
        return inicial + numeros;
    }
}
