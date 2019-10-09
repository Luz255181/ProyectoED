package Logica;
/**
 * La clase Paciente modela a un paciente hospedado en la sala de urgencias del hospital.
 * Por cada paciente creado se lleva registro de su DNI, obra social, fecha de nacimiento y
 * la habitación del hospital en la que se ocupa dicho paciente.
 * @author Luz Cabral y Gonzalo Perez
 *
 */
public class Paciente {
	
	//Atributos de instancia
	protected int DNI;
	protected String fechaNacimiento;
	protected String OS;
	protected char habitacion;
	
	public Paciente(int dni, String fecha, String obra)
	{
		DNI = dni;
		fechaNacimiento = fecha;
		OS = obra;
		habitacion = '\\';
	}
	
	//Comandos
	public void setHabitacion(char hab) {
		habitacion = hab;
	}
	
	
	//Consultas
	public int getDni() {
		return DNI;
	}
	public String getFechaNacimiento()
	{
		return fechaNacimiento;
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
