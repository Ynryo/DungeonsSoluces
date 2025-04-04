package sae.solver;

<<<<<<< HEAD
import sae.solver.GraphSoluce;
=======
import sae.graph.GraphSoluce;
>>>>>>> 8504583745afba01c325bd8cc852f3c83269ddaa
import sae.graph.Node;

public abstract class SolverGeneric implements Solver {

	private Node startingNode;
	private Node endingNode;
<<<<<<< HEAD
	
	private int steps;
	
	private GraphSoluce graphSoluce;

	public SolverGeneric(Node startingNode, Node endingNode) {
		this.startingNode = startingNode;
		this.endingNode = endingNode;
	}

	@Override
	public GraphSoluce getGraphSoluce() {
		return graphSoluce;
	}

	@Override
	public int getSteps() {
		return steps;
	}
	
	public void incSteps() {
		steps++;
	}

	public Node getStartingNode() {
		return startingNode;
	}

	public Node getEndingNode() {
		return endingNode;
	}

	@Override
	public void solve() {
		initializeResolution();
		resolve();
	}

	protected abstract void resolve();

=======

	private int steps;

	private GraphSoluce graphSoluce;

	public SolverGeneric(Node startingNode, Node endingNode) {
		this.startingNode = startingNode;
		this.endingNode = endingNode;
	}

	@Override
	public GraphSoluce getGraphSoluce() {
		return graphSoluce;
	}

	@Override
	public int getSteps() {
		return steps;
	}

	public void incSteps() {
		steps++;
	}

	public Node getStartingNode() {
		return startingNode;
	}

	public Node getEndingNode() {
		return endingNode;
	}

	@Override
	public void solve() {
		initializeResolution();
		resolve();
	}

	protected abstract void resolve();

>>>>>>> 8504583745afba01c325bd8cc852f3c83269ddaa
	private void initializeResolution() {
		steps = 0;
		graphSoluce = new GraphSoluce();
	}
<<<<<<< HEAD
	
	
	
=======

>>>>>>> 8504583745afba01c325bd8cc852f3c83269ddaa
}