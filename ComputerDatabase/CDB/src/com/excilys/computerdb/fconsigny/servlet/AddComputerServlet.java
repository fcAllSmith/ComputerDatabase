package com.excilys.computerdb.fconsigny.servlet;

import java.util.List;

import com.excilys.computerdb.fconsigny.model.Company;
import com.excilys.computerdb.fconsigny.services.CompanyServices;

@WebServlet(urlPatterns = { "/computer"})
public class AddComputerServlet extends HttpServlet{

	private List<Company> companyList;

	/**
	 * Default serial VersionId.
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		companyList = loadCompany();
		//Set list into the view
		request.setAttribute("companyList", companyList);
		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/AddComputerFile.jsp");

		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 *  load a company list in order to print it in a list 
	 * @return List of companies we want to be sure that the computer added 
	 *     is affected to a company which exist.
	 */
	public List<Company> loadCompany() {
		//company listLoader
		return new CompanyServices().getAllCompanies();
	}

}

}