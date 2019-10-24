package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import Auxiliar.*;
import Programa.*;
import TDALista.*;

public class GUI_Programa extends JFrame {
	private JButton botonAsignar, botonDesasignar, botonDatos, botonListar, botonCantPacientes;
	private JButton botonIngresar, botonAtender, botonCantUrgencias, botonSalir;
	private JLabel etiquetaContraseña, etiquetaDNIhab, etiquetaDNIurg, etiquetaDNIdatos;
	private JLabel etiquetaFecha, etiquetaOS, etiquetaHabitacion, etiquetaCodigo;
	private JPasswordField textoContraseña;
	private JTextField dniHab, dniUrg, dniDatos, fechaNacimiento, OS;
	private JComboBox<Character> habitacion;
	private JComboBox<Integer> codUrgencia;
	private JPanel panelIngreso, panelControles, panelHabitaciones, panelUrgencias, panelCentral;
	private Logica programa;

	public GUI_Programa() {
		super("Control sala de urgencias");
		setSize(new Dimension(600, 450));
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		programa = new Logica();
		armarBotones();
		armarComponentes();
		armarPaneles();
		getContentPane().add(panelIngreso, BorderLayout.NORTH);
		getContentPane().add(panelControles, BorderLayout.CENTER);
	}

	private void armarBotones() {
		// Creo los botones
		botonSalir = new JButton(" Salir ");
		botonSalir.setEnabled(false);
		botonAsignar = new JButton("Asignar habitación");
		botonAsignar.setEnabled(false);
		botonDesasignar = new JButton("Desasignar habitación");
		botonDesasignar.setEnabled(false);
		botonDatos = new JButton("Consultar datos del paciente");
		botonDatos.setEnabled(false);
		botonListar = new JButton("Listar las habitaciones vacias");
		botonListar.setEnabled(false);
		botonCantPacientes = new JButton("Cantidad de pacientes en habitación");
		botonCantPacientes.setActionCommand("cantPacientes");
		botonCantPacientes.setEnabled(false);
		botonIngresar = new JButton("Ingresar paciente");
		botonIngresar.setEnabled(false);
		botonIngresar.setActionCommand("Ingresar");
		botonAtender = new JButton("Atender paciente");
		botonAtender.setEnabled(false);
		botonAtender.setActionCommand("Atender");
		botonCantUrgencias = new JButton("Cantidad de pacientes de urgencias");
		botonCantUrgencias.setActionCommand("cantUrgencias");
		botonCantUrgencias.setEnabled(false);

		// Asigno los oyentes a cada botón
		botonSalir.addActionListener(new OyenteSalir());
		botonAsignar.addActionListener(new OyenteAsignar());
		botonDesasignar.addActionListener(new OyenteDesasignar());
		botonDatos.addActionListener(new OyenteConsultar());
		botonListar.addActionListener(new OyenteListar());
		botonCantPacientes.addActionListener(new OyenteCantidad());
		botonCantUrgencias.addActionListener(new OyenteCantidad());
		botonIngresar.addActionListener(new OyenteUrgencias());
		botonAtender.addActionListener(new OyenteUrgencias());
	}

	private void armarComponentes() {
		// Creo las etiquetas con sus respectivos textos
		etiquetaContraseña = new JLabel(" Contraseña: ");
		etiquetaDNIhab = new JLabel("DNI: ");
		etiquetaDNIurg = new JLabel("DNI: ");
		etiquetaDNIdatos = new JLabel("DNI: ");
		etiquetaFecha = new JLabel("Fecha de nacimiento: ");
		etiquetaOS = new JLabel("Obra social: ");
		etiquetaHabitacion = new JLabel("Habitación: ");
		etiquetaCodigo = new JLabel("Código de urgencia: ");

		// Creo el campo de contraseña y le agrego su oyente
		textoContraseña = new JPasswordField();
		textoContraseña.addActionListener(new OyenteAcceso());

		// Creo los campos de texto y seteo sus tamaños
		dniHab = new JTextField();
		dniHab.setPreferredSize(new Dimension(500, 25));
		dniUrg = new JTextField();
		dniUrg.setPreferredSize(new Dimension(200, 25));
		dniDatos = new JTextField();
		dniDatos.setPreferredSize(new Dimension(500, 25));
		fechaNacimiento = new JTextField();
		fechaNacimiento.setPreferredSize(new Dimension(400, 25));
		OS = new JTextField();
		OS.setPreferredSize(new Dimension(450, 25));

		// Creo las cajas de opciones y seteo las opciones
		Character[] habitaciones = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j' };
		habitacion = new JComboBox<Character>(habitaciones);
		Integer[] prioridades = { 1, 2, 3, 4, 5 };
		codUrgencia = new JComboBox<Integer>(prioridades);
	}

