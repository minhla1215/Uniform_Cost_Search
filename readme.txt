Name: Minh La
ID: 3545124759
Email: minht@usc.edu

Project Name: Uniform Cost Search - Stepping A onto B!

1. Brief description:
This project is made for USC AI 460. The program uses uniform cost search and the goal is find the steps needed for A to move onto B in a n-dimension grid. When A moves, B moves as well but different. For instance, A move up, B will move left, etc.

The structure of this program is broken down into Coordinates and State. The State contains where A and B is, the cost of the moves, and the list of previous moves. The States are stored into an 2-D array of size [n][n] of object Grid, which contains the State and the cost. The reason I am using the Grid is to store the previous cost, which I can access easily and through this I do not have to use open and closed queue. I can just have one queue that can see if the object is visted or not in the array.

The reason I am using an 2-D array instead of a hash map is because using a hashmap, I could not access the hash using coordinates, such are objects. Since I am making new coordinates for each child, it was not very successful in getting the hash since I has to attempt to get the compare the two different coordinates, while they are the same x and y. I feel like I could structure this better, but I decided to use a 2-D array and used the x and y to access.

Having the 2-D array, I can get the previous cost, thus I will know if the child or the state in the queue is different or not. The state in the 2-D array is always updated since I add/update the 2-D whenever I add a state to the queue.

In my A1, which has my main function, I have the uniform cost search, expanding, and file writing/reading.

2. How to compile:
using aludra/UNIX:
1. unzip the files
2. Use Filezilla or file transfer to put onto aludra/UNIX
3. go to file and call - 
      javac *.java
4. then enter
      java A1 
5. Open the output.txt to see the results.
Results contain:

cost
step 1
step 2
step 3

3. My answers to the questions:
 3-1. The state space for this problem is 4^d. The reason for this is that the branch has a maximum of 4 max, thus that is the worst case. D is depth, thus it is a arbitrary since we do not know the depth. But since the size is n^2, we could determine that the minimum depth is (n^2)/4 with the max d as n^2. But theoritically, there are invalid moves that A or B can do, so the max d is much lower than n^2.
 
 I would say that state space = 4^d. But given the explanations above, it could be between 4^((n^2)/4) and 4^((n^2)).  


 3-2. The problem given does not have a solution. Actually, i has a solution roughly 1/2 of the inputs. The reason for this is, since both A and B moves simultaneously, they would have an even number of steps, e.g. 0 2. No matter how their combination of moves each time, they either move a net worth of 0 or 2 steps. So you can safely state that as long as A and B are evenly distance away from each other, they should be able to step on each other. If they are odd, they would be next to each other. The equation I used to find if they can step each is: 

|Ax - Bx| + |Ay - By|

This would find if the distance from each other is even or odd. If it's odd, it's safe to assume that they cannot step on each other. 

This could be used to cut the algorithm in half by implementing it at the beginning of the project. If we find that it is odd, we can skip the search and save the run-time. This would cut our time if we were to execute this project many times in half.

