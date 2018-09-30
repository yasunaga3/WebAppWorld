package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the country database table.
 *
 */
@Entity
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
@NamedQuery(name="Country.finfByContinent", query="SELECT c FROM Country c WHERE  c.continent = :continent")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String code;
	private String name;
	private String continent;
	private String region;
	private float surfaceArea;
	private Short indepYear;
	private int population;
	private Float lifeExpectancy;
	private Float gnp;
	private Float GNPOld;
	private String localName;
	private String governmentForm;
	private String headOfState;
	private Integer capital;
	private String code2;

	//bi-directional many-to-one association to City
	@OneToMany(mappedBy="country")
	private List<City> cities;
	//bi-directional many-to-one association to Countrylanguage
	@OneToMany(mappedBy="country")
	private List<Countrylanguage> countrylanguages;




	public Country() {	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getCapital() {
		return this.capital;
	}

	public void setCapital(Integer capital) {
		this.capital = capital;
	}

	public String getCode2() {
		return this.code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	public String getContinent() {
		return this.continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public Float getGnp() {
		return this.gnp;
	}

	public void setGnp(Float gnp) {
		this.gnp = gnp;
	}

	public Float getGNPOld() {
		return this.GNPOld;
	}

	public void setGNPOld(Float GNPOld) {
		this.GNPOld = GNPOld;
	}

	public String getGovernmentForm() {
		return this.governmentForm;
	}

	public void setGovernmentForm(String governmentForm) {
		this.governmentForm = governmentForm;
	}

	public String getHeadOfState() {
		return this.headOfState;
	}

	public void setHeadOfState(String headOfState) {
		this.headOfState = headOfState;
	}

	public Short getIndepYear() {
		return this.indepYear;
	}

	public void setIndepYear(Short indepYear) {
		this.indepYear = indepYear;
	}

	public Float getLifeExpectancy() {
		return this.lifeExpectancy;
	}

	public void setLifeExpectancy(Float lifeExpectancy) {
		this.lifeExpectancy = lifeExpectancy;
	}

	public String getLocalName() {
		return this.localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPopulation() {
		return this.population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public float getSurfaceArea() {
		return this.surfaceArea;
	}

	public void setSurfaceArea(float surfaceArea) {
		this.surfaceArea = surfaceArea;
	}

	public List<City> getCities() {
		return this.cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public City addCity(City city) {
		getCities().add(city);
		city.setCountry(this);

		return city;
	}

	public City removeCity(City city) {
		getCities().remove(city);
		city.setCountry(null);

		return city;
	}

	public List<Countrylanguage> getCountrylanguages() {
		return this.countrylanguages;
	}

	public void setCountrylanguages(List<Countrylanguage> countrylanguages) {
		this.countrylanguages = countrylanguages;
	}

	public Countrylanguage addCountrylanguage(Countrylanguage countrylanguage) {
		getCountrylanguages().add(countrylanguage);
		countrylanguage.setCountry(this);

		return countrylanguage;
	}

	public Countrylanguage removeCountrylanguage(Countrylanguage countrylanguage) {
		getCountrylanguages().remove(countrylanguage);
		countrylanguage.setCountry(null);

		return countrylanguage;
	}

}