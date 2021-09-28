package cz.cvut.fit.juriczby
package Common.BackpackProblem.Solvers
import Common.BackpackProblem.DecisionInstance

import cz.cvut.fit.juriczby.Common.BackpackProblem.DecisionInstance.{nextInstance, nextInstanceNotChosen}

class DecisionKnapsackSolver() extends KnapsackSolver {
   def solve(i: DecisionInstance): Boolean = {
    if(i.numberOfItems != i.items.length) return false
    if(i.numberOfItems == 0 || i.minPrice == 0) return true
    solveRecursive(i)
  }

  def solveRecursive(i: DecisionInstance): Boolean = {
    if(i.knapsackCapacity < 0) return false
    if(i.minPrice <= 0) return true
    if(i.numberOfItems == 0) return false
    solveRecursive(nextInstance(i)) || solveRecursive(nextInstanceNotChosen(i))
  }
}
