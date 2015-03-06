import java.util.ArrayList;
import java.util.Scanner;


public class Jogo {
	
	// -- mapa da tela, o mapa do jogo sera construido com base nesse mapa, # representa parede e ' ' representa espaco vazio --
	char [][] mapeamentoTela = {
			{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
			{'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
			{'#', ' ', '#', '#', ' ', '#', '#', '#', ' ', '#', ' ', '#', '#', '#', ' ', '#', '#', ' ', '#'},
			{'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
			{'#', ' ', '#', '#', ' ', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', ' ', '#', '#', ' ', '#'},
			{'#', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', '#'},
			{'#', '#', '#', '#', ' ', '#', '#', '#', ' ', '#', ' ', '#', '#', '#', ' ', '#', '#', '#', '#'},
			{'#', ' ', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#', ' ', ' ', '#'},
			{'#', '#', '#', '#', ' ', '#', ' ', '#', '#', ' ', '#', '#', ' ', '#', ' ', '#', '#', '#', '#'},
			{'#', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
			{'#', '#', '#', '#', ' ', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', ' ', '#', '#', '#', '#'},
			{'#', ' ', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#', ' ', ' ', '#'},
			{'#', '#', '#', '#', ' ', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', ' ', '#', '#', '#', '#'},
			{'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
			{'#', ' ', '#', '#', ' ', '#', '#', '#', ' ', '#', ' ', '#', '#', '#', ' ', '#', '#', ' ', '#'},
			{'#', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', '#'},
			{'#', ' ', ' ', '#', ' ', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', ' ', '#', ' ', ' ', '#'},
			{'#', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', '#'},
			{'#', ' ', '#', '#', '#', '#', '#', '#', ' ', '#', ' ', '#', '#', '#', '#', '#', '#', ' ', '#'},
			{'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
			{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
	};
	
	// -- array de ItemMapas que representa a tela do jogo --
	ItemMapa[][] tela;
	
	// -- array para armazenar os personagens --
	ArrayList<Personagem> jogadores;
	
	Jogo(){
		jogadores = new ArrayList<Personagem>();
		
		// -- cria o heroi --
		Personagem heroi = new Heroi(15, 9);
		jogadores.add(heroi);
				
		// -- cria os fantasmas --
		Personagem fantasma1 = new Fantasma(9, 8);
		jogadores.add(fantasma1);
		Personagem fantasma2 = new Fantasma(9, 9);
		jogadores.add(fantasma2);
		Personagem fantasma3 = new Fantasma(9, 10);
		jogadores.add(fantasma3);
		
		// -- atualiza a tela com as posicoes dos personagens --
		atualizaTela();
	}
	
	public void rodada(){
		// -- looping principal do jogo --
		while(true){
			// -- imprime a tela para o usuario --
			imprimeTela();
			// -- move os jogadores --
			for(int i=0; i<jogadores.size(); i++){
				moverPersonagem(jogadores.get(i));
				// -- atualiza a tela com as posicoes dos personagens --
				atualizaTela();
			}
			
			// -- verifica se ainda ha fantasmas vivos --
			boolean fantasmaVivo = false;
			for(Personagem p : jogadores) if(p instanceof Fantasma) fantasmaVivo = true;
			// -- se nao ha nenhum fantasma vivo, termina o jogo --
			if(!fantasmaVivo) break; 
		}
		terminaJogo(true);
	}
	
	public void atualizaTela(){
		// -- constroi a tela do jogo baseando no mapa --
		tela = new ItemMapa[mapeamentoTela.length][];
		for(int i=0; i<mapeamentoTela.length; i++){
			tela[i] = new ItemMapa[mapeamentoTela[i].length];
			for(int j=0; j<mapeamentoTela[i].length; j++){
				// -- se no mapa estiver uma parede, cria uma nova instancia de parede naquela posicao --
				if(mapeamentoTela[i][j] == '#') tela[i][j] = new Parede(i, j);
				// -- se nao, cria um novo espaco vazio --
				else tela[i][j] = new Vazio(i, j);
			}
		}
		
		// -- coloca os personagens no mapa --
		for(Personagem p : jogadores){
			tela[p.getPosicaoX()][p.getPosicaoY()] = p;
		}
	}
	
	public void moverPersonagem(Personagem personagem){
		// -- pega a posicao atual do personagem --
		int posicaoX = personagem.getPosicaoX();
		int posicaoY = personagem.getPosicaoY();
		
		// -- pega a direcao para a qual ele quer se mover --
		char direcao = personagem.mover();

		switch(direcao){
			// -- mover pra cima = incrementar x --
			case 's': if(posicaoX < 21) posicaoX++;
				break;
			// -- mover pra esquerda = decrementar y --
			case 'a': if(posicaoY > 0) posicaoY--;
				break;
			// -- mover pra baixo = decrementar x --
			case 'w': if(posicaoX > 0) posicaoX--;
				break;
			// -- mover pra direita = incrementar y --
			case 'd': if(posicaoY < 19) posicaoY++;
				break;
		}
		
		// -- o que tem na posicao para onde o personagem esta se movendo? --
		ItemMapa novaPosicao = tela[posicaoX][posicaoY];
		
		// -- qual eh a resposta dessa nova posicao para o objeto que encostou nela? --
		Resultado resultado = novaPosicao.encostou(personagem);
		
		// -- switch dependendo da resposta da nova posicao --	
		switch(resultado){
			case MORRO: {
				// -- se o personagem que morreu foi o heroi, acaba o jogo --
				if(novaPosicao instanceof Heroi){
					terminaJogo(false);
				}
				
				// -- atualiza a posicao do personagem no mapa --
				personagem.setPosicaoX(posicaoX);
				personagem.setPosicaoY(posicaoY);
				
				// -- remove do array de jogadores o jogador que morreu --
				jogadores.remove(novaPosicao);
			}
				break;
			case MATO: {
				// -- remove do array de jogadores o jogador que morreu --
				jogadores.remove(personagem);
			}
				break;
			case VAZIO: {
				// -- atualiza a posicao do personagem no mapa --
				personagem.setPosicaoX(posicaoX);
				personagem.setPosicaoY(posicaoY);
			}
				break;
			case BLOQUEIO: {
				// -- nao acontece nada, personagem nao pode se mover para essa posicao --
			}
				break;
			default: System.out.println("Comando nao reconhecido"); terminaJogo(false);
		}
		
	}
	
	public void terminaJogo(boolean ganhou){
		if(ganhou){
			System.out.println("=============== GANHOU ===============");
		}else{
			System.out.println("=============== PERDEU ===============");
		}
		System.exit(0);
	}
	
	public void imprimeTela(){
		for(int i=0; i<tela.length; i++){
			for(int j=0; j<tela[i].length; j++){
				System.out.print(tela[i][j].getDisplay() + " ");
			}
			System.out.println();
		}
	}

}
