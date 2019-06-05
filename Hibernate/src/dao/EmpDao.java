package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;


import Util.HibernateUtil;
import pojos.Emp;

public class EmpDao {
	
	
	public EmpDao() {
		
	}

	
	public void insertarEmp(Emp empleado) throws HibernateException { 
		
		Transaction trs  = null;
		if(obtenerEmpleado(empleado.getEmpno()) == null) {
	        try (Session sesion = HibernateUtil.getSessionFactory().openSession()){ 
	        	trs = sesion.beginTransaction(); 
	            sesion.save(empleado); 
	            trs.commit();
	            sesion.close(); 
	    		System.out.println("El empleado ha sido añadido "+ empleado.getEmpno());
	        } catch (HibernateException he) { 
	        	if (trs != null) {
	        		trs.rollback();
	            }
	           	System.out.println(he.getMessage());
	        } 
		}else {
			System.out.println("El empleado ya existe " + empleado.getEmpno());
		}

    }
	
	public void actualizaEmpleado(Emp empleado) throws HibernateException { 
		
	    
		Transaction trs  = null;
	    
		if(obtenerEmpleado(empleado.getEmpno()) != null) {
	        try (Session sesion = HibernateUtil.getSessionFactory().openSession())
	        { 
	        	trs = sesion.beginTransaction(); 
	            sesion.update(empleado); 
	            trs.commit(); 
	            System.out.println("Empleado actualizado " + empleado.getEmpno());
	        } catch (HibernateException he) { 
	        	if (trs != null) {
	        		trs.rollback();
	            }
	           	System.out.println(he.getMessage());
	        } 
		}else {
			System.out.println("Empleado no encontrado "+ empleado.getEmpno());
		}
    }
	
		
	public void eliminaEmpleado(Emp empleado) throws HibernateException { 
		
		
		Transaction trs  = null;
		if(obtenerEmpleado(empleado.getEmpno()) != null) {
		    try (Session sesion = HibernateUtil.getSessionFactory().openSession())
	        { 
		    	
	        	trs = sesion.beginTransaction(); 
	            sesion.delete(empleado); 
	            trs.commit(); 
	            System.out.println("Empleado eliminado "+ empleado.getEmpno());
	        } catch (HibernateException he) { 
	        	if (trs != null) {
	        		trs.rollback();
	            }
	           	System.out.println(he.getMessage());
	        } 
		}else {
			System.out.println("Empleado no encontrdo "+ empleado.getEmpno());
		}
    }
	
	public Emp obtenerEmpleado(Short idEmpleado) throws HibernateException { 
		
	    Emp emp = null;
	    
        try (Session sesion = HibernateUtil.getSessionFactory().openSession())
        { 
        	 emp = (Emp)sesion.get(Emp.class, idEmpleado);           
        } catch (HibernateException he) { 
           	System.out.println(he.getMessage());
           	
        } 
        return emp;
    }
	
	
	public List<Emp> obtenListaEmpleados() throws HibernateException { 
		
        List<Emp> listaEmpleados = null;  
        
        try (Session sesion = HibernateUtil.getSessionFactory().openSession())
        { 
            listaEmpleados = sesion.createQuery("from Emp").list(); 
        } catch (HibernateException he) { 
           	System.out.println(he.getMessage());
        } 
    
		return listaEmpleados;
	}
	

}
