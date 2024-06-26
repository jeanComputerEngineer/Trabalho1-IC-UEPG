package aima.test.core.unit.search.informed;

import aima.core.agent.Action;
import aima.core.environment.eightpuzzle.BidirectionalEightPuzzleProblem;
import aima.core.environment.map.MapFunctions;
import aima.core.environment.map.MoveToAction;
import aima.core.search.framework.problem.GeneralProblem;
import aima.core.search.framework.problem.GoalTest;
import org.junit.Assert;
import org.junit.Test;

import aima.core.environment.eightpuzzle.EightPuzzleBoard;
import aima.core.environment.eightpuzzle.EightPuzzleFunctions;
import aima.core.environment.map.Map;
import aima.core.environment.map.SimplifiedRoadMapOfPartOfRomania;
import aima.core.search.framework.QueueBasedSearch;
import aima.core.search.agent.SearchAgent;
import aima.core.search.framework.SearchForActions;
import aima.core.search.framework.problem.Problem;
import aima.core.search.framework.qsearch.GraphSearch;
import aima.core.search.framework.qsearch.GraphSearchReducedFrontier;
import aima.core.search.framework.qsearch.TreeSearch;
import aima.core.search.informed.GreedyBestFirstSearch;

public class GreedyBestFirstSearchTest {

	@Test
	public void testGreedyBestFirstSearch() {
		try {
			// EightPuzzleBoard extreme = new EightPuzzleBoard(new int[]
			// {2,0,5,6,4,8,3,7,1});
			// EightPuzzleBoard extreme = new EightPuzzleBoard(new int[]
			// {0,8,7,6,5,4,3,2,1});
                        
                        //https://www.researchgate.net/figure/8-Puzzle-problem-instances_tbl1_280545587
			EightPuzzleBoard board = new EightPuzzleBoard(new int[] { 0, 3, 5, 4, 2, 8, 6, 1, 7 });

			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(board);
			SearchForActions<EightPuzzleBoard, Action> search = new GreedyBestFirstSearch<>
					(new GraphSearch<>(), EightPuzzleFunctions.createManhattanHeuristicFunction());
			SearchAgent<EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);

//			Assert.assertEquals(49, agent.getActions().size()); // GraphSearchReducedFrontier: "49"
//			Assert.assertEquals("332", // GraphSearchReducedFrontier: "197"
//					agent.getInstrumentation().getProperty("nodesExpanded"));
//			Assert.assertEquals("241", // GraphSearchReducedFrontier: "140"
//					agent.getInstrumentation().getProperty("queueSize"));
//			Assert.assertEquals("242", // GraphSearchReducedFrontier: "141"
//					agent.getInstrumentation().getProperty("maxQueueSize"));
                        System.out.println(agent.getActions().size());
                        System.out.println(agent.getActions().toString());
                        System.out.println(agent.getInstrumentation().getProperty("nodesExpanded"));
                        
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception thrown.");
		}
	}

	@Test
	public void testGreedyBestFirstSearchReducedFrontier() {
		try {
			// EightPuzzleBoard extreme = new EightPuzzleBoard(new int[]
			// {2,0,5,6,4,8,3,7,1});
			// EightPuzzleBoard extreme = new EightPuzzleBoard(new int[]
			// {0,8,7,6,5,4,3,2,1});
			EightPuzzleBoard board = new EightPuzzleBoard(new int[] { 7, 1, 8, 0, 4, 6, 2, 3, 5 });

			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(board);
			QueueBasedSearch<EightPuzzleBoard, Action> search = new GreedyBestFirstSearch<>
					(new GraphSearchReducedFrontier<>(), EightPuzzleFunctions.createManhattanHeuristicFunction());

			SearchAgent<EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			Assert.assertEquals(49, agent.getActions().size());
			Assert.assertEquals("197", agent.getInstrumentation().getProperty("nodesExpanded"));
			Assert.assertEquals("140", agent.getInstrumentation().getProperty("queueSize"));
			Assert.assertEquals("141", agent.getInstrumentation().getProperty("maxQueueSize"));
                        System.out.println(agent.getActions().size());
                        System.out.println(agent.getActions().toString());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception thrown.");
		}
	}

	@Test
	public void testAIMA3eFigure3_23() throws Exception {
		Map romaniaMap = new SimplifiedRoadMapOfPartOfRomania();
		Problem<String, MoveToAction> problem = new GeneralProblem<>(SimplifiedRoadMapOfPartOfRomania.ARAD,
				MapFunctions.createActionsFunction(romaniaMap), MapFunctions.createResultFunction(),
				GoalTest.forState(SimplifiedRoadMapOfPartOfRomania.BUCHAREST),
				MapFunctions.createDistanceStepCostFunction(romaniaMap));

		SearchForActions<String, MoveToAction> search = new GreedyBestFirstSearch<>(new TreeSearch<>(),
				MapFunctions.createSLDHeuristicFunction(SimplifiedRoadMapOfPartOfRomania.BUCHAREST, romaniaMap));
		SearchAgent<String, MoveToAction> agent = new SearchAgent<>(problem, search);
		Assert.assertEquals(
				"[Action[name=moveTo, location=Sibiu], Action[name=moveTo, location=Fagaras], Action[name=moveTo, location=Bucharest]]",
				agent.getActions().toString());
		Assert.assertEquals(3, agent.getActions().size());
		Assert.assertEquals("3", agent.getInstrumentation().getProperty("nodesExpanded"));
		Assert.assertEquals("6", agent.getInstrumentation().getProperty("queueSize"));
		Assert.assertEquals("7", agent.getInstrumentation().getProperty("maxQueueSize"));
	}

	@Test
	public void testAIMA3eFigure3_23_using_GraphSearch() throws Exception {
		Map romaniaMap = new SimplifiedRoadMapOfPartOfRomania();
		Problem<String, MoveToAction> problem = new GeneralProblem<>(SimplifiedRoadMapOfPartOfRomania.ARAD,
				MapFunctions.createActionsFunction(romaniaMap), MapFunctions.createResultFunction(),
				GoalTest.forState(SimplifiedRoadMapOfPartOfRomania.BUCHAREST),
				MapFunctions.createDistanceStepCostFunction(romaniaMap));

		SearchForActions<String, MoveToAction> search = new GreedyBestFirstSearch<>(new GraphSearch<>(),
				MapFunctions.createSLDHeuristicFunction(SimplifiedRoadMapOfPartOfRomania.BUCHAREST, romaniaMap));
		SearchAgent<String, MoveToAction> agent = new SearchAgent<>(problem, search);
		Assert.assertEquals(
				"[Action[name=moveTo, location=Sibiu], Action[name=moveTo, location=Fagaras], Action[name=moveTo, location=Bucharest]]",
				agent.getActions().toString());
		Assert.assertEquals(3, agent.getActions().size());
		Assert.assertEquals("3", agent.getInstrumentation().getProperty("nodesExpanded"));
		Assert.assertEquals("4", agent.getInstrumentation().getProperty("queueSize"));
		Assert.assertEquals("5", agent.getInstrumentation().getProperty("maxQueueSize"));
	}
}
