package cz.cvut.fit.juriczby.Common.KnapsackProblem.Solvers
import cz.cvut.fit.juriczby.Common.KnapsackProblem.{DecisionInstance, Instance}

abstract class AbstractDecisionKnapsackSolver {
  def solve(i: DecisionInstance): Boolean
}
