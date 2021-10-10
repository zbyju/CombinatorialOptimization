package cz.cvut.fit.juriczby
package DU1

import Common.KnapsackProblem.{DecisionInstance, Solution}
import Common.KnapsackProblem.Solvers.{AbstractDecisionKnapsackSolver, BBDecisionKnapsackSolver, DecisionKnapsackSolver}
import Common.{FileLoader, StatsTracker}

import cz.cvut.fit.juriczby.Common.FileSaver.{saveFile, saveFileNum}

object DU1FileLoader extends FileLoader {
  val pathNR = "/DU1/NR/"
  val pathZR = "/DU1/ZR/"
  def filesNR: Seq[String] = getListOfFiles(pathNR).filter(f => f.endsWith("_inst.dat")).sorted
  def filesZR: Seq[String] = getListOfFiles(pathZR).filter(f => f.endsWith("_inst.dat")).sorted
  def solFilesNR: Seq[String] = getListOfFiles(pathNR).filter(f => f.endsWith("_sol.dat")).sorted
  def solFilesZR: Seq[String] = getListOfFiles(pathZR).filter(f => f.endsWith("_sol.dat")).sorted
  def getLinesByFileIndex(i: Int, filenames: Seq[String]): Seq[String] = getInputLines(getInputFile(filenames(i))).toList
  def instances(i: Int, filenames: Seq[String]): Seq[DecisionInstance] = getLinesByFileIndex(i, filenames).map(l => DecisionInstance(filenames(i), l))
  def solutions(i: Int, filenames: Seq[String]): Seq[Solution] = getLinesByFileIndex(i, filenames).map(l => Solution(filenames(i), l))
  def sampleSols(i: Int, filenames: Seq[String], filenamesSols: Seq[String]): Seq[(DecisionInstance, Solution)] = instances(i, filenames).map(x => (x,
                                                  solutions(i, filenamesSols).find(_.id == x.id)
                                                    .getOrElse(Solution(s"Could not find ${x.id}", 0, 0, 0, Seq()))))

  def run(inst: DecisionInstance, solver: AbstractDecisionKnapsackSolver, statsTracker: StatsTracker): StatsTracker = {
    solver.solve(inst, statsTracker)
    statsTracker
  }

  def runAll(maxIndex: Int, solver: AbstractDecisionKnapsackSolver, files: Seq[String]): Seq[Seq[StatsTracker]] = {
    files.zipWithIndex.filter(x => x._2 <= maxIndex)
      .map(x => instances(x._2, files)).map(file => file.map(i => {
        val statsTracker = StatsTracker()
        run(i, solver, statsTracker)
    }))
  }

  def runIndex(index: Int, solver: AbstractDecisionKnapsackSolver, files: Seq[String]): Seq[Seq[StatsTracker]] = {
    files(index)
      .map(x => instances(index, files)).map(file => file.map(i => {
      val statsTracker = StatsTracker()
      run(i, solver, statsTracker)
    }))
  }

  def main(args: Array[String]): Unit = {
    val solver = new DecisionKnapsackSolver()
    val bbSolver = new BBDecisionKnapsackSolver()
    val maxIndex = 7
    val maxIndexZR = 5
    val statsNR = runAll(maxIndex, solver, filesNR)
    println("NR Done")
    val statsZR = runAll(maxIndexZR, solver, filesZR)
    println("ZR Done")
    val statsBBNR = runAll(maxIndex, bbSolver, filesNR)
    println("BBNR Done")
    val statsBBZR = runAll(maxIndexZR, bbSolver, filesZR)
    println("BBZR Done")
    saveFile(statsNR.map(file => file.map(inst => inst.getConfigurationsCount).mkString(",")), "countsNR", "counts")
    saveFile(statsZR.map(file => file.map(inst => inst.getConfigurationsCount).mkString(",")), "countsZR", "counts")
    saveFile(statsBBNR.map(file => file.map(inst => inst.getConfigurationsCount).mkString(",")), "countsBBNR", "counts")
    saveFile(statsBBZR.map(file => file.map(inst => inst.getConfigurationsCount).mkString(",")), "countsBBZR", "counts")

    saveFile(statsNR.map(file => file.map(inst => inst.getTimeMillis).mkString(",")), "timesNR", "times")
    saveFile(statsZR.map(file => file.map(inst => inst.getTimeMillis).mkString(",")), "timesZR", "times")
    saveFile(statsBBNR.map(file => file.map(inst => inst.getTimeMillis).mkString(",")), "timesBBNR", "times")
    saveFile(statsBBZR.map(file => file.map(inst => inst.getTimeMillis).mkString(",")), "timesBBZR", "times")
  }
}
