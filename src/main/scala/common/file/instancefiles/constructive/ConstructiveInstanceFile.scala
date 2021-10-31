package cz.cvut.fit.juriczby
package common.file.instancefiles.constructive

import cz.cvut.fit.juriczby.common.file.instancefiles.InstanceFile
import cz.cvut.fit.juriczby.common.knapsack.instance.contructive.ConstructiveInstance

case class ConstructiveInstanceFile( override val id: Int,
                                     override val path: String,
                                     override val name: String,
                                     override val lines: Seq[String]) extends InstanceFile(id, path, name, lines) {
  override val instances: Seq[ConstructiveInstance] = lines.map(l => ConstructiveInstance(name, l))
}
