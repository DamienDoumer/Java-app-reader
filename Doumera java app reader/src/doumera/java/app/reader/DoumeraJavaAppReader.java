/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doumera.java.app.reader;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Damien
 */

class Reader
{
    private int count;
    private String path, first, second;
    FileWriter Writer;
    private final String keyWord = "java -jar ";
    
    public Reader(String Path) throws IOException
    {
        path = Path;
        first = "cls "+"@echo off "+"color 0A ";
        second  = " echo. " + " pause";
        try
        {
        Writer = new FileWriter("Reader.bat");
        }
        catch(FileNotFoundException e)
        {
            System.out.println("The batch file Reader.bat is missing");
        }
    }
    
    private void Write() throws IOException
    {
        String path2 = path;
        try
        {
            String newLine = System.getProperty("line.separator");//call for a new Line
            
            String[] wr = new String[6];
            wr[0] = "cls "+newLine;
            wr[1] = "@echo off "+newLine;
            wr[2] = "color 0A"+newLine;
            wr[3] = keyWord+path2+newLine;
            wr[4] = "echo."+newLine;
            wr[5] = "pause"+newLine+"echo Please close this window "
                    + "off if execution is finished running"+newLine;
            
            for(int i=0;i<6;i++)
            {
                Writer.append(wr[i]);
            }
        }
        catch(IOException e)
        {
            System.out.println("Batch file may be missing");
        }
        finally
        {
            Writer.close();
        }
    }
    public void Wait()
    {
        //tells the program to wait when implemented on console
    }
    
    public void run()
    {
        try {
            Write();
        } catch (IOException ex) {
            System.out.println("Error occured while using the batch file");
        }
        batch();
    }
    
    private void batch()
    {
        try {
            
            Process proc = Runtime.getRuntime().exec("cmd /c start Reader.bat");
            InputStream is = proc.getInputStream();
            int i = 0;
            while( (i = is.read() ) != -1) {
             System.out.print((char)i);
            }
        } catch (IOException ex) {
            System.out.println("Error processing the batch file");
        }
    }
    
    public static void Bat2(String arg)
    {
        try {
            
            Process proc = Runtime.getRuntime().exec("cmd /c start "+arg);
            InputStream is = proc.getInputStream();
            int i = 0;
            while( (i = is.read() ) != -1) {
             System.out.print((char)i);
            }
        } catch (IOException ex) {
            System.out.println("Error processing the batch file");
        }
    }
    /*private String removeCharacter(String sentence, char quotes)
    {
        char[] build = new char[sentence.length()];
        
        for(int i = 0;i<sentence.length();i++)
        {
            if(sentence.charAt(i) == quotes)
            {
                continue;
            }
            build[i] = sentence.charAt(i);
        }
        return new String(build);
    }*/
}

public class DoumeraJavaAppReader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       //Allow the program to run in a loop since stopping the console is dificult
        BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));
        String Path ;
        int choice = 1;
        Reader.Bat2("attrib +h output.bat");
        Reader.Bat2("attrib -h Reader.bat");
        Reader.Bat2("output.bat");
          
        do
        {
            System.out.println("Please drag and drop your .jar program here,  "
                    + "and press enter"
            +"\nYou must terminate the execution of a droped"
                    + " program before dropping the next\n");
            Path = reader.readLine();
            Reader r = new Reader(Path);
            r.run();
            System.out.println("Please to exit, press 0 OR "
                    + "press any other number to continue");
            try
            {
                choice = Integer.parseInt(reader.readLine());
            }
            catch(Exception e)
            {
                    System.out.printf("Your input was not a number the program will exit\n");
                    break;
            }
        }
        while(choice!= 0);
        Reader.Bat2("attrib +h Reader.bat");
    }
}
