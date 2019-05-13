package DSLPerso.Shape
import DSLPerso.ShapeAttributeException
import DSLPerso.Style.SV.SValue
import DSLPerso.Style.{Clear, ColorRGB, Fill, Gradient, Stroke, Style}
import org.scalajs.dom
import org.scalajs.dom.{CanvasRenderingContext2D, Event}
import org.scalajs.dom.html.Image
import org.scalajs.dom.raw.HTMLImageElement

case class Picture (from : (Double, Double), path: String) extends Shape {
  override var opacity: Double = 0
  override var style : Style = new Fill
  override var x : Double = if(from._1 >= 0) from._1 else throw new ShapeAttributeException("x cannot be smaller than 0")
  override var y : Double = if(from._2 >= 0) from._2 else throw new ShapeAttributeException("y cannot be smaller than 0")
  override var size: Int = _
  override var rotation: Double = 0
  override var isMirror: Boolean = false
  override var vx: Double = 0
  override var vy: Double = 0
  val image: HTMLImageElement = dom.document.createElement("img").asInstanceOf[HTMLImageElement]
  image.src = path
  override val rangeSize: Double = getSize()
  override var ax: Double = 0
  override var ay: Double = 1
  override var id : String = ""
  var scale : Double = 1
  var isBack : Boolean = false

  def this(i : String,from : (Double, Double), path: String) {
    this(from,path)
    this.id = i
  }

  def this(i : Boolean,from : (Double, Double), path: String) {
    this(from,path)
    this.isBack = i
  }


  override def getSize(): Double = {
      val width = image.naturalWidth
      val height = image.naturalHeight
      if(rotation == 0) {
        if(width > height) width else height
      }
      else {
        if(rotation <= 45 && rotation >= -45)
          if(width > height) width + width/2 * Math.cos(Math.abs(rotation)*Math.PI/180) else height + height/2 * Math.cos(Math.abs(rotation)*Math.PI/180)
        else
        if(width > height) width - width/2 * Math.cos(Math.abs(rotation)*Math.PI/180) else height - height/2 * Math.cos(Math.abs(rotation)*Math.PI/180)
      }
  }

  override def draw(ctx: CanvasRenderingContext2D): Unit = {
    image.onload = (event: Event) => {
      if(!isBack){
        val imageWidth = image.naturalWidth
        val imageHeight = image.naturalHeight
        ctx.canvas.width = (imageWidth*scale).toInt + 10
        ctx.canvas.height = (imageHeight*scale).toInt +10
        Shape.checkOpacity(this,ctx)
        val width: Int = imageWidth
        val height: Int = imageHeight
        ctx.drawImage(image, x, y, width, height, 0, 0, width*scale, height*scale)
      }
      else{
        val imageWidth = image.naturalWidth
        val imageHeight = image.naturalHeight
        val width: Int = imageWidth
        val height: Int = imageHeight
        ctx.globalAlpha = opacity
        ctx.drawImage(image, x, y, width, height, 0, 0, ctx.canvas.width, ctx.canvas.width)
        ctx.globalAlpha = 1
      }
    }
  }
}