package DSLStatic.Shape

import DSLStatic.Style.{Clear, Color, ColorRGB, ColorStyle, Fill, Stroke, Style}


case class RectangleTriangle(X: Double, Y: Double, A: Double, B: Double, s: Int , o : Double) extends Triangle {
  override var a = (X, Y)
  override var b = (X + A, Y)
  override var c = (X, Y + B)

  override var opacity: Double = o
  override var style : Style = if(s == 1) new Fill else if (s == 2) new Stroke else new Clear
  override var x: Double = X
  override var y: Double = Y
  override var size: Int = _
  override var rotation: Double = 0
//  def this(X: Double, Y: Double, angleA: Double, H: Double) {

  //  def this(X: Double, Y: Double, angleA: Double, H: Double) {
//    this(X, Y, H*Math.cos(a), H*Math.sin(a))
//  }

}
