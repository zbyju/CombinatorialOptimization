package cz.cvut.fit.juriczby
package Common.KnapsackProblem.Solvers
import Common.KnapsackProblem.DecisionInstance

import cz.cvut.fit.juriczby.Common.KnapsackProblem.DecisionInstance.{nextInstance, nextInstanceNotChosen}

class BBDecisionKnapsackSolver() extends AbstractDecisionKnapsackSolver {
  override def solve(i: DecisionInstance): Boolean = {
    if(i.numberOfItems != i.items.length) return false
    if(i.numberOfItems == 0 || i.minPrice == 0) return true
    solveRecursive(i)
  }

  private def solveRecursive(i: DecisionInstance): Boolean = {
    if(i.knapsackCapacity < 0) return false
    if(i.minPrice <= 0) return true
    if(i.minPrice - i.totalWeight > 0) return false
    if(i.numberOfItems == 0) return false
    solveRecursive(nextInstance(i)) || solveRecursive(nextInstanceNotChosen(i))
  }
}
