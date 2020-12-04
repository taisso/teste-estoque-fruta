package Teste;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import modelo.Fruta;

class CasosTestes {
	

	@Test
	void ehStringtest() {
		assertEquals(true, Fruta.ehString("Ola")); //VERIFICA SE POSSUI ALGUM CARACTERE ESPECIAL
	}
	
	@Test
	void validaNum() {
		assertEquals(true, Fruta.validaNumero(-1.0F)); // VERIFICA SE ALGUM NÚMERO É ABAIXO DE ZERO
	}

}
