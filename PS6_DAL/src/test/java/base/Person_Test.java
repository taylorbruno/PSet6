package base;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person_Test {
	
	private static PersonDomainModel p1;
	private static UUID p1id = UUID.randomUUID();
			
	private static PersonDomainModel p2;
	private static UUID p2id = UUID.randomUUID();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		p1 = new PersonDomainModel();
		p2 = new PersonDomainModel();
				
		p1.setPersonID(p1id);
		p1.setFirstName("Taylor");
		p1.setLastName("Bruno");
		p1.setStreet("703 Daniel Drive");
		p1.setPostalCode(19426);
		p1.setCity("Newark");
		
		p2.setPersonID(p2id);
		p2.setFirstName("Lauren");
		p2.setLastName("Davis");
		p2.setStreet("1 South Main");
		p2.setPostalCode(19717);
		p2.setCity("Newark");
		
		PersonDAL.addPerson(p1);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		PersonDAL.deletePerson(p1id);
		PersonDAL.deletePerson(p2id);
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void TestAddPerson() {
		PersonDAL.addPerson(p2);
		assertTrue(PersonDAL.getPersons().contains(p2));
	}
	@Test
	public void TestGetPersons() {
		ArrayList<PersonDomainModel> Plist = PersonDAL.getPersons();
		assertTrue(Plist.contains(p1));
	}
	@Test
	public void TestGetPerson() {
		assertTrue(p1.equals(PersonDAL.getPerson(p1id)));
	}
	@Test
	public void TestDeletePerson() {
		PersonDAL.deletePerson(p2id);
		assertFalse(PersonDAL.getPersons().contains(p2));
	}
	@Test
	public void TestUpdatePerson() {
		p1.setFirstName("Mary");
		PersonDAL.updatePerson(p1);
		assertTrue(PersonDAL.getPerson(p1id).getFirstName().equals("Mary"));
	}

}