package DSLStatic.Shape
import DSLStatic.ShapeAttributeException
import DSLStatic.Style.SV.SValue
import DSLStatic.Style.{Clear, ColorRGB, Fill, Gradient, Stroke, Style}
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
  video.controls = true
  video.src = path
  override val rangeSize: Double = getSize()
  override var ax: Double = 0
  override var ay: Double = 1
  override var id : String = ""

  def this(i : String,from : (Double, Double), path: String) {
    this(from,path)
    this.id = i
  }

  override def getSize(): Double = {
    100
  }

  override def draw(ctx: CanvasRenderingContext2D): Unit = {
    var playVideo = (e: Event) => {
      //video.style.display = "none"
      println(video.videoWidth)
      println(video.videoHeight)
      ctx.drawImage(video, x, y, video.videoWidth, video.videoHeight)
      timers.setInterval(30) {
        ctx.drawImage(video, x, y, video.videoWidth, video.videoHeight)
      }
    }
    video.addEventListener("play", playVideo, false)
    document.getElementById("container").appendChild(video)
  }
}