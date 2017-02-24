import java.util.LinkedList;
public class Snake{
	
	int size;
	LinkedList<Position> posi =new LinkedList<Position>();
	Direction direction;
	Snake(int size,LinkedList<Position> posi){
		this.size=size;
		this.posi=posi;
	}	
	public LinkedList<Position> pos(){
		return posi;
	}
	
	
}