package cz.cvut.fit.juriczby.Common

import java.io.{BufferedWriter, File, FileWriter}

object FileSaver {
  def saveFileNum(numbers: Seq[Long], filename: String, folder: String): Unit = saveFile(numbers.map(n => n.toString), filename, folder)

  def saveFile(lines: Seq[String], filename: String, folder: String): Unit = {
    val file = new File(filename)
    val bw = new BufferedWriter(new FileWriter(file))
    for (line <- lines) {
      bw.write(line)
      bw.write("\n")
    }
    bw.close()
    println(s"Saved file '$filename'")
  }
}
