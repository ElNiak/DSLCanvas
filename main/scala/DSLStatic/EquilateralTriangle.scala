package DSLStatic

import java.lang.Math

case class EquilateralTriangle (X: Double, Y: Double, A: Double) extends Triangle {
  override var a: (Double, Double) = (X, Y)
  override var b: (Double, Double) = (X + A, Y)
  override var c: (Double, Double) = (X + A*0.5, Y + A*Math.sqrt(3)/2)
  override var stroke: Stroke = new Stroke
  override var color: String = Color.red
  override var x: Double = X
  override var y: Double = Y
  override var size: Int = _
}
