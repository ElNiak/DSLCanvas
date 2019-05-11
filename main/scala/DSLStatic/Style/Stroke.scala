package DSLStatic.Style

import DSLStatic.Style.Cap.CapValue
import DSLStatic.Style.Join.JoinValue

class Stroke() extends Style {
  var width : Double = -1
  var offset : Double = 0
  var join : JoinValue = JoinValue("")
  var cap : CapValue = CapValue("")
  override var colorStyle : ColorStyle = ColorRGB("",1)

  def apply(w: Double): Unit = {
    width = w
  }

  def apply [I <: ColorStyle](c: I): Unit = {
    colorStyle = c
  }
}



