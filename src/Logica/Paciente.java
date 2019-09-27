package Logica;

public class Paciente 
{
	protected String FN;
	protected String OS;
	protected char habitacion;
	
	public Paciente(String fecha, String obra, char hab)
	{
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
}
