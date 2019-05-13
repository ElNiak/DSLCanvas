package DSLPerso.Shape
import DSLPerso.ShapeAttributeException
import DSLPerso.Style.SV.SValue
import DSLPerso.Style.{Clear, ColorRGB, Fill, Gradient, Stroke, Style}
import org.scalajs.dom
import org.scalajs.dom.{CanvasRenderingContext2D, Event, document}
import org.scalajs.dom.html.Image
import org.scalajs.dom.raw.{HTMLImageElement, HTMLVideoElement}

import scala.scalajs.js.timers


case class Video (from : (Double, Double), path: String) extends Shape {
  override var opacity: Double = 0
  override var style : Style = new Fill
  override var x : Double = if(from._1 >= 0) from._1 else throw new ShapeAttributeException("x cannot be smaller than 0")
  override var y : Double = if(from._2 >= 0) from._2 else throw new ShapeAttributeException("y cannot be smaller than 0")
  override var size: Int = _
  override var rotation: Double = 0
  override var isMirror: Boolean = false
  override var vx: Double = 0
  override var vy: Double = 0
  val video: HTMLVideoElement = document.createElement("video").asInstanceOf[HTMLVideoElement]
  video.style.position = "absolute"
  video.controls = true
  video.src = path
  var draggable : Boolean = false
  override val rangeSize: Double = getSize()
  override var ax: Double = 0
  override var ay: Double = 1
  override var id : String = ""
  var scale : Double = 1

  def this(i : String,from : (Double, Double), path: String) {
    this(from,path)
    this.id = i
  }

  override def getSize(): Double = {
    0
  }

  override def draw(ctx: CanvasRenderingContext2D): Unit = {
    val loadmetadata = (e: Event) => {
      ctx.canvas.width = (video.videoWidth*scale).toInt + 10
      ctx.canvas.height = (video.videoHeight*scale).toInt +10
      video.setAttribute("height",(video.videoHeight*scale).toString)
      video.setAttribute("width",(video.videoWidth*scale).toString)
      ctx.drawImage(video, x, y, video.videoWidth, video.videoHeight, 0, 0, video.videoWidth*scale, video.videoHeight*scale)

    }

    val playVideo = (e: Event) => {
      timers.setInterval(30) {
        ctx.drawImage(video, x, y, video.videoWidth, video.videoHeight, 0, 0, video.videoWidth*scale, video.videoHeight*scale)
      }
    }

    def rect = ctx.canvas.getBoundingClientRect()
    var offX : Double = 0
    var offY : Double = 0
    var isDragging = false
    val onmousemove ={e: dom.MouseEvent =>
      if(isDragging){
        ctx.canvas.style.left = (e.clientX - offX) + "px"
        ctx.canvas.style.top  = (e.clientY - offY) + "px"
        video.style.left = (e.clientX - offX) + "px"
        video.style.top  = (e.clientY - offY) + "px"
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
    if(draggable){
      video.addEventListener("play", playVideo, false)
      video.addEventListener("loadedmetadata", loadmetadata, false)
    }
    ctx.canvas.addEventListener("mousedown", onmousedown, useCapture = false)
    dom.window.addEventListener("mouseup", onmouseup, useCapture = false)
    document.getElementById("container").appendChild(video)
  }
}