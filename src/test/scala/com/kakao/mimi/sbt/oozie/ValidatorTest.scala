package com.kakao.mimi.sbt.oozie

import org.junit.Test
import org.junit.Assert._
/**
 * Created by samuel281 on 15. 4. 1..
 */
class ValidatorTest {
  @Test
  def testValidate(): Unit = {
    val workflowPath = this.getClass.getResource("/workflow.xml").getPath
    val validator = new Validator()
    val result = validator.validate(workflowPath)
    assertTrue(result)

    val brokenWorkflowPath = this.getClass.getResource("/broken_workflow.xml").getPath
    assertFalse(validator.validate(brokenWorkflowPath))
  }
}
