package DSLStatic

object Color extends Enumeration {
  type Color = ColorRGB
  case class ColorValue(color: String) extends Val(color)
  val red = ColorRGB("#ff0000",1)
}


