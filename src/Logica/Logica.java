package Logica;
import Auxiliar.*;
import Cola.*;
import Lista.*;
import Mapeo.*;
import Pila.*;

public class Logica {
	protected MapeoHashAbierto<Integer,Paciente> habitaciones;
	
	public Logica() {
		habitaciones  = new MapeoHashAbierto<Integer,Paciente>();
	}

	/**
	 * Ingresa un paciente y le asigna una habitacion.
	 * @param pas Paciente a ingresar.
	 * @return Habitacion que se le asigno al paciente.
	 * @throws PacienteException Si los datos del paciente no son validos.
	 */
	public char IngresarPaciente(Paciente p) throws PacienteException {
		try {
			if(habitaciones.get(p.getDni()) != null) {
				throw new PacienteException("Ya se encuentra hospedado un paciente con el DNI ingresado");
			}
			switch(p.getDni()%10) {
				case 0: p.setHabitacion('a');
			break;
				case 1: p.setHabitacion('b');
			break;
				case 2: p.setHabitacion('c');
			break;
				case 3: p.setHabitacion('d');
			break;
				case 4: p.setHabitacion('e');
			break;
				case 5: p.setHabitacion('f');
			break;
				case 6: p.setHabitacion('g');
			break;
				case 7: p.setHabitacion('h');
			break;
				case 8: p.setHabitacion('i');
			break;
				case 9: p.setHabitacion('j');
			break;
			}
			habitaciones.put(p.getDni(), p);
		}
		catch (InvalidKeyException e) {
			throw new PacienteException("Los datos del paciente ingresado son Invalidos");
		}
		return p.getHabitacion();
	}

	/**
	 * Le desasigna la habitacion al paciente dado de alta.
	 * @param dni Es el DNI del paciente dado de alta.
	 * @return Paciente que se le dio de alta
	 * @throws PacienteException Si el paciente no se encuentra registrado en el hospital o si el DNI no es valido.
	 */
	public Paciente desasignarHabitacion(int dni) throws PacienteException {
		try {
			Paciente p = habitaciones.remove(dni);
			if(p.equals(null))
				throw new PacienteException("El DNI ingresado no corresponde con ningun paciente en el hospital");
			return p;
		}
		catch(InvalidKeyException e) {
			throw new PacienteException("El documento ingresado es invalido");
		}
	}
}