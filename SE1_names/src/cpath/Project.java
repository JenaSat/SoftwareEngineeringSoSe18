package cpath;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * @author Jena Satkunarajan, 2965839, st116472@stud.uni-stuttgart.de
 * @author
 * @author
 * @author
 * 
 * 
 */
public class Project {

	private List<Workpackage> startNodes = new ArrayList<Workpackage>();
	private List<Workpackage> endNodes = new ArrayList<Workpackage>();
	private Set<Workpackage> criticalPathNodes = new HashSet<Workpackage>();
	private Set<Workpackage> allNodes = new HashSet<Workpackage>();
	private Stack<Workpackage> currentNodes = new Stack<Workpackage>();

	//the latest finish of any workpackage of the project (i.e. the project duration) 
	private int maxEF = 0;

	public List<Workpackage> getStartNodes() {
		return startNodes;
	}

	public void setStartNodes(List<Workpackage> startNodes) {
		this.startNodes = startNodes;
	}

	public Set<Workpackage> getCriticalPathNodes() {
		return criticalPathNodes;
	}

	public Set<Workpackage> getAllNodes() {
		return allNodes;
	}

	
	// TODO: Implementieren Sie diese Methode, so dass nach deren Aufruf
	// alle Werte im Graph gesetzt sind und die beiden verfuegbaren Sets
	// korrekt befuellt wurden. Veraendern Sie nicht die Workpackage-Klasse!
	// Zum Testen koennen Sie die beigefuegte Test-Klasse verwenden und
	// erweitern. Beachten Sie, dass ihre Implementierung generell
	// funktionieren und nicht nur dieses eine Problem loesen soll.
	public void computeCriticalPath() {
		resetLists();

		/*
		 * process starting nodes: set ES and EF, check for maxEF and push all
		 * successor nodes on the processing stack
		 * 
		 */
		for (Workpackage startNode : this.startNodes) {
			startNode.setEarliestStart(0);
			startNode.setEarliestFinish(startNode.getDuration());
			if (startNode.getEarliestFinish() > maxEF)
				maxEF = startNode.getEarliestFinish();
			this.currentNodes.addAll(startNode.getSuccessors());
		}

		/*
		 * depth-first processing of the successor nodes: set ES (for which the
		 * maxEF of all pred. is needed), EF, check for maxEF, push successors
		 * on stack OR (of no succ.) put it into the endnodes-list
		 */
		while (!this.currentNodes.isEmpty()) {
			Workpackage currNode = currentNodes.pop();
			currNode.setEarliestStart(this.getMaxEFOfPred(currNode));
			currNode.setEarliestFinish(currNode.getEarliestStart() + currNode.getDuration());
			if (currNode.getEarliestFinish() > maxEF)
				maxEF = currNode.getEarliestFinish();
			if (!currNode.getSuccessors().isEmpty()) {
				currentNodes.addAll(currNode.getSuccessors());
			} else {
				this.endNodes.add(currNode);
			}
		}

		/*
		 * after processing all the nodes in the graph, the maxEF is known: set
		 * LS (= maxEF), LF and slack of all endnodes; if slack is zero, add the
		 * node to the critical path. Add all predecessors to the processing
		 * stack
		 * 
		 */
		for (Workpackage endNode : this.endNodes) {
			endNode.setLatestFinish(maxEF);
			endNode.setLatestStart(endNode.getLatestFinish() - endNode.getDuration());
			endNode.setSlack(endNode.getLatestStart() - endNode.getEarliestStart());
			if (endNode.getSlack() == 0)
				this.criticalPathNodes.add(endNode);
			this.currentNodes.addAll(endNode.getPredecessors());
		}
		
		/*
		 * "reverse" depth first processing of predecessors: basically the same
		 * thing as for the endnodes
		 */
		while (!this.currentNodes.isEmpty()) {
			Workpackage currNode = currentNodes.pop();
			currNode.setLatestFinish(this.getMinLSOfSucc(currNode));
			currNode.setLatestStart(currNode.getLatestFinish() - currNode.getDuration());
			currNode.setSlack(currNode.getLatestStart() - currNode.getEarliestStart());
			if (currNode.getSlack() == 0)
				this.criticalPathNodes.add(currNode);
			currentNodes.addAll(currNode.getPredecessors());
		}

	}

	/**
	 * compares the EF's of all the predecessors of the given node and determines the maxEF
	 * 
	 * 
	 * @param node  the node for which the maximum EF of all its predecessors has to be determined
	 * @return      the maxEF of the nodes predecessors
	 */
	private int getMaxEFOfPred(Workpackage node) {
		int maxEF = 0;
		for (Workpackage pred : node.getPredecessors()) {
			if (pred.getEarliestFinish() > maxEF)
				maxEF = pred.getEarliestFinish();
		}
		return maxEF;
	}

	/**
	 * compares the LS' of all the successors of the given node and determines the minLS
	 * 
	 * 
	 * @param node  the node for which the minimum LS of all its successors has to be determined
	 * @return      the minLS of the nodes successors
	 */
	private int getMinLSOfSucc(Workpackage node){
		int minLS = Integer.MAX_VALUE;
		for(Workpackage succ : node.getSuccessors()){
			if(succ.getLatestStart() < minLS) minLS = succ.getLatestStart();
		}
		return minLS;
	}
	
	/**
	 * 
	 * @return the critical path nodes
	 */
	private Set<Workpackage> getCriticalPath(){
		return this.criticalPathNodes;
	}


	private void resetLists() {
		currentNodes.clear();
		endNodes.clear();
		criticalPathNodes.clear();
		allNodes.clear();
	}
}
