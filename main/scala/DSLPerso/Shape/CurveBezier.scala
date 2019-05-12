package DSLPerso.Shape
import DSLPerso.ShapeAttributeException
import DSLPerso.Style.SV.SValue
import DSLPerso.Style._
import org.scalajs.dom.CanvasRenderingContext2D

import scala.collection.mutable.ListBuffer

case class CurveBezier(from : (Double, Double), to : (Double, Double), s: SValue, o : Double, cp1 : (Double, Double), cp2 : (Double, Double)) extends Shape {
  override var opacity: Double = if(o >= 0 && o <=1) o else throw new ShapeAttributeException("Opacity cannot be smaller than 0")
  override var style : Style =  if(s ==  SV.fill) new Fill else if (s == SV.stroke) new Stroke else null
  override var x : Double = if(from._1 >= 0) from._1 else throw new ShapeAttributeException("x cannot be smaller than 0")
  override var y : Double = if(from._2 >= 0) from._2 else throw new ShapeAttributeException("y cannot be smaller than 0")
  override var  vx : Double = 0
  override var vy : Double = 0
  var tx : Double = if(to._1 >= 0) to._1 else throw new ShapeAttributeException("tx cannot be smaller than 0")
  var ty : Double = if(to._2 >= 0) to._2 else throw new ShapeAttributeException("ty cannot be smaller than 0")
  override var size: Int = _
  override var rotation: Double = 0
  override var isMirror: Boolean = false
  var cp1x : Double = if(cp1._1 >= 0) cp1._1 else throw new ShapeAttributeException("cp1x cannot be smaller than 0")
  var cp1y : Double = if(cp1._2 >= 0) cp1._2 else throw new ShapeAttributeException("cp1y cannot be smaller than 0")
  var cp2x : Double = if(cp2._1 >= 0) cp2._1 else throw new ShapeAttributeException("cp2x cannot be smaller than 0")
  var cp2y : Double = if(cp2._2 >= 0) cp2._2 else throw new ShapeAttributeException("cp2y cannot be smaller than 0")
  var coordinates: ListBuffer[(Double, Double)] = ListBuffer[(Double, Double)]((x,y),(tx,ty),(cp1x,cp1y),(cp2x,cp2y))
  override val rangeSize: Double = getSize()
  override var ax: Double = 0
  override var ay: Double = 1
  override var id : String = ""

  def this(i : String, from : (Double, Double), to : (Double, Double), s : SValue, o : Double, cp1 : (Double, Double), cp2 : (Double, Double)) {
    this(from,to, s , o, cp1,cp2)
    this.id = i
  }

  def this(from : (Double, Double), to : (Double, Double), s : SValue, o : Double, cp1 : (Double, Double), cp2 : (Double, Double), ct : ColorRGB) {
    this(from,to, s , o, cp1,cp2)
    this.style.colorStyle = ct
  }

  def this(from : (Double, Double), to : (Double, Double), s : SValue, o : Double, cp1 : (Double, Double), cp2 : (Double, Double), ct : Gradient) {
    this(from,to, s , o, cp1,cp2)
    this.style.colorStyle = ct
  }

  def this(i : String, from : (Double, Double), to : (Double, Double), s : SValue, o : Double, cp1 : (Double, Double), cp2 : (Double, Double), ct : ColorRGB) {
    this(from,to, s , o, cp1,cp2)
    this.style.colorStyle = ct
    this.id = i
  }

  def this(i: String, from : (Double, Double), to : (Double, Double), s : SValue, o : Double, cp1 : (Double, Double), cp2 : (Double, Double), ct : Gradient) {
    this(from,to, s , o, cp1,cp2)
    this.style.colorStyle = ct
    this.id = i
  }

  override def getSize(): Double ={
    var minX = Double.MaxValue
    var minY = Double.MaxValue
    var maxX = Double.MinValue
    var maxY = Double.MinValue
    for(shape <- coordinates){
      if(shape._1+shape._2 < minX + minY){
        minX = shape._1
        minY = shape._2
      }
      if(shape._1+shape._1 > maxX + maxY){
        maxX = shape._1
        maxY = shape._1
      }
    }
    if(maxX - minX  > maxY - minY)  (maxX - minX) * 2 else  (maxY - minY) * 2
  }

  override def draw(ctx: CanvasRenderingContext2D): Unit = {
    style match {
      case _: Stroke =>
        Shape.checkColor(this,ctx)
        Shape.checkOpacity(this,ctx)
        ctx.rotate(rotation * Math.PI / 180)
        ctx.beginPath()
        ctx.moveTo(x,y)
        ctx.bezierCurveTo(cp1x, cp1y, cp2x, cp2y, tx, ty)
        ctx.stroke()
      case _: Fill =>
        Shape.checkColor(this,ctx)
        Shape.checkOpacity(this,ctx)
        ctx.rotate(rotation * Math.PI / 180)
        ctx.beginPath()
        ctx.moveTo(x,y)
        ctx.bezierCurveTo(cp1x, cp1y, cp2x, cp2y, tx, ty)
        ctx.fill()
      case _ =>
    }
  }
}