	private void armarPaneles() {
		panelIngreso = new JPanel();
		panelIngreso.setLayout(new BorderLayout());
		panelIngreso.add(etiquetaContraseña, BorderLayout.WEST);
		panelIngreso.add(textoContraseña, BorderLayout.CENTER);
		panelIngreso.add(botonSalir, BorderLayout.EAST);

		panelCentral = new JPanel();
		panelCentral.setBorder(BorderFactory.createTitledBorder(null, "Datos Paciente", TitledBorder.CENTER,
				TitledBorder.DEFAULT_POSITION));
		panelCentral.add(etiquetaDNIdatos);
		panelCentral.add(dniDatos);
		panelCentral.add(etiquetaFecha);
		panelCentral.add(fechaNacimiento);
		panelCentral.add(etiquetaOS);
		panelCentral.add(OS);
		panelCentral.add(botonAsignar);

		panelHabitaciones = new JPanel();
		panelHabitaciones.setLayout(new FlowLayout());
		panelHabitaciones.setPreferredSize(new Dimension(600, 130));
		panelHabitaciones.setBorder(BorderFactory.createTitledBorder(null, "Habitaciones", TitledBorder.LEFT,
				TitledBorder.DEFAULT_POSITION));
		panelHabitaciones.add(etiquetaDNIhab);
		panelHabitaciones.add(dniHab);
		panelHabitaciones.add(botonDatos);
		panelHabitaciones.add(botonDesasignar);
		panelHabitaciones.add(botonListar);
		panelHabitaciones.add(botonCantPacientes);
		panelHabitaciones.add(etiquetaHabitacion);
		panelHabitaciones.add(habitacion);

		panelUrgencias = new JPanel();
		panelUrgencias.setLayout(new FlowLayout());
		panelUrgencias.setPreferredSize(new Dimension(600, 100));
		panelUrgencias.setBorder(
				BorderFactory.createTitledBorder(null, "Urgencias", TitledBorder.RIGHT, TitledBorder.DEFAULT_POSITION));
		panelUrgencias.add(etiquetaDNIurg);
		panelUrgencias.add(dniUrg);
		panelUrgencias.add(etiquetaCodigo);
		panelUrgencias.add(codUrgencia);
		panelUrgencias.add(botonIngresar);
		panelUrgencias.add(botonAtender);
		panelUrgencias.add(botonCantUrgencias);

		panelControles = new JPanel();
		panelControles.setLayout(new BorderLayout());
		panelControles.add(panelHabitaciones, BorderLayout.NORTH);
		panelControles.add(panelCentral, BorderLayout.CENTER);
		panelControles.add(panelUrgencias, BorderLayout.SOUTH);
	}

