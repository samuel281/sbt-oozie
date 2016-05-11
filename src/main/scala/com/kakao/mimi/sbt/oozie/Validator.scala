package com.kakao.mimi.sbt.oozie

import java.io.File
import javax.xml.transform.{stream, Source}
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory
import org.xml.sax.SAXException
import scala.collection.JavaConversions._
import scala.util.Try
import sbt.Logger
/**
 * Created by samuel281 on 15. 4. 1..
 */
class Validator(implicit logger: Logger) {
  def validate(workflowPath: String) : Boolean = {
    val oozie = this.getClass.getResourceAsStream("/oozie-workflow-0.4.xsd")
    val distcp = this.getClass.getResourceAsStream("/distcp-action-0.2.xsd")
    val email = this.getClass.getResourceAsStream("/email-action-0.1.xsd")
    val hive = this.getClass.getResourceAsStream("/hive-action-0.2.xsd")
    val shell = this.getClass.getResourceAsStream("/shell-action-0.1.xsd")
    val sqoop = this.getClass.getResourceAsStream("/sqoop-action-0.2.xsd")
    val ssh = this.getClass.getResourceAsStream("/ssh-action-0.2.xsd")
    val spark = this.getClass.getResourceAsStream("/spark-action-0.1.xsd")


    Try({
      val schemaLang = "http://www.w3.org/2001/XMLSchema"
      val factory = SchemaFactory.newInstance(schemaLang)
      val schema = factory.newSchema(
        Array(
          new StreamSource(oozie).asInstanceOf[Source],
          new StreamSource(distcp).asInstanceOf[Source],
          new StreamSource(email).asInstanceOf[Source],
          new StreamSource(hive).asInstanceOf[Source],
          new StreamSource(shell).asInstanceOf[Source],
          new StreamSource(sqoop).asInstanceOf[Source],
          new StreamSource(ssh).asInstanceOf[Source],
          new StreamSource(spark).asInstanceOf[Source]
        )
      )

      val validator = schema.newValidator()
      validator.validate(new StreamSource(workflowPath))
      true
    }) recover {
      case ex: SAXException => logger.error(ex.getMessage); false
      case ex: Exception => logger.error(ex.getMessage); false
    } getOrElse(false)
  }
}
