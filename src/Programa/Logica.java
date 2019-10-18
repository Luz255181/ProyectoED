package Programa;
import java.util.Iterator;
import Auxiliar.*;
import TDACola.*;
import TDAColaConPrioridad.*;
import TDALista.*;
import TDAMapeo.*;
import TDAPila.*;

public class Logica {
	protected MapeoHashAbierto<Integer,Paciente> pacientes;
	protected PriorityQueue<Integer, Integer> urgencias;
	//Constructor
	public Logica() {
		pacientes  = new MapeoHashAbierto<Integer,Paciente>();
	}
	//Consultas
	/**
	 * Ingresa un paciente al sistema y le asigna una habitacion.
	 * @param p Paciente a ingresar.
	 * @return Habitacion que se le asigno al paciente.
	 * @throws PacienteException Si los datos del paciente no son validos.
	 */
	public char IngresarPaciente(Paciente p) throws PacienteException {
		try {
			if(pacientes.get(p.getDni()) != null) {
				throw new PacienteException("Ya se encuentra hospedado un paciente con el DNI ingresado.");
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
			pacientes.put(p.getDni(), p);
		}
		catch (InvalidKeyException e) {
			throw new PacienteException("Los datos del paciente ingresado no son validos.");
		}
		return p.getHabitacion();
	}

	/**
	 * Desasigna la habitacion asignada al paciente dado de alta y lo retorna.
	 * @param dni Es el DNI del paciente dado de alta.
	 * @return Paciente que se le dio de alta.
	 * @throws PacienteException Si el paciente no se encuentra registrado en el hospital o si el DNI no es valido.
	 */
	public Paciente desasignarHabitacion(int dni) throws PacienteException {
		try {
			Paciente p = pacientes.remove(dni);
			if(p.equals(null))
				throw new PacienteException("El DNI ingresado no corresponde con ningun paciente registrado.");
			return p;
		}
		catch(InvalidKeyException e) {
			throw new PacienteException("El documento ingresado no es valido");
		}
	}
	/**
	 * Consulta los datos del paciente correspondiente al DNI ingresado por parámetro y lo retorna.
	 * @param dni Es el DNI del paciente a buscar
	 * @return Retorna al paciente correspondiente al DNI ingresado.
	 * @throws PacienteException Si no existe un paciente con el DNI ingresado o no se encontró en el sistema.
	 */
	public Paciente consultarDatosPaciente(int dni) throws PacienteException {
		try {
			Paciente p = pacientes.get(dni);
			if (p == null)
				throw new PacienteException("No existe un paciente con el DNI ingresado.");
			return p;
		}
		catch (InvalidKeyException e) {
			throw new PacienteException("No se encontró un paciente con el DNI ingresado.");
		}
	}
	/**
	 * Consulta cuales son las habitaciones que se encuentran totalmente vacías.
	 * @return Retorna una lista que contiene las letras de las habitaciones que están vacias.
	 */
	public PositionList<Character> habitacionesVacias() {
		PositionList<Character> vacias= new ListaDobleEnlace<Character>();
		boolean estaVacia;
		for (char hab = 'a'; hab <= 'j'; hab++) {
			estaVacia = true;
			Iterator<Paciente> itPacientes = pacientes.values().iterator();
			while (estaVacia && itPacientes.hasNext())
				if (itPacientes.next().getHabitacion() == hab)
					estaVacia = false;
			if (estaVacia)
				vacias.addLast(hab);
		}
		return vacias;
	}
	/**
	 * Consulta la cantidad de pacientes que se encuentran en la habitación pasada por parámetro.
	 * @param hab Es la letra de la habitación a consultar.
	 * @return Retorna la cantidad de pacientes en la habitación solicitada.
	 */
	public int cantPacientesHabitacion(char hab) {
		int cant=0;
		for (Paciente p:pacientes.values())
			if (p.getHabitacion()==hab)
				cant++;
		return cant;
	}
	/**
	 * Consulta la cantidad de pacientes que esperan ser atendidos en Urgencias
	 * @return Retorna la cantidad de pacientes en Urgencias
	 */
	public int cantPacientesUrgencias() {
		return urgencias.size();
	}

	/**
	 * Ingresa un paciente a urgencias segun su prioridad
	 * @param prioridad Prioridad del paciente.
	 * @param DNI DNI del paciente.
	 * @throws PacienteException Si la prioridad pasada no se encuentra en el rango aceptado
	 */
	public void IngresarPaciente(int prioridad, int DNI) throws PacienteException
	{
		try
		{
			if(prioridad == 1 || prioridad == 2 || prioridad == 3 || prioridad == 4|| prioridad == 5)
			urgencias.insert(prioridad, DNI);
			else
				throw new PacienteException("La prioridad no se encuentra dentro del rango aceptado");
		}
		catch(InvalidKeyException e)
		{
			throw new PacienteException("La prioridad no es valida");
		}
	}

	/**
	 * Atiende al Paciente con mayor prioridad.
	 * @return DNI del paciente.
	 * @throws PacienteException Si no hay pacientes en Urgencias.
	 */
	public int AtenderPaciente() throws PacienteException
	{
		try
		{
			int DNI = urgencias.removeMin().getValue();
			return DNI;
		}
		catch(EmptyPriorityQueueException e)
		{
			throw new PacienteException("No hay pacientes en urgencias");
		}
	}
	/**
	 * Verifica si la contraseña pasada por parámetro respeta el formato especificado.
	 * De ser así, se brinda acceso al sistema, en caso contrario la aplicación permanece bloqueada. 
	 * @param contraseña Es el código de acceso ingresado.
	 * @return Retorna true si el código de acceso es valido o false en caso contrario.
	 */
	public boolean validarContraseña(String contraseña) {
		try {
			boolean esValida = contraseña.length()>1;
			int indice = 0;
			char leido = '@';
			boolean finalizoApellido = false;
			Stack<Character> pilaAux = new PilaEnlazada<Character>();
			Queue<Character> colaAux = new ColaCircular<Character>();
			while (esValida && indice<contraseña.length()) {
				while (!finalizoApellido && indice<contraseña.length()) {
					leido = contraseña.charAt(indice);
					indice++;
					if (leido == 'x')
						finalizoApellido = true;
					else pilaAux.push(leido);
				}
				if (!finalizoApellido)
					esValida = false;
				while (esValida && indice<contraseña.length() && !pilaAux.isEmpty()) {
					leido = contraseña.charAt(indice);
					indice++;
					if (leido == pilaAux.top())
						colaAux.enqueue(pilaAux.pop());
					else esValida = false;
				}
				if (!(indice<contraseña.length()) || !pilaAux.isEmpty())
					esValida = false;
				while (esValida && indice<contraseña.length() && !colaAux.isEmpty()) {
					leido = contraseña.charAt(indice);
					indice++;
					if (leido == colaAux.front())
						colaAux.dequeue();
					else esValida = false;
				}
				if (!colaAux.isEmpty())
					esValida = false;
			}
			if (indice!=contraseña.length() || !pilaAux.isEmpty() || !colaAux.isEmpty())
				esValida = false;
			return esValida;
		}
		catch (EmptyStackException | EmptyQueueException e) {
			e.printStackTrace();
		}
		return false;
	}
}