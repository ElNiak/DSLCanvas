package DSLPerso.Modifier

import DSLPerso.Shape.{Circle, Shape}
import DSLPerso.Style.{ColorRGB, ColorStyle, Fill, Gradient, Style}
import DSLPerso.{CanvasyElementModifier, ShapeAttributeException}

case class FillColor [I <: ColorStyle](c: I) extends CanvasyElementModifier[Shape] {
  override def change(x: Shape): Unit = {
    if (x == null)
      throw new ShapeAttributeException("shape cannot be null")
    x.style match {
      case fill: Fill =>
        c match {
          case b: ColorRGB =>
            fill.colorStyle = b
            x.opacity = b.opacity
          case b: Gradient =>
            if(x.isInstanceOf[Circle])
              throw new ShapeAttributeException("Only Rectangle can have a gradient")
            fill.colorStyle = b
        }
      case _ => throw new ShapeAttributeException("Only filled shape can be modified")
    }
  }
}
