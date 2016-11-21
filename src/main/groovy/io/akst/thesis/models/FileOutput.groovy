package io.akst.thesis.models


import java.math.BigDecimal

import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Entity
import javax.persistence.Column
import javax.persistence.ManyToOne
import javax.persistence.FetchType
import javax.persistence.JoinTable
import javax.persistence.JoinColumn
import javax.persistence.NamedQuery
import javax.persistence.ColumnResult
import javax.persistence.NamedQueries
import javax.persistence.GeneratedValue
import javax.persistence.NamedNativeQuery
import javax.persistence.ConstructorResult
import javax.persistence.SqlResultSetMapping

import io.akst.thesis.models.util.Semver


@Entity
@Table(name="file_output")
@NamedNativeQuery(
  name = "Average.times",
  resultSetMapping = "average_dto",
  query = """
    SELECT time.package_id  AS package_id,
           time.ghc_version AS ghc_version,
           time.avg_time    AS average_time,
           size.total_size  AS total_size

    FROM (SELECT package.id          AS package_id,
                 result.version      AS ghc_version,
                 avg(result.seconds) AS avg_time
            FROM Package package,
                 Result result,
                 Batch batch
           WHERE package.id = batch.package
             AND batch.id   = result.batch
           GROUP BY package_id, ghc_version) AS time

    LEFT OUTER JOIN (
          SELECT package.id AS package_id,
                 result.version AS ghc_version,
                 sum(file_output.file_size) AS total_size
            FROM Package package,
                 Result result,
                 Batch batch,
                 File_Output file_output
           WHERE package.id = batch.package
             AND batch.id   = result.batch
             AND result.id  = file_output.result
             AND file_output.file_extension = 'hi'
           GROUP BY package_id, ghc_version) AS size

        ON time.package_id = size.package_id
       AND time.ghc_version = size.ghc_version
     ORDER BY package_id, ghc_version DESC
""")
@SqlResultSetMapping(
  name = "average_dto",
  classes = @ConstructorResult(
    targetClass = Average.class,
    columns = [
      @ColumnResult(name="package_id"),
      @ColumnResult(name="ghc_version", type=String.class),
      @ColumnResult(name="average_time"),
      @ColumnResult(name="total_size")
    ]
  )
)
class FileOutput implements Serializable {
  @Id @GeneratedValue @Column(name="id")
  def int id

  @Column(name="result")
  def int resultId

  @Column(name="relative_path")
  def String relativePath

  @Column(name="file_extension")
  def String fileExtension

  @Column(name="file_size")
  def BigDecimal fileSize

  //@ManyToOne(fetch=FetchType.LAZY)
  //@JoinTable(name="result",
  //  joinColumns=@JoinColumn(name="result"),
  //  inverseJoinColumns=@JoinColumn(name="id"))
  //def Result result
}
