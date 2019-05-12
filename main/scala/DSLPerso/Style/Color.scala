package DSLPerso.Style

/**
  * Define some enumeration for common colors
  */
object Color extends Enumeration {
  type Color = ColorRGB
  case class ColorValue(color: String) extends Val(color)
  val red = ColorRGB("#ff0000",1)
  val blue = ColorRGB("#0061ff",1)
  val green = ColorRGB("#17d802",1)
  val yellow = ColorRGB("#fff711",1)
  val black = ColorRGB("#000000",1)
  val white = ColorRGB("#ffffff",1)
  val cyan = ColorRGB("#00e4f4",1)
  val orange = ColorRGB("#f76f00",1)
  val purple = ColorRGB("#8202f2",1)
}
