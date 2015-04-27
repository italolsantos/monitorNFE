/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.monitor.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory
{
	public Connection conexao;
	private String host = "jdbc:sqlserver://ITALO-HP:1433;databaseName=FIATNET_PROD";

	public Connection pegaConexao(String usuario, String senha)
	{
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			conexao = DriverManager.getConnection(host, usuario, senha);
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Classe nao encontrada" + e.getMessage());
		}
		catch (SQLException e)
		{
			System.out.println("Erro de SQL" + e.getErrorCode() + e.getMessage());
		}

		return conexao;
	}
}

/*Connection conn = new ConexaoFactory().pegaConexao("usuario", "senha";

String sql = "SELECT * FROM tabela";

PreparedStatement ps = conn.prepareStatement(sql);

ResultSet rs = ps.executeQuery();
*/