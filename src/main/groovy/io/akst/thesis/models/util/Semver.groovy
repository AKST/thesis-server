package io.akst.thesis.models.util

class Semver {
  int major
  int minor
  int patch

  def String toString() {
    return "${this.major}.${this.minor}.${this.patch}"
  }
}
