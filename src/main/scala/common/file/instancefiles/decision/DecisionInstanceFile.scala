package cz.cvut.fit.juriczby
package common.file.instancefiles.decision

import common.file.instancefiles.InstanceFile
import cz.cvut.fit.juriczby.common.knapsack.instance.decision.DecisionInstance

case class DecisionInstanceFile( override val id: Int,
                                 override val path: String,
                                 override val name: String,
                                 override val lines: Seq[String]) extends InstanceFile(id, path, name, lines) {
  override val instances: Seq[DecisionInstance] = lines.map(l => DecisionInstance(name, l))
}
