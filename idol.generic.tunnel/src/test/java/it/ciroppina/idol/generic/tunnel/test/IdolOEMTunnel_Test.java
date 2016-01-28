package it.ciroppina.idol.generic.tunnel.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.ciroppina.idol.generic.tunnel.ErrorResponse;
import it.ciroppina.idol.generic.tunnel.Hit;
import it.ciroppina.idol.generic.tunnel.IdolCategoryResultObject;
import it.ciroppina.idol.generic.tunnel.IdolOEMTunnel;
import it.ciroppina.idol.generic.tunnel.ResultList;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This @Test class is not part of the WS-Client, it doesnt access the endpoint
 * <br/>This is a test class which directly uses IdolOEMTunnel class methods
 * <br/>This is intended for JUnit and Maven tests
 * <br/>
 * THIS ALWAYS SHOULD RETURN ALL-GREEN SIGNALS
 */
public class IdolOEMTunnel_Test {
	
	static PrintStream CONSOLE;
	
	static IdolOEMTunnel ic; 
	@Before
	public void init() {
		ic = new IdolOEMTunnel();
		CONSOLE = System.out;
	}

	@After
	public void finish() {
		ic = null;
		System.gc();
	}
	
	@Test
	public void testErrorAutnResponseAsString() throws Exception {
		Map<String, String> pList = new HashMap<String, String>();
		pList.put("action", "query");
		pList.put("text", ""); //this is an ERROR
		pList.put("anylanguage", "true");
		pList.put("maxresults", "25");
		pList.put("databasematch", "");
		pList.put("combine", "simple");
		pList.put("outputencoding", "utf8");
		pList.put("totalResults", "true");
		
		String expected = "{\"$\":\"ERROR\"}";
		
		String autnresponse = ic.autnResponseAsString(pList, "json");
		assertTrue("should be in Json!", 
				   autnresponse.contains(expected));
		
		autnresponse = ic.autnResponseAsString(pList, "xml");
		assertFalse("should not be in Json, but in XML!", 
			        autnresponse.contains(expected));
	}

	@Test
	public void testAutnResponseAsString() throws Exception {
		Map<String, String> pList = new HashMap<String, String>();
		pList.put("action", "query");
		pList.put("text", "*");
		pList.put("anylanguage", "true");
		pList.put("maxresults", "25");
		pList.put("databasematch", "");
		pList.put("combine", "simple");
		pList.put("outputencoding", "utf8");
		pList.put("totalResults", "true");
		
		String expected = "{\"$\":\"SUCCESS\"}";
		
		String autnresponse = ic.autnResponseAsString(pList, "json");
		assertTrue("should be in Json!", 
				   autnresponse.contains(expected));
		
		autnresponse = ic.autnResponseAsString(pList, "xml");
		assertFalse("should not be in Json, but in XML!", 
			        autnresponse.contains(expected));
	}
	
	@Test
	public void testErrorResponseNode() throws Exception {
		
		Map<String, String> pList = new HashMap<String, String>();
		pList.put("action", "action");
		pList.put("text", "?");
		pList.put("anylanguage", "true");
		pList.put("outputencoding", "utf8");
		
		String expected = "ERROR";
		String autnresponse = ic.autnResponseAsString(pList, "xml");
		
		String result = ic.getQueryResponse(autnresponse);
		System.out.println("testErrorResponseNode - result: " + result);
		assertTrue("RESPONSE-NODE should be ERROR!", 
				result.toUpperCase() .contains(expected));
	}

	@Test
	public void testResponseNode() throws Exception {
		
		Map<String, String> pList = new HashMap<String, String>();
		pList.put("action", "query");
		pList.put("text", "*");
		pList.put("anylanguage", "true");
		pList.put("maxresults", "25");
		pList.put("databasematch", "");
		pList.put("combine", "simple");
		pList.put("outputencoding", "utf8");
		pList.put("totalResults", "true");
		
		String expected = "SUCCESS";
		String autnresponse = ic.autnResponseAsString(pList, "xml");
		//Document documentResult = ic.responseToXMLDocument(autnresponse);
		String result = ic.getQueryResponse(autnresponse);
		
		assertTrue("RESPONSE-NODE should be SUCCESS!", result.equals(expected));
	}
	
	@Test
	public void testErrorAutnResponseAsList() throws Exception {
		
		Map<String, String> pList = new HashMap<String, String>();
		pList.put("action", "query");
		pList.put("text", ""); //this is an ERROR
		pList.put("anylanguage", "true");
		pList.put("maxresults", "25");
		pList.put("databasematch", "");
		pList.put("combine", "simple");
		pList.put("outputencoding", "utf8");
		pList.put("totalResults", "true");
		pList.put("print", "all");
		
		String expected = "ERROR";
		ArrayList<Hit> listOfHits = ic.autnResponseAsList(pList, "xml");
		ErrorResponse er = (ErrorResponse) listOfHits.get(0);
		assertTrue("Result List Size should be empty!",  er.getResponse().toUpperCase()
			.contains(expected));

		printContentOf(listOfHits);
	}

