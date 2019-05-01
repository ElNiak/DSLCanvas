package DSLStatic

import DSLStatic.Shape._
import DSLStatic.Style.{ColorRGB, Fill, Gradient, Stroke}
import org.scalajs.dom
import org.scalajs.dom.raw.CanvasGradient
import org.scalajs.dom.{document, html}

import scala.collection.mutable.ListBuffer

class Canvasy[I <: CanvasyElement](c: html.Canvas) {
  private val ctx = c.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
  private val shape_groups = new ListBuffer[Array[I]]()
  private val ctx_stroke_color = ctx.strokeStyle
  private val ctx_stroke_width = ctx.lineWidth
  private val ctx_fill_color = ctx.fillStyle

  def draw(): Unit = {
    shape_groups foreach(_ foreach(drawShape(_)))
  }

  def drawShape(shape: CanvasyElement): Unit = {
    println("Draw shape")
    shape match {
      case Rectangle(a,b,width, height,s,o) =>
        drawRectangle(shape.asInstanceOf[Rectangle])
      case Square(a,b,cote,s,o) =>
        drawSquare(shape.asInstanceOf[Square])
      case Circle(radius, a,b,s,o) =>
        drawCircle(shape.asInstanceOf[Circle])
      case RectangleTriangle(x, y, a, b,s,o) =>
        drawTriangle(shape.asInstanceOf[Triangle])
      case EquilateralTriangle(x, y, a,s,o) =>
        drawTriangle(shape.asInstanceOf[Triangle])
      case Text(x, y, t,sx,sy,b,c,f) =>
        drawText(shape.asInstanceOf[Text])
      case _ => print("Can only draw Rectangle and Circle")
    }
  }

  def drawTriangle(triangle: Triangle): Unit = {
    triangle.style match {
      case _: Stroke =>
        checkColor(triangle)
        checkOpacity(triangle)
        ctx.beginPath()
        ctx.moveTo(triangle.a._1, triangle.a._2)
        ctx.lineTo(triangle.b._1, triangle.b._2)
        ctx.lineTo(triangle.c._1, triangle.c._2)
        ctx.lineTo(triangle.a._1, triangle.a._2)
        ctx.stroke()
      case _: Fill =>
        checkColor(triangle)
        checkOpacity(triangle)
        ctx.beginPath()
        ctx.moveTo(triangle.a._1, triangle.a._2)
        ctx.lineTo(triangle.b._1, triangle.b._2)
        ctx.lineTo(triangle.c._1, triangle.c._2)
        ctx.lineTo(triangle.a._1, triangle.a._2)
        ctx.fill()
      case _ =>
    }

  }

  def drawCircle(circle: Circle): Unit = {
    circle.style match {
      case _: Stroke =>
        checkColor(circle)
        checkOpacity(circle)
        ctx.beginPath()
        ctx.arc(circle.x, circle.y, circle.radius, 0, 2 * Math.PI)
        ctx.stroke()
      case _: Fill =>
        checkColor(circle)
        checkOpacity(circle)
        ctx.beginPath()
        ctx.arc(circle.x, circle.y, circle.radius, 0, 2 * Math.PI)
        ctx.fill()
      case _ =>
    }

  }

  def drawRectangle(rectangle: Rectangle): Unit = {
    rectangle.style match {
      case _: Stroke =>
        checkColor(rectangle)
        checkOpacity(rectangle)
        ctx.strokeRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height)
      case _: Fill =>
        checkColor(rectangle)
        checkOpacity(rectangle)
        ctx.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height)
      case _ =>
    }
  }

  def drawText(text: Text): Unit = {
    text.style match {
      case _: Fill =>
        checkColor(text)
        ctx.shadowOffsetX = text.soX
        ctx.shadowOffsetY = text.soY
        ctx.shadowBlur = text.sb
        ctx.shadowColor = text.sc
        ctx.shadowOffsetX = text.soX
        ctx.font = text.font
        ctx.fillText(text.text, text.x, text.y)
      case _ =>
    }
  }

  def drawSquare(square: Square): Unit = {
    square.style match {
      case _: Stroke =>
        checkColor(square)
        checkOpacity(square)
        ctx.strokeRect(square.x, square.y, square.cote, square.cote)
      case _: Fill =>
        checkColor(square)
        checkOpacity(square)
        println(ctx.fillStyle)
        ctx.fillRect(square.x, square.y, square.cote, square.cote)
      case _ =>
    }
  }


  def += [J <: CanvasyElement](group: Array[J]): Unit = {
    shape_groups += group.asInstanceOf[Array[I]]
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

  def checkOpacity [K <: Shape](shape : K) : Unit = {
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

  def checkColor [K <: Shape](shape : K) : Unit = {
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
