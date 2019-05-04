package DSLStatic.Shape

import DSLStatic.Style.{Clear, Fill, Stroke, Style}

case class Square (X:Double, Y: Double, cotee: Double, s : Int, o : Double) extends Shape {
  override var opacity: Double = o
  override var style: Style = if (s == 1) new Fill else if (s == 2) new Stroke else new Clear
  override var x: Double = X
  override var y: Double = Y
  var cote : Double = cotee
  override var rotation: Double = 0
  override var size: Int = _
  override var canRotate: Boolean = false

  def apply(w: Stroke): Unit = {
    style = w
  }

  def apply(w: Fill): Unit = {
    style = w
  }

}
