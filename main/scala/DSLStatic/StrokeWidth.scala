package DSLStatic

case class StrokeWidth(w: Double) extends Stroke {
  // every Shape has a stroke.
  override def change(x: Shape): Unit = x.stroke.width = w

  override var width: Double
  override var color: Color.Value
}
