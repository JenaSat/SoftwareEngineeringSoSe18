package tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import cpath.Project;
import cpath.Workpackage;

/**
 * Testet die Implementierung mit dem Beispiel aus der Übung.
 * 
 * @author Jonas Allali (2965826), Jena Satkunarajan (2965839). Heinrich Pauli
 *         (3245875), Timo Hüttner (3220544)
 */
public class SheetExampleTest {
	private static Workpackage a1 = new Workpackage("a1", 3);
	private static Workpackage a2 = new Workpackage("a2", 4);
	private static Workpackage a3 = new Workpackage("a3", 5);
	private static Workpackage a4 = new Workpackage("a4", 4);
	private static Workpackage a5 = new Workpackage("a5", 9);
	private static Workpackage a6 = new Workpackage("a6", 4);
	private static Workpackage a7 = new Workpackage("a7", 2);
	private static Workpackage a8 = new Workpackage("a8", 4);
	private static Workpackage a9 = new Workpackage("a9", 2);
	private static Workpackage a10 = new Workpackage("a10", 3);
	private static Workpackage a11 = new Workpackage("a11", 3);
	private static Workpackage a12 = new Workpackage("a12", 2);
	private static Workpackage a13 = new Workpackage("a13", 4);
	private static Workpackage a14 = new Workpackage("a14", 7);
	private static Workpackage a15 = new Workpackage("a15", 2);
	private static Workpackage a16 = new Workpackage("a16", 4);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Project graph = new Project();

		graph.getStartNodes().add(a1);
		graph.getStartNodes().add(a2);
		graph.getStartNodes().add(a3);

		a1.addSuccessor(a4).addSuccessor(a8).addSuccessor(a12).addSuccessor(a14);
		a12.addSuccessor(a15).addSuccessor(a16);
		a1.addSuccessor(a5).addSuccessor(a12);
		a2.addSuccessor(a6).addSuccessor(a9).addSuccessor(a14);
		a9.addSuccessor(a15);
		a6.addSuccessor(a10).addSuccessor(a13).addSuccessor(a16);
		a3.addSuccessor(a7).addSuccessor(a11).addSuccessor(a13);

