package DSLStatic

object Color extends Enumeration {
  type Color = ColorValue
  case class ColorValue(color: String) extends Val(color)
  val red, blue, green = Value
  def rgb(v:String) =  Color
}
