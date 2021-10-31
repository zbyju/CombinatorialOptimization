package cz.cvut.fit.juriczby
package common.knapsack.solver.decision

import common.knapsack.instance.decision.DecisionInstance

import cz.cvut.fit.juriczby.common.knapsack.instance.decision.DecisionInstance.{nextInstance, nextInstanceNotChosen}
import cz.cvut.fit.juriczby.common.knapsack.solver.AbstractDecisionKnapsackSolver
import cz.cvut.fit.juriczby.common.knapsack.instance.decision.DecisionResult
import cz.cvut.fit.juriczby.common.stats.StatsTracker

class DecisionKnapsackSolver() extends AbstractDecisionKnapsackSolver {
  override def solve(i: DecisionInstance, statsTracker: StatsTracker): DecisionResult = {
    statsTracker.setTimeStart()
    if(i.numberOfItems != i.items.length) DecisionResult(true, statsTracker)
    if(i.numberOfItems == 0 || i.minPrice == 0) return DecisionResult(false, statsTracker)
    DecisionResult(solveRecursive(i, statsTracker), statsTracker)
  }

  private def solveRecursive(i: DecisionInstance, statsTracker: StatsTracker): Boolean = {
    statsTracker.incConfigurationsCount()
    if(i.knapsackCapacity < 0) return false
    if(i.minPrice <= 0) return true
    if(i.numberOfItems == 0) return false
    solveRecursive(nextInstance(i), statsTracker) || solveRecursive(nextInstanceNotChosen(i), statsTracker)
  }
}
