package DSLStatic.Shape

import DSLStatic.Style.{Clear, Color, ColorRGB, ColorStyle, Fill, Gradient, Stroke, Style}

case class Circle(radiusv: Double, X:Double, Y:Double, s: Int , o : Double) extends Shape {
  override var opacity: Double = o
  override var style : Style =  if(s == 1) new Fill else if (s == 2) new Stroke else new Clear
  override var x : Double = X
  override var y : Double = Y
  override var size: Int = _
  override var rotation: Double = 0
  var radius : Double = radiusv

  def this(X:Double, Y: Double, rad: Double, s : Int, o : Double, ct : ColorRGB) {
    this(rad, X, Y, s , o)
    this.style.colorStyle = ct
  }

  def this(X:Double, Y: Double, rad: Double, s : Int, o : Double, ct : Gradient) {
    this(rad, X, Y, s , o)
    this.style.colorStyle = ct
  }

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
