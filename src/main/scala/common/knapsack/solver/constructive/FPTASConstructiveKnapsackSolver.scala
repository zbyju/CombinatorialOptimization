package cz.cvut.fit.juriczby
package common.knapsack.solver.constructive
import common.knapsack.instance.contructive.{ConstructiveInstance, ConstructiveResult}
import common.stats.StatsTracker

import cz.cvut.fit.juriczby.common.knapsack.instance.Item

class FPTASConstructiveKnapsackSolver(val epsilon: Double = 0.1) extends AbstractConstructiveKnapsackSolver {
  override val name = "FPTAS-" + epsilon

  override def solve(i: ConstructiveInstance, statsTracker: StatsTracker): ConstructiveResult = {
    statsTracker.setTimeStart()
    val timeStart = statsTracker.getTimeStart()

    val dpcSolver = new DPPriceConstructiveKnapsackSolver()
    val itemsThatFit = i.items.filter(_.weight <= i.knapsackCapacity)
    if(itemsThatFit.isEmpty) return ConstructiveResult(0, i.chosenItems, statsTracker)

    val maxPrice = itemsThatFit.maxBy(_.price).price
    val K = Math.max((epsilon * maxPrice) / i.numberOfItems, 1)
    val FPTASInstance = ConstructiveInstance(i.chosenItems, i.name, i.id, i.numberOfItems, i.knapsackCapacity, i.items.map(i => Item(i.weight, Math.max(1, Math.floor(i.price / K).toInt))))
    val dpcRes = dpcSolver.solve(FPTASInstance, statsTracker)

    statsTracker.setTimeStart(timeStart)
    ConstructiveResult(Math.round(dpcRes.maxPrice * K).toInt, dpcRes.chosenItems, statsTracker)
  }
}
