package cz.cvut.fit.juriczby
package common.knapsack.instance

case class Item(weight: Int, price: Int) {
  override def toString = {
    s"""$weight
       |$price
       |""".stripMargin
  }
}
