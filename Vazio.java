
public class Vazio extends ItemMapa {
	
	/*
	 * Classe que representa um espaco vazio no mapa
	 */

	Vazio(int posicaoX, int posicaoY) {
		super(posicaoX, posicaoY);
		super.display = ' ';
	}

	@Override
	public Resultado encostou(ItemMapa item) {
		// -- para todos os casos retorna move pois o espaco esta vazio --
		return Resultado.VAZIO;
	}
	
	

}
