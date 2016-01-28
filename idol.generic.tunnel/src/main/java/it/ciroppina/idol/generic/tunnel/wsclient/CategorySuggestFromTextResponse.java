
package it.ciroppina.idol.generic.tunnel.wsclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per categorySuggestFromTextResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="categorySuggestFromTextResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://tunnel.generic.idol.ciroppina.it/}resultList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "categorySuggestFromTextResponse", propOrder = {
    "_return"
})
public class CategorySuggestFromTextResponse {

    @XmlElement(name = "return")
    protected ResultList _return;

    /**
     * Recupera il valore della propriet� return.
     * 
     * @return
     *     possible object is
     *     {@link ResultList }
     *     
     */
    public ResultList getReturn() {
        return _return;
    }

    /**
     * Imposta il valore della propriet� return.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultList }
     *     
     */
    public void setReturn(ResultList value) {
        this._return = value;
    }

}
