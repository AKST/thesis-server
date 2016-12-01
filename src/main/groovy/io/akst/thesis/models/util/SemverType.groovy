package io.akst.thesis.models.util

import java.sql.Types
import java.sql.ResultSet
import java.sql.PreparedStatement

import org.hibernate.type.StringType
import org.hibernate.usertype.UserType
import org.hibernate.engine.spi.SharedSessionContractImplementor as SessionImplementor


class SemverType implements UserType {

  static String[] KEYS = [ Semver.class.getName() ]

  def Class<Semver> returnedClass() {
    Semver.class
  }

  def boolean isMutable() {
    true
  }

  def int[] sqlTypes() {
    [ StringType.INSTANCE.sqlType() ]
  }

  def boolean equals(l, r) {
    if (!(l instanceof Semver && r instanceof Semver)) return false
    if (l.patch != r.patch) return false
    if (l.minor != r.minor) return false
    if (l.major != r.major) return false
    true
  }

  def Object deepCopy(source) {
    new Semver(major: source.major, minor: source.minor, patch: source.patch)
  }

  def Serializable disassemble(v) {
    deepCopy(v)
  }

  def Object assemble(Serializable cached, owner) {
    deepCopy(cached)
  }

  def replace(original, target, owner) {
    deepCopy(original)
  }

  def Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor s, Object owner) {
    def repr = StringType.INSTANCE.get(rs, names[0], s)
    SemverType.decode(repr)
  }

  def void nullSafeSet(PreparedStatement st, Object v, int index, SessionImplementor s) {
    String repr = v != null ? encode(v) : null
    StringType.INSTANCE.nullSafeSet(st, repr, index, s)
  }

  def int hashCode(obj) {
    def prime1 = 2860486313
    def prime2 = 5463458053
    def prime3 = 3367900313
    def delta1 = obj.major * prime1
    def delta2 = obj.minor * prime2
    def delta3 = obj.patch * prime3
    delta1 * delta2 * delta3
  }

  def private static encode(v) {
    "(${v.major},${v.minor},${v.patch})"
  }

  def private static decode(repr) {
    def lastIndex = repr.length() - 1
    def split = repr.substring(1, lastIndex).split(',')
    def major = Integer.parseInt split[0], 10
    def minor = Integer.parseInt split[1], 10
    def patch = Integer.parseInt split[2], 10
    new Semver(major: major, minor: minor, patch: patch)
  }
}
