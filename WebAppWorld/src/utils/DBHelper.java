package utils;

import java.util.List;

import javax.persistence.EntityManager;

import model.City;
import model.Country;
import model.CountryTable;
import model.Countrylanguage;

public class DBHelper {

	// 大陸ごとの首都名を取得する
	public static String getCapitalName(Country country, EntityManager em) {
		Integer capital_code = country.getCapital();
		String capital = null;
		if (capital_code != null) {
			City city = em.createNamedQuery("City.findById", City.class)
					.setParameter("id", capital_code).getSingleResult();
			capital =  city.getName();
		} else {
			capital = "なし";
		}
		return capital;
	}

	// 国ごとの言語リストを取得する
	public static List<Countrylanguage> getLanguagesByCountry(Country country, EntityManager em){
		List<Countrylanguage> languages = em.createNamedQuery("Countrylanguage.getLanguage", Countrylanguage.class)
																			.setParameter("country", country).getResultList();
//		for (Countrylanguage c : languages) {
//			System.out.println(c.getCountry().getName() + ": " + c.getId().getLanguage());
//		}
		return languages;
	}

	// 大陸ごとの国テーブル(国情報 + 首都 + 言語リスト)配列を取得する
	public static CountryTable[] getCountryTable(EntityManager em, String continentName) {
	    // 大陸ごとの国情報リストを取得する
	    List<Country> countryList = em.createNamedQuery("Country.finfByContinent", Country.class)
	    														.setParameter("continent", continentName).getResultList();
//	    System.out.println("countryList.size()=" + countryList.size());
	    // 大陸ごとの国テーブル(国情報 + 首都 + 言語リスト)配列を取得する
	    CountryTable[] table = new CountryTable[countryList.size()];
	    for (int i = 0; i < table.length; i++) {
	    	CountryTable countryTable = new CountryTable();
	    	Country country = countryList.get(i);

	    	//----- HibernateのLazy initializeエラー対策用の遅延処理 ------
	    	// http://www.pwv.co.jp/~take/TakeWiki/index.php?Hibernate%E3%81%AELazy%20initialize%E3%82%A8%E3%83%A9%E3%83%BC%E3%81%A7%E3%83%93%E3%83%A5%E3%83%BC%E3%82%92%E8%A1%A8%E7%A4%BA%E3%81%A7%E3%81%8D%E3%81%AA%E3%81%84
	    	List<City> cities = country.getCities(); // 追加
//	    	System.out.print(country.getName());
//	    	System.out.print("(" + cities.size() + "): ");
	    	for (City city : cities) {
//				System.out.print(city.getName() + ", ");
			}
//	    	System.out.println();
	    	//--- HibernateのLazy initializeエラー対策用の遅延処理END ----

	    	List<Countrylanguage> languages = getLanguagesByCountry(country, em);
//	    	System.out.println("languages.size()=" + languages.size());
	    	countryTable.setCountry(country);
	    	countryTable.setCapital(getCapitalName(country, em));
	    	countryTable.setLanguage(languages);
	    	table[i] = countryTable;
	    	// デバッグ検証用
//	    	System.out.print(country.getName() + ": ");
//	    	System.out.print(getCapitalName(country, em) + ": ");
//			for (Countrylanguage c : languages) {
//				System.out.print(c.getId().getLanguage() + ", ");
//			}
//	    	System.out.println();
		}
		return table;
	}

	// 国コードからCountryオブジェクトを取得する
	public static Country getCountryByCode(EntityManager em, String countryCode) {
		Country country = null;
		try {
			country = em.createNamedQuery("Country.finfByCode", Country.class)
					.setParameter("code", countryCode).getSingleResult();
		} catch (Exception e) {
			System.out.println("getCountryByCode Error: country not found");
		}
		return country;
	}
}
