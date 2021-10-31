package cz.cvut.fit.juriczby
package common.knapsack.solver.constructive

import common.knapsack.instance.contructive.{ConstructiveInstance, ConstructiveResult}

import cz.cvut.fit.juriczby.common.stats.StatsTracker

abstract class AbstractConstructiveKnapsackSolver {
  def solve(i: ConstructiveInstance, statsTracker: StatsTracker): ConstructiveResult
}
