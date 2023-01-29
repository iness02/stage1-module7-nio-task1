package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Map<String, String> profileData = new LinkedHashMap<>();

        try (BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
                line = br.readLine();
            }

            String[] parts = stringBuilder.toString().split("\n");

            for (String part : parts) {
                String[] res = part.split(":");
                profileData.put(res[0].trim().toLowerCase(), res[1].trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return profileBuilder(profileData);
    }

    private Profile profileBuilder(Map<String, String> profileData) {

        String name = profileData.get("name");
        int age = Integer.parseInt(profileData.get("age"));
        String email = profileData.get("email");
        Long phone = Long.valueOf(profileData.get("phone"));

        return new Profile(name, age, email, phone);
    }
}
