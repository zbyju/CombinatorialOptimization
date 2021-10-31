package cz.cvut.fit.juriczby
package common.knapsack.instance

class Instance(val name: String, private val internalId: Int, val numberOfItems: Int, val knapsackCapacity: Int, val items: Seq[Item]) {
  val id = Math.abs(internalId)

  def totalWeightAll: Int = items.map(i => i.weight).sum
  def totalPriceAll: Int = items.map(i => i.price).sum
  def totalWeight: Int = items.take(numberOfItems).map(i => i.price).sum
  def totalPrice: Int = items.take(numberOfItems).map(i => i.price).sum
  def lastItem: Item = items(numberOfItems - 1)
}
