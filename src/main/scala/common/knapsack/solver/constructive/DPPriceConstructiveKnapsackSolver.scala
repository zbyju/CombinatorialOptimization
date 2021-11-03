package cz.cvut.fit.juriczby
package common.knapsack.solver.constructive
import common.knapsack.instance.contructive.{ConstructiveInstance, ConstructiveResult}
import common.stats.StatsTracker

case class TableCell(weight: Int, itemIndex: Int, costIndex: Int) {
  override def toString() = {
    this.weight.toString
  }
}

class DPPriceConstructiveKnapsackSolver extends AbstractConstructiveKnapsackSolver {

  override def solve(inst: ConstructiveInstance, statsTracker: StatsTracker): ConstructiveResult = {
    statsTracker.setTimeStart()

    val maxIntWeight = Int.MaxValue - inst.maxPotentialPrice
    val W = Array.ofDim[TableCell](inst.numberOfItems + 1, inst.maxPotentialPrice + 1)

    for(i <- (0 to inst.numberOfItems)) {
      for(c <- (0 to inst.maxPotentialPrice)) {
        W(i)(c) = TableCell(maxIntWeight, i, c)
      }
    }

    W(0)(0) = TableCell(0, 0, 0)

    for(i <- (0 to inst.numberOfItems)) {
      for(c <- (0 to inst.maxPotentialPrice)) {
        if(i == 0 && c > 0) {
          W(i)(c) = TableCell(maxIntWeight, i, c)
        } else if(i > 0) {
          if(c - inst.items(i - 1).price < 0) {
            W(i)(c) = TableCell(W(i - 1)(c).weight, i, c)
          } else {
            W(i)(c) = TableCell(Math.min(W(i - 1)(c).weight, W(i - 1)(c - inst.items(i - 1).price).weight + inst.items(i - 1).weight), i, c)
          }
        }
      }
    }

    var finalChosenItems = inst.chosenItems
    val maxWeight = W(inst.numberOfItems).filter(_.weight <= inst.knapsackCapacity).maxBy(_.costIndex)
    val maxPrice = maxWeight.costIndex
    var c: Int = maxPrice
    for (i <- (inst.numberOfItems.until(0, -1))) {
      if(W(i)(c).weight != W(i - 1)(c).weight) {
        finalChosenItems = finalChosenItems.updated(i - 1, true)
        c -= inst.items(i - 1).price
      }
    }

    ConstructiveResult(maxPrice, finalChosenItems, statsTracker)
  }
}
