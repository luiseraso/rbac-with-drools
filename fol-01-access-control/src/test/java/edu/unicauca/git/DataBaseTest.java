package edu.unicauca.git;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.unicauca.git.model.ehr.Patient;
import edu.unicauca.git.model.ehr.PatientRecord;
import edu.unicauca.git.model.rbcc.Role;
import edu.unicauca.git.model.rbcc.User;

public class DataBaseTest {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static EntityTransaction tx;

	@BeforeClass
	public static void initEntityManager() throws Exception {
		emf = Persistence.createEntityManagerFactory("dbpmPU");
		em = emf.createEntityManager();
	}

	@AfterClass
	public static void closeEntityManager() throws SQLException {
		em.close();
		emf.close();
	}

	@Before
	public void initTransaction() {
		tx = em.getTransaction();
	}
	
	@Test
	public void persistEntities()throws Exception {
		
		//Create users and roles...
		Role doctor = new Role("Doctor");
		Role psychiatry = new Role("Psychiatry");
		Role nurse = new Role("Nurse");
		
		User rodrigo = new User("rjep","123","Rodrigo");
		User maria = new User("maria","123","Maria");
		
		rodrigo.addRole(doctor);
		maria.addRole(nurse);
		
		System.out.println("doctor.users: "+ doctor.getUsers().size());
		System.out.println("user.roles: "+ rodrigo.getRoles().size());
		
		Patient julian = new Patient("Julian Camilo", 'M', new Date() );
		Patient luis = new Patient("Luis Alberto", 'M', new Date() );
		
		PatientRecord issue = luis.addPatientRecord("Dolor de cabeza profundo", rodrigo);
		PatientRecord issue2 = julian.addPatientRecord("Fiebre de 39 grados", rodrigo);
				
		issue2.addPhyisicianToTeam(maria);
		issue.addImage("/img/lab.png", "TAC de la cabeza...", rodrigo);
		//issue2.addMedicalNote.. 
		//issue2.addTestResult..
								
		tx.begin();
		em.persist(rodrigo);
		em.persist(maria);
		em.persist(doctor);
		em.persist(psychiatry);
		em.persist(nurse);
		em.persist(julian);
		em.persist(luis);
		tx.commit();
		
		assertNotNull("ID should not be null", rodrigo.getId());
		assertNotNull("ID should not be null", maria.getId());
		assertNotNull("ID should not be null", doctor.getId());
		assertNotNull("ID should not be null", psychiatry.getId());
		assertNotNull("ID should not be null", nurse.getId());
		assertNotNull("ID should not be null", julian.getId());
		assertNotNull("ID should not be null", luis.getId());
		
	}
}
