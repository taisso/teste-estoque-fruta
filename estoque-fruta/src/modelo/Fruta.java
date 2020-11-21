package modelo;

public class Fruta{
	
	private Integer id;
	
	private String nome;
	private Integer quantidade;
	private Float preco;
	
	public Fruta() {
		
	}
	
	public Fruta(Integer id, String nome, Integer quantidade, Float preco) {
		this.id = id;
		this.nome = nome;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "Fruta [id=" + id + ", nome=" + nome + ", quantidade=" + quantidade + ", preco=" + preco + "]";
	}
	
	
	public static boolean validar(Fruta fr) {
		boolean  f1 = true, f2 = true, f3 = true;
		if(!fr.nome.matches("(?=.*[}{,.^?~=+\\-_\\/*\\-+.\\|])(?=.*[a-zA-Z])(?=.*[0-9]).{8,}")) {
			System.out.println("Nome inv�lido, n�o pode possuir caracteres especiais");
			f1 = false;
		}
		if(fr.quantidade < 0) {
			System.out.println("Quantidade inv�lida, quantidade n�o pode ser menor que ZERO");
			f2 = false;
		}	
		if(fr.preco < 0.0) {
			System.out.println("Pre�o inv�lido, pre�o n�o pode ser menor que ZERO");
			f3 = false;
		}
		if(!f1 && !f2 && !f3) return false;
		return true;
			
	}
	
	
	
	

}
