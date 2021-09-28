package cz.cvut.fit.juriczby
package Common.BackpackProblem

case class Solution(name: String, id: Int, numberOfItems: Int, totalPrice: Int, items: Seq[Boolean])

object Solution {
  def apply(name: String, s: String): Solution = {
    val fields = s.split(" ")
    val itemFields = fields.drop(3).map(x => x.toInt != 0)
    Solution(name, fields(0).toInt, fields(1).toInt, fields(2).toInt, itemFields)
  }
}
