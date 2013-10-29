import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;


public class A1 {

	//input and output
	private static Scanner sc = null;
	private static PrintStream ps = null;
	private static String input = "input.txt";
	private static String output = "output.txt";
	
	private static int size = -1;
	private static int aX, aY, bX, bY;
	
	//used instead of hash since I am using Coordinate to access, need x and y rather than object
	private static Grid[][] grid = null;
	
	public A1(){
	}
	
	public static void main(String[] args) throws Exception{
		//Read in a file and set the size and initial positions of pieces
		sc = new Scanner(new BufferedReader(new FileReader(input)));
		try{
			size = sc.nextInt();
			aX = sc.nextInt();
			aY= sc.nextInt();
			bX= sc.nextInt();
			bY= sc.nextInt();
			sc.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		//set up file writing
		try{
			File file = new File(output);
			if (!file.exists()) {
				file.createNewFile();
			}
			ps = new PrintStream(new FileOutputStream(output));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		//Start the search
		grid = new Grid[size + 1][size + 1];
		State end = uniformCostSearch(new State(new Coordinate(aX,aY), new Coordinate(bX,bY), 0, new ArrayList<Coordinate>()));
		
		if(end == null){
			try{ // there's no solution
				ps.println("-1");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		else{
			//printing
			try{
				ps.println(end.getCost());
				for(int i = 0;i < end.getSteps().size();i++){
					ps.println(end.getSteps().get(i).getX() + " " + end.getSteps().get(i).getY() );
					//System.out.println(end.getSteps().get(i).getX() + " " + end.getSteps().get(i).getY() );
				}
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public static State uniformCostSearch(State root){
		grid[root.getA().getX()][root.getA().getY()] = new Grid(root,0);
		Comparator<State> comparator = new Comparator<State>(){
			public int compare(State a, State b){
				if(a.getCost() > b.getCost())
					return 1;
				else if(a.getCost() == b.getCost())
					return 0;
				else
					return -1;
			}

		};
		PriorityQueue <State> open = new PriorityQueue<State>(10, comparator);
		open.add(root);
		do{
			if(open.isEmpty())
				return null;
			State current = open.poll();
			if(current.goalCheck())
				return current;
			ArrayList<State> children = expand(current);
			for(State child:children){
				if(grid[child.getA().getX()][child.getA().getY()] == null){
					open.add(child);
					grid[child.getA().getX()][child.getA().getY()] = new Grid(child,child.getCost());
				}
				if(child.getCost() < grid[child.getA().getX()][child.getA().getY()].getCost()){
					open.remove(grid[child.getA().getX()][child.getA().getY()].getState());
					open.add(child);
					grid[child.getA().getX()][child.getA().getY()] = new Grid(child,child.getCost());
				}				
			}	
		}
		while(true);
	}
	
	
	public static ArrayList<State> expand(State s){
		ArrayList<State> children = new ArrayList<State>();
		if(s.getA().getX() > 1 && s.getB().getY() < size){ // A move up, B move right
			State temp = new State(s);
			temp.getA().setX(temp.getA().getX() - 1);
			temp.getB().setY(temp.getB().getY() + 1);
			temp.setCost(s.getCost() + 3);
			temp.getSteps().add(new Coordinate(temp.getA().getX(),temp.getA().getY()));
			children.add(temp);
		}
		if(s.getA().getY() > 1 && s.getB().getX() > 1){ // A move left, B move up
			State temp = new State(s);
			temp.getA().setY(temp.getA().getY() - 1);
			temp.getB().setX(temp.getB().getX() - 1);
			temp.setCost(s.getCost() + 4);
			temp.getSteps().add(new Coordinate(temp.getA().getX(),temp.getA().getY()));
			children.add(temp);	
		}
		if(s.getA().getY() < size && s.getB().getX() < size ){ // A move right, B move down
			State temp = new State(s);
			temp.getA().setY(temp.getA().getY() + 1);
			temp.getB().setX(temp.getB().getX() + 1);
			temp.setCost(s.getCost() + 5);
			temp.getSteps().add(new Coordinate(temp.getA().getX(),temp.getA().getY()));
			children.add(temp);
		}
		if(s.getA().getX() < size && s.getB().getY() > 1){ // A move down, B move left
			State temp = new State(s);
			temp.getA().setX(temp.getA().getX() + 1);
			temp.getB().setY(temp.getB().getY() - 1);
			temp.setCost(s.getCost() + 6);
			temp.getSteps().add(new Coordinate(temp.getA().getX(),temp.getA().getY()));
			children.add(temp);
		}
		return children;
	}
}


