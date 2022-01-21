package cz.cvut.fit.juriczby
package hw4

import common.TMain
import common.file.FileSaver
import common.file.instancefiles.constructive.ConstructiveInstanceFile
import common.knapsack.instance.contructive.{ConstructiveInstance, ConstructiveResult}
import common.knapsack.solver.constructive.{AbstractConstructiveKnapsackSolver, BBConstructiveKnapsackSolver, ConstructiveKnapsackSolver, DPPriceConstructiveKnapsackSolver, FPTASConstructiveKnapsackSolver, GreedyConstructiveKnapsackSolver, GreedyReduxConstructiveKnapsackSolver, SimulatedAnnealingKnapsackSolver}
import common.stats.StatsTracker
import hw2.HW2FileLoader

object HW4Main extends TMain(4) {
  val fl = HW2FileLoader()
  val bbSolver = new BBConstructiveKnapsackSolver()
  val saSolver = new SimulatedAnnealingKnapsackSolver()
  val grSolver = new GreedyReduxConstructiveKnapsackSolver()

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

  def testInstancesByFile(file: ConstructiveInstanceFile): Unit = {
    var correct = 0
    val result = file.instances.map(i => {
      val bb = bbSolver.solve(i, new StatsTracker)
//      print("*")
//      Console.flush()
      val sa = saSolver.solve(i, new StatsTracker)
//      print("|")
      if(bb.maxPrice == sa.maxPrice) correct += 1
    })
    println("\nCorrect ("+ file.name +"): " + correct)
  }

  def runAllFiles(instanceFiles: Seq[ConstructiveInstanceFile], solver: AbstractConstructiveKnapsackSolver): Seq[Seq[ConstructiveResult]] = {
    instanceFiles.map(f => runInstancesByFile(f, solver))
  }

  def testAllFiles(instanceFiles: Seq[ConstructiveInstanceFile], minIndex: Int = 0): Unit = {
    instanceFiles.drop(minIndex).foreach(f => {
      testInstancesByFile(f)
    })
  }

  def runAllFiles(maxIndex: Int, instanceFiles: Seq[ConstructiveInstanceFile], solver: AbstractConstructiveKnapsackSolver): Seq[Seq[ConstructiveResult]] = {
    runAllFiles(instanceFiles.zipWithIndex.filter(f => f._2 <= maxIndex).map(f => f._1), solver)
  }

  def runAllFiles(minIndex: Int, maxIndex: Int, instanceFiles: Seq[ConstructiveInstanceFile], solver: AbstractConstructiveKnapsackSolver): Seq[Seq[ConstructiveResult]] = {
    runAllFiles(instanceFiles.zipWithIndex.filter(f => f._2 >= minIndex && f._2 <= maxIndex).map(f => f._1), solver)
  }

  def runSolver(solver: AbstractConstructiveKnapsackSolver): Unit = {
    val statsNK = runAllFiles(fl.instanceFilesNK, solver)
    FileSaver.saveFileConstructiveResults(statsNK, solver.name, 4)
    println("DONE - " + solver.name)
  }

  def runSolver(maxIndex: Int, solver: AbstractConstructiveKnapsackSolver): Unit = {
    val statsNK = runAllFiles(maxIndex, fl.instanceFilesNK, solver)
    FileSaver.saveFileConstructiveResults(statsNK, solver.name, 4)
    println("DONE - " + solver.name)
  }

  def testInstance(): Unit = {
    val res = runInstance(fl.instanceFilesNK(10).instances.head, saSolver)
    val b = runInstance(fl.instanceFilesNK(10).instances.head, bbSolver)
    println(b.maxPrice +" vs "+ res.maxPrice)
    FileSaver.saveProgression(res, "freeze-1000", 4)
  }

  def branchBounds(maxIndex: Int): Unit = runSolver(maxIndex, bbSolver)
  def simulatedAnnealing(maxIndex: Int): Unit = runSolver(maxIndex, saSolver)
  def redux(maxIndex: Int): Unit = runSolver(maxIndex, grSolver)

  override def main(args: Array[String]): Unit = {
    simulatedAnnealing(99)
  }
}