	private class OyenteAcceso implements ActionListener {
		public void actionPerformed(ActionEvent evento) {
			boolean esValida = programa.validarContraseña(evento.getActionCommand());
			if (esValida) {
				textoContraseña.setEnabled(false);
				textoContraseña.setText("");
				botonSalir.setEnabled(true);
				botonAsignar.setEnabled(true);
				botonDesasignar.setEnabled(true);
				botonDatos.setEnabled(true);
				botonListar.setEnabled(true);
				botonCantPacientes.setEnabled(true);
				botonIngresar.setEnabled(true);
				botonAtender.setEnabled(true);
				botonCantUrgencias.setEnabled(true);
			} else {
				JOptionPane aviso = new JOptionPane();
				aviso.showMessageDialog(null, "La contraseña es invalida", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private class OyenteSalir implements ActionListener {
		public void actionPerformed(ActionEvent evento) {
			textoContraseña.setEnabled(true);
			botonSalir.setEnabled(false);
			botonAsignar.setEnabled(false);
			botonDesasignar.setEnabled(false);
			botonDatos.setEnabled(false);
			botonListar.setEnabled(false);
			botonCantPacientes.setEnabled(false);
			botonIngresar.setEnabled(false);
			botonAtender.setEnabled(false);
			botonCantUrgencias.setEnabled(false);
		}
	}

	private class OyenteAsignar implements ActionListener {
	public void actionPerformed(ActionEvent evento) {
		JOptionPane aviso=new JOptionPane();
		try {
			if (dniDatos.getText().isEmpty() || fechaNacimiento.getText().isEmpty() || OS.getText().isEmpty())
				aviso.showMessageDialog(null, "Debe completar todos los datos del paciente para poder asignarle una habitación.", "", JOptionPane.WARNING_MESSAGE);
			else {
				char h = programa.asignarHabitacion(Integer.parseInt(dniDatos.getText()), fechaNacimiento.getText(), OS.getText());
				dniDatos.setText(""); fechaNacimiento.setText(""); OS.setText("");
				aviso.showMessageDialog(null, "Se ha asignado al paciente a la habitación: "+h, " Confirmación", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		catch (PacienteException e) {
			aviso.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	 }
	}

	private class OyenteDesasignar implements ActionListener {
		public void actionPerformed(ActionEvent evento) {
			JOptionPane aviso = new JOptionPane();
			try {
				if (dniHab.getText().equals(""))
					aviso.showMessageDialog(null, "Debe ingresar un DNI valido.", "", JOptionPane.WARNING_MESSAGE);
				else {
					int dni = Integer.parseInt(dniHab.getText());
					dniHab.setText("");
					programa.desasignarHabitacion(dni);
					aviso.showMessageDialog(null, "El paciente de DNI: " + dni + " ha abandonado su habitación.",
							"Confirmación", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (PacienteException e) {
				aviso.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private class OyenteConsultar implements ActionListener {
		public void actionPerformed(ActionEvent evento) {
			JOptionPane mensaje = new JOptionPane();
			try {
				if (!dniHab.getText().isEmpty()) {
					int documento = Integer.parseInt(dniHab.getText());
					Paciente p = programa.consultarDatosPaciente(documento);
					dniHab.setText("");
					mensaje.showMessageDialog(null,
							"DNI: " + p.getDni() + "\n Fecha de nacimiento: " + p.getFechaNacimiento()
									+ "\n Obra social: " + p.getObraSocial() + "\n Habitación: " + p.getHabitacion(),
							"Datos del paciente", JOptionPane.INFORMATION_MESSAGE);
				} else {
					dniHab.setText("");
					mensaje.showMessageDialog(null, "No ingresó un DNI", "Error", JOptionPane.WARNING_MESSAGE);
				}
			} catch (PacienteException e) {
				dniHab.setText("");
				mensaje.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private class OyenteListar implements ActionListener {
		public void actionPerformed(ActionEvent evento) {
			JOptionPane mensaje = new JOptionPane();
			try {
				mensaje.showMessageDialog(null, "Las habitaciones vacías son: " + programa.habitacionesVacias(), "Habitaciones vacías",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (PacienteException e) {
				mensaje.showMessageDialog(null, "No hay habitaciones vacías", "Habitaciones vacías",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	private class OyenteCantidad implements ActionListener {
		public void actionPerformed(ActionEvent evento) {
			JOptionPane mensaje = new JOptionPane();
			if (evento.getActionCommand().equals("cantPacientes")) {
				char hab = String.valueOf(habitacion.getSelectedItem()).charAt(0);
				mensaje.showMessageDialog(null, "La cantidad de pacientes en la habitación " + hab + " es: "
						+ programa.cantPacientesHabitacion(hab), "Habitación", JOptionPane.INFORMATION_MESSAGE);
			}
			if (evento.getActionCommand().equals("cantUrgencias"))
				mensaje.showMessageDialog(null,
						"La cantidad de pacientes en urgencias es: " + programa.cantPacientesUrgencias(), "Urgencias",
						JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private class OyenteUrgencias implements ActionListener {
		public void actionPerformed(ActionEvent evento) {
			JOptionPane mensaje = new JOptionPane();
			try {
				if (evento.getActionCommand().equals("Ingresar")) {
					if (dniUrg.getText().equals("")) {
						dniUrg.setText("");
						mensaje.showMessageDialog(null, "No ingresó un DNI", "Error", JOptionPane.WARNING_MESSAGE);
					} else {
						int dni = Integer.parseInt(dniUrg.getText());
						programa.ingresarPaciente(codUrgencia.getSelectedIndex() + 1, dni);
						dniUrg.setText("");
						mensaje.showMessageDialog(null,
								"El paciente de DNI: " + dni + " ingresó a la lista de urgencias.", "Confirmación",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
				if (evento.getActionCommand().equals("Atender")) {
					int dni = programa.atenderPaciente();
					mensaje.showMessageDialog(null, "El paciente de DNI: " + dni + " ha sido atendido.", "Confirmación",
							JOptionPane.INFORMATION_MESSAGE);
					;
				}
			} catch (PacienteException e) {
				mensaje.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}