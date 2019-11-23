package Programa;
import java.util.Iterator;
import Auxiliar.*;
import TDACola.*;
import TDAColaCP.*;
import TDALista.*;
import TDAMapeo.*;
import TDAPila.*;

/**
 * Clase que modela las funciones que brinda el sistema.
 * @author Luz  Cabral & Gonzalo  Perez
 *
 */
public class Logica {
	protected MapeoConHashAbierto<Integer,Paciente> pacientes;
	protected PriorityQueue<Integer, Integer> urgencias;
	
	//Constructor
	/**
	 * Inicializa el sistema de registro de habitaciones y el sistema de urgencias ambos vacíos.
	 */
	public Logica() {
		pacientes = new MapeoConHashAbierto<Integer,Paciente>();
		urgencias = new Heap<Integer, Integer>(new Comparador<Integer>());
	}
	//Comandos
	/**
	 * Ingresa un paciente a urgencias ordenandolo según su prioridad.
	 * @param prioridad Es la prioridad del paciente a ingresar.
	 * @param DNI Es el DNI del paciente a ingresar.
	 * @throws PacienteException Si la prioridad pasada por parámetro no se encuentra dentro del rango aceptado
	 */
	public void ingresarPaciente(int prioridad, int DNI) throws PacienteException {
		try {
			if (prioridad<1 || 5<prioridad)
				throw new PacienteException("La prioridad no se encuentra dentro del rango aceptado");
			else urgencias.insert(prioridad, DNI);
		}
		catch(InvalidKeyException e) {
			throw new PacienteException("La prioridad no es valida");
		}
	}
	//Consultas
	/**
	 * Ingresa un paciente al sistema y le asigna una habitación.
	 * @param DNI Es el dni del paciente a ingresar al sistema.
	 * @param fecha Es la fecha de nacimiento del paciente a ingresar al sistema.
	 * @param obra Es la obra social del paciente a ingresar al sistema.
	 * @param hab Es la habitación a asignarle al paciente.
	 * @throws PacienteException Si el paciente ya se encuentra en el sistema o 
	 * 							 los datos ingresado son invalidos.
	 */
	public void asignarHabitacion(int DNI, String fecha, String obra, char hab) throws PacienteException {
		try {
			if(pacientes.get(DNI) != null) {
				throw new PacienteException("Ya se encuentra hospedado un paciente con el DNI ingresado.");
			}
			Paciente p = new Paciente(DNI,fecha, obra,hab);
			pacientes.put(DNI, p);
		}
		catch (InvalidKeyException e) {
			throw new PacienteException("Los datos del paciente ingresado no son validos.");
		}
	}
	/**
	 * Desasigna la habitacion asignada al paciente dado de alta y lo retorna.
	 * @param dni Es el DNI del paciente dado de alta.
	 * @return Retorna el paciente al que se le dio de alta.
	 * @throws PacienteException Si el paciente no se encuentra registrado en el hospital o si el DNI no es valido.
	 */
	public Paciente desasignarHabitacion(int dni) throws PacienteException {
		try {
			Paciente p = pacientes.remove(dni);
			if(p == null)
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
			Paciente p=pacientes.get(dni);
			if (p==null)
				throw new PacienteException("No se encontró un paciente con el DNI ingresado.");
			return p;
		}
		catch (InvalidKeyException e) {
			throw new PacienteException("No existe un paciente con el DNI ingresado.");
		}
	}
	/**
	 * Consulta cuales son las habitaciones que se encuentran totalmente vacías.
	 * @return Retorna una lista que contiene las letras de las habitaciones que están vacias.
	 */
	public PositionList<Character> habitacionesVacias() {
		PositionList<Character> vacias= new ListaDoblementeEnlazada<Character>();
		boolean estaVacia;
		for (char hab='a'; hab<='j'; hab++) {
			estaVacia=true;
			Iterator<Paciente> itPacientes=pacientes.values().iterator();
			while (estaVacia && itPacientes.hasNext())
				if (itPacientes.next().getHabitacion()==hab)
					estaVacia=false;
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
	 * Atiende al Paciente prioritario del sistema de urgencias.
	 * @return Retorna el DNI del paciente atendido.
	 * @throws PacienteException Si no hay pacientes en el sistema de urgencias.
	 */
	public int atenderPaciente() throws PacienteException {
		try {
			int DNI = urgencias.removeMin().getValue();
			return DNI;
		}
		catch(EmptyPriorityQueueException e) {
			throw new PacienteException("No hay pacientes en urgencias");
		}
	}
	/**
	 * Consulta la cantidad de pacientes que esperan ser atendidos en Urgencias.
	 * @return Retorna la cantidad de pacientes en urgencias.
	 */
	public int cantPacientesUrgencias() {
		return urgencias.size();
	}
	/**
	 * Verifica si la contraseña pasada por parámetro respeta el formato especificado.
	 * De ser así, se brinda acceso al sistema, en caso contrario la aplicación permanece bloqueada. 
	 * @param contraseña Es el código de acceso ingresado.
	 * @return Retorna verdadero si el código de acceso es valido, falso en caso contrario.
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