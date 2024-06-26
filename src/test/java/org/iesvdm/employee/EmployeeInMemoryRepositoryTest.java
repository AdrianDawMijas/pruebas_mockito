package org.iesvdm.employee;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test doubles that are "fakes" must be tested
 *
 *
 */
public class EmployeeInMemoryRepositoryTest {

	private EmployeeInMemoryRepository employeeRepository;

	private List<Employee> employees;

	@BeforeEach
	public void setup() {

		employees = new ArrayList<>();
		employeeRepository = new EmployeeInMemoryRepository(employees);
	}

	/**
	 * Descripcion del test:
	 * crea 2 Employee diferentes
	 * aniadelos a la coleccion de employees
	 * comprueba que cuando llamas a employeeRepository.findAll
	 * obtienes los empleados aniadidos en el paso anterior
	 */
	@Test
	public void testEmployeeRepositoryFindAll() {
		Employee empleado1 = new Employee("A", 1000);
		Employee empleado2 = new Employee("B", 1000);
		employees.add(empleado1);
		employees.add(empleado2);
		List<Employee> listaObtenida = employeeRepository.findAll();
		assertThat(listaObtenida.size()).isEqualTo(2);
		assertThat(listaObtenida.contains(empleado1)).isTrue();
		assertThat(listaObtenida.contains(empleado2)).isTrue();
	}

	/**
	 * Descripcion del test:
	 * salva un Employee mediante el metodo
	 * employeeRepository.save y comprueba que la coleccion
	 * employees contiene solo ese Employee
	 */
	@Test
	public void testEmployeeRepositorySaveNewEmployee() {
		Employee empleado1 = new Employee("A", 1000);
		employeeRepository.save(empleado1);
		assertThat(employees.size()==1).isTrue();
		assertThat(employees.contains(empleado1)).isTrue();

	}

	/**
	 * Descripcion del tets:
	 * crea un par de Employee diferentes
	 * aniadelos a la coleccion de employees.
	 * A continuacion, mediante employeeRepository.save
	 * salva los Employee anteriores (mismo id) con cambios
	 * en el salario y comprueba que la coleccion employees
	 * los contiene actualizados.
	 */
	@Test
	public void testEmployeeRepositorySaveExistingEmployee() {
		Employee empleado1 = new Employee("A", 1000);
		Employee empleado2 = new Employee("B", 1300);
		employees.add(empleado1);
		employees.add(empleado2);
		empleado2.setSalary(1500);
		employeeRepository.save(empleado2);
		int indiceEmpleado2 = employees.indexOf(empleado2);
		assertThat(employees.get(indiceEmpleado2).getSalary()).isEqualTo(1500);
	}
}
