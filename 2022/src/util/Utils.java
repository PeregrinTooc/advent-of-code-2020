package util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class Utils {

    public static String[] transform(File file) {
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        ArrayList<String> intermediate = new ArrayList<String>();
        try {
            while ((line = bufferedReader.readLine()) != null) {
                intermediate.add(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        String[] result = intermediate.toArray(new String[0]);
        System.out.println(result);
        return result;
    }

    public static List<List<String>> splitAt(String[] in) {
        return splitAt(in, "");
    }

    public static List<List<String>> splitAt(String[] in, String separator) {
        Iterator<String> puzzleInput = new ArrayList<String>(Arrays.asList(in)).iterator();
        List<List<String>> result = new ArrayList<List<String>>();
        ArrayList<String> part = new ArrayList<String>();
        while (puzzleInput.hasNext()) {
            String s = puzzleInput.next();
            if (!s.equals(separator)) {
                part.add(s);
            } else {
                result.add(part);
                part = new ArrayList<String>();
            }
        }
        result.add(part);
        return result;
    }
}