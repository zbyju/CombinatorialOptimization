package cz.cvut.fit.juriczby.Common.KnapsackProblem.Solvers
import cz.cvut.fit.juriczby.Common.KnapsackProblem.{DecisionInstance, Instance}
import cz.cvut.fit.juriczby.Common.StatsTracker

abstract class AbstractDecisionKnapsackSolver {
  def solve(i: DecisionInstance, statsTracker: StatsTracker): Boolean
}
