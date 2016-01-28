package it.ciroppina.idol.generic.tunnel;

public class IdolCategoryResultObject {

	private String rilevanza;
	private String titolo;
	private String id;
	
	public IdolCategoryResultObject() {}

	/**
	 * @param rilevanza
	 * @param titolo
	 * @param id
	 */
	public IdolCategoryResultObject(String rilevanza, String titolo, String id) {
		super();
		this.rilevanza = rilevanza;
		this.titolo = titolo;
		this.id = id;
	}

	
	/**** GETTER & SETTER ******/
	
	public String getRilevanza() {
		return rilevanza;
	}
	public void setRilevanza(String rilevanza) {
		this.rilevanza = rilevanza;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
