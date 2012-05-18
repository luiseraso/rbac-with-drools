package edu.unicauca.git;

import java.util.Arrays;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

import edu.unicauca.git.context.ActionPermission;
//import edu.unicauca.git.context.ApplicationContext;
import edu.unicauca.git.context.Appointment;
import edu.unicauca.git.context.Emergency;
import edu.unicauca.git.context.SystemContext;
import edu.unicauca.git.model.ehr.Patient;
import edu.unicauca.git.model.ehr.PatientRecord;
import edu.unicauca.git.model.rbcc.User;

public class Main {

	public static void main(String[] args) {
		
		try {
			
			//UtilDataBase.dropCreateAndPopulateTables();
			//Get ksession..
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbpmPU");
			EntityManager em = emf.createEntityManager();
			
			KnowledgeBase kbase = readKnowledgeBase();
			StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
												
			//Create an appointment...
			User user1 = em.find(User.class, 1);
			User user2 = em.find(User.class, 2);
			User user3 = em.find(User.class, 3);
			
			System.out.println(user3);
			//Patient patient1 = em.find(Patient.class, 1);
			Patient patient2 = em.find(Patient.class, 2);
			
			PatientRecord issue1 = patient2.getPatientRecord().get(0);
			
			Calendar initDate = Calendar.getInstance();
			Calendar endDate = Calendar.getInstance();
			Appointment appointment = new Appointment(user2, patient2, initDate, endDate);
			
			//Examples to addPatientRecord to a Patient...
			System.out.println("Patient2 has an appointment with user2 who is a doctor...");
			SystemContext sysCtx1 = new SystemContext("192.168.10.15", "office", "workstation");
			ActionPermission action1 = new ActionPermission("addPatientRecord", user2, patient2);			
			Object[] facts1 = {action1, sysCtx1, appointment};			
			ksession.execute(Arrays.asList(facts1));			
			assert(action1.isResponse() == true );
			
			System.out.println("\nPatient2 has not an appointment with user1, also user1 isn't a Doctor nor in emeregency-room");
			SystemContext sysCtx2= new SystemContext("192.168.10.16", "office", "workstation");
			ActionPermission action2 = new ActionPermission("addPatientRecord", user1, patient2);			
			Object[] facts2 = {action2, sysCtx2};			
			ksession.execute(Arrays.asList(facts2));			
			assert(action2.isResponse() == false );
			
			//Create an emergency
			Emergency emergency = new Emergency("Traficc accident");
			
			System.out.println("\nThere is an emergency but user1 isn't in emergency-room");			
			SystemContext sysCtx3= new SystemContext("192.168.10.16", "office", "workstation");
			ActionPermission action3 = new ActionPermission("addPatientRecord", user1, patient2);
			Object[] facts3 = {action3, sysCtx3, emergency};			
			ksession.execute(Arrays.asList(facts3));			
			assert(action3.isResponse() == false );
			
			System.out.println("\nThere is an emergency and user1 is in emergency-room");
			SystemContext sysCtx4= new SystemContext("192.168.20.12", "emergency-room", "workstation");
			ActionPermission action4 = new ActionPermission("addPatientRecord", user1, patient2);
			Object[] facts4 = {action4, sysCtx4, emergency};			
			ksession.execute(Arrays.asList(facts4));			
			assert(action4.isResponse() == true );
			
			//Examples to addTestResult to a PatientRecord...
			System.out.println("\nUser1 is in the PatientRecord team, but she is not a Laboratorian");
			SystemContext sysCtx5= new SystemContext("192.168.630.2", "laboratory-room", "workstation");
			ActionPermission action5 = new ActionPermission("addTestResult", user1, issue1);
			Object[] facts5 = {action5, sysCtx5};			
			ksession.execute(Arrays.asList(facts5));			
			assert(action5.isResponse() == false );
			
			System.out.println("\nUser3 is in the PatientRecord team, he has the role Laboratorian, but he isn't in the laboratory-room");
			SystemContext sysCtx6= new SystemContext("192.168.20.12", "emergency-room", "workstation");
			ActionPermission action6 = new ActionPermission("addTestResult", user3, issue1);
			Object[] facts6 = {action6, sysCtx6};			
			ksession.execute(Arrays.asList(facts6));			
			assert(action6.isResponse() == false );
			
			System.out.println("\nUser3 is in the PatientRecord team, he has the role Laboratorian, and he is in the laboratory-room");
			SystemContext sysCtx7= new SystemContext("192.168.630.2", "laboratory-room", "workstation");
			ActionPermission action7 = new ActionPermission("addTestResult", user3, issue1);
			Object[] facts7 = {action7, sysCtx7};			
			ksession.execute(Arrays.asList(facts7));			
			assert(action7.isResponse() == true );
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
    private static KnowledgeBase readKnowledgeBase() throws Exception {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("rules/AccessControl.drl"), ResourceType.DRL);
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error: errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        return kbase;
    }
    
}
