package cz.cvut.fit.juriczby
package hw3

import common.file.FileSaver
import common.file.instancefiles.constructive.ConstructiveInstanceFile
import common.knapsack.instance.contructive.{ConstructiveInstance, ConstructiveResult}
import common.knapsack.solver.constructive.{AbstractConstructiveKnapsackSolver, BBConstructiveKnapsackSolver, ConstructiveKnapsackSolver, DPPriceConstructiveKnapsackSolver, FPTASConstructiveKnapsackSolver, GreedyConstructiveKnapsackSolver, GreedyReduxConstructiveKnapsackSolver}
import common.stats.StatsTracker

import cz.cvut.fit.juriczby.common.TMain

object HW3Main extends TMain(3)  {
  val fl = new HW3FileLoader()
  val solver = new ConstructiveKnapsackSolver()
  val BBsolver = new BBConstructiveKnapsackSolver()
  val Gsolver = new GreedyConstructiveKnapsackSolver()
  val GRsolver = new GreedyReduxConstructiveKnapsackSolver()
  val DPsolver = new DPPriceConstructiveKnapsackSolver()
  val Fsolver = new FPTASConstructiveKnapsackSolver()

  def runInstance(instance: ConstructiveInstance, solver: AbstractConstructiveKnapsackSolver): ConstructiveResult = {
    val result = solver.solve(instance, new StatsTracker)
//    println(s"    Done instance - ${instance.id} (${instance.name}) - ${solver.name}")
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

  def runSolver(files: List[ConstructiveInstanceFile], name: String, solver: AbstractConstructiveKnapsackSolver): Unit = {
    val statsNK = runAllFiles(files, solver)
    println(s"DONE - ${solver.name} - ${files(0).name}")
    FileSaver.saveFileConstructiveResults(statsNK, solver.name + "_" + name, 3)
  }

  def runRatio(): Unit = {
    val name = "ratio"
    runSolver(fl.instanceFilesRatio, name, BBsolver)
    runSolver(fl.instanceFilesRatio, name, DPsolver)
    runSolver(fl.instanceFilesRatio, name, Gsolver)
    println("=============================== RATIO DONE ===============================")
  }

  def runCorrelation(): Unit = {
    val name = "corr"
    runSolver(fl.instanceFilesCorr, name, BBsolver)
    runSolver(fl.instanceFilesCorr, name, DPsolver)
    runSolver(fl.instanceFilesCorr, name, Gsolver)
    println("=============================== CORR DONE ===============================")
  }

  def runDistribution(): Unit = {
    val name = "distr"
    runSolver(fl.instanceFilesDistr, name, BBsolver)
    runSolver(fl.instanceFilesDistr, name, DPsolver)
    runSolver(fl.instanceFilesDistr, name, Gsolver)
    println("=============================== DISTR DONE ===============================")
  }

  def runLightGranularity(): Unit = {
    val name = "light"
    runSolver(fl.instanceFilesLight, name, BBsolver)
    runSolver(fl.instanceFilesLight, name, DPsolver)
    runSolver(fl.instanceFilesLight, name, Gsolver)
    println("=============================== LIGHT DONE ===============================")
  }

  def runHeavyGranularity(): Unit = {
    val name = "heavy"
    runSolver(fl.instanceFilesHeavy, name, BBsolver)
    runSolver(fl.instanceFilesHeavy, name, DPsolver)
    runSolver(fl.instanceFilesHeavy, name, Gsolver)
    println("=============================== HEAVY DONE ===============================")
  }

  def runMaxWeight(): Unit = {
    val name = "weight"
    runSolver(fl.instanceFilesWeight, name, BBsolver)
    runSolver(fl.instanceFilesWeight, name, DPsolver)
    runSolver(fl.instanceFilesWeight, name, Gsolver)
    println("=============================== WEIGHT DONE ===============================")
  }

  def runMaxCost(): Unit = {
    val name = "cost"
    runSolver(fl.instanceFilesCost, name, BBsolver)
    runSolver(fl.instanceFilesCost, name, DPsolver)
    runSolver(fl.instanceFilesCost, name, Gsolver)
    println("=============================== COST DONE ===============================")
  }

  def runPermutationsAlgo(instanceFile: ConstructiveInstanceFile, solver: AbstractConstructiveKnapsackSolver): Any = {
    val results = instanceFile.instances.map(i => {
      val shuffledSet = Seq(i, i.shuffle(), i.shuffle(), i.shuffle(), i.shuffle(), i.shuffle(), i.shuffle(), i.shuffle(), i.shuffle(), i.shuffle())
      val results = shuffledSet.map(i => runInstance(i, solver).statsTracker.getTimeNano)
      val max = results.max
      val min = results.min
      (max, min, results.sum / results.length)
    })
    // Max, min, avg, avg difference, relative change, relative % change
    val max = results.map(r => r._1).max
    val min = results.map(r => r._1).min
    val avg = results.map(r => r._3).sum / results.length
    (
      max,
      min,
      avg,
      results.map(r => r._1 - r._2).sum / results.length,
      results.map(r => (r._1 - r._2).toDouble * 100 / r._2).sum / results.length,
      results.map(r => (r._1 - r._2).toDouble * 100 / r._3).sum / results.length
    )
  }

  def runPermutations(): Unit = {
    val instances = fl.instanceFilesNormal.head
    println(runPermutationsAlgo(instances, BBsolver))
    println(runPermutationsAlgo(instances, DPsolver))
    println(runPermutationsAlgo(instances, GRsolver))
  }

  override def main(args: Array[String]): Unit = {
//    runRatio()
//    runCorrelation()
//    runDistribution()
//    runLightGranularity()
//    runHeavyGranularity()
//    runMaxWeight()
//    runMaxCost()
    runPermutations()
  }
}
