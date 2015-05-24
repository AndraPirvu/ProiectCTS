package teste;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import bazadedate.ConexiuneMySQL;
import clase.*;

public class TesteProduse {

	private Produs produs = new Produs();
	private ProdusPromotie produsPromotie = new ProdusPromotie();
	private Categorie categ = new Categorie();
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

	// Teste produse
	@Test
	public void test23Produs() throws Exception {
		String denumire = bd.getDenumirePrimulProdusInserat();
		assertEquals(denumire, "Blugi");
	}

	@Test
	public void test24Produs() throws Exception {
		boolean existaProdus = bd.existaProdus("Tricouri");
		assertTrue(existaProdus);
	}

	@Test
	public void test25Produs() throws Exception {
		int nrProduse = bd.getProduse(1).size();
		// sunt 3 produse in prima categorie
		assertEquals(nrProduse, 3);
	}

	// Teste nume produs
	@Test
	public void test26NumeProdus() {
		verif = produs.verifNume("Biscuiti");
		assertTrue(verif);
	}

	@Test
	public void test27NumeProdus() {
		produs.setNume(null);
		assertNull(produs.getNume());
	}

	@Test
	public void test28NumeProdus() {
		produs.setNume("Ciocolata");
		assertEquals(produs.getNume(), "Ciocolata");
	}

	// Teste descriere produs
	@Test
	public void test29DescriereProdus() {
		verif = produs.verifDescriere("Biscuiti cu crema de cacao");
		assertTrue(verif);
	}

	@Test
	public void test30DescriereProdus() {
		produs.setDescriere(null);
		assertNull(produs.getDescriere());
	}

	@Test
	public void test31DescriereProdus() {
		produs.setDescriere("Ciocolata Milka cu lapte");
		assertEquals(produs.getDescriere(), "Ciocolata Milka cu lapte");
	}

	// Teste pret produse
	@Test
	public void test32PretProdus() {
		produs.setPret(10);
		verif = produs.verifPret();
		assertTrue(verif);
	}

	@Test
	public void test33SumaPretProduse() throws Exception {
		double suma = bd.getSumaPretProduse();
		// valoarea produselor din prima categorie este de 115.5 lei
		// am pus o marja de 1 leu
		assertEquals(suma, 115, 1);
	}

	@Test
	public void test34PretMediuProduse() throws Exception {
		double pretMediu = bd.getPretMediu();
		// pretul mediu al produselor din prima categorie este de 38.5 lei
		// am pus o marja de 1 leu
		assertEquals(pretMediu, 38, 1);
	}

	@Test
	public void test35PretMinimProduse() throws Exception {
		double pretMinim = bd.getPretMinim();
		// pretul mediu al produselor este de 3.5
		// am pus o marja de 1 leu
		assertEquals(pretMinim, 3, 1);
	}

	@Test
	public void test36PretMaximProduse() throws Exception {
		double pretMaxim = bd.getPretMaxim();
		// pretul mediu al produselor este de 65
		// fara marja de eroare
		assertEquals(pretMaxim, 65, 0);
	}

	// Teste produse reduse
	@Test
	public void test37ReducereProdus() {
		produsPromotie.setProcentReducere(30);
		verif = produsPromotie.verifReducere();
		assertTrue(verif);
	}

	@Test
	public void test38ReducereProdus() {
		produsPromotie.setPret(200);
		produsPromotie.setProcentReducere(0.3);
		double reducere = produsPromotie.getPretRedus();
		// reducerea este de 60 de lei din 200 lei, pret initial
		// fara marja de eroare
		assertEquals(reducere, 60, 0);
	}

	@Test
	public void test39SumaPretReduceri() throws Exception {
		double sumaReduceri = bd.getSumaPretReduceri();
		// suma preturilor produselor reduse este de 114.4 lei
		// am pus o marja de eroare de 1 leu
		assertEquals(sumaReduceri, 114, 1);
	}

	@Test
	public void test40ReducereMaxima() throws Exception {
		double reducereMaxima = bd.getReducereMaxima();
		// reducerea maxima din magazin este de 60%
		// fara marja de eroare
		assertEquals(reducereMaxima, 0.6, 0);
	}

	// Teste cantitate produse
	@Test
	public void test41CantitateProdus() {
		produs.setCantitate(5);
		verif = produs.verifCantitate();
		assertTrue(verif);
	}

	@Test
	public void test42SumaProduse() throws Exception {
		double suma = bd.getSumaProduse();
		// cantitatea produselor din prima categorie este de 54 bucati
		// fara marja de eroare
		assertEquals(suma, 54, 0);
	}

	@Test
	public void test43CantitateMinima() throws Exception {
		double cantitateMinima = bd.getCantitateMinimaProduse();
		// cantitatea minima din prima categorie este de 12 bucati
		// fara marja de eroare
		assertEquals(cantitateMinima, 12, 0);
	}

	@Test
	public void test44CantitateMaxima() throws Exception {
		double cantitateMaxima = bd.getCantitateMaximaProduse();
		// cantitatea maxima din prima categorie este de 22 bucati
		// fara marja de eroare
		assertEquals(cantitateMaxima, 22, 0);
	}

	@Test
	public void test45CantitateMedie() throws Exception {
		double cantitateMedie = bd.getCantitateMedieProduse();
		// cantitatea medie din prima categorie este de 18 bucati
		// fara marja de eroare
		assertEquals(cantitateMedie, 18, 0);
	}

	@Test
	public void test46ProfitMaxim() throws Exception {
		double profit = bd.getProfitMaxim();
		// profitul este de 2697 lei
		// fara marja de eroare
		assertEquals(profit, 2697, 0);
	}

	@Test
	public void test47ProfitReal() throws Exception {
		double profitReal = bd.getProfitReal();
		// profitul este de 2059.8 lei
		// cu o marja de eroare de 1 leu
		assertEquals(profitReal, 2060, 1);
	}

	// Teste categorii produse
	@Test
	public void test48Categorie() throws Exception {
		String denumire = bd.getDenumirePrimaCategorieInserata();
		assertEquals(denumire, "Haine");
	}

	@Test
	public void test49Categorie() throws Exception {
		boolean existaCategorie = bd.existaCategorie("Incaltaminte");
		assertTrue(existaCategorie);
	}

	@Test
	public void test50Categorie() throws Exception {
		int nrCategorii = bd.getCategorii().size();
		// sunt 3 produse in prima categorie
		assertEquals(nrCategorii, 3);
	}

	// Teste denumire categorie
	@Test
	public void test51DenumireCategorie() {
		verif = categ.verifDenumire("Incaltaminte");
		assertTrue(verif);
	}

	@Test
	public void test52DenumireCategorie() {
		categ.setDenumire(null);
		assertNull(categ.getDenumire());
	}

	@Test
	public void test53DenumireCategorie() {
		categ.setDenumire("Incaltaminte");
		assertEquals(categ.getDenumire(), "Incaltaminte");
	}

	// Teste descriere categorie
	@Test
	public void test54DescriereCategorie() {
		verif = categ.verifDescriere("Colectia de primavara-vara 2015");
		assertTrue(verif);
	}

	@Test
	public void test55DescriereCategorie() {
		categ.setDescriere(null);
		assertNull(categ.getDescriere());
	}

	@Test
	public void test56DescriereCategorie() {
		categ.setDescriere("Colectia de primavara-vara 2015");
		assertEquals(categ.getDescriere(), "Colectia de primavara-vara 2015");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Finalizare teste produse!");
	}

}
