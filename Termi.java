import java.util.*;
import com.googlecode.lanterna.input.*;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.googlecode.lanterna.TerminalFacade;

public class Termi {
	Terminal term;
	LinkedList<Position> barriers = new LinkedList<Position>();
	LinkedList<Position> obstacle = new LinkedList<Position>();
	LinkedList<Position> ini = new LinkedList<Position>();
	LinkedList<Position> pos = new LinkedList<Position>();

	Position head = new Position(10, 10);
	Position food;

	String difi = "Choose Dificulty";
	String easy = "Easy     <-";
	String med = "Medium      ";
	String hard = "Hard       ";
	String pausa2 = "BOM CAFE";
	String pausa = "PAUSA";
	String over = "Game Over";
	String restart1 = "Press Enter for Restart";
	String exit = "Press Esc for Exit";
	String point2;

	int dificulty2 = 1;
	int dificulty = 0;
	int obstacles = 0;
	int point = 0;
	int times = 0;
	int size = 5;
	int f = 0;
	int eaten =0;
	char[] Array;
	char[] point3;
	boolean dirty;

	


	

	public Termi(Terminal term) {
		this.term=term;
		
		
		TerminalSize termSize = term.getTerminalSize();

		Position terminal = new Position(termSize.getColumns() - 1,
				termSize.getRows() - 1);
		Game game = new Game(terminal);
		byte right = 0;
		byte left = 1;
		byte down = 2;
		byte up = 3;

		Position[] directions = new Position[] { new Position(1, 0), // right
				new Position(-1, 0), // left
				new Position(0, 1), // down
				new Position(0, -1), // up
		};
		term.applyForegroundColor(Terminal.Color.CYAN);
		term.setCursorVisible(false);
		int direction = right;

		while (dificulty == 0) {
			
			term.moveCursor(((termSize.getColumns()) / 2 - 7),
					((termSize.getRows()) / 2 - 8));
			putString(difi);
			
			term.moveCursor(((termSize.getColumns()) / 2 - 4),
					(termSize.getRows()) / 2 - 6);
			putString(easy);

			term.moveCursor(((termSize.getColumns()) / 2 - 4),
					(termSize.getRows()) / 2 - 4);
			putString(med);
			
			term.moveCursor(((termSize.getColumns()) / 2 - 4),
					(termSize.getRows()) / 2 - 2);
			putString(hard);
			
			dirty = true;
			while (dirty == true) {
				Key a = term.readInput();
				if (a != null) {
					if (a.getKind() == Key.Kind.Enter) {
						dificulty = dificulty2;
						dirty = false;
					} else if (a.getKind() == Key.Kind.Escape) {
						term.exitPrivateMode();
						return;
					} else if (a.getKind() == Key.Kind.ArrowDown && times == 0) {
						easy = "Easy          ";
						med = "Medium   <-";
						times = 1;
						dificulty2 = 2;
						dirty = false;
					} else if (a.getKind() == Key.Kind.ArrowUp && times == 0) {
						easy = "Easy         ";
						hard = "Hard     <-";
						times = 2;
						dificulty2 = 3;
						dirty = false;
					} else if (a.getKind() == Key.Kind.ArrowDown && times == 1) {
						med = "Medium        ";
						hard = "Hard     <-";
						times = 2;
						dificulty2 = 3;
						dirty = false;
					} else if (a.getKind() == Key.Kind.ArrowUp && times == 1) {
						easy = "Easy     <-";
						med = "Medium     ";
						times = 0;
						dificulty2 = 1;
						dirty = false;
					}

					else if (a.getKind() == Key.Kind.ArrowDown && times == 2) {
						easy = "Easy     <-";
						hard = "Hard       ";
						times = 0;
						dificulty2 = 1;

						dirty = false;
					} else if (a.getKind() == Key.Kind.ArrowUp && times == 2) {
						med = "Medium   <-";
						hard = "Hard        ";
						times = 1;
						dificulty2 = 2;
						dirty = false;
					}
				}
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException ie) {
				ie.printStackTrace();

			}

		}

		term.clearScreen();
		term.applyForegroundColor(Terminal.Color.CYAN);

		// Cria os obstaculos do jogo
		obstacle = game.obstacles();
		for (int i = 0; i < obstacle.size() - 1; i++) {
			term.moveCursor(obstacle.get(i).point_x, obstacle.get(i).point_y);
			term.putCharacter('#');
		}
		// Cria as barreiras exteriores
		barriers = game.showBarriers();
		for (int i = 0; i < barriers.size() - 1; i++) {
			term.moveCursor(barriers.get(i).point_x, barriers.get(i).point_y);
			term.putCharacter('#');
		}

		// Cria a comida
		food = game.showFood();
		for (int l = 0; l <= obstacle.size() - 1; l++) {
			if (food.point_x == obstacle.get(l).point_x
					&& food.point_y == obstacle.get(l).point_y) {
				food = game.showFood();
				l = 0;
			}
		}
		term.applyForegroundColor(Terminal.Color.GREEN);
		term.applySGR(Terminal.SGR.ENTER_BOLD);
		term.moveCursor(food.point_x, food.point_y);
		if(eaten%5==0){
		term.putCharacter('o');
		eaten++;}
		else{
			term.putCharacter('@');
			eaten++;}
		term.applySGR(Terminal.SGR.EXIT_BOLD);

		// Verifica se a comida não esta na mesma posição que os obstaculos
		for (int k = 0; k <= obstacle.size() - 1; k++) {
			if (food.point_x == obstacle.get(k).point_x
					&& food.point_y == obstacle.get(k).point_y) {
				food = game.showFood();
				k = 0;
				k = 0;

			}
		}

		// escrever a pontuação
		point2 = Integer.toString(point);
		term.applyForegroundColor(Terminal.Color.GREEN);
		int s = point2.length() - 1;
		int j = terminal.point_x - 1;
		term.moveCursor(j - 20, 0);
		String point3 = "POINTS:";
		putString(point3);
		
		for (int l = 0; l < 10 - s; l++) {
			term.putCharacter(' ');
		}
		
		putString(point2);
		term.putCharacter(' ');
		term.putCharacter(' ');

		// Cria e desenha a cobra inicial
		term.applyForegroundColor(Terminal.Color.BLUE);
		pos = game.iniSnake(size);
		Snake snake1 = new Snake(size, pos);
		head = show(snake1, head);

		// ler movimentos da Snake
		while (true) {
			Key k = term.readInput();
			if (k != null) {
				switch (k.getKind()) {
				case Escape:
					term.exitPrivateMode();
					return;
				case ArrowLeft:
					if (direction != right)
						direction = left;
					break;
				case ArrowRight:
					if (direction != left)
						direction = right;
					break;
				case ArrowDown:
					if (direction != up)
						direction = down;
					break;
				case ArrowUp:
					if (direction != down)
						direction = up;
					break;
				case Enter:
					// Faz Pausa no jogo
					term.clearScreen();
					term.applyForegroundColor(Terminal.Color.WHITE);
					term.moveCursor((terminal.point_x / 2),
							((terminal.point_y) / 2));
					putString(pausa);
					
					while (true) {
						Key l = term.readInput();
						if (l != null) {
							if (l.getKind() == Key.Kind.Enter)
								break;
						}
					}
					term.clearScreen();
					// Desenha as barreiras novamente apos a pausa
					term.applyForegroundColor(Terminal.Color.CYAN);
					for (int i = 0; i < barriers.size(); i++) {
						term.moveCursor(barriers.get(i).point_x,
								barriers.get(i).point_y);
						term.putCharacter('#');
					}
					// Desenha a comidaapos a pausa
					term.applyForegroundColor(Terminal.Color.GREEN);
					term.applySGR(Terminal.SGR.ENTER_BOLD);
					term.moveCursor(food.point_x, food.point_y);
					if(eaten%5==0){
						term.putCharacter('o');
						}
						else{
							term.putCharacter('@');
							}eaten++;
					term.applySGR(Terminal.SGR.EXIT_BOLD);
					term.applyForegroundColor(Terminal.Color.CYAN);
					obstacle = game.obstacles();
					for (int i = 0; i < obstacle.size() - 1; i++) {
						term.moveCursor(obstacle.get(i).point_x,
								obstacle.get(i).point_y);
						term.putCharacter('#');
					}
				}

			
				
				// Escreve a pontuação apos a pausa				
				point2 = Integer.toString(point);
				term.applyForegroundColor(Terminal.Color.GREEN);
				s = point2.length() - 1;
				j = terminal.point_x - 1;
				term.moveCursor(j - 20, 0);
				point3 = "POINTS:";
				putString(point3);
				
				for (int l = 0; l < 10 - s; l++) {
					term.putCharacter(' ');
				}
				
				putString(point2);
				term.putCharacter(' ');
				term.putCharacter(' ');
			}
			// Escreve os obstaculos

			Snake snake = new Snake(size, pos);

			Position nextDirection = directions[direction];

			Position newhead = new Position(head.point_x
					+ nextDirection.point_x, head.point_y
					+ nextDirection.point_y);

			// verifica se a nova cabeça está na posiçao da comida
			if (newhead.point_x == food.point_x
					&& newhead.point_y == food.point_y) {
				
				food = (game.showFood());
				// verifica se a nova comida esta nos obstaculos
				for (int l = 0; l <= obstacle.size() - 1; l++) {
					if (food.point_x == obstacle.get(l).point_x
							&& food.point_y == obstacle.get(l).point_y) {
						food = game.showFood();
						l = 0;
					}
				}
				term.applyForegroundColor(Terminal.Color.GREEN);
				term.applySGR(Terminal.SGR.ENTER_BOLD);
				term.moveCursor(food.point_x, food.point_y);
				if(eaten%5==0){
					term.putCharacter('o');
					point += 10;
					}
					else{
						term.putCharacter('@');
						point += 50;
						}eaten++;
				term.applySGR(Terminal.SGR.EXIT_BOLD);
					Position tail = new Position(snake.posi.getLast().point_x
							+ nextDirection.point_x,
							snake.posi.getLast().point_y
									+ nextDirection.point_y);
					
					snake.posi.addLast(tail);
					
					//renova a pontuação após comer
					point2 = Integer.toString(point);
					term.applyForegroundColor(Terminal.Color.GREEN);
					s = point2.length() - 1;
					j = terminal.point_x - 1;
					term.moveCursor(j - 20, 0);
					point3 = "POINTS:";
					putString(point3);
					
					for (int t = 0; t < 10 - s; t++) {
						term.putCharacter(' ');
					}
					putString(point2);
					term.putCharacter(' ');
					term.putCharacter(' ');
				}

			term.applyForegroundColor(Terminal.Color.BLUE);

			for (int i = 0; i <= snake.posi.size() - 1; i++) {
				if (newhead.point_x == snake.posi.get(i).point_x
						&& newhead.point_y == snake.posi.get(i).point_y) {
					f = 1;
				}
			}
			for (int i = 0; i <= obstacle.size() - 1; i++) {
				if (newhead.point_x == obstacle.get(i).point_x
						&& newhead.point_y == obstacle.get(i).point_y) {
					obstacles = 1;
				}
			}
			if (newhead.point_x >= termSize.getColumns() - 2
					|| newhead.point_y >= termSize.getRows() - 1
					|| newhead.point_x <= 0 || newhead.point_y <= 0 || f == 1
					|| obstacles == 1) {
				term.clearScreen();
				term.applyForegroundColor(Terminal.Color.CYAN);
				game.showBarriers();
				while (true) {
					term.moveCursor((termSize.getColumns()) / 2 - 9,
							(termSize.getRows()) / 2 - 2);
					putString(over);
					
					term.moveCursor(((termSize.getColumns()) / 2 - 15),
							((termSize.getRows()) / 2 - 2) + 1);
					putString(restart1);
					
					term.moveCursor(((termSize.getColumns()) / 2 - 15),
							((termSize.getRows()) / 2 - 2) + 2);
					putString(exit);
					Key a = term.readInput();
					if (a != null && a.getKind() == Key.Kind.Escape) {
						term.exitPrivateMode();
						return;
					} else if (a != null && a.getKind() == Key.Kind.Enter) {
						term.clearScreen();
						new Termi(term);
					}
				}
			}
			term.applyForegroundColor(Terminal.Color.BLUE);
			head = show(snake, newhead);

			try {

				if (dificulty == 1)
					Thread.sleep(100);
				if (dificulty == 2)
					Thread.sleep(80);
				if (dificulty == 3)
					Thread.sleep(60);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}

	public Position show(Snake str, Position newHead) {
		pos.addFirst(newHead);
		Position lel = pos.removeLast();
		term.moveCursor(lel.point_x, lel.point_y);
		term.putCharacter(' ');
		for (int i = str.size - 1; i > 0; i--) {
			term.moveCursor(str.posi.get(i).point_x, str.posi.get(i).point_y);
			term.putCharacter('O');
		}
		term.moveCursor(str.posi.get(0).point_x, str.posi.get(0).point_y);
		term.putCharacter('Q');
		return newHead;
	}
	public void putString(String printf ){
		char[] Array = printf.toCharArray();
		for (int i = 0; i <= Array.length - 1; i++) {
			term.putCharacter((char) Array[i]);
		}
		}
	}
