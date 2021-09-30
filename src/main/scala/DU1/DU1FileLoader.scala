package cz.cvut.fit.juriczby
package DU1

import Common.KnapsackProblem.{DecisionInstance, Solution}
import Common.KnapsackProblem.Solvers.{AbstractDecisionKnapsackSolver, BBDecisionKnapsackSolver, DecisionKnapsackSolver}
import Common.FileLoader

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

  def run(fileIndex: Int, solver: AbstractDecisionKnapsackSolver): Unit = {
    val inst = instances(fileIndex, filesZR)
    val sSols = sampleSols(fileIndex, filesZR, solFilesZR)

    val sols = inst.map(i => solver.solve(i))
    println(s"True: ${sols.count(x => x)} | ${sSols.count(x => x._2.totalPrice >= x._1.minPrice)}")
    println(s"False: ${sols.count(x => !x)} | ${sSols.count(x => x._2.totalPrice < x._1.minPrice)}")
  }

  def run(fileIndex: Int, instanceIndex: Int, solver: AbstractDecisionKnapsackSolver): Unit = {
    val inst = instances(fileIndex, filesNR)
    val sol = solver.solve(inst(instanceIndex))
    val sSol = sampleSols(fileIndex, filesNR, solFilesNR)(instanceIndex)
    println(s"$instanceIndex | My solution: $sol | Sample Solution: ${sSol._2.totalPrice >= sSol._1.minPrice}")
  }

  def timedRun(fileIndex: Int, solver: AbstractDecisionKnapsackSolver): Unit = {
    val startTime = System.currentTimeMillis()
    run(fileIndex, solver)
    val endTime = System.currentTimeMillis()
    val time = (endTime - startTime) / 1000
    println(s"Total time: ${time}s")
  }

  def timedRun(fileIndex: Int, instanceIndex: Int, solver: AbstractDecisionKnapsackSolver): Unit = {
    val startTime = System.currentTimeMillis()
    run(fileIndex, instanceIndex, solver)
    val endTime = System.currentTimeMillis()
    val time = (endTime - startTime) / 1000
    println(s"Total time: ${time}s")
  }

  def main(args: Array[String]): Unit = {
    val solver = new DecisionKnapsackSolver()
    val bbSolver = new BBDecisionKnapsackSolver()
    timedRun(2, solver)
    timedRun(2, bbSolver)
  }
}
