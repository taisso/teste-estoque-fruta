package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Fruta;

public class Querys {

	public static Connection conn;

	public static ResultSet find(Statement st) throws SQLException {
		st = conn.createStatement();

		return st.executeQuery("SELECT * FROM frutas");

	}

	public static void insert(Fruta fr, PreparedStatement st) throws SQLException {
		st = conn.prepareStatement("INSERT INTO frutas " + "(nome, quantidade, preco) " + "VALUES " + "(?, ?, ?)");

		st.setString(1, fr.getNome());
		st.setInt(2, fr.getQuantidade());
		st.setFloat(3, fr.getPreco());

		st.executeUpdate();

		conn.commit();
	}

	public static void update(int quantidade, int id, PreparedStatement st) throws SQLException {
		st = conn.prepareStatement("UPDATE frutas " + "SET quantidade = quantidade - ? " + "WHERE " + "(id = ?)");

		st.setInt(1, quantidade);
		st.setInt(2, id);

		st.executeUpdate();
		conn.commit();
	}
}
