package DSLStatic

import DSLStatic.Shape._
import DSLStatic.Style.{Clear, ColorRGB, Fill, Gradient, Stroke}
import org.scalajs.dom
import org.scalajs.dom.raw.CanvasGradient
import org.scalajs.dom.{document, html}

import scala.collection.mutable.ListBuffer
import scala.reflect.ClassTag
import scala.scalajs.js

class Canvasy[I <: Shape](shape : I) {
  private val c = document.createElement("canvas").asInstanceOf[html.Canvas]
  private val ctx = c.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
  private val shape_groups = new ListBuffer[I]()
  private val ctx_stroke_color = ctx.strokeStyle
  private val ctx_stroke_width = ctx.lineWidth
  private val ctx_stroke_cap = ctx.lineCap
  private val ctx_stroke_join = ctx.lineJoin
  private val ctx_stroke_dash = ctx.lineDashOffset
  private val ctx_fill_color = ctx.fillStyle
  private var movable : Boolean = false
  private var rotatable : Boolean = false
  private var setEventRotate = true
  private var setEventMovable = true
  private var l : Double = 0
  private var t : Double = 0
  var strokeElement: ListBuffer[Shape] = getStroke()
  var fillElement: ListBuffer[Shape] = getFill()

  c.style.position = "absolute"
  if(shape != null) this += shape

  def this(){
    this(Text((0, 0), "", 2, 2, 2, "#ffffff", "0px Times New Roman", false).asInstanceOf[I])
  }

  def draw() : Unit = {
    resizeCanvas()
    if(movable)
      addListenerMove()
    if(rotatable)
      addListenerRotate()
    shape_groups foreach(drawShape(_))
  }


  private def getStroke() : ListBuffer[Shape] = {
    val lst : ListBuffer[Shape] = new ListBuffer[Shape]
    for(shape <- shape_groups){
      shape.style match {
        case _ : Stroke => lst += shape
        case _ =>
      }
    }
    lst
  }

  def getFillShape[A](implicit tag: ClassTag[A])  : ListBuffer[A] = {
    val lst : ListBuffer[A] = new ListBuffer[A]
    for(shape <- fillElement){
      shape match {
        case a: A =>
            lst += a
        case _ =>
      }
    }
    lst
  }

  def getStrokeShape[A](implicit tag: ClassTag[A])  : ListBuffer[A] = {
    val lst : ListBuffer[A] = new ListBuffer[A]
    for(shape <- strokeElement){
      shape match {
        case a: A =>
            lst += a
        case _ =>
      }
    }
    lst
  }

  private def getFill() : ListBuffer[Shape] ={
    val lst : ListBuffer[Shape] = new ListBuffer[Shape]
    for(shape <- shape_groups){
      shape.style match {
        case _:Fill => lst += shape
        case _ =>
      }
    }
    lst
  }

  def += (group: ListBuffer[Shape]): Canvasy[I] = {
    group foreach(shape_groups += _.asInstanceOf[I])
    shape_groups sortBy(shape_groups => (shape_groups.x,shape_groups.y))
    strokeElement  = getStroke()
    fillElement = getFill()
    this
  }

  def +=  (shape: Shape): Canvasy[I] = {
    shape_groups += shape.asInstanceOf[I]
    shape_groups sortBy(shape_groups => (shape_groups.x,shape_groups.y))
    strokeElement  = getStroke()
    fillElement = getFill()
    this
  }

  def translateX(v : Double): Canvasy[I] ={
    if(!movable){
      l += v
      c.style.left = l + "px"
    }
    this
  }

  def moveMouse(boolean: Boolean): Canvasy[I] = {
    movable = boolean
    this
  }

  def keyRotate(boolean: Boolean): Canvasy[I] = {
    rotatable = boolean
    this
  }

  def translateY(v : Double): Canvasy[I] ={
    if(!movable) {
      t += v
      c.style.top = t + "px"
    }
    this
  }

  def rotate(v: Double): Canvasy[I] = {
    shape_groups foreach(_.rotation=v)
    this
  }

