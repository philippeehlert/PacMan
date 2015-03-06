import java.util.Scanner;


public class Heroi extends Personagem {
	
	/*
	 * Classe que representa o Heroi no jogo
	 */
	
	Heroi(int posicaoX, int posicaoY) {
		super(posicaoX, posicaoY);
		super.display = '0';
	}
	
	
	// -- metodo mover pega a entrada do usuario --
	@Override
	public char mover(){
		Scanner scan = new Scanner(System.in);
		
		char direcao = ' ';
		boolean entradaInvalida = true;
		
		while(entradaInvalida){
			try{
				System.out.println("Digite uma direcao (w, a, s, d): ");
				direcao = scan.nextLine().charAt(0);
				entradaInvalida = false;
			}catch(java.lang.StringIndexOutOfBoundsException e){
				System.out.println("Você deve digitar uma direcao!");
				entradaInvalida = true;
			}
			if(!(direcao == 'w' || direcao == 'a' || direcao == 's' || direcao == 'd')){
				entradaInvalida = true;
			}
		}
		
		return direcao;
	}


	@Override
	public Resultado encostou(ItemMapa p) {
		// -- fantasma encostou no heroi, heroi morre --
		if(p instanceof Fantasma) return Resultado.MORRO;
		
		return null;
	}
	
}