		graph.computeCriticalPath();
	}

	@Test
	public void testNodeA1() {
		assertEquals(3, a1.getDuration());
		assertEquals(0, a1.getEarliestStart());
		assertEquals(3, a1.getEarliestFinish());
		assertEquals(0, a1.getLatestStart());
		assertEquals(3, a1.getLatestFinish());
		assertEquals(0, a1.getSlack());
	}

	@Test
	public void testNodeA2() {
		assertEquals(4, a2.getDuration());
		assertEquals(0, a2.getEarliestStart());
		assertEquals(4, a2.getEarliestFinish());
		assertEquals(2, a2.getLatestStart());
		assertEquals(6, a2.getLatestFinish());
		assertEquals(2, a2.getSlack());
	}

	@Test
	public void testNodeA3() {
		assertEquals(5, a3.getDuration());
		assertEquals(0, a3.getEarliestStart());
		assertEquals(5, a3.getEarliestFinish());
		assertEquals(3, a3.getLatestStart());
		assertEquals(8, a3.getLatestFinish());
		assertEquals(3, a3.getSlack());
	}

	@Test
	public void testNodeA4() {
		assertEquals(4, a4.getDuration());
		assertEquals(3, a4.getEarliestStart());
		assertEquals(7, a4.getEarliestFinish());
		assertEquals(4, a4.getLatestStart());
		assertEquals(8, a4.getLatestFinish());
		assertEquals(1, a4.getSlack());
	}

	@Test
	public void testNodeA5() {
		assertEquals(9, a5.getDuration());
		assertEquals(3, a5.getEarliestStart());
		assertEquals(12, a5.getEarliestFinish());
		assertEquals(3, a5.getLatestStart());
		assertEquals(12, a5.getLatestFinish());
		assertEquals(0, a5.getSlack());
	}

	@Test
	public void testNodeA6() {
		assertEquals(4, a6.getDuration());
		assertEquals(4, a6.getEarliestStart());
		assertEquals(8, a6.getEarliestFinish());
		assertEquals(6, a6.getLatestStart());
		assertEquals(10, a6.getLatestFinish());
		assertEquals(2, a6.getSlack());
	}

	@Test
	public void testNodeA7() {
		assertEquals(2, a7.getDuration());
		assertEquals(5, a7.getEarliestStart());
		assertEquals(7, a7.getEarliestFinish());
		assertEquals(8, a7.getLatestStart());
		assertEquals(10, a7.getLatestFinish());
		assertEquals(3, a7.getSlack());
	}

	@Test
	public void testNodeA8() {
		assertEquals(4, a8.getDuration());
		assertEquals(7, a8.getEarliestStart());
		assertEquals(11, a8.getEarliestFinish());
		assertEquals(8, a8.getLatestStart());
		assertEquals(12, a8.getLatestFinish());
		assertEquals(1, a8.getSlack());
	}

	@Test
	public void testNodeA9() {
		assertEquals(2, a9.getDuration());
		assertEquals(8, a9.getEarliestStart());
		assertEquals(10, a9.getEarliestFinish());
		assertEquals(12, a9.getLatestStart());
		assertEquals(14, a9.getLatestFinish());
		assertEquals(4, a9.getSlack());
	}

	@Test
	public void testNodeA10() {
		assertEquals(3, a10.getDuration());
		assertEquals(8, a10.getEarliestStart());
		assertEquals(11, a10.getEarliestFinish());
		assertEquals(10, a10.getLatestStart());
		assertEquals(13, a10.getLatestFinish());
		assertEquals(2, a10.getSlack());
	}

	@Test
	public void testNodeA11() {
		assertEquals(3, a11.getDuration());
		assertEquals(7, a11.getEarliestStart());
		assertEquals(10, a11.getEarliestFinish());
		assertEquals(10, a11.getLatestStart());
		assertEquals(13, a11.getLatestFinish());
		assertEquals(3, a11.getSlack());
	}

	@Test
	public void testNodeA12() {
		assertEquals(2, a12.getDuration());
		assertEquals(12, a12.getEarliestStart());
		assertEquals(14, a12.getEarliestFinish());
		assertEquals(12, a12.getLatestStart());
		assertEquals(14, a12.getLatestFinish());
		assertEquals(0, a12.getSlack());
	}

	@Test
	public void testNodeA13() {
		assertEquals(4, a13.getDuration());
		assertEquals(11, a13.getEarliestStart());
		assertEquals(15, a13.getEarliestFinish());
		assertEquals(13, a13.getLatestStart());
		assertEquals(17, a13.getLatestFinish());
		assertEquals(2, a13.getSlack());
	}

	@Test
	public void testNodeA14() {
		assertEquals(7, a14.getDuration());
		assertEquals(14, a14.getEarliestStart());
		assertEquals(21, a14.getEarliestFinish());
		assertEquals(14, a14.getLatestStart());
		assertEquals(21, a14.getLatestFinish());
		assertEquals(0, a14.getSlack());
	}

	@Test
	public void testNodeA15() {
		assertEquals(2, a15.getDuration());
		assertEquals(14, a15.getEarliestStart());
		assertEquals(16, a15.getEarliestFinish());
		assertEquals(15, a15.getLatestStart());
		assertEquals(17, a15.getLatestFinish());
		assertEquals(1, a15.getSlack());
	}

	@Test
	public void testNodeA16() {
		assertEquals(4, a16.getDuration());
		assertEquals(16, a16.getEarliestStart());
		assertEquals(20, a16.getEarliestFinish());
		assertEquals(17, a16.getLatestStart());
		assertEquals(21, a16.getLatestFinish());
		assertEquals(1, a15.getSlack());
	}

}
