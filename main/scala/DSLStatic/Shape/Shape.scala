package DSLStatic.Shape

import DSLStatic.Style.{ColorRGB, ColorStyle, Fill, Gradient, Stroke, Style}
import DSLStatic._
import org.scalajs.dom

import scala.collection.mutable.ListBuffer
import scala.scalajs.js

trait Shape extends CanvasyElement {
  var style : Style
  var opacity: Double
  var x: Double
  var y: Double
  var size : Int
  var rotation : Double
  var isMirror : Boolean
  val rangeSize : Double
  var  vx : Double
  var vy : Double

  def size(s: Int): Unit = {
    size = s
  }

  def getSize(): Double

  def draw(ctx : dom.CanvasRenderingContext2D):Unit

  override def change(x: CanvasyElementModifier[Shape]): Shape = {
    x.change(this)
    this
  }

  override def and(x: Shape): ListBuffer[Shape] = {
    ListBuffer(this, x)
  }

  override def and(x: CanvasyElementModifier[Shape]): Shape = {
     this.change(x)
  }


  def translateY(Y: Int): Shape = {
    y += Y
    this
  }

  def translateX(X: Int): Shape = {
    x += X
    this
  }

  def rotate(X: Double) : Shape = {
    if(X <= 90 && X >= -90)
      rotation = X
    else {
      val i : Int = (X/90).toInt
      if(X > 90)
        rotation = 90*i - X
      else
        rotation = 90*i + X
    }
    println(rotation)
    this
  }

  def apply(w: Style): Unit = {
    style = w
  }

}


object Shape {
  implicit class Group[I <: Shape](group: ListBuffer[I]) {
    def translateY(Y: Int) : ListBuffer[I] = {
      group foreach(_ translateY(Y))
      return group
    }
    def translateX(X: Int) : ListBuffer[I] = {
      group foreach(_ translateX(X))
      return group
    }
    def rotate(X: Double) : ListBuffer[I] = {
      group foreach(_ rotate(X))
      return group
    }
  }
  def checkOpacity [K <: Shape](shape : K, ctx : dom.CanvasRenderingContext2D) : Unit = {
    val ctx_stroke_color = ctx.strokeStyle
    val ctx_stroke_width = ctx.lineWidth
    val ctx_stroke_cap = ctx.lineCap
    val ctx_stroke_join = ctx.lineJoin
    val ctx_stroke_dash = ctx.lineDashOffset
    val ctx_fill_color = ctx.fillStyle

    def getRRGBA(c: String) : Array[Int] = {
      val str = c.substring(4)
      var res = str.split(",")
      Array(res(0).toInt, res(1).toInt, res(2).toInt)
    }

    def getRRGB(c: String) : Array[Int] = {
      val str = c.substring(3)
      var res = str.split(",")
      Array(res(0).toInt, res(1).toInt, res(2).toInt)
    }

    def getR(c: String) : Int = {
      val str = c.substring(1,2)
      try {
        Integer.parseInt(str,16) * 16
      } catch {
        case ne: NumberFormatException => -1
      }
    }

    def getG(c: String) : Int = {
      val str = c.substring(3,4)
      try {
        Integer.parseInt(str,16) * 16
      } catch {
        case ne: NumberFormatException => -1
      }
    }

    def getB(c: String) : Int = {
      val str = c.substring(5,6)
      try {
        Integer.parseInt(str,16) * 16
      } catch {
        case ne: NumberFormatException => -1
      }
    }
    shape.style match {
      case _: Fill =>
        if (shape.opacity < 1) {
          if (ctx.fillStyle.toString.charAt(0) == '#') {
            val r = getR(ctx.fillStyle.toString)
            val g = getG(ctx.fillStyle.toString)
            val b = getB(ctx.fillStyle.toString)
            ctx.fillStyle = "rgba(" + r + ", " + g + ", " + b + ", " + shape.opacity + ")"
          }
          else if (ctx.fillStyle.toString.charAt(0) == 'r') {
            if (ctx.fillStyle.toString.charAt(3) == 'a') {
              val array = getRRGBA(ctx.fillStyle.toString)
              val r = array(0)
              val g = array(1)
              val b = array(2)
              ctx.fillStyle = "rgba(" + r + ", " + g + ", " + b + ", " + shape.opacity + ")"
            }
            else {
              val array = getRRGB(ctx.fillStyle.toString)
              val r = array(0)
              val g = array(1)
              val b = array(2)
              ctx.fillStyle = "rgba(" + r + ", " + g + ", " + b + ", " + shape.opacity + ")"
            }
          }
        }
      case _: Stroke =>
        if (shape.opacity < 1) {
          if (ctx.strokeStyle.toString.charAt(0) == '#') {
            val r = getR(ctx.strokeStyle.toString)
            val g = getG(ctx.strokeStyle.toString)
            val b = getB(ctx.strokeStyle.toString)
            ctx.strokeStyle = "rgba(" + r + ", " + g + ", " + b + ", " + shape.opacity + ")"
          }
          else if (ctx.strokeStyle.toString.charAt(0) == 'r') {
            if (ctx.strokeStyle.toString.charAt(3) == 'a') {
              val array = getRRGBA(ctx.strokeStyle.toString)
              val r = array(0)
              val g = array(1)
              val b = array(2)
              ctx.strokeStyle = "rgba(" + r + ", " + g + ", " + b + ", " + shape.opacity + ")"
            }
            else {
              val array = getRRGB(ctx.strokeStyle.toString)
              val r = array(0)
              val g = array(1)
              val b = array(2)
              ctx.strokeStyle = "rgba(" + r + ", " + g + ", " + b + ", " + shape.opacity + ")"
            }
          }
        }
      case _ =>
    }
  }

