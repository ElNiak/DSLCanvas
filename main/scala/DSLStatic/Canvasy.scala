package DSLStatic

import DSLStatic.Shape._
import DSLStatic.Style.{Clear,Fill, Stroke}
import org.scalajs.dom
import org.scalajs.dom.html.Canvas
import org.scalajs.dom.{CanvasRenderingContext2D, document, html}
import scala.collection.mutable.ListBuffer
import scala.reflect.ClassTag
import scala.scalajs.js

class Canvasy[I <: Shape](shape : I) {
  val c : Canvas = document.createElement("canvas").asInstanceOf[html.Canvas]
  val ctx : CanvasRenderingContext2D = c.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
  private val shape_groups : ListBuffer[I] = new ListBuffer[I]() // contains the shapes of the canvas
  //These are used to represente the "state" of the canvas and then modify the behavior
  private var movable : Boolean = false
  private var rotatable : Boolean = false
  private var animable : Boolean = false
  private var border : Boolean = false
  private var acceleration : Boolean = false
  private var valid : Boolean = true
  private var resize : Boolean = true
  private var setEventRotate : Boolean = true
  private var setEventMovable : Boolean = true
  private var setEventAnimation : Boolean = true

  //left and top offset of the canvas
  private var l : Double = 0
  private var t : Double = 0

  c.style.position = "absolute"
  if(shape != null) this += shape

  /**
    * New constructor : Empty
    */
  def this(){
    this(Text((0, 0), "", 2, 2, 2, "#ffffff", "0px Times New Roman", strokeB = false).asInstanceOf[I])
  }
  /**
    * New constructor : List of shape
    */
  def this(l :ListBuffer[I]){
    this(l.head)
    l - l.head
    this += l.asInstanceOf[ListBuffer[Shape]]
  }

  /**
    * Draw all the shape in the canvas
    */
  def draw(): Unit = {
    if(valid){
      if(animable)
        addListenerMoveAnimation()
      if(resize)
        resizeCanvas()
      else
        document.getElementById("container").appendChild(c)
      if(movable)
        addListenerMove()
      if(rotatable)
        addListenerRotate()
      for(shape <- shape_groups){
        ctx.save()
        shape.draw(ctx)
        ctx.restore()
      }
    }
  }


  /**
    * @return all stroke shape in the canvas
    */
  def StrokeShape: ListBuffer[Shape] = {
    val lst : ListBuffer[Shape] = new ListBuffer[Shape]
    for(shape <- shape_groups){
      shape.style match {
        case _ : Stroke => lst += shape
        case _ =>
      }
    }
    lst
  }

  /**
    * @return all fill shape of instance A in the canvas
    */
  def FillShape[A](implicit tag: ClassTag[A])  : ListBuffer[A] = {
    val lst : ListBuffer[A] = new ListBuffer[A]
    val fillElement = FillShape
    for(shape <- fillElement){
      shape match {
        case a: A =>
            lst += a
        case _ =>
      }
    }
    lst
  }

  /**
    * set or not the automatic resize of the canvas
    */
  def automaticResize(v : Boolean): Canvasy[I] ={
    resize = v
    this
  }

  /**
    * @return all stroke shape of instance A in the canvas
    */
  def StrokeShape[A](implicit tag: ClassTag[A])  : ListBuffer[A] = {
    val lst : ListBuffer[A] = new ListBuffer[A]
    val strokeElement = StrokeShape
    for(shape <- strokeElement){
      shape match {
        case a: A =>
            lst += a
        case _ =>
      }
    }
    lst
  }


  /**
    * @return all shape of id:"id" in the canvas
    */
  def get(i: String): ListBuffer[Shape] ={
    val lst : ListBuffer[Shape] = new ListBuffer[Shape]
    if(i == null || i.isEmpty)
      return null
    for(shape <- shape_groups){
      if(shape.id.equals(i))
        lst += shape
    }
    lst
  }

  /**
    * @return all fill shape in the canvas
    */
  def FillShape: ListBuffer[Shape] ={
    val lst : ListBuffer[Shape] = new ListBuffer[Shape]
    for(shape <- shape_groups){
      shape.style match {
        case _:Fill => lst += shape
        case _ =>
      }
    }
    lst
  }

  /**
    * @return all shape in the canvas
    */
  def Shape: ListBuffer[Shape] ={
   shape_groups.asInstanceOf[ListBuffer[Shape]]
  }

  /**
    * @return append new shape in the canvas
    */
  def += (group: ListBuffer[Shape]): Canvasy[I] = {
    if(group != null){
      group foreach(shape_groups += _.asInstanceOf[I])
      shape_groups sortBy(shape_groups => (shape_groups.x,shape_groups.y))
    }
    this
  }
  def +=  (shape: Shape): Canvasy[I] = {
    if(shape != null) {
      shape_groups += shape.asInstanceOf[I]
      shape_groups sortBy (shape_groups => (shape_groups.x, shape_groups.y))
    }
    this
  }

  /**
    * Translate the canvas
    */
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


