package cz.cvut.fit.juriczby
package common.file

import cz.cvut.fit.juriczby.common.knapsack.instance.Result
import cz.cvut.fit.juriczby.common.knapsack.instance.contructive.ConstructiveResult
import cz.cvut.fit.juriczby.common.stats.StatsTracker

import java.io.{BufferedWriter, File, FileWriter}

object FileSaver {
  def saveFileNum(numbers: Seq[Long], filename: String, homeworkNumber: Int): Unit = saveFile(numbers.map(n => n.toString), filename, homeworkNumber)

  def saveFile(lines: Seq[String], filename: String, homeworkNumber: Int): Unit = {
    val file = new File("/results/HW" + homeworkNumber + "/" + filename)
    val bw = new BufferedWriter(new FileWriter(file))
    for (line <- lines) {
      bw.write(line)
      bw.write("\n")
    }
    bw.close()
    println(s"Saved file '$filename'")
  }

  def saveFileResults(results: Seq[Seq[Result]], filename: String, homeworkNumber: Int): Unit = {
    val file = new File("./results/HW" + homeworkNumber + "/" + filename)
    val bw = new BufferedWriter(new FileWriter(file))
    val countsOutput = results.map(rf => rf.map(r => r.statsTracker.getConfigurationsCount).mkString(" ")).mkString("", "\n", "\n")
    val timesOutput = results.map(rf => rf.map(r => r.statsTracker.getTimeNano).mkString(" ")).mkString("", "\n", "\n")
    bw.write(countsOutput)
    bw.write("=\n")
    bw.write(timesOutput)
    bw.close()
    println(s"Saved file '$filename'")
  }

  def saveFileConstructiveResults(results: Seq[Seq[ConstructiveResult]], filename: String, homeworkNumber: Int): Unit = {
    val file = new File("./results/HW" + homeworkNumber + "/" + filename)
    val bw = new BufferedWriter(new FileWriter(file))
    val countsOutput = results.map(rf => rf.map(r => r.statsTracker.getConfigurationsCount).mkString(" ")).mkString("", "\n", "\n")
    val timesOutput = results.map(rf => rf.map(r => r.statsTracker.getTimeNano).mkString(" ")).mkString("", "\n", "\n")
    val resultsOutput = results.map(rf => rf.map(r => r.maxPrice).mkString(" ")).mkString("", "\n", "\n")
    bw.write(countsOutput)
    bw.write("=\n")
    bw.write(timesOutput)
    bw.write("=\n")
    bw.write(resultsOutput)
    bw.close()
    println(s"Saved file '$filename'")
  }

  def saveProgression(result: ConstructiveResult, filename: String, homeworkNumber: Int): Unit = {
    val file = new File("./results/HW" + homeworkNumber + "/" + filename)
    val bw = new BufferedWriter(new FileWriter(file))
    val progressionOutput = result.statsTracker.getProgression.mkString("", " ", "\n")
    bw.write(progressionOutput)
    bw.write("=\n")
    bw.write(result.statsTracker.getTimeNano.toString)
    bw.close()
    println(s"Saved file '$filename'")
  }
}