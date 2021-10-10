package cz.cvut.fit.juriczby
package Common.KnapsackProblem.Solvers
import Common.KnapsackProblem.DecisionInstance

import cz.cvut.fit.juriczby.Common.KnapsackProblem.DecisionInstance.{nextInstance, nextInstanceNotChosen}
import cz.cvut.fit.juriczby.Common.StatsTracker

class DecisionKnapsackSolver() extends AbstractDecisionKnapsackSolver {
  def solved(result: Boolean, statsTracker: StatsTracker): Boolean = {
    statsTracker.setTimeEnd()
    result
  }

   override def solve(i: DecisionInstance, statsTracker: StatsTracker): Boolean = {
     statsTracker.setTimeStart()
     if(i.numberOfItems != i.items.length) return solved(result = false, statsTracker)
     if(i.numberOfItems == 0 || i.minPrice == 0) return solved(result = true, statsTracker)
     solved(result = solveRecursive(i, statsTracker), statsTracker)
  }

  private def solveRecursive(i: DecisionInstance, statsTracker: StatsTracker): Boolean = {
    statsTracker.incConfigurationsCount()
    if(i.knapsackCapacity < 0) return false
    if(i.minPrice <= 0) return true
    if(i.numberOfItems == 0) return false
    solveRecursive(nextInstance(i), statsTracker) || solveRecursive(nextInstanceNotChosen(i), statsTracker)
  }
}
