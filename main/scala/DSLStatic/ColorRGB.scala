package DSLStatic

case class ColorRGB(string: String){
  val color : String = string
}

object ColorRGB {
  implicit class ColorRGBUtils(val sc: StringContext) extends AnyVal {
    def rgb(args : String) : String = ColorRGB(args).color
    def rgb(args : Unit) : String = ColorRGB(sc.parts(0)).color
  }
}