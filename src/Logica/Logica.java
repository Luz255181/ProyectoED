package Logica;
import Exception.*;
import Cola.*;
import Lista.*;
import Mapeo.*;
import Pila.*;

public class Logica 
{
	protected MapeoHashAbierto<Integer,Paciente> habitaciones;
	protected Integer[] PasHabitaciones;
	
	public Logica()
	{
		habitaciones  = new MapeoHashAbierto<Integer,Paciente>();
		PasHabitaciones = new Integer[10];
		for(int i = 0; i<10; i++)
		{
			PasHabitaciones[i] = 0;
		}
	}

	/**
	 * Ingresa un paciente y le asigna una habitacion.
	 * @param pas Paciente a ingresar.
	 * @return Habitacion que se le asigno al paciente.
	 * @throws PacienteException Si los datos del paciente no son validos.
	 */
	public Character IngresarPaciente(Paciente pas) throws PacienteException
	{
		try
		{
			checkPaciente(pas);
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
		catch (InvalidKeyException e)
		{
			throw new PacienteException("Los datos del paciente son Invalidos");
		}
		return pas.getHabitacion();
	}

	/**
	 * Le desasigna la habitacion al paciente dado de alta.
	 * @param DNI DNI del paciente.
	 * @return Paciente que se le dio de alta
	 * @throws PacienteException Si el paciente no se encuentra registrado en el hospital o si el DNI no es valido.
	 */
	public Paciente DesadignarHabitacion(int DNI) throws PacienteException
	{
		try
		{
			Paciente pas = habitaciones.remove(DNI);
			if(pas.equals(null))
				throw new PacienteException("El DNI ingresado no corresponde con ningun paciente en el hospital");
			return pas;
		}
		catch(InvalidKeyException e)
		{
			throw new PacienteException("Documento Invalido");
		}
	}
	
	/**
	 * Chequea si los datos del paciente son validos.
	 * @param pas Paciente que se va a chequear.
	 * @throws PacienteException Si los datos del paciente son Invalidos.
	 */
	private void checkPaciente(Paciente pas) throws PacienteException
	{
		if(pas.getFechaNacimiento().contentEquals("") || pas.getObraSocial().equals(""))
			throw new PacienteException("Los datos del PAciente son Invalidos");
	}
}
