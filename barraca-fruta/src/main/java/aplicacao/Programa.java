package aplicacao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import db.DB;
import db.Querys;
import modelo.Fruta;

public class Programa {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		int opcao = 0;
		Fruta fr = new Fruta();

		Connection conn = null;
		PreparedStatement st = null;
		Statement st1 = null;
		ResultSet rs = null;

		try {

			conn = DB.getConnection();
			Querys.conn = conn;

			while (opcao != 4) {

				try {

					System.out.println("1 - REGISTRAR NOVO PRODUTO NO ESTOQUE");
					System.out.println("2 - REALIZAR COMPRA");
					System.out.println("3 - CONSULTAR O PRODUTO");
					System.out.println("4 - SAIR\n");
					System.out.print("INFORME A OP��O: ");
					opcao = sc.nextInt();

					sc.nextLine();
					switch (opcao) {
					case 1: {
						conn.setAutoCommit(false);

						System.out.print("INFORME O NOME DA NOVA FRUTA: ");
						fr.setNome(sc.nextLine());

						System.out.print("INFORME A QUANTIDADE: ");
						fr.setQuantidade(sc.nextInt());

						System.out.print("INFORME QUAL O PRE�O: ");
						fr.setPreco(sc.nextFloat());

						Querys.insert(fr, st);

						System.out.println();
						break;
					}

					case 2: {

						try {
							st1 = conn.createStatement();
							List<Fruta> frutas = new ArrayList<Fruta>();
							rs = Querys.find(st1);

							System.out.println("\nFRUTAS DISPON�VEIS:");
							while (rs.next()) {
								System.out.print("ID: " + rs.getInt("id") + ", ");
								System.out.print("NOME: " + rs.getString("nome") + ", ");
								System.out.print("QUANTIDADE: " + rs.getInt("quantidade") + ", ");
								System.out.println("PRE�O: " + String.format("%.2f", rs.getFloat("preco")));

								frutas.add(
										new Fruta(rs.getString("nome"), rs.getInt("quantidade"), rs.getFloat("preco")));
							}

							System.out.print("\nINFORME O ID DA FRUTA QUE DESEJA COMPRA: ");
							int id = sc.nextInt();

							if (Fruta.validaNumero((float) id)) {
								Fruta f = null;
								f = frutas.get(id - 1);

								if (f == null) {
									System.out.println("\nFRUTA INFORMADA N�O ENCONTRADA");
								} else {
										
									conn.setAutoCommit(false);
									System.out.println("\nENCONTRADO: " + f);
									System.out.print("INFORME A QUANTIDADE QUE DESEJA COMPRAR: ");
									int quantidade = sc.nextInt();
									
									if(f.getQuantidade() < quantidade)throw new IllegalArgumentException("FRUTA INFORMADA, N�O POSSUI MAIS NO ESTOQUE");

									float calcula = quantidade * f.getPreco();
									System.out.println("O CUSTO � DE: R$ " + calcula);
									Querys.update(quantidade, id, st);
									System.out.println("COMPRA FINALIZADA");

								}
							} else
								throw new IllegalArgumentException("VALOR INFORMADO N�O PODE SER MENOR QUE ZERO");

							System.out.println("\nPRESSIONE PARA CONTINUAR...");
							System.in.read();

						} catch (SQLException e) {
							e.printStackTrace();

						} catch (IOException e) {
							e.printStackTrace();
						}

						break;

					}
					case 3: {

						st1 = conn.createStatement();
						boolean isEmpty = true;

						System.out.print("INFORME O NOME: ");
						String nome = "'" + sc.nextLine() + "'";

						rs = st1.executeQuery("SELECT * FROM frutas where nome ilike " + nome);
						System.out.println("\nRESULTADO ENCONTRADO:");
						while (rs.next()) {
							System.out.print("ID: " + rs.getInt("id") + ", ");
							System.out.print("NOME: " + rs.getString("nome") + ", ");
							System.out.print("QUANTIDADE: " + rs.getInt("quantidade") + ", ");
							System.out.println("PRE�O: " + String.format("%.2f", rs.getFloat("preco")));
							isEmpty = false;
						}
						if (isEmpty)
							System.err.println("DADO INFORMADO N�O ENCONTRADO");
						System.out.println();
						break;

					}
					case 4: {

						opcao = 3;
						System.out.println("VOC� SAIU");

						break;

					}
					default: {
						System.out.println("NENHUMA DA OP��ES");
					}

					}

				} catch (IllegalArgumentException e) {
					System.err.println("\nErro no argmento: " + e.getMessage() + "\n");
					conn.rollback();
				}

			}

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (InputMismatchException e) {
			System.err.println("\nTIPO INFORMADO N�O CORRESPONDE AO TIPO DA VARI�VEL");
		} finally {
			sc.close();
			DB.fechaStatement(st1);
			DB.fechaStatement(st);
			DB.fechaConnection();

		}

	}

}