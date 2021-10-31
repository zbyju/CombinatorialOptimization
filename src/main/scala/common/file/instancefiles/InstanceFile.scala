package cz.cvut.fit.juriczby
package common.file.instancefiles

import common.knapsack.instance.decision.DecisionInstance

class InstanceFile(val id: Int, val path: String, val name: String, private val lines: Seq[String]) {
  val instances = lines.map(l => DecisionInstance(name, l))

  override def toString = {
    s"""Instance File - $path (id: $id)
       |Instances:
       |${instances.mkString("\n")}
       |""".stripMargin
  }
}
