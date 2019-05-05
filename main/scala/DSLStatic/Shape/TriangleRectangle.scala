package DSLStatic.Shape

import DSLStatic.Style.{Clear, Color, ColorRGB, ColorStyle, Fill, Gradient, Stroke, Style}

import scala.collection.mutable.ListBuffer


case class TriangleRectangle(X: Double, Y: Double, A: Double, B: Double, s: Int, o : Double) extends Triangle {
  override var a = (X, Y)
  override var b = (X + A, Y)
  override var c = (X, Y + B)
  override var opacity: Double = o
  override var style : Style = if(s == 1) new Fill else if (s == 2) new Stroke else new Clear
  override var x: Double = X
  override var y: Double = Y
  override var size: Int = _
  override var rotation: Double = 0
  override var isMirror: Boolean = false
  override val rangeSize = getSize()

  def this(X:Double, Y: Double, A: Double, B: Double, s : Int, o : Double, ct : ColorRGB) {
    this(X, Y, A, B, s , o)
    this.style.colorStyle = ct
  }

  def this(X:Double, Y: Double, A: Double, B: Double, s : Int, o : Double, ct : Gradient) {
    this(X, Y, A, B, s , o)
    this.style.colorStyle = ct
  }

  override def getSize(): Double ={
    val minX = X
    val minY = Y
    val maxX = if(X+A+Y > Y+B+X) X + A else X
    val maxY = if(X+A+Y > Y+B+X) Y  else Y + B
    if(maxX - minX  > maxY - minY)  (maxX - minX) * 1.15 else  (maxY - minY) * 1.15
  }
}
