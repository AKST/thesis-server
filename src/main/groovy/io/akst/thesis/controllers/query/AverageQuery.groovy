package io.akst.thesis.controllers.query

import javax.persistence.criteria.Root
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.CriteriaBuilder as CBuilder

import io.akst.thesis.controllers.AverageDAO

import io.akst.thesis.util.AppException
import io.akst.thesis.models.util.ProjectionWrapper
import io.akst.thesis.models.FileOutput
import io.akst.thesis.models.Package
import io.akst.thesis.models.Result
import io.akst.thesis.models.Batch

abstract class AverageQuery {
  abstract String getType()
  abstract Collection<ProjectionWrapper> execute(AverageDAO dao)
  abstract protected void withParam(String key, List<String> value)

  static AverageQuery make(Map<String, String[]> userQuery) {
    def typeProperty = (userQuery.get("type") ?: [] as String[])
    def size = typeProperty.length
    if (0 < size && size < 2) {
      def type = typeProperty[0]
      def query = null

      switch (type) {
        case "size": query = new AverageSizeQuery(); break
        case "time": query = new AverageTimeQuery(); break
        default: throw new AppException(401, "'${type}' is an invalid value for type")
      }

      userQuery.each { key, values ->
        switch (key) {
          case 'type': return
          default: query.withParam(key, values as List<String>)
        }
      }

      return query
    }
    else throw new AppException(401, "A single value for type must be speficied")
  }
}

class AverageSizeQuery extends AverageQuery {
  List<String> fileExtensions = []

  String getType() { "size" }

  protected void withParam(String key, List<String> values) {
    switch (key) {
      case 'type':
        break
      case 'file-extension':
        this.fileExtensions = values
        break
      default:
        throw new UnknownParam(k, 'size')
    }
  }

  Collection<ProjectionWrapper> execute(AverageDAO dao) {
    dao.findAllSizes(this) as Collection<ProjectionWrapper>
  }

  Predicate predicateWith(CBuilder b, Root<FileOutput> pFileOut, List<Predicate> preds) {
    if (this.fileExtensions.size() > 0) {
      def path = pFileOut.get("fileExtension")
      def where = fileExtensions.collect { b.equal(path, it) }
      preds.push(b.or(*where))
    }
    return b.and(*preds)
  }
}

class AverageTimeQuery extends AverageQuery {
  String getType() { "time" }

  Collection<ProjectionWrapper> execute(AverageDAO dao) {
    dao.findAllTimes(this) as Collection<ProjectionWrapper>
  }

  Predicate predicateWith(CBuilder b, List<Predicate> rq) {
    def init = b.and()
    rq.inject(init) { acc, it -> b.and(it, acc) } as Predicate
  }

  protected void withParam(String key, List<String> values) {
    switch (key) {
      case 'type': break
      default: throw new UnknownParam(key, 'time')
    }
  }
}

class UnknownParam extends AppException {
  UnknownParam(String param, String type) {
    super(401, "unknown param '$param' for type '$type'")
  }
}
