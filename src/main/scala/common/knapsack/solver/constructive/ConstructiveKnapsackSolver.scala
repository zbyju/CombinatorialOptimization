package cz.cvut.fit.juriczby
package common.knapsack.solver.constructive

import common.stats.StatsTracker

import cz.cvut.fit.juriczby.common.knapsack.instance.contructive.ConstructiveInstance.{nextInstance, nextInstanceNotChosen}
import cz.cvut.fit.juriczby.common.knapsack.instance.contructive.{ConstructiveInstance, ConstructiveResult}

class ConstructiveKnapsackSolver() extends AbstractConstructiveKnapsackSolver {
  override def solve(i: ConstructiveInstance, statsTracker: StatsTracker): ConstructiveResult = {
    statsTracker.setTimeStart()
    solveRecursive(i, statsTracker)
  }

  private def solveRecursive(i: ConstructiveInstance, statsTracker: StatsTracker): ConstructiveResult = {
    statsTracker.incConfigurationsCount()
    if(i.numberOfItems == 0 || i.knapsackCapacity <= 0) return ConstructiveResult(i.chosenPrice, i.chosenItems, statsTracker)

    val notChosen = solveRecursive(nextInstanceNotChosen(i), statsTracker)
    if(i.lastItem.weight > i.knapsackCapacity) return notChosen

    val chosen = solveRecursive(nextInstance(i), statsTracker)
    if(chosen.maxPrice > notChosen.maxPrice) chosen else notChosen
  }
}
