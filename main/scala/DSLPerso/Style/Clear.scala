package DSLPerso.Style

class Clear extends Style {
  override var colorStyle : ColorStyle = ColorRGB("",1)
  def apply(c: ColorStyle): Unit = {
    colorStyle = c
  }
}
