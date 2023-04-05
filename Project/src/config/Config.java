package config;

import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Config <T>{
    public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE
    public static final String RESET = "\033[0m";  // Text Reset
    public static final String FILE_PRODUCT_PATH = "Project/src/database/product.txt";
    public static final String FILE_USER_PATH = "Project/src/database/user.txt";
    public static final String FILE_LOGIN_PATH = "Project/src/database/loginAccount.txt";
    public static final String FILE_LOVER_PATH = "Project/src/database/lover.txt";
    public static final String VALIDATE_EMAIL = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
    public static final String VALIDATE_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
    public static Scanner scanner () {
        Scanner scanner = new Scanner(System.in);
        return scanner;
    }

    public static boolean validateInput(String regex, String input) {
        boolean isValidInput = Pattern.matches(regex,input);
        return isValidInput;
    }

    public static boolean checkDifference(List<User> registerAccountList, String input) {
        for (int i = 0; i < registerAccountList.size(); i++) {
            if(registerAccountList.get(i).getEmail().equals(input)){
                return true;
            }
            if(registerAccountList.get(i).getUserName().equals(input)){
                return true;
            }
            if(input.equals(registerAccountList.get(i).getPassword())){
                return true;
            }
        }
        return false;
    }




    public List<T> readFromFile (String filePath) {
        List<T> tList = new ArrayList<T>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            if(fileInputStream.available()!=0){
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            tList = (List<T>) objectInputStream.readObject();
            fileInputStream.close();
            }
        } catch (FileNotFoundException f){
            System.err.println("File not found");
        } catch (EOFException c) {
            System.err.println("EOF Exception");
        } catch (IOException i ) {
            System.err.println("IOE Exception");
        } catch (ClassNotFoundException c) {
            System.err.println("Class not found");
        }
        return tList;
    }

    public void writeToFile(String filePath, List<T>tList) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(tList);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (FileNotFoundException f) {
            System.err.println("File not found");
        } catch (IOException i) {
            System.err.println("IOE Exception");
        }
    }

    public void deleteFile(String filePath){
        File file = new File(filePath);
        file.delete();
    }
}
