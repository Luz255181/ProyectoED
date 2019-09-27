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

	public void IngresarPaciente(int DNI, String FN, String OS) throws InvalidKeyException
	{
		Paciente pas;
		switch(DNI%10)
		{
		case 0: pas = new Paciente(FN,OS,'a');
		        break;
		case 1:pas = new Paciente(FN,OS,'b');
		       break;
		case 2: pas = new Paciente(FN,OS,'c');
		        break;
		case 3: pas = new Paciente(FN,OS,'d');
		        break;
		case 4: pas = new Paciente(FN,OS,'e');
		        break;
		case 5: pas = new Paciente(FN,OS,'f');
		        break;
		case 6: pas = new Paciente(FN,OS,'g');
		        break;
		case 7: pas = new Paciente(FN,OS,'h');
		        break;
		case 8: pas = new Paciente(FN,OS,'i');
		        break;
		case 9: pas = new Paciente(FN,OS,'j');
		        break;
		default: throw new InvalidKeyException("Clave Invalida");
		}
		habitaciones.put(DNI, pas);
	}
}