	@Test
	public void testAutnResponseAsList() throws Exception {
		
		Map<String, String> pList = new HashMap<String, String>();
		pList.put("action", "query");
		pList.put("text", "*");
		pList.put("anylanguage", "true");
		pList.put("maxresults", "25");
		pList.put("databasematch", "");
		pList.put("combine", "simple");
		pList.put("outputencoding", "utf8");
		pList.put("totalResults", "true");
		pList.put("print", "all");
		
		int size = 25;
		ArrayList<Hit> listOfHits = ic.autnResponseAsList(pList, "xml");
		assertTrue("Result List Size should be 25!", listOfHits.size()== size);

		printContentOf(listOfHits);
	}
	
	@Test
	public void testAutnResponseAsListDBTitolario() throws Exception {
		String databaseList = "titolario";
		
		Map<String, String> pList = new HashMap<String, String>();
		pList.put("action", "query");
		pList.put("text", "*");
		pList.put("anylanguage", "true");
		pList.put("maxresults", "25");
		pList.put("databasematch", databaseList);
		pList.put("combine", "simple");
		pList.put("outputencoding", "utf8");
		pList.put("totalResults", "true");
		pList.put("print", "all");
		
		int size = 25;
		ArrayList<Hit> listOfHits = ic.autnResponseAsList(pList, "xml");
		assertTrue("Result List Size should be 25!", listOfHits.size()== size);

		printContentOf(listOfHits);
	}
	
	@Test
	public void testErrorHitFromDocument() throws Exception {
		
		Map<String, String> pList = new HashMap<String, String>();
		pList.put("action", "query");
		//this is an ERROR: pList.put("text", "*");
		pList.put("anylanguage", "true");
		pList.put("maxresults", "25");
		pList.put("databasematch", "");
		pList.put("combine", "simple");
		pList.put("outputencoding", "utf8");
		pList.put("totalResults", "true");
		
		String expected = "ERROR";
		String autnresponse = ic.autnResponseAsString(pList, "xml");
		ArrayList<Hit> listHits = ic.getQueryHitsMap(autnresponse);
		ErrorResponse er = (ErrorResponse) listHits.get(0);
		assertTrue("Result List Size should be empty!",  er.getResponse().toUpperCase()
			.contains(expected));
	}

	@Test
	public void testZeroHitsFromDocument() throws Exception {
		
		Map<String, String> pList = new HashMap<String, String>();
		pList.put("action", "query");
		pList.put("text", "*");
		pList.put("anylanguage", "true");
		pList.put("maxresults", "25");
		pList.put("databasematch", "");
		pList.put("combine", "simple");
		pList.put("outputencoding", "utf8");
		pList.put("totalResults", "true");
		pList.put("minScore", "90.95");
		
		final String UNO = "1";
		final String expected = "SUCCESS";
		String autnresponse = ic.autnResponseAsString(pList, "xml");
		String response = ic.getQueryResponse(autnresponse);
		assertTrue(response.toLowerCase().equals(expected.toLowerCase()));
		ArrayList<Hit> listHits = ic.getQueryHitsMap(autnresponse);
		assertTrue("Result List Size should be empty!",  
			listHits.size() < Integer.parseInt(UNO) );
	}

	@Test
	public void testQueryHitsFromDocument() throws Exception {
		
		Map<String, String> pList = new HashMap<String, String>();
		pList.put("action", "query");
		pList.put("text", "*");
		pList.put("anylanguage", "true");
		pList.put("maxresults", "25");
		pList.put("databasematch", "");
		pList.put("combine", "simple");
		pList.put("outputencoding", "utf8");
		pList.put("totalResults", "true");
		
		int size = 25;
		String autnresponse = ic.autnResponseAsString(pList, "xml");
		//Document documentResult = ic.responseToXMLDocument(autnresponse);
		ArrayList<Hit> listHits = ic.getQueryHitsMap(autnresponse);
		
		assertTrue("Result List Size should be 25!", listHits.size()== size);
		
	}
	
	@Test
	public void testGetSpellCheckFieldsFromDocument() throws Exception {
		
		Map<String, String> pList = new HashMap<String, String>();
		pList.put("action", "query");
		pList.put("text", "latitnte indagto ompuatto");
		pList.put("anylanguage", "true");
		pList.put("print", "noresults");
		pList.put("spellcheck", "true");
		pList.put("SpellCheckAlphaNumeric", "false");
		pList.put("outputencoding", "utf8");
		
		String autnresponse = ic.autnResponseAsString(pList, "xml");
		//Document documentResult = ic.responseToXMLDocument(autnresponse);
		String[] spellCheckFields = ic.getSpellCheckFields(autnresponse);
		
		assertTrue("Result should contains two not empty fields", 
				spellCheckFields.length > 1 
				&& spellCheckFields[1].length() > 0
				&& spellCheckFields[0].length() > 0 
		);
		
		//debug;
		System.out.println("\n\tSPELLCHECK FIELDS :\n" + 
				spellCheckFields[0] + "\n" + spellCheckFields[1]
		);
	}
	
