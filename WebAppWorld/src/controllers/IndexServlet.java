package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.City;
import model.Country;
import model.CountryTable;
import model.Countrylanguage;
import utils.DBUtil;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		EntityManager em = DBUtil.createEntityManager();
	    // 大陸ごとの国情報リストを取得する
	    List<Country> asianCountries = em.createNamedQuery("Country.finfByContinent", Country.class)
	    															.setParameter("continent", "Asia").getResultList();
	    List<Country> africanCountries = em.createNamedQuery("Country.finfByContinent", Country.class)
																		.setParameter("continent", "Africa").getResultList();
	    List<Country> europeanCountries = em.createNamedQuery("Country.finfByContinent", Country.class)
																			.setParameter("continent", "Europe").getResultList();
	    List<Country> nAmericanCountries = em.createNamedQuery("Country.finfByContinent", Country.class)
																			.setParameter("continent", "North America").getResultList();
	    List<Country> sAmericanCountries = em.createNamedQuery("Country.finfByContinent", Country.class)
																			  .setParameter("continent", "South America").getResultList();
	    List<Country> oceanianCountries = em.createNamedQuery("Country.finfByContinent", Country.class)
				  															 .setParameter("continent", "Oceania").getResultList();


//	    System.out.println("asianCountries.size()=" + asianCountries.size());
//	    System.out.println("africanCountries.size()=" + africanCountries.size());
//	    System.out.println("europeanCountries.size()=" + europeanCountries.size());
//		System.out.println("nAmericanCountries.size()=" + nAmericanCountries.size());
//	    System.out.println("sAmericanCountries.size()=" + sAmericanCountries.size());
//	    System.out.println("oceanianCountries.size()=" + oceanianCountries.size());


	    CountryTable[] asia = getCountryTable(em, "Asia");
	    CountryTable[] africa = getCountryTable(em, "Africa");
	    CountryTable[] europe = getCountryTable(em, "Europe");
	    CountryTable[] northAmerica = getCountryTable(em, "North America");
	    CountryTable[] southAmerica = getCountryTable(em, "South America");
	    CountryTable[] oceania = getCountryTable(em, "Oceania");

//	    System.out.println("asia.length=" + asia.length);
//	    System.out.println("africa.length=" + africa.length);
//	    System.out.println("europe.length=" + europe.length);
//	    System.out.println("northAmerica.length=" + northAmerica.length);
//	    System.out.println("southAmerica.length=" + southAmerica.length);
//	    System.out.println("oceania.length=" + oceania.length);
//	    for (CountryTable c : africa) {
//			for (Countrylanguage lan : c.getLanguage()) {
//			System.out.println(c.getCountry().getName() + ": " +  c.getCapital() + ": " +  lan.getId().getLanguage());
//			}
//	    }

		String url = "/WEB-INF/views/index.jsp";
		request.setAttribute("ASIA", asia);
		request.setAttribute("AFRICA", africa);
		request.setAttribute("EUROPE", europe);
		request.setAttribute("N_AMERICA", northAmerica);
		request.setAttribute("S_AMERICA", southAmerica);
		request.setAttribute("OCEANIA", oceania);


		// Asiaの国名リスト
	    String[] asianCountiesName = new String[asianCountries.size()];
	    for (int i = 0; i < asianCountiesName.length; i++) {
	    	asianCountiesName[i] = asianCountries.get(i).getName();
		}

		// Africaの国名リスト
	    String[] africanCountiesName = new String[africanCountries.size()];
	    for (int i = 0; i < africanCountiesName.length; i++) {
			africanCountiesName[i] = africanCountries.get(i).getName();
		}
		// Europeの国名リスト
	    String[] europeanCountiesName = new String[europeanCountries.size()];
	    for (int i = 0; i < europeanCountiesName.length; i++) {
			europeanCountiesName[i] = europeanCountries.get(i).getName();
		}
		// North Americaの国名リスト
	    String[] nAmericanCountiesName = new String[nAmericanCountries.size()];
	    for (int i = 0; i < nAmericanCountiesName.length; i++) {
	    	nAmericanCountiesName[i] = nAmericanCountries.get(i).getName();
		}
		// South Americaの国名リスト
	    String[] sAmericanCountiesName = new String[sAmericanCountries.size()];
	    for (int i = 0; i < sAmericanCountiesName.length; i++) {
	    	sAmericanCountiesName[i] = sAmericanCountries.get(i).getName();
		}
		// Oceaniaの国名リスト
	    String[] oceanianCountiesName = new String[oceanianCountries.size()];
	    for (int i = 0; i < oceanianCountiesName.length; i++) {
	    	oceanianCountiesName[i] = oceanianCountries.get(i).getName();
		}

		em.close();

//		String url = "/WEB-INF/views/index.jsp";
//		request.setAttribute("ASIA", asianCountiesName);
//		request.setAttribute("AFRICA", africanCountiesName);
//		request.setAttribute("EUROPE", europeanCountiesName);
//		request.setAttribute("N_AMERICA", nAmericanCountiesName);
//		request.setAttribute("S_AMERICA", sAmericanCountiesName);
//		request.setAttribute("OCEANIA", oceanianCountiesName);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


	// 大陸ごとの首都名を取得する
	private String getCapitalName(Country country, EntityManager em) {
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
	private List<Countrylanguage> getLanguagesByCountry(Country country, EntityManager em){
		List<Countrylanguage> languages = em.createNamedQuery("Countrylanguage.getLanguage", Countrylanguage.class)
																			.setParameter("country", country).getResultList();
//		for (Countrylanguage c : languages) {
//			System.out.println(c.getCountry().getName() + ": " + c.getId().getLanguage());
//		}
		return languages;
	}

	private CountryTable[] getCountryTable(EntityManager em, String continentName) {
	    // 大陸ごとの国情報リストを取得する
	    List<Country> countryList = em.createNamedQuery("Country.finfByContinent", Country.class)
	    														.setParameter("continent", continentName).getResultList();
	    System.out.println("countryList.size()=" + countryList.size());
	    // 大陸ごとの国テーブル(国情報 + 首都 + 言語リスト)配列を取得する
	    CountryTable[] table = new CountryTable[countryList.size()];
	    for (int i = 0; i < table.length; i++) {
	    	CountryTable countryTable = new CountryTable();
	    	Country country = countryList.get(i);
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
}
