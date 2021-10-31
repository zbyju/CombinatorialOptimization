package cz.cvut.fit.juriczby
package common.knapsack.solver

import common.stats.StatsTracker

import cz.cvut.fit.juriczby.common.knapsack.instance.decision.DecisionInstance
import cz.cvut.fit.juriczby.common.knapsack.instance.decision.DecisionResult

abstract class AbstractDecisionKnapsackSolver {
  def solve(i: DecisionInstance, statsTracker: StatsTracker): DecisionResult
}
