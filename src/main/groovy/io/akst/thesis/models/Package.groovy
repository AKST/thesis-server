package io.akst.thesis.models

import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Entity
import javax.persistence.Column
import javax.persistence.OneToMany
import javax.persistence.FetchType
import javax.persistence.JoinTable
import javax.persistence.JoinColumn
import javax.persistence.NamedQuery
import javax.persistence.NamedQueries
import javax.persistence.GeneratedValue


@Entity
@Table(name="package")
@NamedQueries([
  @NamedQuery(name="Package.findAll", query = "SELECT p FROM Package p"),
  @NamedQuery(name="Package.find", query = "SELECT p FROM Package p WHERE p.id = :id"),
])
class Package implements Serializable {
  @Id @GeneratedValue @Column(name="id")
  def int id

  @Column(name="name")
  def String name

  //@OneToMany(fetch=FetchType.LAZY)
  //@JoinTable(name="batch",
  //  joinColumns=@JoinColumn(name="id"),
  //  inverseJoinColumns=@JoinColumn(name="package"))
  //def Collection<Batch> batches
}
