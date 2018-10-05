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
import javax.servlet.http.HttpSession;

import model.City;
import model.Country;
import model.CountryTable;
import utils.DBHelper;
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
	    CountryTable[] asia = DBHelper.getCountryTable(em, "Asia");
	    CountryTable[] africa = DBHelper.getCountryTable(em, "Africa");
	    CountryTable[] europe = DBHelper.getCountryTable(em, "Europe");
	    CountryTable[] northAmerica = DBHelper.getCountryTable(em, "North America");
	    CountryTable[] southAmerica = DBHelper.getCountryTable(em, "South America");
	    CountryTable[] oceania = DBHelper.getCountryTable(em, "Oceania");
		em.close();

		// sessionスコープに大陸ごとの国テーブル(国情報 + 首都 + 言語リスト)配列を設定
		HttpSession session = request.getSession();
		session.setAttribute("ASIA", asia);
		session.setAttribute("AFRICA", africa);
		session.setAttribute("EUROPE", europe);
		session.setAttribute("N_AMERICA", northAmerica);
		session.setAttribute("S_AMERICA", southAmerica);
		session.setAttribute("OCEANIA", oceania);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 国名とCountryオブジェクトの取得
		String countryCode= request.getParameter("selectBox2");
		System.out.println("countyrCode=" + countryCode);
		EntityManager em = DBUtil.createEntityManager();
		Country country = DBHelper.getCountryByCode(em, countryCode);
		String countryName = country.getName();
		// 大陸名の取得
		String continentName = request.getParameter("selectBox1");
		if (continentName == null || continentName.equals("default")) {
			continentName = country.getContinent();
			System.out.println("continentName=" + continentName);
		} else {
			System.out.println("continentName=" + continentName);
		}
		// 取得したCountryオブジェクトの都市リストを取得する
		List<City> cities = country.getCities();
		System.out.println("cities.size()=" + cities.size());
		em.close();

		String url = "/WEB-INF/views/cities_list.jsp";
		request.setAttribute("CONTINENT", continentName);
		request.setAttribute("COUNTRY", countryName);
		request.setAttribute("CITIES", cities);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
