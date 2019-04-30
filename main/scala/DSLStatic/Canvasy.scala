package DSLStatic

import org.scalajs.dom
import org.scalajs.dom.{document, html}

import scala.collection.mutable.ListBuffer

class Canvasy[I <: CanvasyElement](c: html.Canvas) {
  private val ctx = c.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
  private val shape_groups = new ListBuffer[Array[I]]()
  private val ctx_stroke_color = ctx.strokeStyle
  private val ctx_stroke_width = ctx.lineWidth


  def draw(): Unit = {
    shape_groups foreach(_ foreach(drawShape(_)))
  }

  def drawShape(shape: CanvasyElement): Unit = {
    println("Draw shape")
    shape match {
      case Rectangle(a,b,width, height) =>  {
        drawRectangle(shape.asInstanceOf[Rectangle])
      }
      case Circle(radius, a,b) =>  {
        drawCircle(shape.asInstanceOf[Circle])
      }
      case _ => print("Can only draw Rectangle and Circle")
    }
  }

  def drawCircle(circle: Circle): Unit = {
    if (circle.stroke.color != "")
      ctx.strokeStyle = circle.stroke.color
    else
      ctx.strokeStyle = ctx_stroke_color
    if (circle.stroke.width != -1)
      ctx.lineWidth = circle.stroke.width
    else
      ctx.lineWidth = ctx_stroke_width

    ctx.beginPath()
    ctx.arc(circle.x, circle.y, circle.radius, 0, 2 * Math.PI)
    ctx.stroke()

  }

  def drawRectangle(rectangle: Rectangle): Unit = {
    if (rectangle.stroke.color != "")
      ctx.strokeStyle = rectangle.stroke.color
    else
      ctx.strokeStyle = ctx_stroke_color
    if (rectangle.stroke.width != -1)
      ctx.lineWidth = rectangle.stroke.width
    else
      ctx.lineWidth = ctx_stroke_width
    ctx.strokeRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height)
  }

  def += [J <: CanvasyElement](group: Array[J]): Unit = {
    shape_groups += group.asInstanceOf[Array[I]]
  }
}
