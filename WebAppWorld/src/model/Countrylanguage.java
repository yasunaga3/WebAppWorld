package model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the countrylanguage database table.
 *
 */
@Entity
@NamedQuery(name="Countrylanguage.findAll", query="SELECT c FROM Countrylanguage c")
@NamedQuery(name="Countrylanguage.getLanguage",
							query="SELECT c FROM Countrylanguage c WHERE c.country = :country")
public class Countrylanguage implements Serializable {
	private static final long serialVersionUID = 1L;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="CountryCode")
	private Country country;
	@EmbeddedId
	private CountrylanguagePK id;
	private String isOfficial;
	private float percentage;





	public Countrylanguage() {	}

	public CountrylanguagePK getId() {
		return this.id;
	}

	public void setId(CountrylanguagePK id) {
		this.id = id;
	}

	public String getIsOfficial() {
		return this.isOfficial;
	}

	public void setIsOfficial(String isOfficial) {
		this.isOfficial = isOfficial;
	}

	public float getPercentage() {
		return this.percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}