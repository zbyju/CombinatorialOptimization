package cz.cvut.fit.juriczby
package common.knapsack.instance

case class Solution(id: Int, name: String, numberOfItems: Int, totalPrice: Int, items: Seq[Boolean]) {
  override def toString = {
    s"""Solution - $name (id: $id)
       |n = $numberOfItems
       |price = $totalPrice
       |items = $items
       |""".stripMargin
  }
}

object Solution {
  def apply(name: String, s: String): Solution = {
    val fields = s.split(" ")
    val itemFields = fields.drop(3).map(x => x.toInt != 0)
    Solution(fields(0).toInt, name, fields(1).toInt, fields(2).toInt, itemFields)
  }
}
