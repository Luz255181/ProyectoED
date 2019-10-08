package Logica;
import Exception.FechaException;

public class Paciente 
{
	protected String FN;
	protected String OS;
	protected char habitacion;
	
	public Paciente(String fecha, String obra, char hab) throws FechaException
	{
		FechaValida(fecha);
		FN = fecha;
		OS = obra;
		habitacion = hab;
	}
	
	public String getFechaNacimiento()
	{
		return FN;
	}
	
	public String getObraSocial()
	{
		return OS;
	}
	
	public char getHabitacion()
	{
		return habitacion;
	}
	
	private void FechaValida (String fecha) throws FechaException
	{
		int dia;
		int mes;
		int anio;
	}
}
