package DSLStatic.Shape

import DSLStatic.Style.{Clear, Color, ColorRGB, ColorStyle, Fill, Gradient, Stroke, Style}

case class Circle(from : (Double, Double), s: Int , o : Double, radiusv: Double) extends Shape {
  override var opacity: Double = o
  override var style : Style =  if(s == 1) new Fill else if (s == 2) new Stroke else new Clear
  override var x : Double = from._1
  override var y : Double = from._2
  override var size: Int = _
  override var rotation: Double = 0
  override var isMirror: Boolean = false
  var radius : Double = radiusv
  override val rangeSize = getSize()

  override def getSize(): Double ={
    radius
  }

  def this(from : (Double, Double), s : Int, o : Double, rad: Double, ct : ColorRGB) {
    this(from, s , o, rad)
    this.style.colorStyle = ct
  }

  def this(from : (Double, Double), s : Int, o : Double, rad: Double, ct : Gradient) {
    this(from, s , o, rad)
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
