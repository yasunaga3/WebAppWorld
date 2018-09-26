package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.City;
import model.Country;
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

		EntityManager em = DBUtil.createEntityManager();
	    List<City> cities = em.createNamedQuery("City.findAll", City.class).getResultList();
	    response.getWriter().append(Integer.valueOf(cities.size()).toString());
	    response.getWriter().append("<br>");

	    List<Countrylanguage> cl = em.createNamedQuery("Countrylanguage.findAll", Countrylanguage.class).getResultList();
	    response.getWriter().append(Integer.valueOf(cl.size()).toString());
	    response.getWriter().append("<br>");

	    List<Country> countries = em.createNamedQuery("Country.findAll", Country.class).getResultList();
	    response.getWriter().append(Integer.valueOf(countries.size()).toString());
	    response.getWriter().append("<br>");

		em.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}



}
