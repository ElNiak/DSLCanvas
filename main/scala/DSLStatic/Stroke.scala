package DSLStatic

import ColorRGB.ColorRGBUtils

class Stroke() {
  // Default value for an object stroke ?
  var width : Double = 10
  var color : String = rgb"#ee22aa"

  def apply(w: Double): Unit = {
    width = w
  }

  def apply(c: String): Unit = {
    color = c
  }
}



