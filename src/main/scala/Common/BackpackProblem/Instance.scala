package cz.cvut.fit.juriczby
package Common.BackpackProblem

class Instance(val name: String, val id: Int, val numberOfItems: Int, val knapsackCapacity: Int, val items: Seq[Item]) {
  def totalWeightAll: Int = items.map(i => i.weight).sum
  def totalPriceAll: Int = items.map(i => i.price).sum
  def totalWeight: Int = items.take(numberOfItems).map(i => i.price).sum
  def totalPrice: Int = items.take(numberOfItems).map(i => i.price).sum
  def lastItem: Item = items(numberOfItems - 1)
}
