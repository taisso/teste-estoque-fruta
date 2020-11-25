package Teste;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Fruta;

class CasosTestes {
	
	Fruta fr;

	@BeforeEach
	void setUp() throws Exception {
		fr = new Fruta();
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
