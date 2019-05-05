package DSLStatic.Shape

import DSLStatic.Style.{Clear, ColorRGB, Fill, Gradient, Stroke, Style}

case class Square (X:Double, Y: Double, cotee: Double, s : Int, o : Double) extends Shape {
  override var opacity: Double = o
  override var style: Style = if (s == 1) new Fill else if (s == 2) new Stroke else new Clear
  override var x: Double = X
  override var y: Double = Y
  var cote : Double = cotee
  override var rotation: Double = 0
  override var size: Int = _
  override var isMirror: Boolean = false
  override val rangeSize = getSize()

  def this(X:Double, Y: Double, widthI: Double, s : Int, o : Double, ct : ColorRGB) {
    this(X, Y, widthI, s , o)
    this.style.colorStyle = ct
  }

  def this(X:Double, Y: Double, widthI: Double, s : Int, o : Double, ct : Gradient) {
    this(X, Y, widthI, s , o)
    this.style.colorStyle = ct
  }

  def apply(w: Stroke): Unit = {
    style = w
  }

  def apply(w: Fill): Unit = {
    style = w
  }

  override def getSize(): Double = {
    if(rotation == 0) {
      cote
    }
    else {
      if(rotation <= 45 && rotation >= -45)
        cote + cote/2 * Math.cos(Math.abs(rotation)*Math.PI/180)
      else
        cote - cote/2 * Math.cos(Math.abs(rotation)*Math.PI/180)
    }
  }

}
