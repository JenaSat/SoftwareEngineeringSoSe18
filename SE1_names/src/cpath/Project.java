package cpath;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Stellt ein Projekt dar, das aus Arbeitspaketen besteht. Im Prinzip nichts
 * anderes als ein Graph mit Arbeitspaketen als Knoten. Das Projekt kennt nur
 * die Startknoten, diese kennen jeweils ihren Nachfolger.
 */
public class Project {

	private List<Workpackage> startNodes = new ArrayList<Workpackage>();
	private List<Workpackage> endNodes = new ArrayList<Workpackage>();
	private Set<Workpackage> criticalPathNodes = new HashSet<Workpackage>();
	private Set<Workpackage> allNodes = new HashSet<Workpackage>();
	private Stack<Workpackage> currentNodes = new Stack<Workpackage>();

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

	public void computeCriticalPath() {
		resetLists();
		// TODO: Implementieren Sie diese Methode, so dass nach deren Aufruf
		// alle Werte im Graph gesetzt sind und die beiden verfuegbaren Sets
		// korrekt befuellt wurden. Veraendern Sie nicht die Workpackage-Klasse!
		// Zum Testen koennen Sie die beigefuegte Test-Klasse verwenden und
		// erweitern. Beachten Sie, dass ihre Implementierung generell
		// funktionieren und nicht nur dieses eine Problem loesen soll.

		/*
		 * start nodes
		 */
		List<Workpackage> startNodes = getStartNodes();
		for (Workpackage startnode : startNodes) {
			startnode.setEarliestStart(0);
			currentNodes.push(startnode);
		}

		// earliest start and finish
		while (!currentNodes.isEmpty()) {
			Workpackage currentNode = currentNodes.pop();
			List<Workpackage> currentSuccessors = currentNode.getSuccessors();
			List<Workpackage> currentPredecessors = currentNode.getPredecessors();
			currentNodes.addAll(currentSuccessors);

			if (!currentPredecessors.isEmpty()) {
				int x = Integer.MIN_VALUE;
				for (Workpackage preNode : currentPredecessors) {
					if (x < preNode.getEarliestStart() + preNode.getDuration()) {
						x = preNode.getEarliestStart() + preNode.getDuration();
					}
				}
				currentNode.setEarliestFinish(x + currentNode.getDuration());
				currentNode.setEarliestStart(x);
			} else {
				currentNode.setEarliestFinish(currentNode.getDuration());
			}

			// adds the current node to the endNodes list if it is an end node.
			if (currentSuccessors.isEmpty()) {
				if (!endNodes.contains(currentNode)) {
					endNodes.add(currentNode);
				}
			}
			// adds the current node to the node set if it is not in there yet
			allNodes.add(currentNode);
		}
		// calc the latest finish
		int LF = Integer.MIN_VALUE;
		for (Workpackage endNode : endNodes) {
			if (LF < endNode.getEarliestFinish()) {
				LF = endNode.getEarliestFinish();
			}
		}
		// sets the latest finish, latest start and slack for the end nodes
		for (Workpackage endNode : endNodes) {
			endNode.setLatestFinish(LF);
			endNode.setLatestStart(LF - endNode.getDuration());
			endNode.setSlack(endNode.getLatestFinish() - endNode.getEarliestFinish());
		}
		
		//starting at the end nodes
		currentNodes.addAll(endNodes);
		while (!currentNodes.isEmpty()) {
			Workpackage currentNode = currentNodes.pop();
			List<Workpackage> currentSuccessors = currentNode.getSuccessors();
			List<Workpackage> currentPredecessors = currentNode.getPredecessors();
			currentNodes.addAll(currentPredecessors);

			if (!currentSuccessors.isEmpty()) {
				int x = Integer.MAX_VALUE;
				for (Workpackage succNode : currentSuccessors) {
					if (x > succNode.getLatestStart() - currentNode.getEarliestFinish()) {
						x = succNode.getLatestStart() - currentNode.getEarliestFinish();
					}
				}
					currentNode.setSlack(x);
					currentNode.setLatestFinish(currentNode.getEarliestFinish() + currentNode.getSlack());
					currentNode.setLatestStart(currentNode.getEarliestStart() + currentNode.getSlack());
			}
		}
		
		//adds the critical path nodes to the set
		for (Workpackage node : allNodes) {
			if (node.getSlack()==0) {
				criticalPathNodes.add(node);
			}
		}
	}

	private void resetLists() {
		currentNodes.clear();
		endNodes.clear();
		criticalPathNodes.clear();
		allNodes.clear();
	}
}
