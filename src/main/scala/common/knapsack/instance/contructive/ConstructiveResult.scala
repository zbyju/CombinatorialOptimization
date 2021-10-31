package cz.cvut.fit.juriczby
package common.knapsack.instance.contructive

import common.knapsack.instance.Result
import common.stats.StatsTracker

case class ConstructiveResult(maxPrice: Int, chosenItems: Seq[Boolean], override val statsTracker: StatsTracker) extends Result(statsTracker) {
  override def toString() = {
    s"\nMax Price: $maxPrice ($chosenItems), $statsTracker"
  }
}
