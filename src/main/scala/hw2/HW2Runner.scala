package cz.cvut.fit.juriczby
package hw2

import common.file.FileSaver

import cz.cvut.fit.juriczby.common.file.instancefiles.constructive.ConstructiveInstanceFile
import cz.cvut.fit.juriczby.common.knapsack.instance.contructive.ConstructiveResult
import cz.cvut.fit.juriczby.common.knapsack.solver.constructive.{AbstractConstructiveKnapsackSolver, ConstructiveKnapsackSolver}
import cz.cvut.fit.juriczby.common.stats.StatsTracker
import cz.cvut.fit.juriczby.common.Runner

object HW2Runner extends Runner(2) {
  val fl = new HW2FileLoader()
  val solver = new ConstructiveKnapsackSolver()

  def runInstanceById(file: ConstructiveInstanceFile, instanceId: Int, solver: AbstractConstructiveKnapsackSolver): ConstructiveResult = {
    solver.solve(file.instances(instanceId), new StatsTracker())
  }

  def runInstancesByFile(file: ConstructiveInstanceFile, solver: AbstractConstructiveKnapsackSolver): Seq[ConstructiveResult] = {
    file.instances.map(i => solver.solve(i, new StatsTracker()))
  }

  def runAllFiles(instanceFiles: Seq[ConstructiveInstanceFile], solver: AbstractConstructiveKnapsackSolver): Seq[Seq[ConstructiveResult]] = {
    instanceFiles.map(f => f.instances.map(i => solver.solve(i, new StatsTracker)))
  }

  def runAllFiles(maxIndex: Int, instanceFiles: Seq[ConstructiveInstanceFile], solver: AbstractConstructiveKnapsackSolver): Seq[Seq[ConstructiveResult]] = {
    instanceFiles.zipWithIndex.filter(f => f._2 <= maxIndex)
      .map(f => f._1.instances.map(i => solver.solve(i, new StatsTracker)))
  }

  def runAllFiles(minIndex: Int, maxIndex: Int, instanceFiles: Seq[ConstructiveInstanceFile], solver: AbstractConstructiveKnapsackSolver): Seq[Seq[ConstructiveResult]] = {
    instanceFiles.zipWithIndex.filter(f => f._2 >= minIndex && f._2 <= maxIndex)
      .map(f => f._1.instances.map(i => solver.solve(i, new StatsTracker)))
  }

  override def main(args: Array[String]): Unit = {
    val maxIndex = 0
    val maxIndexZR = 0
    val statsNK = runAllFiles(maxIndex, fl.instanceFilesNK, solver)
    val statsZKC = runAllFiles(maxIndexZR, fl.instanceFilesZKC, solver)
    val statsZKW = runAllFiles(maxIndexZR, fl.instanceFilesZKW, solver)
    FileSaver.saveFileResults(statsNK, "NK", 2)
    FileSaver.saveFileResults(statsZKC, "ZKC", 2)
    FileSaver.saveFileResults(statsZKW, "ZKW", 2)
  }
}
