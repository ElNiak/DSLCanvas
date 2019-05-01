package DSLStatic.Style

class Stroke() extends Style {
  var width : Double = -1
  override var colorStyle : ColorStyle = ColorRGB("",1)
  def apply(w: Double): Unit = {
    width = w
  }

  def apply [I <: ColorStyle](c: I): Unit = {
    colorStyle = c
  }
}



