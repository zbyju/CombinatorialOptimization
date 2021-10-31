package cz.cvut.fit.juriczby
package hw1

import common.file.FileLoader
import common.file.instancefiles.{InstanceFile, SolutionFile}

import cz.cvut.fit.juriczby.common.knapsack.instance.Solution

case class HW1FileLoader() extends FileLoader {
  val pathNR = "/HW1/NR/"
  val pathZR = "/HW1/NR/"
  val instanceFileSuffix = "_inst.dat"
  val solutionFileSuffix = "_sol.dat"

  val filenamesNR = getListOfFiles(pathNR).filter(f => f.endsWith(instanceFileSuffix)).sorted
  val solFilenamesNR = getListOfFiles(pathNR).filter(f => f.endsWith(solutionFileSuffix)).sorted
  val filenamesZR = getListOfFiles(pathZR).filter(f => f.endsWith(instanceFileSuffix)).sorted
  val solFilenamesZR = getListOfFiles(pathZR).filter(f => f.endsWith(solutionFileSuffix)).sorted

  val instanceFilesNR = filenamesNR.zipWithIndex.map(f => new InstanceFile(f._2, pathNR + f._1, f._1, getInputLines(getInputFile(f._1)).toList))
  val instanceFilesZR = filenamesZR.zipWithIndex.map(f => new InstanceFile(f._2, pathZR + f._1, f._1, getInputLines(getInputFile(f._1)).toList))

  val solutionFilesNR = solFilenamesNR.zipWithIndex.map(f => new SolutionFile(f._2, pathNR + f._1, f._1, getInputLines(getInputFile(f._1)).toList))
  val solutionFilesZR = solFilenamesZR.zipWithIndex.map(f => new SolutionFile(f._2, pathZR + f._1, f._1, getInputLines(getInputFile(f._1)).toList))
}
