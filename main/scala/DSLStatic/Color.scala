package DSLStatic

object Color extends Enumeration {
  type Color = String
  case class ColorValue(color: String) extends Val(color)
  val red = "#ee22aa"
}

case class ColorRGB(string: String){
  val color : String = string
}

object ColorRGB {
  implicit class ColorRGBUtils(val sc: StringContext) extends AnyVal {
    def rgb(args : String) : String = ColorRGB(args).color
    def rgb(args : Unit) : String = ColorRGB(sc.parts(0)).color
  }
}
