package Logica;
/**
 * La clase Paciente modela a un paciente hospedado en la sala de urgencias del hospital.
 * Por cada paciente creado se lleva registro de su DNI, obra social, fecha de nacimiento y
 * la habitación del hospital en la que se hospeda dicho paciente.
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
		habitacion = '@';
	}
	
	//Comandos
	/**
	 * Setea la obra social del paciente con la obra social pasado por parámetro.
	 * @param obra Es la nueva obra social del paciente.
	 */
	public void setOS(String obra) {
		OS = obra;
	}
	/**
	 * Setea la habitacion en la que se quedara el Paciente.
	 * @param hab Es la habitacion del paciente.
	 */
	public void setHabitacion(char hab) {
		habitacion = hab;
	}
	
	//Consultas
	/**
	 * Consulta el DNI del paciente y lo retorna.
	 * @return DNI del Paciente.
	 */
	public int getDni() {
		return DNI;
	}
	/**
	 * Consulta la fecha de nacimiento del paciente y la retorna.
	 * @return Fecha de nacimiento del paciente
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * Consulta la obra social del paciente y la retorna.
	 * @return Obra social del paciente.
	 */
	public String getObraSocial() {
		return OS;
	}
	/**
	 * Consulta la habitación del paciente y la retorna.
	 * @return Habitacion del paciente
	 */
	public char getHabitacion() {
		return habitacion;
	}
}