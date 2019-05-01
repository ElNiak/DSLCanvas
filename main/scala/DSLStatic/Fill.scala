package DSLStatic

class Fill {
  var color : ColorRGB = ColorRGB("",1)
  def apply(c: ColorRGB): Unit = {
    color = c
  }
}
