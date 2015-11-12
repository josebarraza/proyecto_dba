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
	JMenuBar BarraMenu;
	JMenu menu;
        JMenuItem btnInicio;
	JMenuItem btnInstalar;
	JTextField txtPassword;
	JTextField txtRepassword;
	JTextField txtPuerto;
	JTextField txtDir1;
	JTextField txtDir2;
	JPanel[] paneles;
        int posicion;
	
	
	JButton btnEnviar;
	JButton botonChooser;
	File f,ejecutable;
	JFileChooser archivo;
        
	public Ventana(){
		interfaz();
                escuchas();	
	}
	
	public void interfaz(){
                setTitle("Sistema");
		setSize(750,550);
                setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		//Todo este bloque tiene que ver con el Menu.
		BarraMenu = new JMenuBar();
		menu = new JMenu("Menú");
		btnInstalar= new JMenuItem("Instalar Postgres");
                btnInicio= new JMenuItem("Inicio");
		
                menu.add(btnInicio);
		menu.add(btnInstalar);
		BarraMenu.add(menu);
                /***/
                //Paneles
		paneles = new JPanel[10];
                paneles[0] = new JPanel();
                paneles[1] = new JPanel();
                paneles[1].setSize(300, 400);
                paneles[1].setLayout(new GridLayout(7,2)) ;
               
		
		paneles[0].add(new JLabel("Bienvenido al sistema"));
		add(paneles[0]);
                posicion = 0;
		setJMenuBar(BarraMenu);
                
                //***PANEL 1 = INSTALAR POSTGRES
                botonChooser = new JButton("Examinar");
                paneles[1].add(new JLabel("Instalador:"));
                paneles[1].add(botonChooser);
               
		txtPassword = new JTextField(20); 
                paneles[1].add(new JLabel("Super password:")); 
                paneles[1].add(txtPassword);
		txtRepassword = new JTextField(20); 
                paneles[1].add(new JLabel("Service password:"));
                paneles[1].add(txtRepassword);
                
                txtPuerto = new JTextField();
                paneles[1].add(new JLabel("Puerto:"));
                paneles[1].add(txtPuerto);
                
                paneles[1].add(new JLabel("Ruta de instalación:"));
                txtDir1 = new JTextField();
                paneles[1].add(txtDir1);
                paneles[1].add(new JLabel("Ruta de datos:"));
                txtDir2 = new JTextField();
                paneles[1].add(txtDir2);
                btnEnviar = new JButton("CREAR .BAT");   
                paneles[1].add(btnEnviar);
              
			
		setVisible(true);
                
        }
        public void escuchas(){
                btnInstalar.addActionListener(this);	
                btnInicio.addActionListener(this);
                botonChooser.addActionListener(this); 
                btnEnviar.addActionListener(this);
        }
	
	public void actionPerformed(ActionEvent e) {
	
		//Opcion instalar postgres
		if(e.getSource() == btnInstalar){
			
			this.remove(paneles[posicion]);
                        posicion = 1;
			add(paneles[posicion]);
                         validate();
                        repaint();
		}
                //Opcion Inicio
                else if(e.getSource() == btnInicio){
                    this.remove(paneles[posicion]);
                    posicion = 0;
                    add(paneles[posicion]);
                        validate();
                        repaint();
                }
                //Opcion crear .bat
                else if( btnEnviar == e.getSource()){
			
                        try{
                            f = new File("C:\\Users\\evd00\\Desktop\\Bueno\\postgres.bat");
                            FileWriter w = new FileWriter(f);
                            BufferedWriter bw = new BufferedWriter(w);
                            PrintWriter wr = new PrintWriter(bw);  
                            wr.write(ejecutable.getName());//escribimos el instalador
                            wr.append(" --mode unattended"); //escribimos el modo
                            wr.append(" --superpassword " + txtPassword.getText()); //escribimos super password
                            wr.append(" --servicepassword " + txtRepassword.getText()); //escribimos service password
                            wr.append(" --serverport "+txtPuerto.getText()); // escribimos puerto
                            wr.append(" --unattendedmodeui none");
                            wr.append(" --prefix ''"+txtDir1.getText()+"''"); //Ruta de instalacion
                            wr.append(" --datadir ''"+txtDir2.getText()+"''"); //Ruta de datos
                            wr.close();
                            bw.close();
                            JOptionPane.showMessageDialog(null,"Archivo creado correctamente");

                         }catch(IOException ioe){}
        
                        
		}
		
		//Con esto funciona el FileChooser
		if(botonChooser == e.getSource()){
			archivo = new JFileChooser();
			archivo.showOpenDialog(null);
                        ejecutable = archivo.getSelectedFile();
                        if(ejecutable != null)
                            botonChooser.setText(ejecutable.getName());
                        else
                            botonChooser.setText("Examinar");
			
			
		}
		
	}
 
}