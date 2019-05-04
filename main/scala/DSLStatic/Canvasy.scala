package DSLStatic

import DSLStatic.Shape._
import DSLStatic.Style.{ColorRGB, Fill, Gradient, Stroke}
import org.scalajs.dom
import org.scalajs.dom.raw.CanvasGradient
import org.scalajs.dom.{document, html}

import scala.collection.mutable.ListBuffer
import scala.scalajs.js

class Canvasy[I <: Shape](shape : I) {
  private val c = document.createElement("canvas").asInstanceOf[html.Canvas]
  private val ctx = c.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
  private val shape_groups = new ListBuffer[I]()
  private val ctx_stroke_color = ctx.strokeStyle
  private val ctx_stroke_width = ctx.lineWidth
  private val ctx_fill_color = ctx.fillStyle
  c.style.position = "absolute"
  private var movable : Boolean = false
  private var setEventRotate = true
  private var l : Double = 0
  private var t : Double = 0
  this += shape

  def this(){
    this(Text(0, 0, "", 2, 2, 2, "#ffffff", "0px Times New Roman", false).asInstanceOf[I])
  }

  def draw(): Unit = {
    resizeCanvas()
    shape_groups foreach(drawShape(_))
  }

  def translateX(v : Double): Canvasy[I] ={
    if(!movable){
      l += v
      c.style.left = l + "px"
    }
    this
  }

  def translateY(v : Double): Canvasy[I] ={
    if(!movable) {
      t += v
      c.style.top = t + "px"
    }
    this
  }

  def drawShape(shape: Shape): Unit = {
    if(movable)
      addListenerMove()
    ctx.save()
    if (shape.asInstanceOf[Shape].canRotate)
      addListenerRotate()
    shape match {
      case s : Rectangle =>
        drawRectangle(s)
        ctx.restore()
      case s: Square =>
        drawSquare(s)
        ctx.restore()
      case s: Circle =>
        drawCircle(s)
        ctx.restore()
      case s: RectangleTriangle =>
        drawTriangle(s)
        ctx.restore()
      case s:EquilateralTriangle =>
        drawTriangle(s)
        ctx.restore()
      case s: Text =>
        drawText(s)
        ctx.restore()
      case _ => print("Shape not found")
    }
  }

  def drawTriangle(triangle: Triangle): Unit = {
    triangle.style match {
      case _: Stroke =>
        checkColor(triangle)
        checkOpacity(triangle)
        ctx.rotate(triangle.rotation * Math.PI / 180)
        ctx.beginPath()
        ctx.moveTo(triangle.a._1, triangle.a._2)
        ctx.lineTo(triangle.b._1, triangle.b._2)
        ctx.lineTo(triangle.c._1, triangle.c._2)
        ctx.lineTo(triangle.a._1, triangle.a._2)
        ctx.stroke()
      case _: Fill =>
        checkColor(triangle)
        checkOpacity(triangle)
        ctx.rotate(triangle.rotation * Math.PI / 180)
        ctx.beginPath()
        ctx.moveTo(triangle.a._1, triangle.a._2)
        ctx.lineTo(triangle.b._1, triangle.b._2)
        ctx.lineTo(triangle.c._1, triangle.c._2)
        ctx.lineTo(triangle.a._1, triangle.a._2)
        ctx.fill()
      case _ =>
    }

  }

  def moveMouse(boolean: Boolean): Unit = {
    movable = boolean
  }

  def keyRotate(boolean: Boolean): Unit = {
    shape_groups foreach(_.asInstanceOf[Shape].canRotate = boolean)
  }

  def drawCircle(circle: Circle): Unit = {
    circle.style match {
      case _: Stroke =>
        checkColor(circle)
        checkOpacity(circle)
        ctx.rotate(circle.rotation * Math.PI / 180)
        ctx.beginPath()
        ctx.arc(circle.x, circle.y, circle.radius, 0, 2 * Math.PI)
        ctx.stroke()
      case _: Fill =>
        checkColor(circle)
        checkOpacity(circle)
        ctx.rotate(circle.rotation * Math.PI / 180)
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
        if(rectangle.rotation != 0) {
          ctx.translate(rectangle.x+ rectangle.width/2, rectangle.y+rectangle.height/2)
          ctx.rotate(rectangle.rotation * Math.PI / 180)
          ctx.strokeRect(-rectangle.width/2,-rectangle.height/2, rectangle.width, rectangle.height)
        }
        else {
          ctx.strokeRect(rectangle.x,rectangle.y, rectangle.width, rectangle.height)
        }
      case _: Fill =>
        checkColor(rectangle)
        checkOpacity(rectangle)
        if(rectangle.rotation != 0) {
          ctx.translate(rectangle.x+ rectangle.width/2, rectangle.y+rectangle.height/2)
          ctx.rotate(rectangle.rotation * Math.PI / 180)
          ctx.fillRect(-rectangle.width/2,-rectangle.height/2, rectangle.width, rectangle.height)
        }
        else {
          ctx.fillRect(rectangle.x,rectangle.y, rectangle.width, rectangle.height)
        }
      case _ =>
    }
  }

