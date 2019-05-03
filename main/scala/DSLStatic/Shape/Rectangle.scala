package DSLStatic.Shape

import DSLStatic.Style.{Clear, Color, ColorRGB, ColorStyle, Fill, Stroke, Style}

case class Rectangle(X:Double, Y: Double, widthI: Double, heightI: Double, s : Int, o : Double) extends Shape {
  override var opacity: Double = o
  override var style : Style = if(s == 1) new Fill else if (s == 2) new Stroke else new Clear
  override var x: Double = X
  override var y: Double = Y
  override var rotation: Double = 0
  override var movable: Boolean = false
  override var canRotate: Boolean = false

  var height  = heightI
  var width = widthI

  override var size: Int = _

  def apply(w: Stroke): Unit = {
    style = w
  }

  def apply(w: Fill): Unit = {
    style = w
  }
}
