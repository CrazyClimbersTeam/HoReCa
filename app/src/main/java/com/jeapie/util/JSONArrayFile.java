package com.jeapie.util;

import com.jeapie.util.json.JSONArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class JSONArrayFile {
    private final File file;

    public JSONArrayFile(File file) {
        this.file = file;
    }

    public JSONArrayFile(String filename) {
        this.file = new File(filename);
    }

    public synchronized void write(JSONArray array) throws IOException {
        FileWriter fw = new FileWriter(file, false);
        fw.write(array.toString());
        fw.close();
    }

    public synchronized void append(JSONArray array) throws IOException {
        JSONArray readed = read();
        for (int i = 0; i < array.length(); i++) {
            readed.put(array.get(i));
        }
        write(readed);
    }

    public synchronized JSONArray read() throws IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        String s = new String(data, "UTF-8");
        if (s.isEmpty()) return new JSONArray();
        else
            return new JSONArray(s);
    }
}
