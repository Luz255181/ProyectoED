package GUI;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import Programa.*;

public class GUI_Programa extends JFrame {
	private JButton botonIngresar;
	private JButton botonAsignarH, botonDesasignarH, botonDatos, botonListar, botonCantPacientes;
	private JButton botonIngresarUrgencias, botonAtender, botonCantUrgencias;
	private JLabel etiquetaContraseña;
	private JPasswordField textoContraseña;
	private JPanel panelIngreso, panelHabitaciones, panelUrgencias;
	private Logica logica;
	
	public GUI_Programa() {
		super("Sala de urgencias");
		setSize(new Dimension(1375, 730));
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		logica=new Logica();
		armarBotones();
		etiquetaContraseña=new JLabel(" Ingrese su contraseña: ");
		textoContraseña=new JPasswordField();
		armarPaneles();
		getContentPane().add(panelIngreso, BorderLayout.NORTH);
		getContentPane().add(panelHabitaciones, BorderLayout.WEST);
		//Panel central
		getContentPane().add(panelUrgencias, BorderLayout.EAST);
	}
	private void armarBotones() {
		botonIngresar=new JButton("Ingresar");
		botonAsignarH=new JButton("Asignar habitación");
		botonDesasignarH=new JButton("Desasignar habitación");
		botonDatos=new JButton("Consultar datos");
		botonListar=new JButton("Habitaciones vacias");
		botonCantPacientes=new JButton("Cantidad de pacientes en habitación");
		botonIngresarUrgencias=new JButton("Ingresar paciente");
		botonAtender=new JButton("Atender paciente");
		botonCantUrgencias=new JButton("Cantidad de pacientes de urgencias");
	}
	private void armarPaneles() {
		panelIngreso=new JPanel();
		panelIngreso.setLayout(new BorderLayout());
		panelIngreso.add(etiquetaContraseña, BorderLayout.WEST); panelIngreso.add(textoContraseña, BorderLayout.CENTER); panelIngreso.add(botonIngresar, BorderLayout.EAST);
		
		panelHabitaciones=new JPanel();
		panelHabitaciones.setLayout(new GridLayout(5, 1));
		panelHabitaciones.setBorder(BorderFactory.createTitledBorder(null, "Habitaciones", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
		panelHabitaciones.add(botonAsignarH); panelHabitaciones.add(botonDesasignarH);
		panelHabitaciones.add(botonDatos); panelHabitaciones.add(botonListar);
		panelHabitaciones.add(botonCantPacientes);
		
		panelUrgencias=new JPanel();
		panelUrgencias.setLayout(new GridLayout(5, 1));
		panelUrgencias.setBorder(BorderFactory.createTitledBorder(null, "Urgencias", TitledBorder.RIGHT, TitledBorder.DEFAULT_POSITION));
		panelUrgencias.add(botonIngresarUrgencias); panelUrgencias.add(new JLabel());
		panelUrgencias.add(botonAtender); panelUrgencias.add(new JLabel());
		panelUrgencias.add(botonCantUrgencias);
	}
}