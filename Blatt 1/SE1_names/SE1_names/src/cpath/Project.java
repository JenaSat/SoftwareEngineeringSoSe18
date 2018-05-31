package cpath;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Stellt ein Projekt dar, das aus Arbeitspaketen besteht. Im Prinzip nichts
 * anderes als ein Graph mit Arbeitspaketen als Knoten. Das Projekt kennt nur
 * die Startknoten, diese kennen jeweils ihren Nachfolger.
 * @author Jonas Allali (2965826), Jena Satkunarajan (2965839)
 */
public class Project {

	private List<Workpackage> startNodes = new ArrayList<Workpackage>();
	private List<Workpackage> endNodes = new ArrayList<Workpackage>();
	private Set<Workpackage> criticalPathNodes = new HashSet<Workpackage>();
	private Set<Workpackage> allNodes = new HashSet<Workpackage>();
	private Stack<Workpackage> currentNodes = new Stack<Workpackage>();
	private int projectDuration = 0;

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

		// -----FORWARDING-------
		for (Workpackage startNode : startNodes) {
			// startNode.printInfo();
			int duration = startNode.getDuration();
			startNode.setEarliestFinish(duration);
			allNodes.add(startNode);
			if (!startNode.getSuccessors().isEmpty()) {
				forwarding(startNode.getSuccessors(), duration);
			} else {
				endNodes.add(startNode);
			}
		}
//		 for (Workpackage workpackage : allNodes) {
//		 System.out.println(workpackage.getName());
//		 }

		// -----BACKWARDING------
		for (Workpackage endNode : endNodes) {
			endNode.setLatestFinish(projectDuration);
			endNode.setLatestStart(projectDuration - endNode.getDuration());
			endNode.setSlack(projectDuration - endNode.getEarliestFinish());
			if (endNode.getSlack() == 0) {
				criticalPathNodes.add(endNode);
			}
			if (!endNode.getPredecessors().isEmpty()) {
				backwarding(endNode.getPredecessors(), endNode.getLatestStart());
			}
		}
//		for (Workpackage node : criticalPathNodes) {
//			System.out.println(node.getName());
//		}
	}

	private void backwarding(List<Workpackage> pres, int ls) {
		for (Workpackage node : pres) {
			if(node.getLatestFinish() > ls) {//only look at minimum ls
			node.setLatestFinish(ls);
			}
			node.setLatestStart(node.getLatestFinish()-node.getDuration());
			node.setSlack(node.getLatestFinish()-node.getEarliestFinish());
			if(node.getSlack() == 0) {
				criticalPathNodes.add(node);
			}
//			node.printInfo();
			if(!node.getPredecessors().isEmpty()) {
			backwarding(node.getPredecessors(), node.getLatestStart());
			}
		}
		
	}

	private void forwarding(List<Workpackage> succs, int es) {

		for (Workpackage node : succs) {
			if (!allNodes.contains(node)) {
				allNodes.add(node);
			}
			int duration = node.getDuration();
			int ef = es + duration;
			if (node.getEarliestStart() < es) { // only look at maximum es
				node.setEarliestStart(es);
			}
			if (node.getEarliestFinish() < ef) {// only look at maximum ef
				node.setEarliestFinish(ef);
			}
			// node.printInfo();
			if (node.getSuccessors().isEmpty()) {
				endNodes.add(node);
				if (projectDuration < node.getEarliestFinish()) {
					projectDuration = node.getEarliestFinish();
				}
			} else {
				forwarding(node.getSuccessors(), ef);
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
