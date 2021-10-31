package cz.cvut.fit.juriczby
package hw1

import common.knapsack.solver.decision.{AbstractDecisionKnapsackSolver, BBDecisionKnapsackSolver, DecisionKnapsackSolver}

import cz.cvut.fit.juriczby.common.knapsack.instance.decision.DecisionResult
import cz.cvut.fit.juriczby.common.stats.StatsTracker
import cz.cvut.fit.juriczby.common.Runner
import cz.cvut.fit.juriczby.common.file.FileSaver
import cz.cvut.fit.juriczby.common.file.instancefiles.decision.DecisionInstanceFile

object HW1Runner extends Runner(1) {
  val fl = new HW1FileLoader()
  val solver = new DecisionKnapsackSolver()
  val bbSolver = new BBDecisionKnapsackSolver()

  def runInstanceById(file: DecisionInstanceFile, instanceId: Int, solver: AbstractDecisionKnapsackSolver): DecisionResult = {
    solver.solve(file.instances(instanceId), new StatsTracker())
  }

  def runInstancesByFile(file: DecisionInstanceFile, solver: AbstractDecisionKnapsackSolver): Seq[DecisionResult] = {
    file.instances.map(i => solver.solve(i, new StatsTracker()))
  }

  def runAllFiles(instanceFiles: Seq[DecisionInstanceFile], solver: AbstractDecisionKnapsackSolver): Seq[Seq[DecisionResult]] = {
    instanceFiles.map(f => f.instances.map(i => solver.solve(i, new StatsTracker)))
  }

  def runAllFiles(maxIndex: Int, instanceFiles: Seq[DecisionInstanceFile], solver: AbstractDecisionKnapsackSolver): Seq[Seq[DecisionResult]] = {
    instanceFiles.zipWithIndex.filter(f => f._2 <= maxIndex)
      .map(f => f._1.instances.map(i => solver.solve(i, new StatsTracker)))
  }

  def runAllFiles(minIndex: Int, maxIndex: Int, instanceFiles: Seq[DecisionInstanceFile], solver: AbstractDecisionKnapsackSolver): Seq[Seq[DecisionResult]] = {
    instanceFiles.zipWithIndex.filter(f => f._2 >= minIndex && f._2 <= maxIndex)
      .map(f => f._1.instances.map(i => solver.solve(i, new StatsTracker)))
  }

  override def main(args: Array[String]): Unit = {
    val maxIndex = 6
    val maxIndexZR = 4
    val statsNR = runAllFiles(maxIndex, fl.instanceFilesNR, solver)
    println("NR Done")
    val statsZR = runAllFiles(maxIndexZR, fl.instanceFilesZR, solver)
    println("ZR Done")
    val statsBBNR = runAllFiles(maxIndex, fl.instanceFilesNR, bbSolver)
    println("BBNR Done")
    val statsBBZR = runAllFiles(maxIndexZR, fl.instanceFilesZR, bbSolver)
    println("BBZR Done")
    FileSaver.saveFileResults(statsNR, "NR", 1)
    FileSaver.saveFileResults(statsZR, "ZR", 1)
    FileSaver.saveFileResults(statsBBNR, "BBNR", 1)
    FileSaver.saveFileResults(statsBBZR, "BBZR", 1)
  }
}
