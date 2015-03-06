import java.util.Random;


public class Fantasma extends Personagem {

	/*
	 * Classe que representa um fantasma no jogo
	 */
	
	Fantasma(int posicaoX, int posicaoY) {
		super(posicaoX, posicaoY);
		super.display = '@';
	}

	// -- metodo mover sorteia uma das direcoes --
	@Override
	public char mover() {
		char[] direcoes = {'w', 'a', 's', 'd'};
		Random random = new Random();
		return direcoes[random.nextInt(3)];
	}

	@Override
	public Resultado encostou(ItemMapa item) {
		// -- heroi encostou no fantasma, fantasma morre --
		if(item instanceof Heroi) return Resultado.MORRO;
		
		// -- fantasma encostou em fantasma, nada acontece, personagem nao pode se mover --
		if(item instanceof Fantasma) return Resultado.BLOQUEIO;
		
		return null;
	}

}