  private def drawShape(shape: Shape): Unit = {
    ctx.save()
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
      case s: TriangleRectangle =>
        drawTriangleR(s)
        ctx.restore()
      case s:TriangleEquilateral =>
        drawTriangleE(s)
        ctx.restore()
      case s: Text =>
        drawText(s)
        ctx.restore()
      case s: PPLShape =>
        drawPPLShape(s)
        ctx.restore()
      case s: TrianglePP =>
        drawTrianglePP(s)
        ctx.restore()
      case s: CurveQuadratic =>
        drawQCurve(s)
        ctx.restore()
      case s: CurveBezier =>
        drawBCurve(s)
        ctx.restore()
      case _ => print("Shape not found")
    }
  }

  private def drawQCurve(triangle: CurveQuadratic): Unit = {
    triangle.style match {
      case _: Stroke =>
        checkColor(triangle)
        checkOpacity(triangle)
        ctx.rotate(triangle.rotation * Math.PI / 180)
        ctx.beginPath()
        ctx.moveTo(triangle.x,triangle.y)
        ctx.quadraticCurveTo(triangle.cp1x, triangle.cp1y, triangle.tx, triangle.ty)
        ctx.stroke()
      case _: Fill =>
        checkColor(triangle)
        checkOpacity(triangle)
        ctx.rotate(triangle.rotation * Math.PI / 180)
        ctx.beginPath()
        ctx.moveTo(triangle.x,triangle.y)
        ctx.quadraticCurveTo(triangle.cp1x, triangle.cp1y, triangle.tx, triangle.ty)
        ctx.fill()
      case _ =>
    }
  }

  private def drawBCurve(triangle: CurveBezier): Unit = {
    triangle.style match {
      case _: Stroke =>
        checkColor(triangle)
        checkOpacity(triangle)
        ctx.rotate(triangle.rotation * Math.PI / 180)
        ctx.moveTo(triangle.x,triangle.y)
        ctx.bezierCurveTo(triangle.cp1x, triangle.cp1y, triangle.cp2x, triangle.cp2y, triangle.tx, triangle.ty)
        ctx.stroke()
      case _: Fill =>
        checkColor(triangle)
        checkOpacity(triangle)
        ctx.rotate(triangle.rotation * Math.PI / 180)
        ctx.moveTo(triangle.x,triangle.y)
        ctx.bezierCurveTo(triangle.cp1x, triangle.cp1y, triangle.cp2x, triangle.cp2y, triangle.tx, triangle.ty)
        ctx.fill()
      case _ =>
    }
  }

  private def drawTriangleR(triangle: TriangleRectangle): Unit = {
    triangle.style match {
      case _: Stroke =>
        checkColor(triangle)
        checkOpacity(triangle)
        if(triangle.rotation != 0) {
          ctx.moveTo((triangle.a._1 + triangle.b._1 + triangle.c._1)/3, (triangle.a._2 + triangle.b._2 + triangle.c._2)/3)
          ctx.rotate(triangle.rotation * Math.PI / 180)
        }
        ctx.lineCap = "round"
        ctx.beginPath()
        ctx.moveTo(triangle.a._1, triangle.a._2)
        ctx.lineTo(triangle.b._1, triangle.b._2)
        ctx.lineTo(triangle.c._1, triangle.c._2)
        ctx.lineTo(triangle.a._1, triangle.a._2)
        ctx.stroke()
      case _: Fill =>
        checkColor(triangle)
        checkOpacity(triangle)
        if(triangle.rotation != 0) {
          ctx.moveTo((triangle.a._1 + triangle.b._1 + triangle.c._1)/3, (triangle.a._2 + triangle.b._2 + triangle.c._2)/3)
          ctx.rotate(triangle.rotation * Math.PI / 180)
        }
        ctx.lineCap = "round"
        ctx.beginPath()
        ctx.moveTo(triangle.a._1, triangle.a._2)
        ctx.lineTo(triangle.b._1, triangle.b._2)
        ctx.lineTo(triangle.c._1, triangle.c._2)
        ctx.lineTo(triangle.a._1, triangle.a._2)
        ctx.fill()
      case _ =>
    }
  }

  private def drawTriangleE(triangle: TriangleEquilateral): Unit = {
    triangle.style match {
      case _: Stroke =>
        checkColor(triangle)
        checkOpacity(triangle)
        if(triangle.rotation != 0) {
          ctx.moveTo((triangle.a._1 + triangle.b._1 + triangle.c._1)/3, (triangle.a._2 + triangle.b._2 + triangle.c._2)/3)
          ctx.rotate(triangle.rotation * Math.PI / 180)
        }
        ctx.lineCap = "round"
        ctx.beginPath()
        ctx.moveTo(triangle.a._1, triangle.a._2)
        ctx.lineTo(triangle.b._1, triangle.b._2)
        ctx.lineTo(triangle.c._1, triangle.c._2)
        ctx.lineTo(triangle.a._1, triangle.a._2)
        ctx.stroke()
      case _: Fill =>
        checkColor(triangle)
        checkOpacity(triangle)
        if(triangle.rotation != 0) {
          ctx.moveTo((triangle.a._1 + triangle.b._1 + triangle.c._1)/3, (triangle.a._2 + triangle.b._2 + triangle.c._2)/3)
          ctx.rotate(triangle.rotation * Math.PI / 180)
        }
        ctx.lineCap = "round"
        ctx.beginPath()
        ctx.moveTo(triangle.a._1, triangle.a._2)
        ctx.lineTo(triangle.b._1, triangle.b._2)
        ctx.lineTo(triangle.c._1, triangle.c._2)
        ctx.lineTo(triangle.a._1, triangle.a._2)
        ctx.fill()
      case _ =>
    }
  }

  private def drawPPLShape(xogone: PPLShape): Unit = {
    xogone.style match {
      case _: Stroke =>
        checkColor(xogone)
        checkOpacity(xogone)
        ctx.rotate(xogone.rotation * Math.PI / 180)
        ctx.translate(xogone.x, xogone.y)
        ctx.beginPath()
        ctx.moveTo(xogone.list(0)._1, xogone.list(0)._2)
        for(i <- 1 until xogone.list.length) {
          ctx.lineTo(xogone.list(i)._1, xogone.list(i)._2)
        }
        ctx.lineTo(xogone.list(0)._1, xogone.list(0)._2)
        ctx.stroke()
      case _: Fill =>
        checkColor(xogone)
        checkOpacity(xogone)
        ctx.rotate(xogone.rotation * Math.PI / 180)
        ctx.translate(xogone.x, xogone.y)
        ctx.beginPath()
        ctx.moveTo(xogone.coordinates(0)._1, xogone.coordinates(0)._2)
        for(i <- 1 until xogone.list.length) {
          ctx.lineTo(xogone.coordinates(i)._1, xogone.coordinates(i)._2)
        }
        ctx.lineTo(xogone.coordinates(0)._1, xogone.coordinates(0)._2)
        ctx.fill()
      case _ =>
    }
  }

  private def drawTrianglePP(triangle : TrianglePP): Unit = {
    println(triangle.coordinates)
    triangle.style match {
      case _: Stroke =>
        checkColor(triangle)
        checkOpacity(triangle)
        if(triangle.rotation != 0) {
          ctx.moveTo((triangle.a._1 + triangle.b._1 + triangle.c._1)/3, (triangle.a._2 + triangle.b._2 + triangle.c._2)/3)
          ctx.rotate(triangle.rotation * Math.PI / 180)
        }
        ctx.lineCap = "round"
        ctx.beginPath()
        ctx.moveTo(triangle.a._1, triangle.a._2)
        ctx.lineTo(triangle.b._1, triangle.b._2)
        ctx.lineTo(triangle.c._1, triangle.c._2)
        ctx.lineTo(triangle.a._1, triangle.a._2)
        ctx.stroke()
      case _: Fill =>
        checkColor(triangle)
        checkOpacity(triangle)
        if(triangle.rotation != 0) {
          ctx.moveTo((triangle.a._1 + triangle.b._1 + triangle.c._1)/3, (triangle.a._2 + triangle.b._2 + triangle.c._2)/3)
          ctx.rotate(triangle.rotation * Math.PI / 180)
        }
        ctx.lineCap = "round"
        ctx.beginPath()
        ctx.moveTo(triangle.a._1, triangle.a._2)
        ctx.lineTo(triangle.b._1, triangle.b._2)
        ctx.lineTo(triangle.c._1, triangle.c._2)
        ctx.lineTo(triangle.a._1, triangle.a._2)
        ctx.fill()
      case _ =>
    }
  }

  private def drawCircle(circle: Circle): Unit = {
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

  private def drawRectangle(rectangle: Rectangle): Unit = {
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

  private def drawText(text: Text): Unit = {
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

  private def drawSquare(square: Square): Unit = {
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

  private def checkOpacity [K <: Shape](shape : K) : Unit = {
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

  private def checkColor [K <: Shape](shape : K) : Unit = {
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

  private def addListenerMove(): Unit ={
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
    if(setEventMovable) {
      c.addEventListener("mousedown", onmousedown, useCapture = false)
      dom.window.addEventListener("mouseup", onmouseup, useCapture = false)
      setEventMovable = false
    }
  }

  private def addListenerRotate(): Unit ={
    var rotate = false
    val onClick ={(e: dom.MouseEvent) =>
      rotate = !rotate
    }

    val onKey ={(e: dom.KeyboardEvent) =>
      if (rotate) {
        e.key match {
          case "ArrowRight" =>
            shape_groups foreach(_.asInstanceOf[Shape].rotation -= 10)
            ctx.clearRect(0, 0, c.width, c.height)
            draw
          case "ArrowLeft" =>
            shape_groups foreach(_.asInstanceOf[Shape].rotation += 10)
            ctx.clearRect(0, 0, c.width, c.height)
            draw
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


  private def resizeCanvas(): Unit ={
    var minX = Double.MaxValue
    var minY = Double.MaxValue
    var maxX = Double.MinValue
    var maxY = Double.MinValue
    var maxAdd : Double = 0
    var isText :Boolean = false
    for(shape <- shape_groups){
      if(shape.x+shape.y < minX + minY){
        minX = shape.x
        minY = shape.y
        if((minX == 0 || minY == 0) && shape.isInstanceOf[Text])
          isText = true
      }
      if(shape.x+shape.y > maxX + maxY){
        maxX = shape.x
        maxY = shape.y
        maxAdd =shape.getSize()
      }
    }
    if(minY != 0 || minX != 0){
      for(shape <- shape_groups){
        if(!shape.isInstanceOf[Triangle]){
          shape.x = shape.x - minX
          shape.y = shape.y - minY
        }
      }
      resizeCanvas()
    }
    else {
      if(!isText){
        c.width= maxX.toInt + maxAdd.toInt
        c.height= maxY.toInt + maxAdd.toInt
      }
      else {
        c.width= maxX.toInt + maxAdd.toInt
        c.height= maxY.toInt + maxAdd.toInt + 20
        ctx.translate(0,20)
      }
      document.getElementById("container").appendChild(c)
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
