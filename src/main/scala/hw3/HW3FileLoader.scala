package cz.cvut.fit.juriczby
package hw3

import common.file.FileLoader

import cz.cvut.fit.juriczby.common.file.instancefiles.constructive.ConstructiveInstanceFile

case class HW3FileLoader() extends FileLoader {
  val pathRatioCapacityToWeight = "/HW3/RatioCapacityToWeight/"
  val pathCorrelationPriceWeight = "/HW3/CorrelationPriceWeight/"
  val pathWeightDistribution = "/HW3/WeightDistribution/"
  val pathGranularityLight = "/HW3/GranularityLight/"
  val pathGranularityHeavy = "/HW3/GranularityHeavy/"
  val pathMaxWeight = "/HW3/MaxWeight/"
  val pathMaxCost = "/HW3/MaxCost/"
  val pathNormal = "/HW3/Normal/"

  val filenamesRatioCapacityToWeight = getListOfFiles(pathRatioCapacityToWeight).sorted
  val filenamesCorrelationPriceWeight = getListOfFiles(pathCorrelationPriceWeight).sorted
  val filenamesWeightDistribution = getListOfFiles(pathWeightDistribution).sorted
  val filenamesGranularityLight = getListOfFiles(pathGranularityLight).sorted
  val filenamesGranularityHeavy = getListOfFiles(pathGranularityHeavy).sorted
  val filenamesMaxWeight = getListOfFiles(pathMaxWeight).sorted
  val filenamesMaxCost = getListOfFiles(pathMaxCost).sorted
  val filenamesNormal = getListOfFiles(pathNormal).sorted

  val instanceFilesRatio = filenamesRatioCapacityToWeight.zipWithIndex.map(f => new ConstructiveInstanceFile(f._2, pathRatioCapacityToWeight + f._1, f._1.substring(f._1.lastIndexOf("/") + 1), getInputLines(getInputFile(f._1)).toList))
  val instanceFilesCorr = filenamesCorrelationPriceWeight.zipWithIndex.map(f => new ConstructiveInstanceFile(f._2, pathCorrelationPriceWeight + f._1, f._1.substring(f._1.lastIndexOf("/") + 1), getInputLines(getInputFile(f._1)).toList))
  val instanceFilesDistr = filenamesWeightDistribution.zipWithIndex.map(f => new ConstructiveInstanceFile(f._2, pathWeightDistribution + f._1, f._1.substring(f._1.lastIndexOf("/") + 1), getInputLines(getInputFile(f._1)).toList))
  val instanceFilesLight = filenamesGranularityLight.zipWithIndex.map(f => new ConstructiveInstanceFile(f._2, pathGranularityLight + f._1, f._1.substring(f._1.lastIndexOf("/") + 1), getInputLines(getInputFile(f._1)).toList))
  val instanceFilesHeavy = filenamesGranularityHeavy.zipWithIndex.map(f => new ConstructiveInstanceFile(f._2, pathGranularityHeavy + f._1, f._1.substring(f._1.lastIndexOf("/") + 1), getInputLines(getInputFile(f._1)).toList))
  val instanceFilesWeight = filenamesMaxWeight.zipWithIndex.map(f => new ConstructiveInstanceFile(f._2, pathMaxWeight + f._1, f._1.substring(f._1.lastIndexOf("/") + 1), getInputLines(getInputFile(f._1)).toList))
  val instanceFilesCost = filenamesMaxCost.zipWithIndex.map(f => new ConstructiveInstanceFile(f._2, pathMaxCost + f._1, f._1.substring(f._1.lastIndexOf("/") + 1), getInputLines(getInputFile(f._1)).toList))
  val instanceFilesNormal = filenamesNormal.zipWithIndex.map(f => new ConstructiveInstanceFile(f._2, pathNormal + f._1, f._1.substring(f._1.lastIndexOf("/") + 1), getInputLines(getInputFile(f._1)).toList))
}