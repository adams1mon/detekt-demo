/**
 * THIS SHOULD BE A LICENSE
 *
 */
package playground

import kotlin.random.Random

const val ROLL_6 = 6

fun cognitiveComplexMethod(s: String) {

  var roll = Random.nextInt(from = 1, until = 6)

  if (roll != 1) {
    if (roll != 2) {
      if (roll != 3) {
        if (roll != 4) {
          if (roll != 5) {
            println("no dinner")
          } else {
            println("$roll")
          }
        } else {
          println("$roll")
        }
      } else {
        println("$roll")
      }
    } else {
      println("$roll")
    }
  } else {
    println("$roll")
  }
}

// should also trigger the "too many if statements" custom rule
fun cyclomaticComplexMethod() {

  var roll = Random.nextInt(from = 1, until = 12)

  if (roll != 1) {
    if (roll != 2) {
      if (roll != 3) {
        if (roll != 4) {
          if (roll != 6) {
            if (roll != 7) {
              if (roll != 8) {
                if (roll != 9) {
                  if (roll != 10) {
                    if (roll != 11) {
                      if (roll != 12) {
                        if (roll != 13) {
                          if (roll != 14) {
                            if (roll != 15) {
                              println("must not happen")
                            }
                          }
                        }
                      } else {
                        println("$roll")
                      }
                    } else {
                      println("$roll")
                    }
                  } else {
                    println("$roll")
                  }
                } else {
                  println("$roll")
                }
              } else {
                println("$roll")
              }
            } else {
              println("$roll")
            }
          } else {
            println("$roll")
          }
        } else {
          println("$roll")
        }
      } else {
        println("$roll")
      }
    } else {
      println("$roll")
    }
  } else {
    println("$roll")
  }
}

fun typeSafety() {
  val roll: Any? = if (Random.nextInt(1, ROLL_6) == ROLL_6) ROLL_6 else null

  // cast fails if roll != 6
  println("typeSafety: rolled $roll")

  val text: String = (roll as Int).toString()
  println(text)
}