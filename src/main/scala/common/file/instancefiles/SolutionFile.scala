package cz.cvut.fit.juriczby
package common.file.instancefiles

import common.knapsack.instance.Solution

class SolutionFile(val id: Int, val path: String, val name: String, private val lines: Seq[String]) {
  val solutions = lines.map(l => Solution(name, l))

  override def toString = {
    s"""Instance File - $path (id: $id)
       |Instances:
       |${solutions.mkString("\n")}
       |""".stripMargin
  }
}