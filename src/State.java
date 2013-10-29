import java.util.ArrayList;


public class State {
	Coordinate A,B;
	int cost;
	ArrayList<Coordinate> steps;
	
	public State(Coordinate A, Coordinate B, int cost, ArrayList<Coordinate> steps){
		this.A = A;
		this.B = B;
		this.cost = cost;
		this.steps = steps;
		steps.add(new Coordinate(A.getX(),A.getY()));
	}
	
	public State(State s){
		this.A = new Coordinate(s.getA());
		this.B = new Coordinate(s.getB());
		this.cost = s.getCost();
		this.steps = new ArrayList<Coordinate>(s.getSteps());
	}
	
	public boolean goalCheck(){
		if(A.getX() == B.getX() && A.getY() == B.getY())
			return true;
		else 
			return false;
	}
	
	public Coordinate getA(){
		return A;
	}

	public void setA(Coordinate A){
		this.A = A;
	}
	
	public Coordinate getB(){
		return B;
	}
	
	public void setB(Coordinate B){
		this.B = B;
	}
	
	public int getCost(){
		return cost;
	}
	
	public void setCost(int cost){
		this.cost = cost;
	}

	public ArrayList<Coordinate> getSteps(){
		return steps;
	}

}
