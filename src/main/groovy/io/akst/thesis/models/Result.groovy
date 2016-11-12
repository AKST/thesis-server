package io.akst.thesis.models

import java.util.UUID
import java.math.BigDecimal

import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Entity
import javax.persistence.Column
import javax.persistence.GeneratedValue

import org.hibernate.annotations.Type

import io.akst.thesis.models.util.Semver


@Entity
@Table(name="result")
class Result implements Serializable {
  @Id @GeneratedValue @Column(name="id")
  int id

  @Column(name="batch")
  UUID name

  @Column(name="version")
  @Type(type="io.akst.thesis.models.util.SemverType")
  Semver version

  @Column(name="seconds")
  BigDecimal seconds
}