  def checkColor [K <: Shape](shape : K, ctx : dom.CanvasRenderingContext2D) : Unit = {
    val ctx_stroke_color = ctx.strokeStyle
    val ctx_stroke_width = ctx.lineWidth
    val ctx_stroke_cap = ctx.lineCap
    val ctx_stroke_join = ctx.lineJoin
    val ctx_stroke_dash = ctx.lineDashOffset
    val ctx_fill_color = ctx.fillStyle
    shape.style match {
      case s : Fill =>
        s.colorStyle match {
          case b: ColorRGB =>
            if (b.color != "")
              ctx.fillStyle = b.color
            else
              ctx.fillStyle = ctx_fill_color
          case b: Gradient =>
            if(b.r1 == -1){
              var style = ctx.createLinearGradient(b.x1,b.y1,b.x2,b.y2)
              for(c <- b.colors.indices){
                style.addColorStop(b.offset(c),b.color(c).color)
              }
              ctx.fillStyle = style
            }
            else {
              var style = ctx.createRadialGradient(b.x1,b.y1,b.r1,b.x2,b.y2,b.r2)
              for(c <- b.colors.indices){
                style.addColorStop(b.offset(c),b.color(c).color)
              }
              style.addColorStop(1, "rgba(0, 0, 0, 0)")
              ctx.fillStyle = style
            }

        }
      case s: Stroke =>
        s.colorStyle match {
          case b: ColorRGB =>
            if (b.color != "")
              ctx.strokeStyle = b.color
            else
              ctx.strokeStyle = ctx_stroke_color

            if (s.width != -1)
              ctx.lineWidth = s.width
            else
              ctx.lineWidth = ctx_stroke_width

            if (!s.cap.equals(""))
              ctx.lineCap = s.cap
            else
              ctx.lineCap = ctx_stroke_cap

            if (!s.join.equals(""))
              ctx.lineJoin = s.join
            else
              ctx.lineJoin = ctx_stroke_join

            if (s.offset != 0.0){
              ctx.lineDashOffset = s.offset
              ctx.setLineDash(js.Array(4.0,2.0))
            }
            else {
              ctx.lineDashOffset = 0
            }

          case b: Gradient =>
            if(b.r1 == -1){
              var style = ctx.createLinearGradient(b.x1,b.y1,b.x2,b.y2)
              for(c <- b.colors.indices){
                style.addColorStop(b.offset(c),b.color(c).color)
              }
              ctx.strokeStyle = style
            }
            else {
              var style = ctx.createRadialGradient(b.x1,b.y1,b.r1,b.x2,b.y2,b.r2)
              for(c <- b.colors.indices){
                style.addColorStop(b.offset(c),b.color(c).color)
              }
              ctx.strokeStyle = style
            }
          case _ =>
        }
    }
  }
}
