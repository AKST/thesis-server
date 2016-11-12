package io.akst.thesis.models

import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Entity
import javax.persistence.Column
import javax.persistence.GeneratedValue


@Entity
@Table(name="package")
class Package implements Serializable {
  @Id @GeneratedValue @Column(name="id")
  int id

  @Column(name="name")
  String name
}
