
public abstract class ItemMapa {
	
	/*
	 * Classe que representa um item generico do mapa
	 * Um item no mapa pode ser:
	 *  - Pedra
	 *  - Espaco vazio
	 *  - Heroi
	 *  - Fantasma
	 * 
	 * Essa classe possui todos os atributos que sao comuns a um item do mapa
	 */
	
	// -- representacao do item no mapa --
	public char display;
	// -- posicao X do item no mapa --
	private int posicaoX;
	// -- posicao Y do item no mapa --
	private int posicaoY;
	
	ItemMapa(int posicaoX, int posicaoY){
		this.posicaoX = posicaoX;
		this.posicaoY = posicaoY;
	}
	
	public int getPosicaoX() {
		return posicaoX;
	}
	
	public void setPosicaoX(int posicaoX) {
		this.posicaoX = posicaoX;
	}
	
	public int getPosicaoY() {
		return posicaoY;
	}
	
	public void setPosicaoY(int posicaoY) {
		this.posicaoY = posicaoY;
	}
	
	public char getDisplay(){
		return display;
	}
	
	// -- metodo utilizado para determinar o que acontece quando algum objeto encosta em outro, eh abstrato pois apesar de ser comum para todos os objeto, possui um comportamento diferente para cada um deles --
	public abstract Resultado encostou(ItemMapa item);

}