  def drawText(text: Text): Unit = {
    text.style match {
      case _: Fill =>
        checkColor(text)
        ctx.rotate(text.rotation * Math.PI / 180)
        ctx.shadowOffsetX = text.soX
        ctx.shadowOffsetY = text.soY
        ctx.shadowBlur = text.sb
        ctx.shadowColor = text.sc
        ctx.shadowOffsetX = text.soX
        ctx.font = text.font
        if(!text.stroke)
          ctx.fillText(text.text, text.x, text.y)
        else
          ctx.strokeText(text.text,text.x,text.y)
      case _ =>
    }
  }

  def drawSquare(square: Square): Unit = {
    square.style match {
      case _: Stroke =>
        checkColor(square)
        checkOpacity(square)
        if(square.rotation != 0) {
          ctx.translate(square.x+ square.cote/2, square.y+square.cote/2)
          ctx.rotate(square.rotation * Math.PI / 180)
          ctx.strokeRect(-square.cote/2,-square.cote/2, square.cote, square.cote)
        }
        else {
          ctx.strokeRect(square.x, square.y, square.cote, square.cote)
        }
      case _: Fill =>
        checkColor(square)
        checkOpacity(square)
        if(square.rotation != 0) {
          ctx.translate(square.x+ square.cote/2, square.y+square.cote/2)
          ctx.rotate(square.rotation * Math.PI / 180)
          ctx.fillRect(-square.cote/2,-square.cote/2, square.cote, square.cote)
        }
        else {
          ctx.fillRect(square.x, square.y, square.cote, square.cote)
        }
      case _ =>
    }
  }


  def += (group: ListBuffer[Shape]): Canvasy[I] = {
    group foreach(shape_groups += _.asInstanceOf[I])
    shape_groups sortBy(shape_groups => (shape_groups.x,shape_groups.y))
    this
  }

  def +=  (shape: Shape): Canvasy[I] = {
    shape_groups += shape.asInstanceOf[I]
    shape_groups sortBy(shape_groups => (shape_groups.x,shape_groups.y))
    this
  }

