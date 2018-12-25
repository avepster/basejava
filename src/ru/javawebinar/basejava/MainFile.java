package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class MainFile {

    public static void listingFS(String path) {
        Objects.requireNonNull(path, "path must not be null");
        File file = new File(path);
        try {
            System.out.println(file.getCanonicalPath());
            if (file.isDirectory()) {
                String[] list = file.list();
                if (list != null) {
                    for (String name : list) {
                        listingFS(path + "/" + name);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String filePath = ".\\.gitignore\\";
        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e); //e.printStackTrace();
        }

        File dir = new File("./src/ru/javawebinar/basejava");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file.getPath());
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try (FileInputStream fis2 = new FileInputStream(file.getPath())) {
            System.out.println(fis2.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        listingFS("./src");
    }

}