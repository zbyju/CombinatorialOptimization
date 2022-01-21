package cz.cvut.fit.juriczby
package hw4

import common.file.FileLoader

import cz.cvut.fit.juriczby.common.file.instancefiles.constructive.ConstructiveInstanceFile

case class HW4FileLoader() extends FileLoader {
  val pathNK = "/HW2/NK/"

  val filenamesNK = getListOfFiles(pathNK).sorted

  val instanceFilesNK = filenamesNK.zipWithIndex.map(f => new ConstructiveInstanceFile(f._2, pathNK + f._1, f._1.substring(f._1.lastIndexOf("/") + 1), getInputLines(getInputFile(f._1)).toList))
}