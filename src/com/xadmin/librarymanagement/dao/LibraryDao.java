package com.xadmin.librarymanagement.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.librarymanagement.bean.Library;



public class LibraryDao {
	
	
	// Changing the name of the db
	private String jdbcURL = "jdbc:mysql://localhost:3306/musicdb?useSSL=false";
	private String jdbcUsername = "root";	
	private String jdbcPassword = "root123";
	private String jdbcDriver = "com.mysql.jdbc.Driver";

	private static final String INSERT_LIBRARY_SQL = "INSERT INTO songs" + "  (title, artist, category) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_LIBRARY_BY_ID = "select id,title,artist,category from songs where id =?";
	private static final String SELECT_ALL_LIBRARY = "select * from songs";
	private static final String DELETE_LIBRARY_SQL = "delete from songs where id = ?;";
	private static final String UPDATE_LIBRARY_SQL = "update songs set title = ?,artist= ?, category =? where id = ?;";

	public LibraryDao() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
		 // Class.forName("com.mysql.jdbc.Driver");
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	
	
	// All CRUD Operations are given below:
	
	
	// Insert Library Method
	public void insertLibrary(Library library) throws SQLException {
		System.out.println(INSERT_LIBRARY_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LIBRARY_SQL)) {
			preparedStatement.setString(1, library.getTitle());
			preparedStatement.setString(2, library.getArtist());
			preparedStatement.setString(3, library.getCategory());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	
	
	
	// Select Library by ID Method
	public Library selectLibrary(int id) {
		Library library = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIBRARY_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String title = rs.getString("title");
				String artist = rs.getString("artist");
				String category = rs.getString("category");
				library = new Library(id, title, artist, category);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return library;
	}
	
	// Select all Libraries
	public List<Library> selectAllLibraries() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Library> libraries = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LIBRARY);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String artist = rs.getString("artist");
				String category = rs.getString("category");
				libraries.add(new Library(id, title, artist, category));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return libraries;
	}
	
	
	// Delete Library Method
	public boolean deleteLibrary(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_LIBRARY_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	
	// Update Library Method
	public boolean updateLibrary(Library library) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_LIBRARY_SQL);) {
			System.out.println("updated Library: "+statement);
			statement.setString(1, library.getTitle());
			statement.setString(2, library.getArtist());
			statement.setString(3, library.getCategory());
			statement.setInt(4, library.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	
	// Printing the SQL Exception
	private void printSQLException(SQLException ex) {
		for (Throwable e: ex) {
			if(e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
				
			}
		}
	}
	
}
