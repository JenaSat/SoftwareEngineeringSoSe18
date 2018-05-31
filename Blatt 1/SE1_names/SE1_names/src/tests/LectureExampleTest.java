package tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import cpath.Project;
import cpath.Workpackage;

/**
 * Testet die Implementierung mit dem Beispiel aus der Vorlesung.
 */
public class LectureExampleTest {
	private static Workpackage a = new Workpackage("a", 6);
	private static Workpackage b = new Workpackage("b", 8);
	private static Workpackage c = new Workpackage("c", 5);
	private static Workpackage d = new Workpackage("d", 15);
	private static Workpackage e = new Workpackage("e", 17);
	private static Workpackage f = new Workpackage("f", 13);
	private static Workpackage g = new Workpackage("g", 9);
	private static Workpackage h = new Workpackage("h", 9);
	private static Workpackage i = new Workpackage("i", 6);
	private static Workpackage j = new Workpackage("j", 12);
	private static Workpackage z = new Workpackage("z", 12);
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Project graph = new Project();

		graph.getStartNodes().add(a);
		graph.getStartNodes().add(b);
		graph.getStartNodes().add(c);
		graph.getStartNodes().add(z);

		a.addSuccessor(d).addSuccessor(h);
		a.addSuccessor(e).addSuccessor(i);
		b.addSuccessor(f).addSuccessor(j);
		c.addSuccessor(g).addSuccessor(j);

		graph.computeCriticalPath();
	}
	
	@Test
	public void testNodeA() {
		assertEquals(6, a.getDuration());
		assertEquals(0, a.getEarliestStart());
		assertEquals(6, a.getEarliestFinish());
		assertEquals(3, a.getLatestStart());
		assertEquals(9, a.getLatestFinish());
		assertEquals(3, a.getSlack());
	}

	@Test
	public void testNodeB() {
		assertEquals(8, b.getDuration());
		assertEquals(0, b.getEarliestStart());
		assertEquals(8, b.getEarliestFinish());
		assertEquals(0, b.getLatestStart());
		assertEquals(8, b.getLatestFinish());
		assertEquals(0, b.getSlack());
	}

	@Test
	public void testNodeC() {
		assertEquals(5, c.getDuration());
		assertEquals(0, c.getEarliestStart());
		assertEquals(5, c.getEarliestFinish());
		assertEquals(7, c.getLatestStart());
		assertEquals(12, c.getLatestFinish());
		assertEquals(7, c.getSlack());
	}

	@Test
	public void testNodeD() {
		assertEquals(15, d.getDuration());
		assertEquals(6, d.getEarliestStart());
		assertEquals(21, d.getEarliestFinish());
		assertEquals(9, d.getLatestStart());
		assertEquals(24, d.getLatestFinish());
		assertEquals(3, d.getSlack());
	}

	@Test
	public void testNodeE() {
		assertEquals(17, e.getDuration());
		assertEquals(6, e.getEarliestStart());
		assertEquals(23, e.getEarliestFinish());
		assertEquals(10, e.getLatestStart());
		assertEquals(27, e.getLatestFinish());
		assertEquals(4, e.getSlack());
	}

	@Test
	public void testNodeF() {
		assertEquals(13, f.getDuration());
		assertEquals(8, f.getEarliestStart());
		assertEquals(21, f.getEarliestFinish());
		assertEquals(8, f.getLatestStart());
		assertEquals(21, f.getLatestFinish());
		assertEquals(0, f.getSlack());
	}

	@Test
	public void testNodeG() {
		assertEquals(9, g.getDuration());
		assertEquals(5, g.getEarliestStart());
		assertEquals(14, g.getEarliestFinish());
		assertEquals(12, g.getLatestStart());
		assertEquals(21, g.getLatestFinish());
		assertEquals(7, g.getSlack());
	}

	@Test
	public void testNodeH() {
		assertEquals(9, h.getDuration());
		assertEquals(21, h.getEarliestStart());
		assertEquals(30, h.getEarliestFinish());
		assertEquals(24, h.getLatestStart());
		assertEquals(33, h.getLatestFinish());
		assertEquals(3, h.getSlack());
	}

	@Test
	public void testNodeI() {
		assertEquals(6, i.getDuration());
		assertEquals(23, i.getEarliestStart());
		assertEquals(29, i.getEarliestFinish());
		assertEquals(27, i.getLatestStart());
		assertEquals(33, i.getLatestFinish());
		assertEquals(4, i.getSlack());
	}

	@Test
	public void testNodeJ() {
		assertEquals(12, j.getDuration());
		assertEquals(21, j.getEarliestStart());
		assertEquals(33, j.getEarliestFinish());
		assertEquals(21, j.getLatestStart());
		assertEquals(33, j.getLatestFinish());
		assertEquals(0, j.getSlack());
	}
	
	@Test
	public void testNodeZ() {
		assertEquals(12, z.getDuration());
		assertEquals(0, z.getEarliestStart());
		assertEquals(12, z.getEarliestFinish());
		assertEquals(21, z.getLatestStart());
		assertEquals(33, z.getLatestFinish());
		assertEquals(21, z.getSlack());
	}

}
