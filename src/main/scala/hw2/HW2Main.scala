package cz.cvut.fit.juriczby
package hw2

import common.file.FileSaver

import cz.cvut.fit.juriczby.common.file.instancefiles.constructive.ConstructiveInstanceFile
import cz.cvut.fit.juriczby.common.knapsack.instance.contructive.ConstructiveResult
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
    println("Done Bruteforce")

    val statsBBNK = runAllFiles(maxIndex, fl.instanceFilesNK, BBsolver)
    val statsBBZKC = runAllFiles(maxIndexZR, fl.instanceFilesZKC, BBsolver)
    val statsBBZKW = runAllFiles(maxIndexZR, fl.instanceFilesZKW, BBsolver)
    println("Done BnB")

    val statsGNK = runAllFiles(maxIndex, fl.instanceFilesNK, Gsolver)
    val statsGZKC = runAllFiles(maxIndexZR, fl.instanceFilesZKC, Gsolver)
    val statsGZKW = runAllFiles(maxIndexZR, fl.instanceFilesZKW, Gsolver)
    println("Done Greedy")

    val statsGRNK = runAllFiles(maxIndex, fl.instanceFilesNK, GRsolver)
    val statsGRZKC = runAllFiles(maxIndexZR, fl.instanceFilesZKC, GRsolver)
    val statsGRZKW = runAllFiles(maxIndexZR, fl.instanceFilesZKW, GRsolver)
    println("Done Greedy redux")

    val statsDPNK = runAllFiles(maxIndex, fl.instanceFilesNK, DPsolver)
    val statsDPZKC = runAllFiles(maxIndexZR, fl.instanceFilesZKC, DPsolver)
    val statsDPZKW = runAllFiles(maxIndexZR, fl.instanceFilesZKW, DPsolver)
    println("Done Dynamic programming")

    val statsFNK = runAllFiles(maxIndex, fl.instanceFilesNK, Fsolver)
    val statsFZKC = runAllFiles(maxIndexZR, fl.instanceFilesZKC, Fsolver)
    val statsFZKW = runAllFiles(maxIndexZR, fl.instanceFilesZKW, Fsolver)
    println("Done FPTAS")

    FileSaver.saveFileConstructiveResults(statsNK, "NK", 2)
    FileSaver.saveFileConstructiveResults(statsZKC, "ZKC", 2)
    FileSaver.saveFileConstructiveResults(statsZKW, "ZKW", 2)

    FileSaver.saveFileConstructiveResults(statsBBNK, "BBNK", 2)
    FileSaver.saveFileConstructiveResults(statsBBZKC, "BBZKC", 2)
    FileSaver.saveFileConstructiveResults(statsBBZKW, "BBZKW", 2)

    FileSaver.saveFileConstructiveResults(statsGNK, "GNK", 2)
    FileSaver.saveFileConstructiveResults(statsGZKC, "GZKC", 2)
    FileSaver.saveFileConstructiveResults(statsGZKW, "GZKW", 2)

    FileSaver.saveFileConstructiveResults(statsGRNK, "GRNK", 2)
    FileSaver.saveFileConstructiveResults(statsGRZKC, "GRZKC", 2)
    FileSaver.saveFileConstructiveResults(statsGRZKW, "GRZKW", 2)

    FileSaver.saveFileConstructiveResults(statsDPNK, "DPNK", 2)
    FileSaver.saveFileConstructiveResults(statsDPZKC, "DPZKC", 2)
    FileSaver.saveFileConstructiveResults(statsDPZKW, "DPZKW", 2)

    FileSaver.saveFileConstructiveResults(statsFNK, "FNK", 2)
    FileSaver.saveFileConstructiveResults(statsFZKC, "FZKC", 2)
    FileSaver.saveFileConstructiveResults(statsFZKW, "FZKW", 2)
  }
}