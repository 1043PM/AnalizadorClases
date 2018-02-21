package analizadorclases;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ale
 */
public class Venta {
    
    private ArrayList<String> lista = new ArrayList<>();
    private int numeroCliente;
    
    public Venta(int numeroCliente,ArrayList<String> lista) throws IOException {        
        this.lista = lista;
        this.numeroCliente = numeroCliente;
        
    }
    //anotacion 3
    public Venta(){
        
    }
    //anotacion 4
    /* hola hola hola*/
    @Override
    public String toString() {
        
        return "Venta{" + "lista=" + lista + ", numeroCliente=" + numeroCliente + '}';
    }

    public ArrayList<String> getLista() {
        return lista;
    }

    public void setLista(ArrayList<String> lista) {
        this.lista = lista;
    }

    public int getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(int numeroCliente) {
        this.numeroCliente = numeroCliente;
    }   
    public void addItem(String item){
        lista.add(item);
    }
    
    private ArrayList<String> consultarArchivo(String archivo) throws FileNotFoundException, IOException{
              
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
}
