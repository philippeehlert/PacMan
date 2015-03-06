
public abstract class Personagem extends ItemMapa {
	
	/*
	 * Classe que representa um Personagem do jogo
	 * Um personagem pode ser:
	 *  - Heroi
	 *  - Fantasma
	 */
	
	Personagem(int posicaoX, int posicaoY) {
		super(posicaoX, posicaoY);
	}
	
	/*
	 * Possui o metodo mover como abstrato pois apesar de esse metodo ser comum a todos
	 * os personagens, dependendo do tipo de personagem ha um comportamento diferente. 
	 */
	public abstract char mover();
	
}
