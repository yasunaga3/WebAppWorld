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
		String url = null;
		String inf = request.getParameter("INF");
		if (inf == null || inf.equals("")) {
			System.out.println("inf = null");
		} else if("country".equals(inf)){
			url = "/WEB-INF/views/countries_list.jsp";
		} else if("city".equals(inf)){
			url = "/WEB-INF/views/cities_list.jsp";
		}

		EntityManager em = DBUtil.createEntityManager();
		// 大陸ごとの国テーブル(国情報 + 首都 + 言語リスト)配列を取得する
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
		em.close();
		// requestスコープに大陸ごとの国テーブル(国情報 + 首都 + 言語リスト)配列を設定
		request.setAttribute("ASIA", asia);
		request.setAttribute("AFRICA", africa);
		request.setAttribute("EUROPE", europe);
		request.setAttribute("N_AMERICA", northAmerica);
		request.setAttribute("S_AMERICA", southAmerica);
		request.setAttribute("OCEANIA", oceania);
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

	// 大陸ごとの国テーブル(国情報 + 首都 + 言語リスト)配列を取得する
	private CountryTable[] getCountryTable(EntityManager em, String continentName) {
	    // 大陸ごとの国情報リストを取得する
	    List<Country> countryList = em.createNamedQuery("Country.finfByContinent", Country.class)
	    														.setParameter("continent", continentName).getResultList();
//	    System.out.println("countryList.size()=" + countryList.size());
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
