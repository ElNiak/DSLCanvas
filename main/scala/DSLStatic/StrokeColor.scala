package DSLStatic

case class StrokeColor(c: ColorRGB) extends CanvasyElementModifier[Shape] {
  // every Shape has a stroke.
  override def change(x: Shape): Unit = {
    if(x.state == 2){
      x.stroke.color = c
      x.opacity = c.opacity
    }
    else
      throw new ShapeAttributeException("Only stroke shape can be modified")
  }

}

