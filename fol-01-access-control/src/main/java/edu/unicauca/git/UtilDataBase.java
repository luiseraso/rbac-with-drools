package edu.unicauca.git;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.unicauca.git.model.ehr.Patient;
import edu.unicauca.git.model.ehr.PatientRecord;
import edu.unicauca.git.model.rbcc.Role;
import edu.unicauca.git.model.rbcc.User;

public class UtilDataBase {

	public static void dropCreateAndPopulateTables() {
		
		//Create EntityManager...
		Map<String, String> persistenceProperties = new HashMap<String, String>();
		persistenceProperties.put("eclipselink.ddl-generation", "drop-and-create-tables");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbpmPU",persistenceProperties);
		EntityManager em = emf.createEntityManager();		
		EntityTransaction tx = em.getTransaction();
				
		//Create some roles, users and patients...
		Role doctor = new Role("Doctor");		
		Role nurse = new Role("Nurse");
		Role laboratorian = new Role("Laboratorian");
		
		User rodrigo = new User("rodrigo","123","Rodrigo");
		User maria = new User("maria","123","Maria");
		User felipe = new User("felipe","123","Felipe");
		
		rodrigo.addRole(doctor);
		maria.addRole(nurse);
		felipe.addRole(laboratorian);
		
		Patient julian = new Patient("Julian Camilo", 'M', new Date() );
		Patient luis = new Patient("Luis Alberto", 'M', new Date() );
		
		PatientRecord issue1 = luis.addPatientRecord("Dolor de cabeza profundo", rodrigo);
		PatientRecord issue2 = julian.addPatientRecord("Fiebre de 39 grados", rodrigo);

		issue1.addPhyisicianToTeam(maria);		
		issue1.addPhyisicianToTeam(felipe);
		issue2.addPhyisicianToTeam(maria);		
		
		issue1.addMedicalNote("Nota medica del Dr. donde resume los sintomas presentados...", rodrigo);
		issue1.addImage("/img/tac.png", "TAC de la cabeza...", felipe);
						
		tx.begin();
		em.persist(rodrigo);
		em.persist(maria);
		em.persist(doctor);
		em.persist(laboratorian);
		em.persist(nurse);
		em.persist(luis);
		em.persist(julian);		
		tx.commit();
		em.close();
		emf.close();
		
		System.out.println("Database populated...");
	
	}

}
