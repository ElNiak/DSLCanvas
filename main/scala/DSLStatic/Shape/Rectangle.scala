package DSLStatic.Shape

import DSLStatic.ShapeAttributeException
import DSLStatic.Style.SV.SValue
import DSLStatic.Style._
import org.scalajs.dom.CanvasRenderingContext2D

case class Rectangle(from : (Double, Double), widthI: Double, heightI: Double, s : SValue, o : Double) extends Shape {
  override var opacity: Double = if(o >= 0) o else throw new ShapeAttributeException("Opacity cannot be smaller than 0")
  override var style : Style =  if(s ==  SV.fill) new Fill else if (s == SV.stroke) new Stroke else new Clear
  override var x : Double = if(from._1 >= 0) from._1 else throw new ShapeAttributeException("x cannot be smaller than 0")
  override var y : Double = if(from._2 >= 0) from._2 else throw new ShapeAttributeException("y cannot be smaller than 0")
  override var  vx : Double = 0
  override var vy : Double = 0
  override var rotation: Double = 0
  override var isMirror: Boolean = false
  override var id : String = ""
  var height: Double = if(heightI >= 0) heightI else throw new ShapeAttributeException("Height cannot be smaller than 0")
  var width: Double = if(widthI >= 0) widthI else throw new ShapeAttributeException("Width cannot be smaller than 0")
  override val rangeSize: Double = getSize()
  override var size: Int = _
  override var ax: Double = 0
  override var ay: Double = 1

  def this(i : String, from : (Double, Double), widthI: Double, heightI: Double, s : SValue, o : Double) {
    this(from, widthI, heightI, s , o)
    this.id = i
  }

  def this(from : (Double, Double), widthI: Double, heightI: Double, s : SValue, o : Double, ct : ColorRGB) {
    this(from, widthI, heightI, s , o)
    this.style.colorStyle = ct
  }

  def this(from : (Double, Double), widthI: Double, heightI: Double, s : SValue, o : Double, ct : Gradient) {
    this(from, widthI, heightI, s , o)
    this.style.colorStyle = ct
  }

  def this(i : String, from : (Double, Double), widthI: Double, heightI: Double, s : SValue, o : Double, ct : ColorRGB) {
    this(from, widthI, heightI, s , o)
    this.style.colorStyle = ct
    this.id = i
  }

  def this(i : String , from : (Double, Double), widthI: Double, heightI: Double, s : SValue, o : Double, ct : Gradient) {
    this(from, widthI, heightI, s , o)
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
      if(width > height) width else height
    }
    else {
      if(rotation <= 45 && rotation >= -45)
        if(width > height) width + width/2 * Math.cos(Math.abs(rotation)*Math.PI/180) else height + height/2 * Math.cos(Math.abs(rotation)*Math.PI/180)
      else
      if(width > height) width - width/2 * Math.cos(Math.abs(rotation)*Math.PI/180) else height - height/2 * Math.cos(Math.abs(rotation)*Math.PI/180)
    }
  }

  override def draw(ctx: CanvasRenderingContext2D): Unit = {
    style match {
      case _: Stroke =>
        Shape.checkColor(this,ctx)
        Shape.checkOpacity(this,ctx)
        if(rotation != 0) {
          ctx.translate(x+ width/2, y+height/2)
          ctx.rotate(rotation * Math.PI / 180)
          ctx.strokeRect(-width/2,-height/2, width, height)
        }
        else {
          ctx.strokeRect(x,y,width,height)
        }
      case _: Fill =>
        Shape.checkColor(this,ctx)
        Shape.checkOpacity(this,ctx)
        if(rotation != 0) {
          ctx.translate(x+ width/2, y+height/2)
          ctx.rotate(rotation * Math.PI / 180)
          ctx.fillRect(-width/2,-height/2, width, height)
        }
        else {
          ctx.fillRect(x,y,width,height)
        }
      case _ =>
    }
  }
}
