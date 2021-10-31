package cz.cvut.fit.juriczby
package hw2

import common.file.FileLoader
import common.file.instancefiles.SolutionFile

import cz.cvut.fit.juriczby.common.file.instancefiles.constructive.ConstructiveInstanceFile

case class HW2FileLoader() extends FileLoader {
  val pathNK = "/HW2/NK/"
  val pathZKC = "/HW2/ZKC/"
  val pathZKW = "/HW2/ZKW/"
  val instanceFileSuffix = "_inst.dat"
  val solutionFileSuffix = "_sol.dat"

  val filenamesNK = getListOfFiles(pathNK).filter(f => f.endsWith(instanceFileSuffix)).sorted
  val solFilenamesNK = getListOfFiles(pathNK).filter(f => f.endsWith(solutionFileSuffix)).sorted
  val filenamesZKC = getListOfFiles(pathZKC).filter(f => f.endsWith(instanceFileSuffix)).sorted
  val solFilenamesZKC = getListOfFiles(pathZKC).filter(f => f.endsWith(solutionFileSuffix)).sorted
  val filenamesZKW = getListOfFiles(pathZKW).filter(f => f.endsWith(instanceFileSuffix)).sorted
  val solFilenamesZKW = getListOfFiles(pathZKW).filter(f => f.endsWith(solutionFileSuffix)).sorted

  val instanceFilesNK = filenamesNK.zipWithIndex.map(f => new ConstructiveInstanceFile(f._2, pathNK + f._1, f._1, getInputLines(getInputFile(f._1)).toList))
  val instanceFilesZKC = filenamesZKC.zipWithIndex.map(f => new ConstructiveInstanceFile(f._2, pathZKC + f._1, f._1, getInputLines(getInputFile(f._1)).toList))
  val instanceFilesZKW = filenamesZKW.zipWithIndex.map(f => new ConstructiveInstanceFile(f._2, pathZKW + f._1, f._1, getInputLines(getInputFile(f._1)).toList))

  val solutionFilesNK = solFilenamesNK.zipWithIndex.map(f => new SolutionFile(f._2, pathNK + f._1, f._1, getInputLines(getInputFile(f._1)).toList))
  val solutionFilesZKC = solFilenamesZKC.zipWithIndex.map(f => new SolutionFile(f._2, pathZKC + f._1, f._1, getInputLines(getInputFile(f._1)).toList))
  val solutionFilesZKW = solFilenamesZKW.zipWithIndex.map(f => new SolutionFile(f._2, pathZKW + f._1, f._1, getInputLines(getInputFile(f._1)).toList))
}
