package cz.cvut.fit.juriczby
package common.file

import cz.cvut.fit.juriczby.common.knapsack.instance.decision.DecisionResult

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

  def saveFileDecisionResults(results: Seq[Seq[DecisionResult]], filename: String, homeworkNumber: Int): Unit = {
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
}