import java.util.*;
public class Game {
	Position terminal;
	
	public Game(Position terminal){this.terminal=terminal;}
	
	
	
	public LinkedList<Position> iniSnake(int size) {
		LinkedList<Position> pos = new LinkedList<Position>();
		int cursor_x = 9, cursor_y = 10;
		for (int i = 0; i < size; i++) {
			Position q = new Position(cursor_x, cursor_y);
			pos.addLast(q);
			cursor_x--;
		}
		return pos;
	}

	public Position showFood() {
		Random rnd = new Random();
		Random bnd = new Random();
		int d, f;
		d = (rnd.nextInt(terminal.point_y - 2)) + 1;
		f = (bnd.nextInt(terminal.point_x - 2)) + 1;
		Position food = new Position(f, d);
		return food;
	}

	public LinkedList<Position> showBarriers() {
		LinkedList<Position> barriers = new LinkedList<Position>();
		Position b;

		for (int i = 0; i < terminal.point_x; i++) {
			b = new Position(i, 0);
			barriers.add(b);
		}

		for (int i = 0; i < terminal.point_y; i++) {
			b = new Position(terminal.point_x, i);
			barriers.add(b);
		}
		for (int i = 0; i < terminal.point_y; i++) {
			b = new Position(0, i);
			barriers.add(b);
		}
		for (int i = 0; i < terminal.point_x; i++) {
			b = new Position(i, terminal.point_y);
			barriers.add(b);
		}
		return barriers;
	}

	public LinkedList<Position> obstacles() {
		LinkedList<Position> obstacle = new LinkedList<Position>();
		Position b;
		// Centro Vertica
		for (int i = 8; i < terminal.point_y; i++) {
			for (int j = 10; j <= terminal.point_y; j++) {
				if (i == (terminal.point_y - j)) {
					b = new Position(terminal.point_x / 2, i);
					obstacle.add(b);
				}
			}
		}
		// Direita Vertical Cima Direita
		for (int i = 4; i < terminal.point_y - 1; i++) {
			for (int j = 22; j <= terminal.point_y - 1; j++) {
				if (i == (terminal.point_y - j)) {
					b = new Position(90, i);
					obstacle.add(b);
				}
			}
		}
		// Direita Vertical Cima Esquerda
		for (int i = 4; i < terminal.point_y - 1; i++) {
			for (int j = 22; j <= terminal.point_y - 1; j++) {
				if (i == (terminal.point_y - j)) {
					b = new Position(79, i);
					obstacle.add(b);
				}
			}
		}

		// Esquerda Diagonal Baixo Direita
		for (int i = 21; i < terminal.point_y; i++) {
			for (int j = 4; j <= terminal.point_y; j++) {
				if (i == (terminal.point_y - j)) {
					b = new Position(i, i);
					obstacle.add(b);
				}
			}
		}
		// Esquerda Diagonal Baixo Esquerda
		for (int i = 21; i < terminal.point_y; i++) {
			for (int j = 4; j <= terminal.point_y; j++) {
				if (i == (terminal.point_y - j)) {
					b = new Position(terminal.point_y - i, i);
					obstacle.add(b);
				}
			}
		}
		// Esquerda Diagonal Cima Esquerda
		for (int i = 4; i < terminal.point_y; i++) {
			for (int j = 21; j <= terminal.point_y; j++) {
				if (i == (terminal.point_y - j)) {
					b = new Position(i, i);
					obstacle.add(b);
				}
			}
		}
		// Esquerda Diagonal Cima Direita
		for (int i = 4; i < terminal.point_y; i++) {
			for (int j = 21; j <= terminal.point_y; j++) {
				if (i == (terminal.point_y - j)) {
					b = new Position(terminal.point_y - i, i);
					obstacle.add(b);
				}
			}
		}
		// Direita Vertical Baixo Esquerda
		for (int i = 21; i < terminal.point_y; i++) {
			for (int j = 5; j <= terminal.point_y; j++) {
				if (i == (terminal.point_y - j)) {
					b = new Position(79, i);
					obstacle.add(b);
				}
			}
		}
		// Direita Vertical Baixo Direita
		for (int i = 21; i < terminal.point_y; i++) {
			for (int j = 5; j <= terminal.point_y; j++) {
				if (i == (terminal.point_y - j)) {
					b = new Position(90, i);
					obstacle.add(b);
				}
			}
		}
		// Centro Horizontal
		for (int i = 40; i < terminal.point_x; i++) {
			for (int j = 40; j < terminal.point_x; j++) {
				if (i == (terminal.point_x - j)) {
					b = new Position(i, terminal.point_y / 2);
					obstacle.add(b);
				}
			}
		}
		// Esquerda horizontal Cima
		for (int i = 10; i < terminal.point_x; i++) {
			for (int j = 80; j < terminal.point_x; j++) {
				if (i == (terminal.point_x - j)) {
					b =new Position(i, terminal.point_y / 6);
					obstacle.add(b);
				}
			}
		}
		// Direita Horizonta Cimal
		for (int i = 80; i < terminal.point_x; i++) {
			for (int j = 10; j < terminal.point_x; j++) {
				if (i == (terminal.point_x-j)) {
					b = new Position(i, terminal.point_y / 6);
					obstacle.add(b);
				}
			}
		}
		// Direita Horizontal Baixo
		for (int i = 80; i < terminal.point_x; i++) {
			for (int j = 10; j < terminal.point_x; j++) {
				if (i == (terminal.point_x - j)) {
					b = new Position(i, terminal.point_y - terminal.point_y / 6);
					obstacle.add(b);
				}
			}
		}
		return obstacle;
	}

}