  /**
    * Active or not the listener
    */
  def moveMouse(boolean: Boolean): Canvasy[I] = {
    movable = boolean
    this
  }
  def keyRotate(boolean: Boolean): Canvasy[I] = {
    rotatable = boolean
    this
  }
  def anim(x : (Boolean, Boolean)): Canvasy[I] = {
    animable = x._1
    border = x._2
    acceleration = false
    this
  }
  def animLeftRight(x : (Boolean, Double , Boolean)): Canvasy[I] = {
    animable = x._1
    border = x._3
    acceleration = false
    for(shape <- shape_groups){
      shape.vx = x._2
      shape.vy = 0
    }
    this
  }
  def animUpDown(x : (Boolean, Double , Boolean)): Canvasy[I] = {
    animable = x._1
    border = x._3
    acceleration = false
    for(shape <- shape_groups){
      shape.vy = x._2
      shape.vx = 0
    }
    this
  }
  def animA(x : (Boolean, Boolean)): Canvasy[I] = { //with acceleration
    animable = x._1
    border = x._2
    acceleration = true
    this
  }
  def animLeftRightA(x : (Boolean, Double , Boolean)): Canvasy[I] = {
    animable = x._1
    border = x._3
    acceleration = true
    for(shape <- shape_groups){
      shape.vx = x._2
      shape.vy = 0
    }
    this
  }
  def animUpDownA(x : (Boolean, Double , Boolean)): Canvasy[I] = {
    animable = x._1
    border = x._3
    acceleration = true
    for(shape <- shape_groups){
      shape.vy = x._2
      shape.vx = 0
    }
    this
  }

  /**
    * set rotation on all shape of the canvas
    */
  def rotate(v: Double): Canvasy[I] = {
    shape_groups foreach(_.rotation=v)
    this
  }


  /**
    * Define the mouse drag and drop canvas
    */
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
    val onmousemove ={e: dom.MouseEvent =>
      if(isDragging){
        c.style.left = (e.clientX - offX) + "px"
        c.style.top  = (e.clientY - offY) + "px"
      }
    }
    val onmouseup = { _: dom.MouseEvent =>
      if(isDragging) {
        dom.window.removeEventListener("mousemove", onmousemove, useCapture = true)
        isDragging = false
      }
    }
    val onmousedown ={e: dom.MouseEvent =>
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

  /**
    * Define the keyboard rotate listener
    */
  private def addListenerRotate(): Unit ={
    var rotate = false

    val onClick ={ _: dom.MouseEvent => rotate = !rotate }

    val onKey ={e: dom.KeyboardEvent =>
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

  /**
    * Allow to draw hand figure
    * from : https://scala-js.github.io/scala-js-dom/
    */
  def drawHand(w:(Int,Int)): Canvasy[I] ={
    valid = false
    c.width = w._1+1
    c.height = w._2+1
    document.body.appendChild(c)
    def rect = c.getBoundingClientRect()
    ctx.lineWidth = 2
    var dragState = 0
    ctx.strokeRect(0, 0, w._1+1, w._2+1)
    c.onmousemove ={e: dom.MouseEvent =>
      if (dragState == 1) {
        ctx.lineTo(
          e.clientX - rect.left,
          e.clientY - rect.top
        )
        ctx.stroke()
      }
    }
    c.onmouseup = { _: dom.MouseEvent =>
      if(dragState == 1) {
        ctx.fill()
        dragState = 2
      }else if (dragState == 2){
        ctx.strokeRect(0, 0, w._1, w._2)
        ctx.clearRect(1, 1, w._1-1, w._2-1)
        dragState = 0
      }
    }
    c.onmousedown ={e: dom.MouseEvent =>
      if (dragState == 0) {
        dragState = 1
        ctx.beginPath()
        ctx.moveTo(
          e.clientX - rect.left,
          e.clientY - rect.top
        )
      }
    }
    this
  }

  /**
    * Define the animation bahavior
    */
  private def addListenerMoveAnimation(): Unit ={
    resize = false
    var move = false
    val onClick ={ _: dom.MouseEvent => move = !move }
    var handler: js.timers.SetIntervalHandle = js.timers.setInterval(20){}

    val onKey ={e: dom.KeyboardEvent =>
      if (move) {
        e.key match {
          case "Enter" =>
            if(animable){
              animable = ! animable
              handler = js.timers.setInterval(20){
                for(shape <- shape_groups){
                  shape.x += shape.vx
                  shape.y += shape.vy
                  if(border){
                    if (shape.y + shape.vy > c.height || shape.y + shape.vy < 0) {
                      shape.vy = -shape.vy
                    }
                    if (shape.x + shape.vx > c.width || shape.x + shape.vx < 0) {
                      shape.vx = -shape.vx
                    }
                  }
                  if(acceleration){
                    shape.vy *= shape.ay
                    shape.vy += shape.ax
                  }
                }
                ctx.clearRect(0, 0, c.width, c.height)
                draw()
              }
            }
            else {
              js.timers.clearInterval(handler)
              animable = !animable
            }
          case _ =>
            println(e.key)
        }
      }
    }

    if(setEventAnimation) {
      resizeCanvas()
      c.addEventListener("click", onClick, useCapture = false)
      dom.window.addEventListener("keydown", onKey, useCapture = false)
      setEventAnimation = false
    }
  }

  /**
    * Resize canvas with specified values
    */
  def resizeCanvas(w : Int, h : Int) : Canvasy[I] = {
    if(!resize){
      if(w < 0 || h < 0) throw new ShapeAttributeException("Canvas weight and height cannot be smaller than 0")
      c.width= w
      c.height= h
    }
    this
  }

  /**
    * Automatic resize with optimal size
    */
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
      if(!isText || animable){
        c.width = maxX.toInt + maxAdd.toInt
        c.height = maxY.toInt + maxAdd.toInt
      }
      else {
        c.width = maxX.toInt + maxAdd.toInt
        c.height = maxY.toInt + maxAdd.toInt + 20
        ctx.translate(0,20)
      }
      document.getElementById("container").appendChild(c)
    }
  }

}