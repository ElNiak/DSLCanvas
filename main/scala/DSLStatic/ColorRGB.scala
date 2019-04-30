package DSLStatic

case class ColorRGB(string: String){
  val color : String = string
}

object ColorRGB {
  implicit class ColorRGBUtils(val sc: StringContext) extends AnyVal {
    def rgb(args : String) : String = {
      if(checkColorPattern(args))
        ColorRGB(args).color
      else
        ColorRGB("#000000").color
    }
    def rgb(args : Unit) : String = {
      if(checkColorPattern(sc.parts(0)))
        ColorRGB(sc.parts(0)).color
      else
        ColorRGB("#000000").color
    }
  }
  def checkColorPattern(color : String): Boolean ={
    if(color.charAt(0) == '#')
      if(color.length == 7)
        true
    false
  }
}