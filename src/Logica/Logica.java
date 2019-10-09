package Logica;
import Exception.*;
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

	public Character IngresarPaciente(Paciente pas) throws InvalidKeyException
	{
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
		default: throw new InvalidKeyException("Clave Invalida");
		}
		habitaciones.put(pas.getDni(), pas);
		return pas.getHabitacion();
	}
	
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
}
