package DSLStatic.Shape

import DSLStatic.ShapeAttributeException
import DSLStatic.Style.SV.SValue
import DSLStatic.Style._
import org.scalajs.dom.CanvasRenderingContext2D


case class TriangleEquilateral(from : (Double, Double), A: Double, s: SValue, o : Double) extends Triangle {
  if(A < 0) throw new ShapeAttributeException("A cannot be smaller than 0")
  override var opacity: Double = if(o >= 0 && o <=1) o else throw new ShapeAttributeException("Opacity cannot be smaller than 0")
  override var style : Style =  if(s ==  SV.fill) new Fill else if (s == SV.stroke) new Stroke else null
  override var x : Double = if(from._1 >= 0) from._1 else throw new ShapeAttributeException("x cannot be smaller than 0")
  override var y : Double = if(from._2 >= 0) from._2 else throw new ShapeAttributeException("y cannot be smaller than 0")
  override var a: (Double, Double) = (x, y)
  override var b: (Double, Double) = (x + A, y)
  override var c: (Double, Double) = (x + A*0.5, y + A*Math.sqrt(3)/2)
  override var  vx : Double = 0
  override var vy : Double = 0
  override var id : String = ""
  override var size: Int = _
  override var rotation: Double = 0
  override var isMirror: Boolean = false
  override val rangeSize: Double = getSize()
  override var ax: Double = 0
  override var ay: Double = 1

  def this(i : String, from : (Double, Double), A: Double, s : SValue, o : Double) {
    this(from, A, s , o)
    this.id = i
  }

  def this(from : (Double, Double), A: Double, s : SValue, o : Double, ct : ColorRGB) {
    this(from, A, s , o)
    this.style.colorStyle = ct
  }

  def this(from : (Double, Double), A: Double, s : SValue, o : Double, ct : Gradient) {
    this(from, A, s , o)
    this.style.colorStyle = ct
  }

  def this(i : String, from : (Double, Double), A: Double, s : SValue, o : Double, ct : ColorRGB) {
    this(from, A, s , o)
    this.style.colorStyle = ct
    this.id = i
  }

  def this(i : String, from : (Double, Double), A: Double, s : SValue, o : Double, ct : Gradient) {
    this(from, A, s , o)
    this.style.colorStyle = ct
    this.id = i
  }

  override def getSize(): Double ={
    val minX = from._1
    val minY = from._2
    val maxX = if(from._1+A+from._2 > (from._1 + A*0.5) + (from._2 + A*Math.sqrt(3)/2)) from._1 + A else from._1 + A*0.5
    val maxY = if(from._1+A+from._2 > (from._1 + A*0.5) + (from._2 + A*Math.sqrt(3)/2)) from._2  else from._2 + A*Math.sqrt(3)/2
    if(maxX - minX  > maxY - minY)  (maxX - minX) * 1.15 else  (maxY - minY) * 1.15
  }

  override def draw(ctx: CanvasRenderingContext2D): Unit = {
     a  = (x, y)
     b = (x + A, y)
     c = (x + A*0.5, y + A*Math.sqrt(3)/2)
    style match {
      case _: Stroke =>
        Shape.checkColor(this,ctx)
        Shape.checkOpacity(this,ctx)
        if(rotation != 0) {
          ctx.moveTo((a._1 + b._1 + c._1)/3, (a._2 + b._2 + c._2)/3)
          ctx.rotate(rotation * Math.PI / 180)
        }
        ctx.lineCap = "round"
        ctx.beginPath()
        ctx.moveTo(a._1+x, a._2+y)
        ctx.lineTo(b._1+x, b._2+y)
        ctx.lineTo(c._1+x, c._2+y)
        ctx.lineTo(a._1+x, a._2+y)
        ctx.stroke()
      case _: Fill =>
        Shape.checkColor(this,ctx)
        Shape.checkOpacity(this,ctx)
        if(rotation != 0) {
          ctx.moveTo((a._1 + b._1 + c._1)/3, (a._2 + b._2 + c._2)/3)
          ctx.rotate(rotation * Math.PI / 180)
        }
        ctx.lineCap = "round"
        ctx.beginPath()
        ctx.moveTo(a._1, a._2)
        ctx.lineTo(b._1, b._2)
        ctx.lineTo(c._1, c._2)
        ctx.lineTo(a._1, a._2)
        ctx.fill()
      case _ =>
    }
  }

}
