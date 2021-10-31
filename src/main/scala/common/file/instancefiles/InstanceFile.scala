package cz.cvut.fit.juriczby
package common.file.instancefiles

import common.knapsack.instance.decision.DecisionInstance

import cz.cvut.fit.juriczby.common.knapsack.instance.Instance

abstract class InstanceFile(val id: Int, val path: String, val name: String, protected val lines: Seq[String]) {
  val instances: Seq[Instance]

  override def toString = {
    s"""Instance File - $path (id: $id)
       |Instances:
       |${instances.mkString("\n")}
       |""".stripMargin
  }
}
