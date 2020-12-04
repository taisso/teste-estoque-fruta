package db;

public class DbIntegridade extends RuntimeException{

	private static final long serialVersionUID = 1L;

	
	public DbIntegridade(String msg) {
		super(msg);
	}
	
	
}