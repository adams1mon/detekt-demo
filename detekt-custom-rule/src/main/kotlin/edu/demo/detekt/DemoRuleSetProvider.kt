package edu.demo.detekt

import edu.demo.detekt.rules.TooManyIfStatements
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class DemoRuleSetProvider : RuleSetProvider {
    override val ruleSetId: String = "DemoRuleSet"

    override fun instance(config: Config): RuleSet {
        return RuleSet(
            ruleSetId,
            listOf(
                TooManyIfStatements(config),
            ),
        )
    }
}
