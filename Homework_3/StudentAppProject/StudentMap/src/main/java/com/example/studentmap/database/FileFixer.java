package com.example.studentmap.database;

import java.io.*;

public class FileFixer{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/csvFiles/out.csv"));
        PrintWriter pw = new PrintWriter(new FileWriter("src/main/java/com/example/studentmap/database/locations.csv"));

        String line;
        while (br.ready()){
            line = br.readLine();
            pw.write(line.replaceAll("\"", ""));
            pw.write("\n");
            pw.flush();
        }
    }
}
