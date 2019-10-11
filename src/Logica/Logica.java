package Logica;
import Auxiliar.*;
import Cola.*;
import Lista.*;
import Mapeo.*;
import Pila.*;

public class Logica 
{
	protected MapeoHashAbierto<Integer,Paciente> habitaciones;
	
	public Logica()
	{
		habitaciones  = new MapeoHashAbierto<Integer,Paciente>();
	}

	/**
	 * Ingresa un paciente y le asigna una habitacion.
	 * @param pas Paciente a ingresar.
	 * @return Habitacion que se le asigno al paciente.
	 * @throws PacienteException Si los datos del paciente no son validos.
	 */
	public Character IngresarPaciente(Paciente pas) throws PacienteException
	{
		try {
			if(habitaciones.get(pas.getDni()) != null)
			{
				
			}
			switch(pas.getDni()%10)
			{
			case 0: pas.setHabitacion('a');
			break;
			case 1: pas.setHabitacion('b');
			break;
			case 2: pas.setHabitacion('c');
			break;
			case 3: pas.setHabitacion('d');
			break;
			case 4: pas.setHabitacion('e');
			break;
			case 5: pas.setHabitacion('f');
			break;
			case 6: pas.setHabitacion('g');
			break;
			case 7: pas.setHabitacion('h');
			break;
			case 8: pas.setHabitacion('i');
			break;
			case 9: pas.setHabitacion('j');
			break;
			}
			habitaciones.put(pas.getDni(), pas);
		}
		catch (InvalidKeyException e) {
			throw new PacienteException("Los datos del paciente son Invalidos");
		}
		return pas.getHabitacion();
	}

	/**
	 * Le desasigna la habitacion al paciente dado de alta.
	 * @param dni Es el DNI del paciente dado de alta.
	 * @return Paciente que se le dio de alta
	 * @throws PacienteException Si el paciente no se encuentra registrado en el hospital o si el DNI no es valido.
	 */
	public Paciente desasignarHabitacion(int dni) throws PacienteException
	{
		try {
			Paciente pas = habitaciones.remove(dni);
			if(pas.equals(null))
				throw new PacienteException("El DNI ingresado no corresponde con ningun paciente en el hospital");
			return pas;
		}
		catch(InvalidKeyException e) {
			throw new PacienteException("Documento Invalido");
		}
	}
}