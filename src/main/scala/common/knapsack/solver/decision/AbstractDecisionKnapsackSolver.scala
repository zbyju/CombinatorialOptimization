package cz.cvut.fit.juriczby
package common.knapsack.solver.decision

import common.knapsack.instance.decision.{DecisionInstance, DecisionResult}
import common.stats.StatsTracker

abstract class AbstractDecisionKnapsackSolver {
  def solve(i: DecisionInstance, statsTracker: StatsTracker): DecisionResult
}
