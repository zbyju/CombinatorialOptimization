package cz.cvut.fit.juriczby
package common.knapsack.solver.constructive

import common.knapsack.instance.contructive.ConstructiveInstance.{nextInstance, nextInstanceNotChosen}
import common.knapsack.instance.contructive.{ConstructiveInstance, ConstructiveResult}
import common.stats.StatsTracker

import java.util.concurrent.atomic.AtomicInteger

case class MaxTracker(max: AtomicInteger = new AtomicInteger(0)) {
  def setValue(value: Int) = if(value > max.get()) max.set(value)
  def isSmaller(value: Int) = value <= max.get()
}

class BBConstructiveKnapsackSolver() extends AbstractConstructiveKnapsackSolver {
  override def solve(i: ConstructiveInstance, statsTracker: StatsTracker): ConstructiveResult = {
    statsTracker.setTimeStart()
    val maxTracker = MaxTracker()
    solveRecursive(i, maxTracker, statsTracker)
  }

  private def solveRecursive(i: ConstructiveInstance, maxTracker: MaxTracker, statsTracker: StatsTracker): ConstructiveResult = {
    statsTracker.incConfigurationsCount()
    if(i.numberOfItems == 0 || i.knapsackCapacity <= 0) {
      maxTracker.setValue(i.chosenPrice)
      return ConstructiveResult(i.chosenPrice, i.chosenItems, statsTracker)
    }

    if(maxTracker.isSmaller(i.maxPotentialPrice)) return ConstructiveResult(0, Seq(), statsTracker)

    val notChosen = solveRecursive(nextInstanceNotChosen(i), maxTracker, statsTracker)
    if(i.lastItem.weight > i.knapsackCapacity) return notChosen

    val chosen = solveRecursive(nextInstance(i), maxTracker, statsTracker)
    if(chosen.maxPrice > notChosen.maxPrice) chosen else notChosen
  }
}
