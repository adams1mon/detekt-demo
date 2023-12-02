package edu.demo.detekt.rules

import io.gitlab.arturbosch.detekt.api.*
import org.jetbrains.kotlin.psi.KtIfExpression
import org.jetbrains.kotlin.psi.KtNamedFunction

class TooManyIfStatements(config: Config) : Rule(config) {

  private var ifCount = 0
  private val maxIfStatementCount: Int by config(defaultValue = 5)

  override val issue = Issue(
    javaClass.simpleName,
    Severity.CodeSmell,
    "",
    Debt.TEN_MINS,
  )

  override fun visitIfExpression(expression: KtIfExpression) {
    super.visitIfExpression(expression)
    ifCount++
  }

  override fun visitNamedFunction(function: KtNamedFunction) {
    super.visitNamedFunction(function)

    if (ifCount > maxIfStatementCount) {
      report(
        CodeSmell(
          issue,
          Entity.atName(function),
          message = "The function ${function.nameAsSafeName.asString()} contains too many ($ifCount) " +
              "'if' statements. The maximum number of allowed 'if' statements specified is $maxIfStatementCount.",
          references = emptyList()
        )
      )
    }
    ifCount = 0
  }
}
