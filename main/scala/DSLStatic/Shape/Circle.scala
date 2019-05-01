package DSLStatic.Shape

import DSLStatic.Style.{Clear, Color, ColorRGB, ColorStyle, Fill, Stroke, Style}

case class Circle(radiusv: Double, X:Double, Y:Double, s: Int , o : Double) extends Shape {
  override var opacity: Double = o
  override var style : Style =  if(s == 1) new Fill else if (s == 2) new Stroke else new Clear
  override var x : Double = X
  override var y : Double = Y
  override var size: Int = _
  var radius : Double = radiusv

  def apply(w: Double): Unit = {
    radius = w
  }

  def apply(w: Stroke): Unit = {
    style = w
  }

  def apply(w: Fill): Unit = {
    style = w
  }
}
