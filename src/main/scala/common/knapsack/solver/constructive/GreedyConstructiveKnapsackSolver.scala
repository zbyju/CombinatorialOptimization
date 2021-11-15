package cz.cvut.fit.juriczby
package common.knapsack.solver.constructive
import common.knapsack.instance.contructive.{ConstructiveInstance, ConstructiveResult}
import common.stats.StatsTracker

class GreedyConstructiveKnapsackSolver extends AbstractConstructiveKnapsackSolver {
  override val name = "Greedy"

  override def solve(i: ConstructiveInstance, statsTracker: StatsTracker): ConstructiveResult = {
    statsTracker.setTimeStart()
    // First item has the biggest ratio
    var greedyInstance = ConstructiveInstance(i.chosenItems, i.name, i.id, i.numberOfItems, i.knapsackCapacity, i.items.sortWith((a, b) => {
      a.price / a.weight > b.price / b.weight
    }))
    var index = 0
    greedyInstance.items.foreach(inst => {
      statsTracker.incConfigurationsCount()
      if(inst.weight <= greedyInstance.knapsackCapacity) {
        greedyInstance = ConstructiveInstance(greedyInstance.chosenItems.updated(index, true),
                                              i.name, i.id, i.numberOfItems,
                                              i.knapsackCapacity - inst.weight, greedyInstance.items)
      }
      index += 1
    })
    ConstructiveResult(greedyInstance.chosenPrice, greedyInstance.chosenItems, statsTracker)
  }
}
