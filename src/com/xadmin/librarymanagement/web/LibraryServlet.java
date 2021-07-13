package com.xadmin.librarymanagement.web;

//public class LibraryServlet {
//
//}


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.librarymanagement.dao.LibraryDao;
import com.xadmin.librarymanagement.bean.Library;



/**
 * Servlet implementation class UserServlet
 */

@WebServlet("/")
public class LibraryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LibraryDao libraryDao;   
   
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		libraryDao = new LibraryDao();
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// All the implementations to handle all the methods.
		
		String action = request.getServletPath();
		
		switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
				
			case "/insert":
			try {
				insertLibrary(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
				
			case "/delete":
			try {
				deleteLibrary(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
				
			case "/edit":
			try {
				showEditForm(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
				
			case "/update":
			try {
				updateLibrary(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
				
			default:
			try {
				listLibrary(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
		}
	}
		
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// input form
		RequestDispatcher dispatcher = request.getRequestDispatcher("library-form.jsp");
		dispatcher.forward(request, response);
	}
		
	
	// Insert Library
	private void insertLibrary(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String title = request.getParameter("title");
		String artist = request.getParameter("artist");
		String category = request.getParameter("category");
		Library newLibrary = new Library(title, artist, category);
		
		libraryDao.insertLibrary(newLibrary);
		response.sendRedirect("list");
	}
	
	
	// Delete Library
	private void deleteLibrary(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			libraryDao.deleteLibrary(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// redirect to the list page
		response.sendRedirect("list");

	}
		
	
	// Edit Library
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		Library existingLibrary;
		try {
			existingLibrary = libraryDao.selectLibrary(id);
			// input form
			RequestDispatcher dispatcher = request.getRequestDispatcher("library-form.jsp");
			request.setAttribute("library", existingLibrary);
			dispatcher.forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		
	}
	
	
	
	// Update Library
	private void updateLibrary(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String artist = request.getParameter("artist");
		String category = request.getParameter("category");

		Library library = new Library(id, title, artist, category);
		libraryDao.updateLibrary(library);
		response.sendRedirect("list");
	}
	
	
	// Default Section
	private void listLibrary(HttpServletRequest request, HttpServletResponse response)	throws SQLException, IOException, ServletException {
		
		try {
			List<Library> listLibrary = libraryDao.selectAllLibraries();
			request.setAttribute("listLibrary", listLibrary);
			// list from
			RequestDispatcher dispatcher = request.getRequestDispatcher("library-list.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	
	}
	
	
	

	
		
}


