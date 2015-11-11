/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectodba;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

public class Ventana extends JFrame implements ActionListener{
	JLabel Bienvenido;
	JMenuBar BarraMenu;
	JMenu menu;
	JMenuItem instalarPosgres;
	JTextField contraseña1;
	JTextField contraseña2;
	JComboBox puertos;
	String [] listaPuertos={"5430","5341","5432"}; 
	JTextField dir1;
	JTextField dir2;
	JPanel panelBienvenida;
	JPanel panelComponentes;
	
	JButton boton;
	JButton botonChooser;
	JLabel etcontraseña1;
	JLabel etcontraseña2;
	JLabel etpuertos;
	JLabel etdir1;
	JLabel etdir2;
	File f;
	JFileChooser archivo;
        
	public Ventana(){
		setTitle("Sistema");
		
		setSize(750,550);
		//setResizable(false);
		
		setDefaultCloseOperation(3);
		//Todo este bloque tiene que ver con el Menu.
		BarraMenu = new JMenuBar();
		menu = new JMenu("Elige...");
		instalarPosgres= new JMenuItem("Instalar...");
		instalarPosgres.addActionListener(this);		
		menu.add(instalarPosgres);
		BarraMenu.add(menu);
			
		//Acaba bloque Menu ****
		panelBienvenida = new JPanel();	
		Bienvenido = new JLabel("BIENVENIDO AL PROGRAMA");
		
		panelBienvenida.add(Bienvenido);
		add(panelBienvenida);
		setJMenuBar(BarraMenu);
		//panelBienvenida.setVisible(true);	
		setVisible(true);
			
		
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
	
		panelComponentes = new JPanel();
		String path="";
		panelComponentes.setLayout(new GridLayout(14,2)) ;
		
		
	
		if(e.getSource() == instalarPosgres){
			
			
			panelBienvenida.setVisible(false);
			
			botonChooser = new JButton("Agrega Ruta del archivo .exe");
			botonChooser.addActionListener(this); 
			panelComponentes.add(botonChooser);
			//agrego la etiqueta y la caja de texto al panel en una sola línea.
			etcontraseña1 = new JLabel(" Contraseña:  "); contraseña1 = new JTextField(20); panelComponentes.add(etcontraseña1); panelComponentes.add(contraseña1);
			etcontraseña2 = new JLabel("Contraseña2: "); contraseña2 = new JTextField(20); panelComponentes.add(etcontraseña2);panelComponentes.add(contraseña2);
			
			puertos = new JComboBox<Object>(listaPuertos); etpuertos = new JLabel("Puertos: ");panelComponentes.add(etpuertos) ;panelComponentes.add(puertos); 
			
			etdir1 = new JLabel("Dirección 1: "); dir1= new JTextField(20); panelComponentes.add(etdir1); panelComponentes.add(dir1);
			etdir2 = new JLabel("Dirección 2: ");	dir2= new JTextField(20); panelComponentes.add(etdir2); panelComponentes.add(dir2);
			boton = new JButton("Crear archivo"); boton.addActionListener(this); panelComponentes.add(boton);
			panelComponentes.setSize(400, 400);
			add(panelComponentes);
		}
		if( boton == e.getSource()){
			File a =  archivo.getSelectedFile();
			path= a.getAbsolutePath();
			try{
				//Cambiale esta ruta por la de tu compu, se guardara allí el archivo.
				f = new File("C:\\"
                                        + "postgrest.bat");
				BufferedWriter out = new BufferedWriter (new OutputStreamWriter(new FileOutputStream(f,false)));
					out.write(path + " --mode unattended" + " --superpassword " + contraseña1.getText() +
							" --servicepassword "+contraseña2.getText() + " --serverport "+puertos.getSelectedItem()+
							" --unattendedmodeui none --prefix "+ '"'+"C:\\Users\\noe\\Desktop\\JULIO\\postgrest"+'"'+
							" --datadir " + '"'+"C:\\Users\\noe\\Desktop\\JULIO\\postgrest\\data"+'"');
					out.close();
			}catch(IOException eb){
				
			}
		}
		
		//Con esto funciona el FileChooser
		if(botonChooser == e.getSource()){
			archivo = new JFileChooser();
			archivo.showOpenDialog(null);
			
			
			System.out.println(path);
			
			
		}
		
	}
 
}