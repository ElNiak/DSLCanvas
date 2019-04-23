package DSLStatic

object Color extends Enumeration {
  type Color = Value
  val red, blue, green = Value
  def rgb(v:String) =  Color
}
