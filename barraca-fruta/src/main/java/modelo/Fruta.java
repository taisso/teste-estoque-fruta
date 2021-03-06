package modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fruta {

	private String nome;
	private Integer quantidade;
	private Float preco;

	public Fruta() {

	}

	public Fruta(String nome, Integer quantidade, Float preco) {
		this.nome = nome;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if(Fruta.ehString(nome)) {
			this.nome = nome;
		}
		else {
			throw new IllegalArgumentException("Nome n�o pode ter caractere especias\n");
		}
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		if (Fruta.validaNumero((float) quantidade)) {
			this.quantidade = quantidade;
		}
		else {
			throw new IllegalArgumentException("Quantidade n�o pode ser menor que zero\n");
		}
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		if (Fruta.validaNumero(preco)) {
			this.preco = preco;
		} else {
			throw new IllegalArgumentException("Pre�o n�o pode ser menor que zero\n");
		}

	}

	@Override
	public String toString() {
		return "FRUTA=" + nome + ", QUANTIDADE=" + quantidade + ", PRE�O=" + preco + "";
	}

	public static Boolean validaNumero(Float num) {
		return num >= 0.0 ? true : false;
	}

	public static Boolean ehString(String nome) {

		if (nome.isEmpty())
			return false;

		Pattern pattern = Pattern.compile("[!#@$%�&*0-9].*");
		Matcher matcher = pattern.matcher(nome);

		return matcher.find() ? false : true;
	}
	
	public static Boolean ehNumeroInteiro(String num) {
		  try {
		        Integer.parseInt(num); 
		        return true;
		    } catch (NumberFormatException ex) {
		        return false;
		    }
	}

}
