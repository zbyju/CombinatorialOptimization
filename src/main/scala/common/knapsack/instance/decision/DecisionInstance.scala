package cz.cvut.fit.juriczby
package common.knapsack.instance.decision

import common.knapsack.instance.{Instance, Item}

case class DecisionInstance(minPrice: Int,
                            override val name: String,
                            override val id: Int,
                            override val numberOfItems: Int,
                            override val knapsackCapacity: Int,
                            override val items: Seq[Item]) extends Instance(name, id, numberOfItems, knapsackCapacity, items) {
  override def toString: String = s"ID: $id, n: $numberOfItems, M: $knapsackCapacity, B: $minPrice"
}

object DecisionInstance {
  def apply(name: String, s: String): DecisionInstance = {
    val dropItems = 4
    val fields: Seq[String] = s.split(" ")
    val itemFieldsWithIndex = fields.drop(dropItems).zipWithIndex
    val items = itemFieldsWithIndex.filter(_._2 % 2 == 0).map(f => f._1)
      .zip(itemFieldsWithIndex.filter(_._2 % 2 == 1).map(f => f._1))
      .map(f => Item(f._1.toInt, f._2.toInt))
    DecisionInstance(fields(3).toInt, name, fields.head.toInt * -1, fields(1).toInt, fields(2).toInt, items)
  }

  def nextInstance(i: DecisionInstance): DecisionInstance = {
    DecisionInstance(i.minPrice - i.lastItem.price, i.name, i.id, i.numberOfItems - 1, i.knapsackCapacity - i.lastItem.weight, i.items)
  }
  def nextInstanceNotChosen(i: DecisionInstance): DecisionInstance = {
    DecisionInstance(i.minPrice, i.name, i.id, i.numberOfItems - 1, i.knapsackCapacity, i.items)
  }
}
