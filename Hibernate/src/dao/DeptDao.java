package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import Util.HibernateUtil;
import pojos.Dept;


public class DeptDao {
	
	public DeptDao() {
		
	}
	
	public void insertaDept(Dept departamento) throws HibernateException { 
		
		Transaction trs  = null;
		if(obtenerDept(departamento.getDeptno()) == null) {
	        try (Session sesion = HibernateUtil.getSessionFactory().openSession()){ 
	        	trs = sesion.beginTransaction(); 
	            sesion.save(departamento); 
	            trs.commit();
	            sesion.close(); 
	    		System.out.println("El departamento ha sido añadido " + departamento.getDeptno());
	        } catch (HibernateException he) { 
	        	if (trs != null) {
	        		trs.rollback();
	            }
	           	System.out.println(he.getMessage());
	        } 
		}else {
			System.out.println("El departamento ya existe " + departamento.getDeptno());
		}

    }
	
	public void actualizaDept(Dept departamento) throws HibernateException { 
		
	    
		Transaction trs  = null;
	    
		if(obtenerDept(departamento.getDeptno()) != null) {
	        try (Session sesion = HibernateUtil.getSessionFactory().openSession())
	        { 
	        	trs = sesion.beginTransaction(); 
	            sesion.update(departamento); 
	            trs.commit(); 
	            System.out.println("Departamento actualizado " + departamento.getDeptno());
	        } catch (HibernateException he) { 
	        	if (trs != null) {
	        		trs.rollback();
	            }
	           	System.out.println(he.getMessage());
	        } 
		}else {
			System.out.println("Departamento no encontrado " + departamento.getDeptno());
		}
    }
	
		
	public void eliminaDept(Dept departamento) throws HibernateException { 
		
		
		Transaction trs  = null;
		if(obtenerDept(departamento.getDeptno()) != null && departamento.getEmps() == null) {
		    try (Session sesion = HibernateUtil.getSessionFactory().openSession())
	        { 
		    	
	        	trs = sesion.beginTransaction(); 
	            sesion.delete(departamento); 
	            trs.commit(); 
	            System.out.println("Departamento eliminado " + departamento.getDeptno());
	        } catch (HibernateException he) { 
	        	if (trs != null) {
	        		trs.rollback();
	            }
	           	System.out.println(he.getMessage());
	        } 
		}else {
			System.out.println("Departamento no eliminado " + departamento.getDeptno());
		}
    }
	
	public Dept obtenerDept(Byte idDept) throws HibernateException { 
		
	    Dept dept = null;
	    
        try (Session sesion = HibernateUtil.getSessionFactory().openSession())
        { 
        	dept = (Dept)sesion.get(Dept.class, idDept);           
        } catch (HibernateException he) { 
           	System.out.println(he.getMessage());
           	
        } 
        return dept;
    }
	
	
	public List<Dept> obtenListaDept() throws HibernateException { 
		
        List<Dept> listaDept = null;  
        
        try (Session sesion = HibernateUtil.getSessionFactory().openSession())
        { 
        	listaDept = sesion.createQuery("from Dept").list(); 
        } catch (HibernateException he) { 
           	System.out.println(he.getMessage());
        } 
    
		return listaDept;
	}
	
	
	
	
	

}
