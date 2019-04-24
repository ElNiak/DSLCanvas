package DSLStatic

object Color extends Enumeration {
  type String = Value
  val red, blue, green = Value
  def rgb(v:String) =  Color
}
