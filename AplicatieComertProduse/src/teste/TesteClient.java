package teste;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import bazadedate.ConexiuneMySQL;
import clase.*;

public class TesteClient {

	private Client client = new Client();
	private ConexiuneMySQL bd;
	private boolean verif;

	@Before
	public void setUp() throws Exception {
		try {
			bd = ConexiuneMySQL.getInstanta();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Teste clienti
	@Test
	public void test4Client() throws Exception {
		boolean existaClient = bd.existaClient("cristescu.mariana");
		assertTrue(existaClient);
	}

	@Test
	public void test5Client() throws Exception {
		boolean existaClient = bd.existaClient("cristescu.cosmin");
		assertFalse(existaClient);
	}

	@Test
	public void test6Client() throws Exception {
		// in baza de date sunt 4 clienti
		int nrClienti = bd.getClienti().size();
		assertNotEquals(nrClienti, 2);
	}

	@Test
	public void test7Client() throws Exception {
		long idClient = bd.getUltimulIdClientInserat();
		assertEquals(idClient, 4);
	}

	// Teste nume client
	@Test
	public void test8NumeClient() {
		verif = client.verifNume("Popescu");
		assertTrue(verif);
	}

	@Test
	public void test9NumeClient() {
		client.setNume(null);
		assertNull(client.getNume());
	}

	@Test
	public void test10NumeClient() {
		client.setNume("Pascu");
		assertEquals(client.getNume(), "Pascu");
	}

	// Teste prenume client
	@Test
	public void test11PrenumeClient() {
		verif = client.verifPrenume("Ciprian");
		assertTrue(verif);
	}

	@Test
	public void test12PrenumeClient() {
		client.setPrenume(null);
		assertNull(client.getPrenume());
	}

	@Test
	public void test13PrenumeClient() {
		client.setPrenume("Andreea");
		assertEquals(client.getPrenume(), "Andreea");
	}

	// Teste telefon client
	@Test
	public void test14TelefonClient() {
		client.setTelefon(null);
		assertNull(client.getTelefon());
	}

	@Test
	public void test15TelefonClient() {
		client.setTelefon("0723203535");
		assertNotEquals(client.getTelefon(), 0723203535);
	}

	@Test
	public void test16TelefonClient() {
		verif = client.verifTelefon("0723333530");
		assertTrue(verif);
	}

	// Teste email client
	@Test
	public void test17EmailClient() {
		client.setEmail(null);
		assertNull(client.getEmail());
	}

	@Test
	public void test18EmailClient() {
		verif = client.verifEmail("ana@gmail.com");
		assertTrue(verif);
	}

	@Test
	public void test19EmailClient() {
		client.setEmail("ana@gmail.com");
		assertEquals(client.getEmail(), "ana@gmail.com");
	}

	// Teste site client
	@Test
	public void test20SiteClient() {
		client.setSite(null);
		assertNull(client.getSite());
	}

	@Test
	public void test21SiteClient() {
		verif = client.verifSite("www.google.com");
		assertTrue(verif);
	}

	@Test
	public void test22SiteClient() {
		client.setSite("www.goole.com");
		assertEquals(client.getSite(), "www.goole.com");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Finalizare teste clienti!");
	}

}
