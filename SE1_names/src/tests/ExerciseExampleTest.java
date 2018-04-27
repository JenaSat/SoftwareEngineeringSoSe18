package tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import cpath.Project;
import cpath.Workpackage;

public class ExerciseExampleTest {

	private static Workpackage a01 = new Workpackage("A01", 3);
	private static Workpackage a02 = new Workpackage("A02", 4);
	private static Workpackage a03 = new Workpackage("A03", 5);
	private static Workpackage a04 = new Workpackage("A04", 4);
	private static Workpackage a05 = new Workpackage("A05", 9);
	private static Workpackage a06 = new Workpackage("A06", 4);
	private static Workpackage a07 = new Workpackage("A07", 2);
	private static Workpackage a08 = new Workpackage("A08", 4);
	private static Workpackage a09 = new Workpackage("A09", 2);
	private static Workpackage a10 = new Workpackage("A10", 3);
	private static Workpackage a11 = new Workpackage("A11", 3);
	private static Workpackage a12 = new Workpackage("A12", 2);
	private static Workpackage a13 = new Workpackage("A13", 4);
	private static Workpackage a14 = new Workpackage("A14", 7);
	private static Workpackage a15 = new Workpackage("A15", 2);
	private static Workpackage a16 = new Workpackage("A16", 4);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Project graph = new Project();

		graph.getStartNodes().add(a01);
		graph.getStartNodes().add(a02);
		graph.getStartNodes().add(a03);

		a01.addSuccessor(a04).addSuccessor(a08).addSuccessor(a12).addSuccessor(a14);
		a01.addSuccessor(a05).addSuccessor(a12).addSuccessor(a15).addSuccessor(a16);
		a02.addSuccessor(a06).addSuccessor(a09).addSuccessor(a14);
		a06.addSuccessor(a10).addSuccessor(a13).addSuccessor(a16);
		a03.addSuccessor(a07).addSuccessor(a11).addSuccessor(a13);

		graph.computeCriticalPath();
	}
	
	@Test
	public void testNodeA01() {
		assertEquals(3, a01.getDuration());
		assertEquals(0, a01.getEarliestStart());
		assertEquals(3, a01.getEarliestFinish());
		assertEquals(0, a01.getLatestStart());
		assertEquals(3, a01.getLatestFinish());
		assertEquals(0, a01.getSlack());
	}
	@Test
	public void testNodeA02() {
		assertEquals(4, a02.getDuration());
		assertEquals(0, a02.getEarliestStart());
		assertEquals(4, a02.getEarliestFinish());
		assertEquals(2, a02.getLatestStart());
		assertEquals(6, a02.getLatestFinish());
		assertEquals(2, a02.getSlack());
	}
	@Test
	public void testNodeA03() {
		assertEquals(5, a03.getDuration());
		assertEquals(0, a03.getEarliestStart());
		assertEquals(5, a03.getEarliestFinish());
		assertEquals(3, a03.getLatestStart());
		assertEquals(8, a03.getLatestFinish());
		assertEquals(3, a03.getSlack());
	}
	@Test
	public void testNodeA04() {
		assertEquals(4, a04.getDuration());
		assertEquals(3, a04.getEarliestStart());
		assertEquals(7, a04.getEarliestFinish());
		assertEquals(4, a04.getLatestStart());
		assertEquals(8, a04.getLatestFinish());
		assertEquals(1, a04.getSlack());
	}
	@Test
	public void testNodeA05() {
		assertEquals(9, a05.getDuration());
		assertEquals(3, a05.getEarliestStart());
		assertEquals(12, a05.getEarliestFinish());
		assertEquals(3, a05.getLatestStart());
		assertEquals(12, a05.getLatestFinish());
		assertEquals(0, a05.getSlack());
	}
	@Test
	public void testNodeA06() {
		assertEquals(4, a06.getDuration());
		assertEquals(4, a06.getEarliestStart());
		assertEquals(8, a06.getEarliestFinish());
		assertEquals(6, a06.getLatestStart());
		assertEquals(10, a06.getLatestFinish());
		assertEquals(2, a06.getSlack());
	}
	@Test
	public void testNodeA07() {
		assertEquals(2, a07.getDuration());
		assertEquals(5, a07.getEarliestStart());
		assertEquals(7, a07.getEarliestFinish());
		assertEquals(8, a07.getLatestStart());
		assertEquals(10, a07.getLatestFinish());
		assertEquals(3, a07.getSlack());
	}
	@Test
	public void testNodeA08() {
		assertEquals(4, a08.getDuration());
		assertEquals(7, a08.getEarliestStart());
		assertEquals(11, a08.getEarliestFinish());
		assertEquals(8, a08.getLatestStart());
		assertEquals(12, a08.getLatestFinish());
		assertEquals(1, a08.getSlack());
	}
	@Test
	public void testNodeA09() {
		assertEquals(2, a09.getDuration());
		assertEquals(8, a09.getEarliestStart());
		assertEquals(10, a09.getEarliestFinish());
		assertEquals(12, a09.getLatestStart());
		assertEquals(14, a09.getLatestFinish());
		assertEquals(4, a09.getSlack());
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
		assertEquals(1, a16.getSlack());
	}
}
