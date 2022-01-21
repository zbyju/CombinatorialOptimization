package cz.cvut.fit.juriczby
package hw2

import common.file.FileSaver

import cz.cvut.fit.juriczby.common.file.instancefiles.constructive.ConstructiveInstanceFile
import cz.cvut.fit.juriczby.common.knapsack.instance.contructive.{ConstructiveInstance, ConstructiveResult}
import cz.cvut.fit.juriczby.common.knapsack.solver.constructive.{AbstractConstructiveKnapsackSolver, BBConstructiveKnapsackSolver, ConstructiveKnapsackSolver, DPPriceConstructiveKnapsackSolver, FPTASConstructiveKnapsackSolver, GreedyConstructiveKnapsackSolver, GreedyReduxConstructiveKnapsackSolver}
import cz.cvut.fit.juriczby.common.stats.StatsTracker
import cz.cvut.fit.juriczby.common.TMain

object HW2Main extends TMain(2) {
  val fl = new HW2FileLoader()
  val solver = new ConstructiveKnapsackSolver()
  val BBsolver = new BBConstructiveKnapsackSolver()
  val Gsolver = new GreedyConstructiveKnapsackSolver()
  val GRsolver = new GreedyReduxConstructiveKnapsackSolver()
  val DPsolver = new DPPriceConstructiveKnapsackSolver()
  val Fsolver = new FPTASConstructiveKnapsackSolver()

  def runInstance(instance: ConstructiveInstance, solver: AbstractConstructiveKnapsackSolver): ConstructiveResult = {
    val result = solver.solve(instance, new StatsTracker)
    println(s"    Done instance - ${instance.id} (${instance.name}) - ${solver.name}")
    result
  }

  def runInstanceById(file: ConstructiveInstanceFile, instanceId: Int, solver: AbstractConstructiveKnapsackSolver): ConstructiveResult = {
    runInstance(file.instances(instanceId), solver)
  }

  def runInstancesByFile(file: ConstructiveInstanceFile, solver: AbstractConstructiveKnapsackSolver): Seq[ConstructiveResult] = {
    val result = file.instances.map(i => runInstance(i, solver))
    println(s"  Done file - ${file.id} (${file.name}) ${solver.name}")
    result
  }

  def runAllFiles(instanceFiles: Seq[ConstructiveInstanceFile], solver: AbstractConstructiveKnapsackSolver): Seq[Seq[ConstructiveResult]] = {
    instanceFiles.map(f => runInstancesByFile(f, solver))
  }

  def runAllFiles(maxIndex: Int, instanceFiles: Seq[ConstructiveInstanceFile], solver: AbstractConstructiveKnapsackSolver): Seq[Seq[ConstructiveResult]] = {
    runAllFiles(instanceFiles.zipWithIndex.filter(f => f._2 <= maxIndex).map(f => f._1), solver)
  }

  def runAllFiles(minIndex: Int, maxIndex: Int, instanceFiles: Seq[ConstructiveInstanceFile], solver: AbstractConstructiveKnapsackSolver): Seq[Seq[ConstructiveResult]] = {
    runAllFiles(instanceFiles.zipWithIndex.filter(f => f._2 >= minIndex && f._2 <= maxIndex).map(f => f._1), solver)
  }

  def runSolver(solver: AbstractConstructiveKnapsackSolver): Unit = {
    val statsNK = runAllFiles(fl.instanceFilesNK, solver)
    println(s"DONE - ${solver.name} - NK")
    FileSaver.saveFileConstructiveResults(statsNK, solver.name + "NK", 2)

    val statsZKC = runAllFiles(fl.instanceFilesZKC, solver)
    println(s"DONE - ${solver.name} - ZKC")
    FileSaver.saveFileConstructiveResults(statsZKC, solver.name + "ZKC", 2)

    val statsZKW = runAllFiles(fl.instanceFilesZKW, solver)
    println(s"DONE - ${solver.name} - ZKW")
    FileSaver.saveFileConstructiveResults(statsZKW, solver.name + "ZKW", 2)

    println("DONE - " + solver.name)
  }

  def runSolver(maxIndex: Int, solver: AbstractConstructiveKnapsackSolver): Unit = {
    val statsNK = runAllFiles(maxIndex, fl.instanceFilesNK, solver)
    println(s"DONE - ${solver.name} - NK")
    FileSaver.saveFileConstructiveResults(statsNK, solver.name + "NK", 2)

    val statsZKC = runAllFiles(Math.max(maxIndex - 2, 0), fl.instanceFilesZKC, solver)
    println(s"DONE - ${solver.name} - ZKC")
    FileSaver.saveFileConstructiveResults(statsZKC, solver.name + "ZKC", 2)

    val statsZKW = runAllFiles(Math.max(maxIndex - 2, 0), fl.instanceFilesZKW, solver)
    println(s"DONE - ${solver.name} - ZKW")
    FileSaver.saveFileConstructiveResults(statsZKW, solver.name + "ZKW", 2)

    println("DONE - " + solver.name)
  }

  def bruteForce(maxIndex: Int): Unit = runSolver(maxIndex, solver)
  def branchBounds(maxIndex: Int): Unit = runSolver(maxIndex, BBsolver)
  def dynamicProgrammingPrice(maxIndex: Int): Unit = runSolver(maxIndex, DPsolver)
  def FPTAS(maxIndex: Int, epsilon: Double): Unit = runSolver(maxIndex, new FPTASConstructiveKnapsackSolver(epsilon))
  def FPTAS(maxIndex: Int): Unit = Seq(0.001, 0.01, 0.1, 0.3, 0.5, 0.7, 0.9, 1.0).foreach(FPTAS(maxIndex, _))
  def greedy(maxIndex: Int): Unit = runSolver(maxIndex, Gsolver)
  def redux(maxIndex: Int): Unit = runSolver(maxIndex, GRsolver)

  override def main(args: Array[String]): Unit = {
//    runInstanceById(fl.instanceFilesNK(3), 475, new FPTASConstructiveKnapsackSolver(0.001))
    branchBounds(1)
  }
}