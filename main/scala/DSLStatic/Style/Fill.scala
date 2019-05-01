package DSLStatic.Style

class Fill extends Style {
  override var colorStyle : ColorStyle = ColorRGB("",1)
  def apply [I <: ColorStyle](c: I): Unit = {
    colorStyle = c
  }
}