	@Test
	public void testGetSpellCheckFieldsFromEmptyQuery() throws Exception {
		
		Map<String, String> pList = new HashMap<String, String>();
		pList.put("action", "query");
		pList.put("text", "*");
		pList.put("anylanguage", "true");
		pList.put("print", "noresults");
		pList.put("spellcheck", "true");
		pList.put("SpellCheckAlphaNumeric", "false");
		pList.put("outputencoding", "utf8");
		
		String autnresponse = ic.autnResponseAsString(pList, "xml");
		//Document documentResult = ic.responseToXMLDocument(autnresponse);
		String[] spellCheckFields = ic.getSpellCheckFields(autnresponse);
		
		assertTrue("Result should contains two not empty fields", 
				spellCheckFields.length > 1 
				&& spellCheckFields[1].length() < 1
				&& spellCheckFields[0].length() > 0 
		);
		
		//debug;
		System.out.println("\n\tSPELLCHECK FIELDS :\n" + 
				spellCheckFields[0] + "\n" + spellCheckFields[1]
		);
	}
	
	@Test
	public void testGetSpellCheck() throws Exception {
		
		Map<String, String> pList = new HashMap<String, String>();
		pList.put("action", "query");
		pList.put("text", "latitnte indagto ompuatto");
		pList.put("anylanguage", "true");
		pList.put("print", "noresults");
		pList.put("spellcheck", "true");
		pList.put("SpellCheckAlphaNumeric", "false");
		pList.put("outputencoding", "utf8");
		
		String[] spellCheckFields = ic.getSpellCheck(pList);
		
		assertTrue("Result should contains two not empty fields", 
				spellCheckFields.length > 1 
				&& spellCheckFields[1].length() > 0
				&& spellCheckFields[0].length() > 0 
		);
		
		//debug;
		System.out.println("\n\tSPELLCHECK FIELDS :\n" + 
				spellCheckFields[0] + "\n" + spellCheckFields[1]
		);
	}
	
	@Test
	public void testGetHitIndexedContent() throws Exception {
		
		Map<String, String> pList = new HashMap<String, String>();
		pList.put("action", "query");
		pList.put("text", "*");
		pList.put("maxresults", "10");
		pList.put("anylanguage", "true");
		pList.put("print", "indexText");
		pList.put("outputencoding", "utf8");
		
		String autnresponse = ic.autnResponseAsString(pList, "xml");
		ArrayList<Hit> hits = ic.getQueryHitsNoDocumentMap(autnresponse);
		
		assertTrue("Result should contains the autn:content not-empty field", 
				hits.size() <= 10 
				&& ic.getHitIndexedContent(hits.get(0).getDreFields()).length() > 0
		);
		
		//debug;
		System.out.println("\n\tINDEXED CONTENT:\n" + 
				ic.getHitIndexedContent(hits.get(0).getDreFields())
		);
	}
	
	@Test
	public void testCategorySuggestFromText() throws Exception {
		
		ResultList rlist = ic.categorySuggestFromText("Esito delle indagini preliminari");
		List<IdolCategoryResultObject> list = rlist.getReturnList();
		
		for (IdolCategoryResultObject cat : list) {
			String catData = cat.getRilevanza() +"% "+ cat.getId() + " - " + cat.getTitolo();
			System.out.println(catData);
			assertTrue("Should be a Double", Double.parseDouble(cat.getRilevanza()) > 1d);
		}
	}

	/**
	 * Utilities
	 */
	
	private void console(Object o) {
		CONSOLE.println("\t" + o.toString());
	}
	
	/**
	 * @param listOfHits
	 */
	public void printContentOf(ArrayList<Hit> listOfHits) {
		console("### Contenuto dell' <autnResponse> ###");
		for (Hit hit : listOfHits) {
			console( "DREREFERENCE: " + hit.getDREREFERENCE() );
			console( "DREDATE: " + hit.getDREDATE() );
			console( "DREDBNAME: " + hit.getDREDBNAME() );
			console( "INDEXEDCONTENT: " + hit.getINDEXEDCONTENT() );
			console( "DRESECTION: " + hit.getSECTION() );
			console( "UUID: " + hit.getUUID() );
			
			Map<String, String> fields = hit.getDreFields();
			Iterator<String> iterator = fields.keySet().iterator();
			while(iterator.hasNext()) {
				String key = iterator.next();
				console( key + ": " + fields.get(key) );
			}
			console( "FINE-HIT ############################################################################################\n\n\n");
		}
		console("### Fine Contenuto dell' <autnResponse> ###\n");
	}
}
