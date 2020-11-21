package aplicacao;

import java.util.Locale;
import java.util.Scanner;

import modelo.Fruta;

public class Programa {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int opcao = 0;
		Locale.setDefault(Locale.US);
		Fruta fr = new Fruta();

		while (opcao != 3) {
			
			try {

			System.out.println("1 - PARA CADASTRAR NOVO PRODUTO");
			System.out.println("2 - REALIZAR COMPRA");
			System.out.println("3 - SAIR\n");
			System.out.print("INFORME A OP��O: ");
			opcao = sc.nextInt();

			sc.nextLine();
			switch (opcao) {
				case 1: {
				
					System.out.print("INFORME O NOME DA NOVA FRUTA: ");
					fr.setNome(sc.nextLine());
					
					System.out.print("INFORME A QUANTIDADE: ");
					fr.setQuantidade(sc.nextInt());
					
					System.out.print("INFORME QUAL O PRE�O: ");
					fr.setPreco(sc.nextFloat());
					
					System.out.println();
					break;
				}
	
				case 2: {
					System.out.println("INFORME O NOME DA FRUTA: ");
					System.out.println("INFORME A QUANTIDADE QUE DESEJA: ");
	
					break;
	
				}
				case 3: {
					System.out.println("VOC� SAIU");
					
					break;
	
				}
				default: {
					System.out.println("NENHUMA DA OP��ES");
				}

			}
			
			}catch(IllegalArgumentException e) {
				System.out.println("\nErro no argmento: " + e.getMessage());
			}

		}
		System.out.println(fr);
		sc.close();

	}

}