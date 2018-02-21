/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorclases;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 *
 * @author ale
 * se codifico en ingles 
 */
public class AnalizadorClases {

    /**
     * @param args the command line arguments
     */
    private static final String PATH_CODE = "./Venta.java";    
    private static int countCodeLines = 1;
    private static int i = 0;
    
    public static void main(String[] args) throws IOException {
        ArrayList<String>  javaClass = new ArrayList<>();                
        javaClass = consultarArchivo(PATH_CODE);
                
        System.out.println("***the class will be modified to remove the empty lines, the \"}\" do not count as a code line***\n\n");
        System.out.println("Here's the result....\n");
        for(String codeLine: javaClass){ 
            
            if(codeLine.trim().isEmpty()==false && hasAnnotations(codeLine)== false){
                i++;
                determineCountOfCodeLine(codeLine);
                System.out.println(i+" "+codeLine);
                
            }
        }        
        System.out.println("\n");
        //the program needs the class in the same package and in the path "./yourClass.java"
        //
        Venta objVenta = new Venta();        
        Class analizeClass = objVenta.getClass();                        
        Method[] methodList=analizeClass.getDeclaredMethods();
        Field[] fieldList=analizeClass.getDeclaredFields();
        Constructor[] constructorList = analizeClass.getConstructors();
                
        System.out.println("Total methods: "+methodList.length);
        System.out.println("Total fields: "+ fieldList.length);
        System.out.println("Total constructors: "+ constructorList.length);
        System.out.println("Count of code lines: "+countCodeLines);
        
    }
    
    private static void determineCountOfCodeLine(String codeLine){
        if(codeLine.isEmpty()==false&&hasCurlyBracket(codeLine)==false){
            countCodeLines++;
        }
    }
    
    private static ArrayList<String> consultarArchivo(String archivo) throws FileNotFoundException, IOException{
              
       String codeLine;
       ArrayList<String> codeLines = new ArrayList<>();       
       FileReader f = new FileReader(archivo);
       BufferedReader b = new BufferedReader(f);
                         
       while ((codeLine = b.readLine()) != null) {           
           codeLines.add(codeLine);                      
       }

       b.close();
              
       return codeLines;
    }
    
    private static boolean hasCurlyBracket(String codeLine){
        String[] charsOfCodeLine = codeLine.split("(?!^)");
        int countOfCurlyBrackets = 0;
        
        for(String charOfCodeLine: charsOfCodeLine){
           if(charOfCodeLine.equalsIgnoreCase("}")){
               countOfCurlyBrackets++;
           } 
        }
        
        if(countOfCurlyBrackets==1){
            return true;
        }else{    
            return false;
        }
    }
    
    private static boolean hasAnnotations(String codeLine){
        String[] charsOfCodeLine = codeLine.split("(?!^)");
        
        if(charsOfCodeLine.length>1){
            if(charsOfCodeLine[0].equalsIgnoreCase("/")||charsOfCodeLine[1].equalsIgnoreCase("*")){
                return true;
            }else{
                return false;
            }   
        }else{
            if(charsOfCodeLine[0].equalsIgnoreCase("/")){
                return true;
            }else{
                return false;
            }
        }
    }        
}
