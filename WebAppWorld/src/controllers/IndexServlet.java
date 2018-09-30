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

import model.Country;
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

		String continentName = request.getParameter("selectContinent");
		if(continentName == null || continentName.equals("")) {

		} else {
			System.out.println("continentName =" + continentName);
		}

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
		em.close();

//	    System.out.println("asianCountries.size()=" + asianCountries.size());
//	    System.out.println("africanCountries.size()=" + africanCountries.size());
//	    System.out.println("europeanCountries.size()=" + europeanCountries.size());
//		System.out.println("nAmericanCountries.size()=" + nAmericanCountries.size());
//	    System.out.println("sAmericanCountries.size()=" + sAmericanCountries.size());
//	    System.out.println("oceanianCountries.size()=" + oceanianCountries.size());

	    // 大陸ごとの国名を配列に格納する
	    String[] asianCountiesName = new String[asianCountries.size()];
	    for (int i = 0; i < asianCountiesName.length; i++) {
	    	asianCountiesName[i] = asianCountries.get(i).getName();
		}
//	    for (String s : asianCountiesName) { System.out.println(s); }

	    String[] africanCountiesName = new String[africanCountries.size()];
	    for (int i = 0; i < africanCountiesName.length; i++) {
			africanCountiesName[i] = africanCountries.get(i).getName();
		}

	    String[] europeanCountiesName = new String[europeanCountries.size()];
	    for (int i = 0; i < europeanCountiesName.length; i++) {
			europeanCountiesName[i] = europeanCountries.get(i).getName();
		}

	    String[] nAmericanCountiesName = new String[nAmericanCountries.size()];
	    for (int i = 0; i < nAmericanCountiesName.length; i++) {
	    	nAmericanCountiesName[i] = nAmericanCountries.get(i).getName();
		}

	    String[] sAmericanCountiesName = new String[sAmericanCountries.size()];
	    for (int i = 0; i < sAmericanCountiesName.length; i++) {
	    	sAmericanCountiesName[i] = sAmericanCountries.get(i).getName();
		}

	    String[] oceanianCountiesName = new String[oceanianCountries.size()];
	    for (int i = 0; i < oceanianCountiesName.length; i++) {
	    	oceanianCountiesName[i] = oceanianCountries.get(i).getName();
		}

		String url = "/WEB-INF/views/index.jsp";
		request.setAttribute("ASIA", asianCountiesName);
		request.setAttribute("AFRICA", africanCountiesName);
		request.setAttribute("EUROPE", europeanCountiesName);
		request.setAttribute("N_AMERICA", nAmericanCountiesName);
		request.setAttribute("S_AMERICA", sAmericanCountiesName);
		request.setAttribute("OCEANIA", oceanianCountiesName);
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

}
