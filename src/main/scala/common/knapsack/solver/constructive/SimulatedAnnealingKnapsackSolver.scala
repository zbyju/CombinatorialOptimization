package cz.cvut.fit.juriczby
package common.knapsack.solver.constructive
import common.stats.StatsTracker

import cz.cvut.fit.juriczby.common.knapsack.instance.contructive.{ConstructiveInstance, ConstructiveResult}

import scala.util.Random
import scala.math.exp

type Temperature = Double
type Frozen = (Temperature) => Boolean
type Equilibrium = (Int) => Boolean
type Cool = (Temperature) => Temperature
type Next = (ConstructiveInstance, Temperature) => ConstructiveInstance
type Best = Option[ConstructiveInstance]
type Better = (ConstructiveInstance, Best) => Boolean

case class SimulatedAnnealingState(var temperature: Temperature, var best: Best, var i: ConstructiveInstance) {
  val coolingCoefficient = 0.8
  val defaultEqui = i.numberOfItems * 1

  var equi = defaultEqui

  i = ConstructiveInstance.randomInstance(i)
}


class SimulatedAnnealingKnapsackSolver extends AbstractConstructiveKnapsackSolver {
  override val name: String = "SimulatedAnnealing-8"
  var config: SimulatedAnnealingState = _
  var statsTracker: StatsTracker = _

  def frozen(T: Temperature): Boolean = {
    T <= 25
  }

  def equilibrium(i: Int): Boolean = {
    i == 0
  }

  def cool(T: Temperature): Temperature = {
    config.coolingCoefficient * T
  }


  def next(i: ConstructiveInstance, T: Temperature): ConstructiveInstance = {
    val nextState = ConstructiveInstance.randomNextInstance(i)
    if(better(nextState, Some(i))) return nextState
    val delta = ConstructiveInstance.howMuchWorse(nextState, i)
    val rnd = new Random()
    if(rnd.nextDouble() < exp(-delta / T)) {
      return nextState
    }
    i
  }

  def better(i1: ConstructiveInstance, i2: Best): Boolean = {
    i2 match {
      case Some(x) => i1.chosenPrice > x.chosenPrice
      case None => true
    }
  }

  def calculateStartingTemperature(i: ConstructiveInstance): Temperature = {
    i.items.map(_.price).max
  }

  override def solve(i: ConstructiveInstance, statsTracker: StatsTracker): ConstructiveResult = {
    this.statsTracker = statsTracker
    statsTracker.setTimeStart()
    if(i.items.map(item => item.weight).min > i.knapsackCapacity) return ConstructiveResult(0, i.chosenItems, statsTracker)

    config = SimulatedAnnealingState(calculateStartingTemperature(i), None, i)

    while(!frozen(config.temperature)) {
      config.equi = config.defaultEqui
      while(!equilibrium(config.equi)) {
        config.i = next(config.i, config.temperature)
        statsTracker.addProgress(config.i.chosenPrice)
        if(better(config.i, config.best)) config.best = Some(config.i)
        config.equi -= 1
      }
      config.temperature = cool(config.temperature)
    }

    // Return result
    config.best match {
      case Some(value) => {
        ConstructiveResult(value.chosenPrice, value.chosenItems, statsTracker)
      }
      case None => ConstructiveResult(0, i.items.map(i => false), statsTracker)
    }
  }
}
