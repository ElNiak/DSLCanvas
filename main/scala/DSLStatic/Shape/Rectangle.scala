package DSLStatic.Shape

import DSLStatic.Style.{Clear, Color, ColorRGB, ColorStyle, Fill, Gradient, Stroke, Style}

case class Rectangle(X:Double, Y: Double, widthI: Double, heightI: Double, s : Int, o : Double) extends Shape {
  override var opacity: Double = o
  override var style : Style = if(s == 1) new Fill else if (s == 2) new Stroke else new Clear
  override var x: Double = X
  override var y: Double = Y
  override var rotation: Double = 0
  override var isMirror: Boolean = false
  var height  = heightI
  var width = widthI
  override val rangeSize = getSize
  override var size: Int = _

  def this(X:Double, Y: Double, widthI: Double, heightI: Double, s : Int, o : Double, ct : ColorRGB) {
    this(X, Y, widthI, heightI, s , o)
    this.style.colorStyle = ct
  }

  def this(X:Double, Y: Double, widthI: Double, heightI: Double, s : Int, o : Double, ct : Gradient) {
    this(X, Y, widthI, heightI, s , o)
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
      if(width > height) width else height
    }
    else {
      if(rotation <= 45 && rotation >= -45)
        if(width > height) width + width/2 * Math.cos(Math.abs(rotation)*Math.PI/180) else height + height/2 * Math.cos(Math.abs(rotation)*Math.PI/180)
      else
      if(width > height) width - width/2 * Math.cos(Math.abs(rotation)*Math.PI/180) else height - height/2 * Math.cos(Math.abs(rotation)*Math.PI/180)
    }
  }
}
