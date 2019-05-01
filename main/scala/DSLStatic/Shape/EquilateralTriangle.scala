package DSLStatic.Shape

import DSLStatic.Style.{Clear, Color, ColorRGB, ColorStyle, Fill, Stroke, Style}


case class EquilateralTriangle (X: Double, Y: Double, A: Double, s: Int , o : Double) extends Triangle {
  override var a: (Double, Double) = (X, Y)
  override var b: (Double, Double) = (X + A, Y)
  override var c: (Double, Double) = (X + A*0.5, Y + A*Math.sqrt(3)/2)
  override var opacity: Double = o
  override var style : Style = if(s == 1) new Fill else if (s == 2) new Stroke else new Clear
  override var x: Double = X
  override var y: Double = Y
  override var size: Int = _
}
