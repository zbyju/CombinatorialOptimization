package cz.cvut.fit.juriczby
package common.knapsack.solver.constructive
import common.knapsack.instance.contructive.{ConstructiveInstance, ConstructiveResult}
import common.stats.StatsTracker

import cz.cvut.fit.juriczby.common.knapsack.instance.Item

class GreedyReduxConstructiveKnapsackSolver extends GreedyConstructiveKnapsackSolver {
  override val name = "GreedyRedux"

  override def solve(i: ConstructiveInstance, statsTracker: StatsTracker): ConstructiveResult = {
    val greedySol = super.solve(i, statsTracker)
    val itemsFit = i.items.filter(item => item.weight <= i.knapsackCapacity)

    if(itemsFit.isEmpty) return greedySol

    val bestSoloItem = itemsFit.zipWithIndex.maxBy(x => x._1.price)
    if(greedySol.maxPrice >= bestSoloItem._1.price) greedySol else ConstructiveResult(bestSoloItem._1.price,
                                                                    List.fill(i.numberOfItems)(false).updated(bestSoloItem._2, true), statsTracker)
  }
}
