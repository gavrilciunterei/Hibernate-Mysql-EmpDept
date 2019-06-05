import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import dao.DeptDao;
import dao.EmpDao;
import pojos.Dept;
import pojos.Emp;

public class PruebaHibernate {
	private static EmpDao ed = new EmpDao();
	private static DeptDao de = new DeptDao();
	
	public static void main(String[] args) {

		listarEmpleados();

		Dept dept1 = new Dept((byte) 30);
		Dept dept2 = new Dept((byte) 10);
		Dept dept3 = new Dept((byte) 20);
		Date date = new Date();
		short empno1 = 998;
		short empno2 = 1000;
		short empno3 = 1001;
		short empnoNoExitente = 5232;
		BigDecimal sal1 = new BigDecimal(30421);
		BigDecimal comm = new BigDecimal(222);
		BigDecimal sal2 = new BigDecimal(20000);
		BigDecimal sal3 = new BigDecimal(52355);
	
		
		//CREAMOS NUEVOS EMPLEADOS
		Emp empleado1 = new Emp(empno1, dept1, null, "Empleado1", "MANAGER", date, sal1, null, null);
		Emp empleado2 = new Emp(empno2, dept2, null, "Empleado2", "SALESMAN", date, sal2, comm, null);
		Emp empleado3 = new Emp(empno3, dept3, null, "Empleado3", "ANALYST", date, sal3, null, null);
		Emp empleadoNoExistente = new Emp(empnoNoExitente, dept1, null, "Empleado1", "MANAGER", date, sal1, null, null);
		
		
		//LOS INSERTAMOS si no existen
		System.out.println("\nAñadimoos varios empleados " + empleado1.getEmpno() + " " + empleado2.getEmpno() + " " + empleado3.getEmpno());
		ed.insertarEmp(empleado1);
		ed.insertarEmp(empleado2);
		ed.insertarEmp(empleado3);
		
		
		//ELIMINAMOS 
		System.out.println("\nEliminamos un empleados exitente: "+ empleado1.getEmpno());
		ed.eliminaEmpleado(empleado1);
		
		System.out.println("\nEliminamos un empleados no exitente: " + empleadoNoExistente.getEmpno());
		ed.eliminaEmpleado(empleadoNoExistente);
		
		//BUSCAMOS UNO POR ID 
		Short idBuscarExitente = 7782;
		
		
		System.out.println("\nBuscamos el Empleado con el EMPNO " + idBuscarExitente+ " : ");
		Emp empleadoBuscado = ed.obtenerEmpleado(idBuscarExitente);
		
		System.out.printf("%-20s%-20s%-20s\n","Empno", "Ename", "Job");
		System.out.printf("%-20s%-20s%-20s\n", empleadoBuscado.getEmpno(), empleadoBuscado.getEname(), empleadoBuscado.getJob());


		Short idBuscarNoExitente = 2222;
		System.out.println("\nBuscamos el Empleado con el EMPNO " + idBuscarNoExitente+ " : ");
		Emp empleadoBuscadoNoExitente = ed.obtenerEmpleado(idBuscarNoExitente);
		if(empleadoBuscadoNoExitente!=null) {
			System.out.printf("%-20s%-20s%-20s\n","Empno", "Ename", "Job");
			System.out.printf("%-20s%-20s%-20s\n", empleadoBuscadoNoExitente.getEmpno(), empleadoBuscadoNoExitente.getEname(), empleadoBuscadoNoExitente.getJob());
		}else {
			System.out.println("No exite: " + idBuscarNoExitente);
		}
		
		//Actualizamos un empleado:
		
		System.out.println("\nActualizamos el contacto con el id 1000 cambiandole nombre y trabajo:");
		empleado2 = new Emp(empno2, dept2, null, "Actualiza", "CLERK", date, sal2, null, null);
		ed.actualizaEmpleado(empleado2);
		
		
		listarEmpleados();
		
	// DEPARTAMENTOS:
		
		System.out.println("\n\nDepartamentos:\n");
		listarDepartamentos();
		
		
	//CREAMOS 2 NUEVOS DEPARTAMENTOS
		Dept departamento1 = new Dept((byte)50, "Departamento1", "Loc1"); 
		Dept departamento2 = new Dept((byte)60, "Departamento2", "Loc2");
		Dept departamentoExitente = new Dept((byte)40, "Departamento2", "Loc2");
		Dept departamentoNoExitente = new Dept((byte)90, "Departamento2", "Loc2");
		

		//LOS INSERTAMOS si no existen
		System.out.println("\nAñadimoos varios empleados");
		de.insertaDept(departamento1);
		de.insertaDept(departamento2);
		de.insertaDept(departamentoExitente);
		
		listarDepartamentos();
		
		
		//ELIMINAMOS 
		System.out.println("\nEliminamos un departamento exitente:");
		de.eliminaDept(departamento1);
		
		System.out.println("\nEliminamos un departamento no exitente:");
		de.eliminaDept(departamentoNoExitente);
		
		
		//BUSCAMOS POR ID:
		
		Dept departamentoObtenido = de.obtenerDept((byte)30);
		if(departamentoObtenido != null) {
			System.out.printf("%-20s%-20s%-20s\n","Deptno", "Dname", "Loc");
			System.out.printf("%-20s%-20s%-20s\n","__________", "__________", "__________");
			System.out.printf("%-20s%-20s%-20s\n", departamentoObtenido.getDeptno(), departamentoObtenido.getDname(), departamentoObtenido.getLoc());
		}else {
			System.out.printf("No encontrado");
		}
		
		System.out.printf("\nActualiamos un contacto:");
		de.actualizaDept(new Dept((byte)10, "ACCOUNTING", "MADRID"));
		listarDepartamentos();
		
		
		//no dejará porque comprueba si el departamento tiene empleados relacionados
		System.out.println("\nEliminamos un departamento con empleados:");
		de.eliminaDept(new Dept((byte)10, "ACCOUNTING", "MADRID"));
		
	}
	
	public static void listarEmpleados() {
		List<Emp> empleados;
		System.out.println("\nListamis todos los empleados");
		empleados= ed.obtenListaEmpleados();
		System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n","Empno", "Ename", "Job", "Mgr", "Hiredate", "Sal", "Comm");
		System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n","__________", "__________", "__________", "__________", "__________", "__________", "__________");
		for(Emp e : empleados) {
			System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", e.getEmpno(), e.getEname(), e.getJob(),(e.getEmp()!=null)?e.getEmp().getEname():"No tiene", e.getHiredate(), e.getSal(),e.getComm());
		}
		
	}
	
	//byte deptno, String dname, String loc, Set<Emp> emps) 
	
	public static void listarDepartamentos() {
		List<Dept> departamento;
		System.out.println("\nListamis todos los Departamentos");
		departamento= de.obtenListaDept();
		System.out.printf("%-20s%-20s%-20s\n","Deptno", "Dname", "Loc");
		System.out.printf("%-20s%-20s%-20s\n","__________", "__________", "__________");
		for(Dept e : departamento) {
			System.out.printf("%-20s%-20s%-20s\n", e.getDeptno(), e.getDname(), e.getLoc());
		}
		
	}



}
