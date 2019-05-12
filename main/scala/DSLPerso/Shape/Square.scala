package DSLPerso.Shape

import DSLPerso.ShapeAttributeException
import DSLPerso.Style.SV.SValue
import DSLPerso.Style._
import org.scalajs.dom.CanvasRenderingContext2D

case class Square (from : (Double, Double), cotee: Double, s : SValue, o : Double) extends Shape {
  override var opacity: Double = if(o >= 0 && o <=1) o else throw new ShapeAttributeException("Opacity cannot be smaller than 0")
  override var style : Style =  if(s ==  SV.fill) new Fill else if (s == SV.stroke) new Stroke else new Clear
  override var x : Double = if(from._1 >= 0) from._1 else throw new ShapeAttributeException("x cannot be smaller than 0")
  override var y : Double = if(from._2 >= 0) from._2 else throw new ShapeAttributeException("y cannot be smaller than 0")
  override var  vx : Double = 0
  override var vy : Double = 0
  override var ax: Double = 0
  override var ay: Double = 1
  override var id : String = ""
  var cote : Double = if(cotee >= 0) cotee else throw new ShapeAttributeException("Cote cannot be smaller than 0")
  override var rotation: Double = 0
  override var size: Int = _
  override var isMirror: Boolean = false
  override val rangeSize: Double = getSize()

  def this(i: String, from : (Double, Double), widthI: Double, s : SValue, o : Double) {
    this(from, widthI, s , o)
    this.id = i
  }

  def this(from : (Double, Double), widthI: Double, s : SValue, o : Double, ct : ColorRGB) {
    this(from, widthI, s , o)
    this.style.colorStyle = ct
  }

  def this(from : (Double, Double), widthI: Double, s : SValue, o : Double, ct : Gradient) {
    this(from, widthI, s , o)
    this.style.colorStyle = ct
  }

  def this(i: String, from : (Double, Double), widthI: Double, s : SValue, o : Double, ct : ColorRGB) {
    this(from, widthI, s , o)
    this.style.colorStyle = ct
    this.id = i
  }

  def this(i: String,  from : (Double, Double), widthI: Double, s : SValue, o : Double, ct : Gradient) {
    this(from, widthI, s , o)
    this.style.colorStyle = ct
    this.id = i
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

  override def draw(ctx: CanvasRenderingContext2D): Unit = {
    style match {
      case _: Stroke =>
        Shape.checkColor(this,ctx)
        Shape.checkOpacity(this,ctx)
        if(rotation != 0) {
          ctx.translate(x+ cote/2, y+cote/2)
          ctx.rotate(rotation * Math.PI / 180)
          ctx.strokeRect(-cote/2,-cote/2, cote, cote)
        }
        else {
          ctx.strokeRect(x,y,cote,cote)
        }
      case _: Fill =>
        Shape.checkColor(this,ctx)
        Shape.checkOpacity(this,ctx)
        if(rotation != 0) {
          ctx.translate(x+ cote/2, y+cote/2)
          ctx.rotate(rotation * Math.PI / 180)
          ctx.fillRect(-cote/2,-cote/2, cote, cote)
        }
        else {
          ctx.fillRect(x,y,cote,cote)
        }
      case _ =>
    }
  }

}
