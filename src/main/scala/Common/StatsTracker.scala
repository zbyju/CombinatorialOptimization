package cz.cvut.fit.juriczby
package Common
import java.util.concurrent.atomic.AtomicLong

case class StatsTracker() {
  private var timeStart: Long = _
  private var timeEnd: Long = _
  private val configurationsCount = new AtomicLong(0)
  def getConfigurationsCount: Long = configurationsCount.get()
  def incConfigurationsCount(): Long = configurationsCount.incrementAndGet()

  def setTimeStart(): Unit = timeStart = System.currentTimeMillis()

  def setTimeEnd(): Unit = timeEnd = System.currentTimeMillis()

  def getTimeMillis: Long = timeEnd - timeStart
}
