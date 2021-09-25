package com.epam.knight.controller;

import com.epam.knight.model.Knight;
import com.epam.knight.model.ammunition.Helmet;
import com.epam.knight.model.ammunition.Sword;
import com.epam.knight.view.ConsoleView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class KnightGenerator {
    private static final byte AMMUNITION_TYPE = 0;
    private static final byte WEIGHT = 1;
    private static final byte COST = 2;
    private static final byte DAMAGE = 3;
    private static final byte PROTECTION = 3;
    private static final String SWORD = "Sword";
    private static final String HELMET = "Helmet";
    private static final String FILE_KNIGHT = "src/main/java/com/epam/knight/model/Knight.txt";

    private KnightGenerator() {
    }
    /**
     * Use it to quickly generate knight
     * @return knight
     */

    public static Knight generateKnight() {
        Knight knight;
        try {
            knight = new Knight();
            File file = new File(FILE_KNIGHT);
            try (FileReader fr = new FileReader(file)) {
                BufferedReader reader = new BufferedReader(fr);
                String line = reader.readLine();
                while (line != null) {
                    String[] ammunition = line.split("-");
                    if (ammunition[AMMUNITION_TYPE].equals(SWORD)) {
                        int weight = Integer.parseInt(ammunition[WEIGHT].trim());
                        int cost = Integer.parseInt(ammunition[COST].trim());
                        int damage = Integer.parseInt(ammunition[DAMAGE].trim());
                        knight.equip(new Sword(weight, cost, damage));
                    }
                    if (ammunition[AMMUNITION_TYPE].equals(HELMET)) {
                        int weight = Integer.parseInt(ammunition[WEIGHT].trim());
                        int cost = Integer.parseInt(ammunition[COST].trim());
                        int protection = Integer.parseInt(ammunition[PROTECTION].trim());
                        knight.equip(new Helmet(weight, cost, protection));
                    }
                    line = reader.readLine();
                }
            }
        } catch (IOException ioException) {
            new ConsoleView().printMessage(ConsoleView.FILE_NOT_FOUND_EXCEPTION);
            knight = new Knight();
        }
        return knight;
    }
}
