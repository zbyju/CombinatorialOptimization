package cz.cvut.fit.juriczby
package common.stats

import java.util.concurrent.atomic.AtomicLong

case class StatsTracker() {
  private var timeStart: Long = _
  private var timeEnd: Long = _
  private val configurationsCount = new AtomicLong(0)
  private var progression: Seq[Int] = Seq()
  def getConfigurationsCount: Long = configurationsCount.get()
  def incConfigurationsCount(): Long = configurationsCount.incrementAndGet()

  def getTimeStart(): Long = timeStart

  def setTimeStart(): Unit = timeStart = System.nanoTime()
  def setTimeStart(time: Long): Unit = timeStart = time

  def setTimeEnd(): Unit = timeEnd = System.nanoTime()

  def getTimeNano: Long = timeEnd - timeStart

  def addProgress(p: Int): Unit = progression = progression :+ p

  def getProgression: Seq[Int] = progression

  override def toString() = {
    s"""#Count: $configurationsCount, Time: $getTimeNano""".stripMargin
  }
}
