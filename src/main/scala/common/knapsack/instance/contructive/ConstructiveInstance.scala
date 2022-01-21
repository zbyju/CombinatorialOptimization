package cz.cvut.fit.juriczby
package common.knapsack.instance.contructive

import common.knapsack.instance.{Instance, Item}

import scala.util.Random

case class ConstructiveInstance(chosenItems: Seq[Boolean],
                            override val name: String,
                            override val id: Int,
                            override val numberOfItems: Int,
                            override val knapsackCapacity: Int,
                            override val items: Seq[Item]) extends Instance(name, id, numberOfItems, knapsackCapacity, items) {

  lazy val chosenPrice: Int = {
    chosenItems.zipWithIndex.map(x => if(x._1) items(x._2).price else 0).sum
  }

  lazy val chosenWeight: Int = {
    chosenItems.zipWithIndex.map(x => if(x._1) items(x._2).weight else 0).sum
  }

  lazy val chosenItemsInt: Seq[Int] = chosenItems.map(x => if(x) 1 else 0)

  lazy val maxPotentialPrice: Int = {
    items.map(i => i.price).take(numberOfItems).sum + chosenPrice
  }

  def shuffle(): ConstructiveInstance = {
    val itemsForShuffle = chosenItems.zip(items)
    val shuffled = Random.shuffle(itemsForShuffle)
    val (newChosen, newItems) = shuffled.unzip
    new ConstructiveInstance(newChosen, name, id, numberOfItems, knapsackCapacity, newItems)
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
  def randomNextInstance(i: ConstructiveInstance): ConstructiveInstance = {
    val rnd = new Random()
    val rndIndices = rnd.shuffle(Seq.range(0, i.numberOfItems))
    var weightChange = 0
    rndIndices.foreach(rIndex => {
      val chosen = i.chosenItems(rIndex)
      if(!chosen && i.chosenWeight + i.items(rIndex).weight <= i.knapsackCapacity) {
        return ConstructiveInstance(i.chosenItems.updated(rIndex, true),
          i.name, i.id, i.numberOfItems, i.knapsackCapacity, i.items
        )
      }
      if(chosen) {
        return ConstructiveInstance(i.chosenItems.updated(rIndex, false),
          i.name, i.id, i.numberOfItems, i.knapsackCapacity, i.items
        )
      }
    })
    println("WRONG3")
    i
  }
  def calculateChosenWeight(chosenItems: Seq[Boolean], items: Seq[Item]): Int = {
    chosenItems.zipWithIndex.map(x => if(x._1) items(x._2).weight else 0).sum
  }
  def randomInstance(i: ConstructiveInstance): ConstructiveInstance = {
    val rnd = new Random()
    var rndItems = Seq.fill(i.numberOfItems)(rnd.nextBoolean())
    val rndIndices = rnd.shuffle(Seq.range(0, i.numberOfItems))
    rndIndices.foreach(index => {
      val item = rndItems(index)
      if(item && calculateChosenWeight(rndItems, i.items) > i.knapsackCapacity) {
        rndItems = rndItems.updated(index, false)
      }
    })
    ConstructiveInstance(rndItems, i.name, i.id, i.numberOfItems, i.knapsackCapacity, i.items)
  }
  def howMuchWorse(i: ConstructiveInstance, that: ConstructiveInstance): Int = {
    that.chosenPrice - i.chosenPrice
  }
}
