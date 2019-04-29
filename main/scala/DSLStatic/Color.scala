package DSLStatic

object Color extends Enumeration {
  type Color = String
  case class ColorValue(color: String) extends Val(color)
  val red = "#ee22aa"
  def rgb(v:String) =  Color
}
