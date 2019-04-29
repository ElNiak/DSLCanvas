package DSLStatic

import ColorRGB.ColorRGBUtils

class Stroke(v : String) {
  // Default value for an object stroke ?
  var width : Double = 10
  var color : String = rgb"$v"

  def apply(w: Double): Unit = {
    width = w
  }

  def apply(c: String): Unit = {
    color = c
  }
}



