package DSLStatic

import org.scalajs.dom
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
      case Rectangle(a,b,width, height,s,o) =>  {
        drawRectangle(shape.asInstanceOf[Rectangle])
      }
      case Circle(radius, a,b,s,o) =>  {
        drawCircle(shape.asInstanceOf[Circle])
      }
      case _ => print("Can only draw Rectangle and Circle")
    }
  }

  def drawCircle(circle: Circle): Unit = {
    if(circle.state == 2) {
      if (circle.stroke.color.color != "")
        ctx.strokeStyle = circle.stroke.color.color
      else
        ctx.strokeStyle = ctx_stroke_color

      if(circle.opacity < 1){
        if(ctx.strokeStyle.toString.charAt(0) == '#'){
          val r = getR(ctx.strokeStyle.toString)
          val g = getG(ctx.strokeStyle.toString)
          val b = getB(ctx.strokeStyle.toString)
          ctx.strokeStyle = "rgba(" + r + ", " + g + ", "  +  b  + ", "  +  circle.opacity + ")"
        }
        else if (ctx.strokeStyle.toString.charAt(0) == 'r') {
          if(ctx.strokeStyle.toString.charAt(3) == 'a') {
            val array = getRRGBA(ctx.strokeStyle.toString)
            val r = array(0)
            val g = array(1)
            val b = array(2)
            ctx.strokeStyle = "rgba(" + r + ", " + g + ", "  +  b  + ", "  +  circle.opacity + ")"
          }
          else {
            val array = getRRGB(ctx.strokeStyle.toString)
            val r = array(0)
            val g = array(1)
            val b = array(2)
            ctx.strokeStyle = "rgba(" + r + ", " + g + ", "  +  b  + ", "  +  circle.opacity + ")"
          }
        }
      }

      if (circle.stroke.width != -1)
        ctx.lineWidth = circle.stroke.width
      else
        ctx.lineWidth = ctx_stroke_width

      ctx.beginPath()
      ctx.arc(circle.x, circle.y, circle.radius, 0, 2 * Math.PI)
      ctx.stroke()
    }
    else if(circle.state == 1 ) {
      print(circle.fill.color.color)
      if (circle.fill.color.color != "")
        ctx.fillStyle = circle.fill.color.color
      else
        ctx.fillStyle = ctx_fill_color

      if(circle.opacity < 1){
        if(ctx.fillStyle.toString.charAt(0) == '#'){
          val r = getR(ctx.fillStyle.toString)
          val g = getG(ctx.fillStyle.toString)
          val b = getB(ctx.fillStyle.toString)
          ctx.fillStyle = "rgba(" + r + ", " + g + ", "  +  b  + ", "  +  circle.opacity + ")"
        }
        else if (ctx.fillStyle.toString.charAt(0) == 'r') {
          if(ctx.fillStyle.toString.charAt(3) == 'a') {
            val array = getRRGBA(ctx.fillStyle.toString)
            val r = array(0)
            val g = array(1)
            val b = array(2)
            ctx.fillStyle = "rgba(" + r + ", " + g + ", "  +  b  + ", "  +  circle.opacity + ")"
          }
          else {
            val array = getRRGB(ctx.fillStyle.toString)
            val r = array(0)
            val g = array(1)
            val b = array(2)
            ctx.fillStyle = "rgba(" + r + ", " + g + ", "  +  b  + ", "  +  circle.opacity + ")"
          }
        }
        println(ctx.fillStyle.toString)
      }
      ctx.beginPath()
      ctx.arc(circle.x, circle.y, circle.radius, 0, 2 * Math.PI)
      ctx.fill()
    }

  }

  def drawRectangle(rectangle: Rectangle): Unit = {
    if(rectangle.state == 2) {
      if (rectangle.stroke.color.color != "")
        ctx.strokeStyle = rectangle.stroke.color.color
      else
        ctx.strokeStyle = ctx_stroke_color
      if(rectangle.opacity < 1){
        if(ctx.strokeStyle.toString.charAt(0) == '#'){
          val r = getR(ctx.strokeStyle.toString)
          val g = getG(ctx.strokeStyle.toString)
          val b = getB(ctx.strokeStyle.toString)
          ctx.strokeStyle = "rgba(" + r + ", " + g + ", "  +  b  + ", "  +  rectangle.opacity + ")"
        }
        else if (ctx.strokeStyle.toString.charAt(0) == 'r') {
          if(ctx.strokeStyle.toString.charAt(3) == 'a') {
            val array = getRRGBA(ctx.strokeStyle.toString)
            val r = array(0)
            val g = array(1)
            val b = array(2)
            ctx.strokeStyle = "rgba(" + r + ", " + g + ", "  +  b  + ", "  +  rectangle.opacity + ")"
          }
          else {
            val array = getRRGB(ctx.strokeStyle.toString)
            val r = array(0)
            val g = array(1)
            val b = array(2)
            ctx.strokeStyle = "rgba(" + r + ", " + g + ", "  +  b  + ", "  +  rectangle.opacity + ")"
          }
        }
      }
      if (rectangle.stroke.width != -1)
        ctx.lineWidth = rectangle.stroke.width
      else
        ctx.lineWidth = ctx_stroke_width
      ctx.strokeRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height)
    }
    else if (rectangle.state == 1){
      if (rectangle.fill.color.color != "")
        ctx.fillStyle = rectangle.fill.color.color
      else
        ctx.fillStyle = ctx_fill_color
      if(rectangle.opacity < 1) {
        if (ctx.fillStyle.toString.charAt(0) == '#') {
          val r = getR(ctx.fillStyle.toString)
          val g = getG(ctx.fillStyle.toString)
          val b = getB(ctx.fillStyle.toString)
          ctx.fillStyle = "rgba(" + r + ", " + g + ", " + b + ", " + rectangle.opacity + ")"
        }
        else if (ctx.fillStyle.toString.charAt(0) == 'r') {
          if(ctx.fillStyle.toString.charAt(3) == 'a') {
            val array = getRRGBA(ctx.fillStyle.toString)
            val r = array(0)
            val g = array(1)
            val b = array(2)
            ctx.fillStyle = "rgba(" + r + ", " + g + ", "  +  b  + ", "  +  rectangle.opacity + ")"
          }
          else {
            val array = getRRGB(ctx.fillStyle.toString)
            val r = array(0)
            val g = array(1)
            val b = array(2)
            ctx.fillStyle = "rgba(" + r + ", " + g + ", "  +  b  + ", "  +  rectangle.opacity + ")"
          }
        }
      }
      ctx.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height)
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
}
