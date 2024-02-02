package com.example.basictodolist;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHelper {

    public static final String FILENAME = "listinfo.dat";

    public static void writeData(ArrayList<String> items, Context context){
        //This method writes an ArrayList of Strings (the to-do list items) to a file

        try{
            FileOutputStream fileOutputStream = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(items);
            objectOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<String> readData(Context context){
        //This method reads data from the file and returns it as an ArrayList of String

        ArrayList<String> itemsList = null;

        try {
            FileInputStream fileInputStream = context.openFileInput(FILENAME);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            itemsList = (ArrayList<String>) objectInputStream.readObject();

        } catch (FileNotFoundException e) {

            itemsList = new ArrayList<String>();
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return itemsList;
    }
}
