package DSLStatic

import java.lang.Math

case class RectangleTriangle(X: Double, Y: Double, A: Double, B: Double) extends Triangle {

  override var a = (X, Y)
  override var b = (X + A, Y)
  override var c = (X, Y + B)

  override var stroke: Stroke = new Stroke
  override var color: String = Color.red
  override var x: Double = X
  override var y: Double = Y
  override var size: Int = _

//  def this(X: Double, Y: Double, angleA: Double, H: Double) {
//    this(X, Y, H*Math.cos(a), H*Math.sin(a))
//  }

}
