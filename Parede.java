
public class Parede extends ItemMapa {
	
	/*
	 * Classe que representa uma parede no mapa
	 */

	Parede(int posicaoX, int posicaoY) {
		super(posicaoX, posicaoY);
		super.display = '#';
	}

	@Override
	public Resultado encostou(ItemMapa item) {
		// -- para todos os casos retorna nao move pois nenhum personagem ultrapassa a parede --
		return Resultado.BLOQUEIO;
	}
	
}
