package GUI;
import java.awt.*;
import javax.swing.*;
import Programa.Programa;

public class GUI_Programa extends JFrame {
	private JButton botonIngresar;
	private JButton botonAsignarH, botonDesasignarH, botonDatos, botonListar, botonCantPacientes;
	private JButton botonIngresarUrgencias, botonAtender, botonCantUrgencias;
	private JLabel etiquetaContrase�a;
	private JPasswordField textoContrase�a;
	private JPanel panelIngreso, panelHabitaciones, panelUrgencias;
	private Programa programa;
	
	public GUI_Programa() {
		super();
		setSize(new Dimension(1375, 730));
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		programa=new Programa();
		armarBotones();
		etiquetaContrase�a=new JLabel(" Ingrese su contrase�a: ");
		textoContrase�a=new JPasswordField();
		armarPaneles();
		getContentPane().add(panelIngreso, BorderLayout.NORTH);
		getContentPane().add(panelHabitaciones, BorderLayout.WEST);
		//Panel central
		getContentPane().add(panelUrgencias, BorderLayout.EAST);
	}
	private void armarBotones() {
		botonIngresar=new JButton("Ingresar");
		botonAsignarH=new JButton("Asignar habitaci�n");
		botonDesasignarH=new JButton("Desasignar habitaci�n");
		botonDatos=new JButton("Consultar datos");
		botonListar=new JButton("Habitaciones vacias");
		botonCantPacientes=new JButton("Cantidad de pacientes en habitaci�n");
		botonIngresarUrgencias=new JButton("Ingresar paciente");
		botonAtender=new JButton("Atender paciente");
		botonCantUrgencias=new JButton("Cantidad de pacientes de urgencias");
	}
	private void armarPaneles() {
		panelIngreso=new JPanel();
		panelIngreso.setLayout(new BorderLayout());
		panelIngreso.add(etiquetaContrase�a, BorderLayout.WEST); panelIngreso.add(textoContrase�a, BorderLayout.CENTER); panelIngreso.add(botonIngresar, BorderLayout.EAST);
		
		panelHabitaciones=new JPanel();
		panelHabitaciones.setLayout(new GridLayout(5, 1));
		panelHabitaciones.add(botonAsignarH); panelHabitaciones.add(botonDesasignarH);
		panelHabitaciones.add(botonDatos); panelHabitaciones.add(botonListar); panelHabitaciones.add(botonCantPacientes);
		
		panelUrgencias=new JPanel();
		panelUrgencias.setLayout(new GridLayout(3, 1));
		panelUrgencias.add(botonIngresarUrgencias); panelUrgencias.add(botonAtender); panelUrgencias.add(botonCantUrgencias);
	}
}