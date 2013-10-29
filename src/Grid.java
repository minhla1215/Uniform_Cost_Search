
public class Grid {
	State state;
	int cost;

	public Grid(State s, int c){
		state = s;
		cost = c;
	}
	
	public void setState(State s){
		state = s;
	}
	
	public State getState(){
		return state;
	}

	public void setCost(int c){
		cost = c;
	}
	
	public int getCost(){
		return cost ;
	}
}
