package Logica;
/**
 * La clase Paciente modela a un paciente hospedado en la sala de urgencias del hospital.
 * Por cada paciente creado se lleva registro de su DNI, obra social, fecha de nacimiento y
 * la habitación del hospital en la que se ocupa dicho paciente.
 * @author Maria Luz Cabral y Gonzalo Perez
 *
 */
public class Paciente {
	
	//Atributos de instancia
	protected int DNI;
	protected String fechaNacimiento;
	protected String OS;
	protected char habitacion;
	
	/**
	 * Crea un paciente con DNI, Obra Social y Fecha de Nacimiento.
	 * @param dni DNI del Paciente.
	 * @param fecha Fecha de nacimiento del Paciente.
	 * @param obra Obra Social de Paciente.
	 */
	public Paciente(int dni, String fecha, String obra)
	{
		DNI = dni;
		fechaNacimiento = fecha;
		OS = obra;
		habitacion = '\\';
	}
	
	//Comandos
	
	/**
	 * Setea la habitacion en la que se quedara el Paciente.
	 * @param hab Habitacion del Paciente.
	 */
	public void setHabitacion(char hab) 
	{
		habitacion = hab;
	}
	
	
	//Consultas
	
	/**
	 * Retorna el DNI del Paciente.
	 * @return DNI del Paciente.
	 */
	public int getDni() 
	{
		return DNI;
	}
	
	/**
	 * Retorna la Fecha de nacimiento del Paciente.
	 * @return Fecha de Nacimiento
	 */
	public String getFechaNacimiento()
	{
		return fechaNacimiento;
	}
	
	/**
	 * Retorna la Obra Social del Paciente.
	 * @return Obra Social
	 */
	public String getObraSocial()
	{
		return OS;
	}
	
	/**
	 * Retorna la Habitacion del Paciente.
	 * @return Habitacion del Paciente
	 */
	public char getHabitacion()
	{
		return habitacion;
	}
}
