/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectodba;
import java.io.*;
/**
 *
 * @author evd00
 */
public class ProyectoDBA {

   
   public static void main(String [] arg) {
    new Ventana();
    
   }
   public  void escribir(String nombreArchivo)
    {
        File f;

        f = new File(nombreArchivo);
        try{
          FileWriter w = new FileWriter(f);
          BufferedWriter bw = new BufferedWriter(w);
          PrintWriter wr = new PrintWriter(bw);  
          wr.write("Esta es una linea de codigo");//escribimos en el archivo
          wr.append(" - y aqui continua"); //concatenamos en el archivo sin borrar lo existente
          wr.close();
          bw.close();

        }catch(IOException e){};
        }
   public void ejecutaBAT(){
        Runtime aplicacion = Runtime.getRuntime(); 
        try{
            aplicacion.exec("cmd.exe  /K start C:\\Users\\evd00\\Desktop\\Bueno\\ProyectoDBA\\nuevoArchivo.bat"); 
            System.out.println("ya chingamos");
                    
        }
        catch(Exception e){System.out.println(e);}
   }
}
