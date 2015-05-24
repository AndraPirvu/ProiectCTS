package teste;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import bazadedate.ConexiuneMySQL;

public class TesteAdmin {

	private ConexiuneMySQL bd;

	@Before
	public void setUp() throws Exception {
		try {
			bd = ConexiuneMySQL.getInstanta();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Teste administratori
	@Test
	public void test1Admin() throws Exception {
		boolean existaAdmin = bd.existaAdministrator("andra.pirvu");
		assertTrue(existaAdmin);
	}

	@Test
	public void test2Admin() throws Exception {
		boolean existaAdmin = bd.existaAdministrator("andra");
		assertFalse(existaAdmin);
	}

	@Test
	public void test3Admin() throws Exception {
		int nrAdmin = bd.getAdministratori().size();
		assertEquals(nrAdmin, 1);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Finalizare teste administratori!");
	}

}
