package DSLStatic.Style

case class Gradient(X1 : Double, Y1 : Double,X2 : Double, Y2 : Double, R1 : Double, R2 : Double,  color: Array[ColorRGB]) extends ColorStyle {
  val x1 : Double = X1
  val y1 : Double = Y1
  val x2 : Double = X2
  val y2 : Double = Y2
  val r1 : Double = R1
  val r2 : Double = R2
  val colors : Array[ColorRGB] = color
  def this() {
      this(0,0,0,0,0,0,null)
  }
}

object Gradient {
  implicit class GradientUtils(val sc: StringContext) extends AnyVal {
    def grad(args : String) : Gradient = {
        null
    }
    def grad(args : Unit) : Gradient = {
        null
    }
  }
}