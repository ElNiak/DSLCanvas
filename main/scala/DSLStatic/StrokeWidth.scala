package DSLStatic

case class StrokeWidth(w: Double) extends CanvasyElementModifier[Shape] {
  // every Shape has a stroke.
  override def change(x: Shape): Unit = {
    if(x.state == 2)
      x.stroke.width = w
    else
      throw new ShapeAttributeException("Only stroke shape can be modified")
  }

}
