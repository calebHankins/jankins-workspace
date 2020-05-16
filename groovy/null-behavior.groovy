#!/usr/bin/env groovy

// How does groovy handle empty strings / nulls?

// Empty String
String empty = ''

trimMatch(empty, 'empty')
groovyMatch(empty, 'empty')
groovyNonMatch(empty, 'empty')

// Set a default value using elvis operator
// https://codenarc.github.io/CodeNarc/codenarc-rules-convention.html#couldbeelvis-rule
empty = empty ?: 'A non-empty value'
println "empty after setDefaultValue: $empty"

groovyMatch(empty, 'empty')
groovyNonMatch(empty, 'empty')

// Default / Unset
String unset

trimMatch(unset, 'unset')
groovyMatch(unset, 'unset')
groovyNonMatch(unset, 'unset')

// Set a default value using elvis operator
// https://codenarc.github.io/CodeNarc/codenarc-rules-convention.html#couldbeelvis-rule
unset = unset ?: 'A non-unset value'
println "unset after setDefaultValue: $unset"

groovyMatch(unset, 'unset')
groovyNonMatch(unset, 'unset')

// A set string
String set = "I'm set already ya big mook!"

trimMatch(set, 'set')
groovyMatch(set, 'set')
groovyNonMatch(set, 'set')

// Set a default value using elvis operator
// https://codenarc.github.io/CodeNarc/codenarc-rules-convention.html#couldbeelvis-rule
set = set ?: 'An overridden value'
println "set after setDefaultValue: $set"

groovyMatch(set, 'set')
groovyNonMatch(set, 'set')

// A null string
String nullVar = null

trimMatch(nullVar, 'nullVar')
groovyMatch(nullVar, 'nullVar')
groovyNonMatch(nullVar, 'nullVar')

// nullVar a default value using elvis operator
// https://codenarc.github.io/CodeNarc/codenarc-rules-convention.html#couldbeelvis-rule
nullVar = nullVar ?: 'An overridden value'
println "nullVar after nullVarDefaultValue: $nullVar"

groovyMatch(nullVar, 'nullVar')
groovyNonMatch(nullVar, 'nullVar')

void trimMatch (String var, String varName) {
    if ("${var}".trim() == '') { println "$varName trim match!" } else { println "no $varName trim match" }
}

// groovier way
/* groovylint-disable-next-line LineLength */
// @see https://stackoverflow.com/questions/17256855/is-there-a-better-way-to-write-this-null-check-and-a-non-empty-check-in-groovy
void groovyMatch (String var, String varName) {
    if (var) { println "$varName groovy match!" } else { println "no $varName groovy match" }
}

void groovyNonMatch (String var, String varName) {
    if (var) { println "$varName groovyNonMatch match!" } else { println "no $varName groovyNonMatch match" }
}

// void defaultValue (Map defaultArgs) {
//     String var = defaultArgs.var
//     String varName = defaultArgs.varName
//     String defaultValue = defaultArgs.defaultValue
//     if (var) {
//         println "$varName: has the value: $var"
//     } else {
//         var = defaultValue
//        // uh, this would have been nice to know about ahead of time lol
//        // https://codenarc.github.io/CodeNarc/codenarc-rules-convention.html#couldbeelvis-rule
//        // if (!varName) { varName = 'An empty value'}
//         varName = varName ?: 'An empty value'
//         println "$varName was defaulted defaulted to the value: $var"
//     }
// }

// grooviest way, use elvis operator
// https://codenarc.github.io/CodeNarc/codenarc-rules-convention.html#couldbeelvis-rule
//  varName = varName ?: 'An empty value'
