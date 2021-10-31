package cz.cvut.fit.juriczby
package common.knapsack.instance.decision

import common.knapsack.instance.Result

import cz.cvut.fit.juriczby.common.stats.StatsTracker

case class DecisionResult(result: Boolean, override val statsTracker: StatsTracker) extends Result(statsTracker) {
  override def toString() = {
    s"\nResult: $result, $statsTracker"
  }
}
