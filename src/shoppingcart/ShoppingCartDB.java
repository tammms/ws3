package shoppingcart;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class ShoppingCartDB {

    static List<String> usernames = new LinkedList<>();
    static List<String> supermarket = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        Console cons = System.console();
        
        while(true){


        
        ////////////////////Creat Directory to store file//////////////////////
        System.out.println("\nPlease specify database, if not type \"not specified\"");

        
        String dbName = cons.readLine();
        
        if(dbName.equals("not specified")){
            dbName = "db";
        }

        File theDir = new File(dbName);
        if (!theDir.exists()){
            theDir.mkdirs();
            System.out.printf("Data will be stored in %s Folder\n", dbName);
        }
        
        else{
            System.out.printf("Data will be stored in %s Folder\n", dbName);
        }

        /*/check whether file exist
        String username = cons.readLine();
        Path filepath = Paths.get(dbName, username+ ".db");
        boolean check = Files.exists(filepath);
        System.out.println(check); */

        System.out.println("\nWelcome to your shopping cart");

        String input = cons.readLine();
        Path filepath = null;
        
        boolean userinput = true;

        while(userinput){
            
            input = input.trim().toLowerCase();
            String[] inputWord = input.split("\\W+");

            if(inputWord[0].equals("login")){

                filepath = Paths.get(dbName, inputWord[1] + ".db");
                boolean check = Files.exists(filepath);

                if (check == true){
                    // user file exist, load existing text file into supermarket list
                    FileReader fr = new FileReader(filepath.toString());
                    BufferedReader br = new BufferedReader(fr);
                    
                    String line;
                    while ((line = br.readLine())!=null) {
                        supermarket.add(line);
                    }

                }
                else{
                    // user file don't exist, create user db, add user name to usernames list
                    //Path p = Path.createFile(filepath);

                    Files.createFile(filepath);
                    usernames.add(inputWord[1]);
                    
                }

                if(supermarket.size() == 0){
                    System.out.printf("%s, Your cart is empty\n",inputWord[1]);
                    userinput = false;
                        }
                else{
                    System.out.printf("%s, your cart contains the following items\n", inputWord[1]);
                    for(int i = 0; i<supermarket.size(); i++){
                        System.out.printf((i+1) + ". "+ supermarket.get(i) +"\n");
                            }
                    userinput = false;
                        }
                
                

                
            }

            else {
                    System.out.println("Please login! Enter: login <your name>");
                    input = cons.readLine();
                    continue;
                }
            



        } //Login while loop end here  */

        String strFilePath = filepath.toString();
        String cartinput = "";
        userinput = true;
    

        while(userinput){
            cartinput = cons.readLine();
            cartinput = cartinput.trim().toLowerCase();
            String[] inputWord = cartinput.split("\\W+");


            if(inputWord[0].equals("list")){
                if(supermarket.size() == 0){
                    System.out.println("Your cart is empty");
                }
                else{
                for(int i = 0; i<supermarket.size(); i++){
                    System.out.printf((i+1) + ". "+ supermarket.get(i) +"\n");
                }
                } 
            }

            else if(inputWord[0].equals("add")){
                for(int i = 1; i<inputWord.length; i++){
                    boolean itemExists = false;
                    for(int j = 0; j<supermarket.size(); j++){
                        if((inputWord[i]).equals(supermarket.get(j))){
                            System.out.printf("you have %s in your cart \n", inputWord[i]);
                            itemExists = true;
                            break;
                        }
                        
                    }
                    if(!itemExists){ // to check if condition is not true
                    supermarket.add(inputWord[i]);
                    System.out.printf("%s added to cart \n",inputWord[i]);
                }
                }
            }
            else if(inputWord[0].equals("delete")){
                Integer deleteIndex = Integer.parseInt(inputWord[1]);
                if((deleteIndex -1) <= supermarket.size()){

                    System.out.printf("%s removed from cart",supermarket.get(deleteIndex-1));
                    supermarket.remove(deleteIndex-1);
                }
                else System.out.println("Incorrect item index");
            }
            else if (inputWord[0].equals("save")) {
                System.out.println("Your cart has been saved");
                BufferedWriter bw = new BufferedWriter(new FileWriter(strFilePath));
                
                for (String item : supermarket) {
                    bw.write(item);
                    bw.newLine(); // Add a new line after each item
                }
                bw.close();
                userinput = false;
            }

            else if(inputWord[0].equals("users")){
                System.out.println("The follwoing users are registered");
                for(int i = 0; i<usernames.size(); i++){
                    System.out.printf((i+1) + ". "+ usernames.get(i) +"\n");

                }

            }

            
            else {
                cartinput = cons.readLine("""
            \nPlease choose the following actions:
            1. add: Add items to list
            2. delete: Delete items from list
            3. list: View items in shopping cart\n 
            """);
            continue;
            } 
}



    } 


        




        

    } 
}