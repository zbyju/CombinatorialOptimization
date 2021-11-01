package cz.cvut.fit.juriczby
package common.knapsack.instance.contructive

import common.knapsack.instance.{Instance, Item}

case class ConstructiveInstance(chosenItems: Seq[Boolean],
                            override val name: String,
                            override val id: Int,
                            override val numberOfItems: Int,
                            override val knapsackCapacity: Int,
                            override val items: Seq[Item]) extends Instance(name, id, numberOfItems, knapsackCapacity, items) {

  val chosenPrice: Int = {
    chosenItems.zipWithIndex.map(x => if(x._1) items(x._2).price else 0).sum
  }

  val chosenItemsInt = chosenItems.map(x => if(x) 1 else 0)

  def maxPotentialPrice: Int = {
    items.map(i => i.price).take(numberOfItems).sum + chosenPrice
  }

  override def toString: String = s"ID: $id, n: $numberOfItems, M: $knapsackCapacity, chosen: $chosenPrice - $chosenItems"
}

object ConstructiveInstance {
  def apply(name: String, s: String): ConstructiveInstance = {
    val dropItems = 3
    val fields: Seq[String] = s.split(" ")
    val itemFieldsWithIndex = fields.drop(dropItems).zipWithIndex
    val items = itemFieldsWithIndex.filter(_._2 % 2 == 0).map(f => f._1)
      .zip(itemFieldsWithIndex.filter(_._2 % 2 == 1).map(f => f._1))
      .map(f => Item(f._1.toInt, f._2.toInt))
    ConstructiveInstance(List.fill(fields(1).toInt)(false), name, Math.abs(fields.head.toInt), fields(1).toInt, fields(2).toInt, items)
  }

  def nextInstance(i: ConstructiveInstance): ConstructiveInstance = {
    ConstructiveInstance(i.chosenItems.updated(i.numberOfItems - 1, true), i.name, i.id, i.numberOfItems - 1, i.knapsackCapacity - i.lastItem.weight, i.items)
  }
  def nextInstanceNotChosen(i: ConstructiveInstance): ConstructiveInstance = {
    ConstructiveInstance(i.chosenItems, i.name, i.id, i.numberOfItems - 1, i.knapsackCapacity, i.items)
  }
}
