package DSLStatic

case class Radius(r: Double) extends CanvasyElementModifier[Shape] {
  // every Shape has a stroke.
  override def change(x: Shape): Unit = x.radius = r
}