  def checkOpacity [K <: Shape](shape : K) : Unit = {
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

  def addListenerMove(): Unit ={
    def reinitXY(): Unit ={
      shape_groups foreach(_.asInstanceOf[Shape].x = 0)
      shape_groups foreach(_.asInstanceOf[Shape].y = 0)
    }
    if(shape_groups.length == 1){
      reinitXY()
      resizeCanvas()
    }
    def rect = c.getBoundingClientRect()
    var offX : Double = 0
    var offY : Double = 0
    var isDragging = false
    val onmousemove ={(e: dom.MouseEvent) =>
      if(isDragging){
        c.style.left = (e.clientX - offX) + "px"
        c.style.top  = (e.clientY - offY) + "px"
      }
    }
    val onmouseup = {(e: dom.MouseEvent) =>
      if(isDragging) {
        dom.window.removeEventListener("mousemove", onmousemove, useCapture = true)
        isDragging = false
      }
    }
    val onmousedown ={(e: dom.MouseEvent) =>
      if(!isDragging){
        isDragging = true
        offX = e.clientX - rect.left
        offY = e.clientY - rect.top
        dom.window.addEventListener("mousemove", onmousemove, useCapture = true)
      }
    }
    c.addEventListener("mousedown", onmousedown, useCapture = false)
    dom.window.addEventListener("mouseup", onmouseup, useCapture = false)
  }

  def addListenerRotate(): Unit ={
    var rotate = false
    val onClick ={(e: dom.MouseEvent) =>
      rotate = !rotate
//      if (rotate)
//        shape_groups foreach (_ foreach(_.asInstanceOf[Shape]. += ))
//      else
//        shape_groups foreach (_ foreach(_.asInstanceOf[Shape].opacity -= ))
//      ctx.clearRect(0, 0, c.width, c.height)
//      draw()
      println(rotate)
    }

    val onKey ={(e: dom.KeyboardEvent) =>
      if (rotate) {
        e.key match {
          case "ArrowRight" =>
            shape_groups foreach(_.asInstanceOf[Shape].rotation -= 10)
            ctx.clearRect(0, 0, c.width, c.height)
            draw()
          case "ArrowLeft" =>
            shape_groups foreach(_.asInstanceOf[Shape].rotation += 10)
            ctx.clearRect(0, 0, c.width, c.height)
            draw()
          case _ =>
            println(e.key)
        }
      }
    }
    if(setEventRotate) {
      c.addEventListener("click", onClick, useCapture = false)
      dom.window.addEventListener("keydown", onKey, useCapture = false)
      setEventRotate = false
    }
  }


  def resizeCanvas(): Unit ={
    var minX = Double.MaxValue
    var minY = Double.MaxValue
    var maxX = Double.MinValue
    var maxY = Double.MinValue
    var maxAdd : Double = 0
    for(shape <- shape_groups){
      if(shape.x+shape.y < minX + minY){
        minX = shape.x
        minY = shape.y
      }
      if(shape.x+shape.y > maxX + maxY){
        maxX = shape.x
        maxY = shape.y
        maxAdd = addSizeForShape(shape)
      }
    }
    if(minY != 0 || minX != 0){
      for(shape <- shape_groups){
        shape.x = shape.x - minX
        shape.y = shape.y - minY
      }
      resizeCanvas()
    }
    else {
      c.width= maxX.toInt + maxAdd.toInt
      c.height= maxY.toInt + maxAdd.toInt
      document.getElementById("container").appendChild(c)
    }
  }

  def addSizeForShape(shape : Shape): Double = {
    shape match {
      case Rectangle(a,b,width, height,s,o) =>
        if(shape.rotation == 0) {
          if(width > height) width else height
        }
        else {
          if(shape.rotation <= 45 && shape.rotation >= -45)
            if(width > height) width + width/2 * Math.cos(Math.abs(shape.rotation)*Math.PI/180) else height + height/2 * Math.cos(Math.abs(shape.rotation)*Math.PI/180)
          else
            if(width > height) width - width/2 * Math.cos(Math.abs(shape.rotation)*Math.PI/180) else height - height/2 * Math.cos(Math.abs(shape.rotation)*Math.PI/180)
        }
      case Square(a,b,cote,s,o) =>
        if(shape.rotation == 0) {
          cote
        }
        else {
          if(shape.rotation <= 45 && shape.rotation >= -45)
             cote + cote/2 * Math.cos(Math.abs(shape.rotation)*Math.PI/180)
          else
             cote - cote/2 * Math.cos(Math.abs(shape.rotation)*Math.PI/180)
        }
      case Circle(radius, a,b,s,o) =>
        radius
      case RectangleTriangle(x, y, a, b,s,o) =>
        200 //TODO
      case EquilateralTriangle(x, y, a,s,o) =>
        200 //TODO
      case Text(x, y, t,sx,sy,b,c,f,str) =>
        200 //TODO
      case _ => 0
    }
  }

}

object Canvasy {
  def drawHand(w:Int): Unit ={
    val c = document.createElement("canvas").asInstanceOf[html.Canvas]
    val ctx = c.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    document.body.appendChild(c)
    c.width = w+1
    c.height = w+1
    def rect = c.getBoundingClientRect()
    ctx.strokeStyle = "#000000"
    ctx.lineWidth = 2
    var dragState = 0
    ctx.strokeRect(0, 0, w+1, w+1)
    c.onmousemove ={(e: dom.MouseEvent) =>
      if (dragState == 1) {
        ctx.lineTo(
          e.clientX - rect.left,
          e.clientY - rect.top
        )
        ctx.stroke()
      }
    }
    c.onmouseup = {(e: dom.MouseEvent) =>
      if(dragState == 1) {
        ctx.fill()
        dragState = 2
      }else if (dragState == 2){
        ctx.strokeRect(0, 0, w, w)
        ctx.clearRect(1, 1, w-1, w-1)
        dragState = 0
      }
    }
    c.onmousedown ={(e: dom.MouseEvent) =>
      if (dragState == 0) {
        dragState = 1
        ctx.beginPath()
        ctx.moveTo(
          e.clientX - rect.left,
          e.clientY - rect.top
        )
      }
    }
  }
}
