
package it.ciroppina.idol.generic.tunnel.wsclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per idolCategoryResultObject complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="idolCategoryResultObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rilevanza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titolo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "idolCategoryResultObject", propOrder = {
    "id",
    "rilevanza",
    "titolo"
})
public class IdolCategoryResultObject {

    protected String id;
    protected String rilevanza;
    protected String titolo;

    /**
     * Recupera il valore della propriet� id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Imposta il valore della propriet� id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Recupera il valore della propriet� rilevanza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRilevanza() {
        return rilevanza;
    }

    /**
     * Imposta il valore della propriet� rilevanza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRilevanza(String value) {
        this.rilevanza = value;
    }

    /**
     * Recupera il valore della propriet� titolo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Imposta il valore della propriet� titolo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitolo(String value) {
        this.titolo = value;
    }

}
