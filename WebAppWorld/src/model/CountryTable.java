package model;

import java.util.List;

public class CountryTable {

	private Country country;
	private String capital;
	private List<Countrylanguage> language;

	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public List<Countrylanguage> getLanguage() {
		return language;
	}
	public void setLanguage(List<Countrylanguage> language) {
		this.language = language;
	}


}
