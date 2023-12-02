package edu.demo.detekt

import edu.demo.detekt.rules.TooManyIfStatements
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.rules.KotlinCoreEnvironmentTest
import io.gitlab.arturbosch.detekt.test.assertThat
import io.gitlab.arturbosch.detekt.test.compileAndLintWithContext
import io.kotest.matchers.collections.shouldHaveSize
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.junit.jupiter.api.Test

@KotlinCoreEnvironmentTest
internal class TooManyIfStatementsTest(private val env: KotlinCoreEnvironment) {

  @Test
  fun `too many if statements reported`() {
    val code = """
        fun tooManyIfs() {
          if (1 == 1) {}
          if (1 == 2) {}
          if (1 == 2) {}
          if (1 == 2) {}
          if (1 == 2) {}
          if (1 == 2) {}; if (1 == 2) {}
          val c = if (1 == 1) 1 else 2
          val func: () -> Unit = {
          if (2==2) 
            println("hello")
          }
          if (4 == 4) {
            println("9th if")
          }
          if (c == 12) println("10th if")
        }
          
        fun notTooManyIfs() {
          if (1==1) println("if")
          if (1==1) println("if")
          if (1==1) println("if")
          if (1==1) println("if")
        }
        """

    val findings = TooManyIfStatements(Config.empty).compileAndLintWithContext(env, code)
    findings shouldHaveSize 1
  }

  @Test
  fun `too many if statements not reported`() {
    val code = """
        fun notTooManyIfs() {
          if (1 == 2) {}
          if (1 == 2) {}; if (1 == 2) {}
        }
      
        class A {
          fun notTooManyIfs() {
            if (1==1) println("if")
            if (1==1) println("if")
            if (1==1) println("if")
            if (1==1) println("if")
          }
        }
        """

    val findings = TooManyIfStatements(Config.empty).compileAndLintWithContext(env, code)
    findings shouldHaveSize 0
  }
}
