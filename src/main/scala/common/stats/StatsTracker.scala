package cz.cvut.fit.juriczby
package common.stats

import java.util.concurrent.atomic.AtomicLong

case class StatsTracker() {
  private var timeStart: Long = _
  private var timeEnd: Long = _
  private val configurationsCount = new AtomicLong(0)
  def getConfigurationsCount: Long = configurationsCount.get()
  def incConfigurationsCount(): Long = configurationsCount.incrementAndGet()

  def setTimeStart(): Unit = timeStart = System.nanoTime()

  def setTimeEnd(): Unit = timeEnd = System.nanoTime()

  def getTimeNano: Long = timeEnd - timeStart

  override def toString() = {
    s"""#Count: $configurationsCount, Time: $getTimeNano""".stripMargin
  }
}
