/*
 * Sonar C++ Plugin (Community)
 * Copyright (C) 2010-2017 SonarOpenCommunity
 * http://github.com/SonarOpenCommunity/sonar-cxx
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.cxx.parser;

import static com.sonar.sslr.api.GenericTokenType.EOF;
import static com.sonar.sslr.api.GenericTokenType.IDENTIFIER;
import static org.sonar.cxx.api.CxxTokenType.CHARACTER;
import static org.sonar.cxx.api.CxxTokenType.NUMBER;
import static org.sonar.cxx.api.CxxTokenType.STRING;

import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;
import org.sonar.cxx.CxxConfiguration;
import org.sonar.cxx.api.CxxKeyword;
import org.sonar.sslr.grammar.GrammarRuleKey;
import org.sonar.sslr.grammar.LexerfulGrammarBuilder;

import com.sonar.sslr.api.Grammar;

/**
 * Based on the C++ Standard, Appendix A
 */
public enum CxxGrammarImpl implements GrammarRuleKey {
  // Misc
  BOOL,
  NULLPTR,
  LITERAL,

  // Top level components
  translationUnit,

  // Expressions
  primaryExpression,
  idExpression,
  unqualifiedId,
  qualifiedId,
  nestedNameSpecifier,
  lambdaExpression,
  lambdaIntroducer,
  lambdaCapture,
  captureDefault,
  captureList,
  capture,
  lambdaDeclarator,
  foldExpression,
  foldOperator,
  postfixExpression,
  expressionList,
  pseudoDestructorName,
  unaryExpression,
  unaryOperator,
  binaryOperator,
  newExpression,
  newPlacement,
  newTypeId,
  newDeclarator,
  noptrNewDeclarator,
  newInitializer,
  deleteExpression,
  noexceptExpression,
  castExpression,
  pmExpression,
  multiplicativeExpression,
  additiveExpression,
  shiftExpression,
  relationalExpression,
  equalityExpression,
  andExpression,
  exclusiveOrExpression,
  inclusiveOrExpression,
  logicalAndExpression,
  logicalOrExpression,
  conditionalExpression,
  assignmentExpression,
  assignmentOperator,
  expression,
  constantExpression,
  cudaKernel,

  // Statements
  statement,
  emptyStatement,
  labeledStatement,
  expressionStatement,
  compoundStatement,
  statementSeq,
  condition,
  selectionStatement,
  iterationStatement,
  initStatement,
  forRangeDeclaration,
  forRangeInitializer,
  jumpStatement,
  declarationStatement,

  // Declarations
  declarationSeq,
  declaration,
  blockDeclaration,
  aliasDeclaration,
  simpleDeclaration,
  staticAssertDeclaration,
  emptyDeclaration,
  attributeDeclaration,
  declSpecifier,
  recoveredDeclaration,
  vcAtlDeclaration,   //Microsoft Extension: attributed ATL

  conditionDeclSpecifierSeq,
  forRangeDeclSpecifierSeq,
  parameterDeclSpecifierSeq,
  functionDeclSpecifierSeq,
  simpleDeclSpecifierSeq,
  memberDeclSpecifierSeq,

  storageClassSpecifier,
  functionSpecifier,
  typedefName,  
  typeSpecifier,
  typeSpecifierSeq,
  trailingTypeSpecifier,
  trailingTypeSpecifierSeq,
  simpleTypeSpecifier,
  typeName,
  decltypeSpecifier,
  elaboratedTypeSpecifier,
  enumName,
  enumSpecifier,
  enumHead,
  opaqueEnumDeclaration,
  enumKey,
  enumBase,
  enumeratorList,
  enumeratorDefinition,
  enumerator,
  namespaceName,
  namespaceDefinition,
  namedNamespaceDefinition,
  unnamedNamespaceDefinition,
  nestedNamespaceDefinition,
  enclosingNamespaceSpecifier,
  namespaceBody,
  namespaceAlias,
  namespaceAliasDefinition,
  qualifiedNamespaceSpecifier,
  usingDeclaration,
  usingDirective,
  asmDefinition,
  linkageSpecification,
  attributeSpecifierSeq,
  attributeSpecifier,
  alignmentSpecifier,
  attributeList,
  attribute,
  attributeToken,
  attributeScopedToken,
  attributeNamespace,
  attributeArgumentClause,
  balancedTokenSeq,
  balancedToken,
  vcAtlAttribute,   //Microsoft Extension: attributed ATL

  // Declarators
  initDeclaratorList,
  initDeclarator,
  declarator,
  ptrDeclarator,
  noptrDeclarator,
  parametersAndQualifiers,
  trailingReturnType,
  ptrOperator,
  cvQualifierSeq,
  cvQualifier,
  refQualifier,
  declaratorId,
  typeId,
  typeIdEnclosed,
  abstractDeclarator,
  ptrAbstractDeclarator,
  noptrAbstractDeclarator,
  abstractPackDeclarator,
  noptrAbstractPackDeclarator,
  parameterDeclarationClause,
  parameterDeclarationList,
  parameterDeclaration,
  functionDefinition,
  functionBody,
  initializer,
  braceOrEqualInitializer,
  initializerClause,
  initializerList,
  bracedInitList,

  // Classes
  className,
  classSpecifier,
  classHead,
  classHeadName,
  classVirtSpecifier,
  classKey,
  memberSpecification,
  memberDeclaration,
  memberDeclaratorList,
  memberDeclarator,
  virtSpecifierSeq,
  virtSpecifier,
  pureSpecifier,

  // cli extension
  cliTopLevelVisibility,
  cliFinallyClause,
  cliFunctionModifiers,
  cliFunctionModifier,
  cliEventDefinition,
  cliEventModifiers,
  cliPropertyOrEventName,
  cliEventType,
  cliParameterArray,
  cliPropertyDefinition,
  cliPropertyDeclSpecifierSeq,
  cliPropertyBody,
  cliPropertyModifiers,
  cliPropertyIndexes,
  cliPropertyIndexParameterList,
  cliAccessorSpecification,
  cliAccessorDeclaration,
  cliDelegateSpecifier,
  cliDelegateDeclSpecifierSeq,
  cliGenericDeclaration,
  cliGenericParameterList,
  cliConstraintClauseList,
  cliConstraintItemList,
  cliGenericParameter,
  cliGenericId,
  cliGenericName,
  cliGenericArgumentList,
  cliGenericArgument,
  cliConstraintClause,
  cliConstraintItem,
  cliAttributes,
  cliAttributeSection,
  cliAttributeTargetSpecifier,
  cliAttributeTarget,
  cliAttributeList,
  cliAttribute,
  cliAttributeArguments,
  cliPositionArgumentList,
  cliNamedArgumentList,
  cliPositionArgument,
  cliNamedArgument,
  cliAttributeArgumentExpression,

  // Derived classes
  baseClause,
  baseSpecifierList,
  baseSpecifier,
  classOrDecltype,
  baseTypeSpecifier,
  accessSpecifier,

  // Special member functions
  conversionFunctionId,
  conversionTypeId,
  conversionDeclarator,
  ctorInitializer,
  memInitializerList,
  memInitializer,
  memInitializerId,

  // Overloading
  operatorFunctionId,
  operator,
  literalOperatorId,

  // Templates
  templateDeclaration,
  templateParameterList,
  templateParameterListEnclosed,
  templateParameter,
  typeParameter,
  typeParameterKey,
  innerTypeParameter,
  simpleTemplateId,
  templateId,
  templateName,
  templateArgumentList,
  templateArgument,
  innerSimpleTemplateId,
  innerTrailingTypeSpecifier,
  innerTypeId,
  innerTemplateId,
  innerTemplateArgumentList,
  innerTemplateArgument,
  typenameSpecifier,
  explicitInstantiation,
  explicitSpecialization,

  // Exception handling
  tryBlock,
  functionTryBlock,
  handlerSeq,
  handler,
  exceptionDeclaration,
  throwExpression,
  exceptionSpecification,
  dynamicExceptionSpecification,
  typeIdList,
  noexceptSpecification;

  public static final Logger LOG = Loggers.get(CxxGrammarImpl.class);

  public static Grammar create(CxxConfiguration conf) {
    LexerfulGrammarBuilder b = LexerfulGrammarBuilder.create();

    toplevel(b, conf);
    expressions(b);
    statements(b);
    declarations(b);
    declarators(b);
    classes(b);
    properties(b);
    derivedClasses(b);
    specialMemberFunctions(b);
    overloading(b);
    templates(b);
    generics(b);
    exceptionHandling(b);
    cliAttributes(b);

    misc(b);
    vcAttributedAtl(b);

    b.setRootRule(translationUnit);

    return b.buildWithMemoizationOfMatchesForAllRules();
  }


  private static void misc(LexerfulGrammarBuilder b) {
    // C++ Standard, Section 2.14.6 "Boolean literals"
    b.rule(BOOL).is(b.firstOf(CxxKeyword.TRUE, CxxKeyword.FALSE));
    b.rule(NULLPTR).is(CxxKeyword.NULLPTR);
    b.rule(LITERAL).is(
      b.firstOf(
        CHARACTER,
        STRING,
        NUMBER,
        BOOL,
        NULLPTR));
  }

  private static void vcAttributedAtl(LexerfulGrammarBuilder b) {
    b.rule(vcAtlAttribute).is(
      "[", b.oneOrMore(b.anyTokenButNot("]")), "]"
    );
    b.rule(vcAtlDeclaration).is(vcAtlAttribute, ";");
  }

  // A.3 Basic concepts
  //
  private static void toplevel(LexerfulGrammarBuilder b, CxxConfiguration conf) {
    if (conf.getErrorRecoveryEnabled() == true) {
      b.rule(translationUnit).is(b.zeroOrMore(b.firstOf(declaration, recoveredDeclaration)), EOF);
      b.rule(recoveredDeclaration).is(b.oneOrMore(b.nextNot(b.firstOf(declaration, EOF)), b.anyToken()));
    } else {
      b.rule(translationUnit).is(b.zeroOrMore(declaration), EOF);
    }
  }

  // A.4 Expressions
  //
  private static void expressions(LexerfulGrammarBuilder b) {
    b.rule(primaryExpression).is(
      b.firstOf(
        LITERAL, // C++
        CxxKeyword.THIS, // C++
        // EXTENSION: gcc's statement expression: a compound statement enclosed in parentheses may appear as an expression
        b.sequence("(", b.firstOf(expression, compoundStatement), ")"), // C++: ( expression )
        idExpression, // C++
        lambdaExpression, // C++
        foldExpression // C++
      )
    ).skipIfOneChild();

    b.rule(idExpression).is(
      b.firstOf(
        qualifiedId, // C++
        unqualifiedId // C++ but wrong order
      )
    );

    b.rule(unqualifiedId).is(
      b.firstOf(
        // Mitigate ambiguity between relational operators < > and angular brackets by looking ahead
        b.sequence(templateId, b.next(b.firstOf("(", ")", "[", "]", "?", ":", binaryOperator, ",", ";", EOF))), // todo?
        IDENTIFIER, // C++
        operatorFunctionId, // C++
        conversionFunctionId, // C++
        literalOperatorId, // C++
        b.sequence("~", className), // C++
        b.sequence("~", decltypeSpecifier), // C++
        b.sequence("!", className), // ???
        cliGenericId,
        CxxKeyword.DEFAULT // ???
      )
    );

    b.rule(binaryOperator).is( // todo
      b.firstOf("||", "&&", "&", "|", "^", "==", "!=", "<=", "<", ">=", ">", "<<", ">>", "*", "/", "+", "-", assignmentOperator)
    );

    b.rule(qualifiedId).is(
      nestedNameSpecifier, b.optional(CxxKeyword.TEMPLATE), unqualifiedId // C++
    );

    b.rule(nestedNameSpecifier).is( // todo
      b.firstOf(
        "::",
        b.sequence(typeName, "::"),
        b.sequence(namespaceName, "::"),
        b.sequence(decltypeSpecifier, "::")
      ),
      b.zeroOrMore(
        b.firstOf(
          b.sequence(IDENTIFIER, "::"),
          b.sequence(b.optional(CxxKeyword.TEMPLATE), simpleTemplateId, "::")
        )
      )
    );

    b.rule(lambdaExpression).is(lambdaIntroducer, b.optional(lambdaDeclarator), compoundStatement); // C++

    b.rule(lambdaIntroducer).is("[", b.optional(lambdaCapture), "]"); // C++

    b.rule(lambdaCapture).is(
      b.firstOf( // todo ok but wrong order?
        b.sequence(captureDefault, ",", captureList), // C++
        captureList, // C++
        captureDefault // C++
      )
    );

    b.rule(captureDefault).is(b.firstOf("&", "=")); // C++

    b.rule(captureList).is(b.sequence(capture, b.optional("...")), b.zeroOrMore(",", b.sequence(capture, b.optional("...")))); // C++

    b.rule(capture).is( // todo simple-capture, init-capture
      b.firstOf(
        expression,
        b.sequence("&", expression)
      ));

    b.rule(lambdaDeclarator).is(
      "(", parameterDeclarationClause, ")", b.optional(CxxKeyword.MUTABLE), b.optional(exceptionSpecification), b.optional(attributeSpecifierSeq), b.optional(trailingReturnType) // C++
    );

    b.rule(foldExpression).is(
      b.firstOf(
        b.sequence("(", castExpression, foldOperator, "...", ")"), // C++
        b.sequence("(", "...", foldOperator, castExpression, ")"), // C++
        b.sequence("(", castExpression, foldOperator, "...", foldOperator, castExpression, ")") // C++
      )
    );

    b.rule(foldOperator).is(
      b.firstOf( // C++
        "+", "-", "*", "/", "%", "ˆ", "&", "|", "<<", ">>",
        "+=", "-=", "*=", "/=", "%=", "ˆ=", "&=", "|=", "<<=", ">>=", "=",
        "==", "!=", "<", ">", "<=", ">=", "&&", "||", ",", ".*", "->*"
      )
    );

    b.rule(postfixExpression).is( // todo
      b.firstOf(
        b.sequence(typenameSpecifier, "::", CxxKeyword.TYPEID),
        b.sequence(simpleTypeSpecifier, "::", CxxKeyword.TYPEID),
        b.sequence(simpleTypeSpecifier, b.optional(cudaKernel), "(", b.optional(expressionList), ")"),
        b.sequence(simpleTypeSpecifier, bracedInitList),
        b.sequence(typenameSpecifier, b.optional(cudaKernel), "(", b.optional(expressionList), ")"),
        b.sequence(typenameSpecifier, bracedInitList),
        primaryExpression,
        b.sequence(CxxKeyword.DYNAMIC_CAST, typeIdEnclosed, "(", expression, ")"),
        b.sequence(CxxKeyword.STATIC_CAST, typeIdEnclosed, "(", expression, ")"),
        b.sequence(CxxKeyword.REINTERPRET_CAST, typeIdEnclosed, "(", expression, ")"),
        b.sequence(CxxKeyword.CONST_CAST, typeIdEnclosed, "(", expression, ")"),
        b.sequence(CxxKeyword.TYPEID, "(", expression, ")"), // C++
        b.sequence(CxxKeyword.TYPEID, "(", typeId, ")") // C++

      ),
      // postfixExpression [ expression ]
      // postfixExpression [ bracedInitList ]
      // postfixExpression ( expressionListopt )
      // postfixExpression . template opt(idExpression)
      // postfixExpression -> template opt(idExpression)
      // postfixExpression . pseudoDestructorName
      // postfixExpression -> pseudoDestructorName
      // postfixExpression ++
      // postfixExpression --

      // should replace the left recursive stuff above

      b.zeroOrMore(
        b.firstOf(
          b.sequence("[", expression, "]"),
          b.sequence("[", bracedInitList, "]"),
          b.sequence("(", b.optional(expressionList), ")"),
          b.sequence(b.firstOf(".", "->"),
            b.firstOf(b.sequence(b.optional(CxxKeyword.TEMPLATE), idExpression),
              pseudoDestructorName)),
          "++",
          "--"
        )
      )
    ).skipIfOneChild();

    b.rule(cudaKernel).is(b.sequence("<<", "<", b.optional(expressionList), ">>", ">"));

    b.rule(typeIdEnclosed).is(
      b.firstOf(
        b.sequence("<", typeId, ">"),
        b.sequence("<", innerTypeId, ">>")
      )
    );

    b.rule(expressionList).is(initializerList); // C++

    b.rule(pseudoDestructorName).is(
      b.firstOf(
        b.sequence(b.optional(nestedNameSpecifier), typeName, "::", "~", typeName), // C++
        b.sequence(nestedNameSpecifier, CxxKeyword.TEMPLATE, simpleTemplateId, "::", "~", typeName), // C++
        b.sequence("~", typeName), // C++
        b.sequence("~", decltypeSpecifier) // C++
      )
    );

    b.rule(unaryExpression).is(
      b.firstOf(        
        b.sequence(unaryOperator, castExpression), // C++, todo wrong order
        postfixExpression, // C++
        b.sequence("++", castExpression), // C++
        b.sequence("--", castExpression), // C++        
        b.sequence(CxxKeyword.SIZEOF, unaryExpression), // C++
        b.sequence(CxxKeyword.SIZEOF, "(", typeId, ")"), // C++
        b.sequence(CxxKeyword.SIZEOF, "...", "(", IDENTIFIER, ")"), // C++
        b.sequence(CxxKeyword.ALIGNOF, "(", typeId, ")"), // C++
        noexceptExpression, // C++
        newExpression, // C++
        deleteExpression // C++
      )
    ).skipIfOneChild();

    b.rule(unaryOperator).is(
      b.firstOf("*", "&", "+", "-", "!", "~") // C++
    );

    b.rule(newExpression).is(
      b.firstOf( // todo gcnew must be string
        b.sequence(b.optional("::"), b.firstOf(CxxKeyword.NEW, CxxKeyword.GCNEW), b.optional(newPlacement), newTypeId, b.optional(newInitializer)), // C++
        b.sequence(b.optional("::"), b.firstOf(CxxKeyword.NEW, CxxKeyword.GCNEW), b.optional(newPlacement), "(", typeId, ")", b.optional(newInitializer)), // C++
        b.sequence(b.optional("::"), b.firstOf(CxxKeyword.NEW, CxxKeyword.GCNEW), "(", typeId, ")", b.optional(newInitializer)) // todo remove?
      )
    );

    b.rule(newPlacement).is("(", expressionList, ")"); // C++

    b.rule(newTypeId).is(typeSpecifierSeq, b.optional(newDeclarator)); // C++

    b.rule(newDeclarator).is(
      b.firstOf(
        b.sequence(ptrOperator, b.optional(newDeclarator)), // C++
        noptrNewDeclarator // C++
      )
    );

    b.rule(noptrNewDeclarator).is(
      "[", expression, "]", b.optional(attributeSpecifierSeq), b.zeroOrMore("[", constantExpression, "]", b.optional(attributeSpecifierSeq)) // C++
    );

    b.rule(newInitializer).is(
      b.firstOf(
        b.sequence("(", b.optional(expressionList), ")"), // C++
        bracedInitList // C++
      )
    );

    b.rule(deleteExpression).is(b.optional("::"), CxxKeyword.DELETE, b.optional("[", "]"), castExpression); // C++

    b.rule(noexceptExpression).is(CxxKeyword.NOEXCEPT, "(", expression, ")"); // C++

    b.rule(castExpression).is(
      b.firstOf(
        // bracedInitList: C-COMPATIBILITY: C99 compound literals        
        b.sequence("(", typeId, ")", b.firstOf(castExpression, bracedInitList)), // C++        
        unaryExpression // C++, todo wrong order
      )
    ).skipIfOneChild();

    b.rule(pmExpression).is(
      castExpression, b.zeroOrMore(b.firstOf(".*", "->*"), castExpression) // C++
    ).skipIfOneChild();

    b.rule(multiplicativeExpression).is(
      pmExpression, b.zeroOrMore(b.firstOf("*", "/", "%"), pmExpression) // C++
    ).skipIfOneChild();

    b.rule(additiveExpression).is(
      multiplicativeExpression, b.zeroOrMore(b.firstOf("+", "-"), multiplicativeExpression) // C++
    ).skipIfOneChild();

    b.rule(shiftExpression).is(
      additiveExpression, b.zeroOrMore(b.firstOf("<<", ">>"), additiveExpression) // C++
    ).skipIfOneChild();

    b.rule(relationalExpression).is(
      shiftExpression, b.zeroOrMore(b.firstOf("<", ">", "<=", ">="), shiftExpression) // C++
    ).skipIfOneChild();

    b.rule(equalityExpression).is(
      relationalExpression, b.zeroOrMore(b.firstOf("==", "!="), relationalExpression) // C++
    ).skipIfOneChild();

    b.rule(andExpression).is(
      equalityExpression, b.zeroOrMore("&", equalityExpression) // C++
    ).skipIfOneChild();

    b.rule(exclusiveOrExpression).is(
      andExpression, b.zeroOrMore("^", andExpression) // C++
    ).skipIfOneChild();

    b.rule(inclusiveOrExpression).is(
      exclusiveOrExpression, b.zeroOrMore("|", exclusiveOrExpression) // C++
    ).skipIfOneChild();

    b.rule(logicalAndExpression).is(
      inclusiveOrExpression, b.zeroOrMore("&&", inclusiveOrExpression) // C++
    ).skipIfOneChild();

    b.rule(logicalOrExpression).is(
      logicalAndExpression, b.zeroOrMore("||", logicalAndExpression) // C++
    ).skipIfOneChild();

    b.rule(conditionalExpression).is(
      // EXTENSION: gcc's conditional with omitted operands: the expression is optional
      logicalOrExpression, b.optional("?", b.optional(expression), ":", assignmentExpression) // C++
    ).skipIfOneChild();

    b.rule(throwExpression).is(CxxKeyword.THROW, b.optional(assignmentExpression)); // C++

    b.rule(assignmentExpression).is(
      b.firstOf(
        b.sequence(logicalOrExpression, assignmentOperator, initializerClause), // C++ todo but wrong order
        conditionalExpression, // C++
        throwExpression // C++
      )
    ).skipIfOneChild();

    b.rule(assignmentOperator).is(
      b.firstOf("=", "*=", "/=", "%=", "+=", "-=", ">>=", "<<=", "&=", "^=", "|=") // C++
    );

    b.rule(expression).is(assignmentExpression, b.zeroOrMore(",", assignmentExpression)); // C++

    b.rule(constantExpression).is(conditionalExpression); // C++
  }

  // A.5 Statements
  //
  private static void statements(LexerfulGrammarBuilder b) {

    b.rule(statement).is(
      b.firstOf(
        labeledStatement, // C++
        b.sequence(b.optional(attributeSpecifierSeq), expressionStatement), // C++
        b.sequence(b.optional(attributeSpecifierSeq), compoundStatement), // C++
        b.sequence(b.optional(attributeSpecifierSeq), selectionStatement), // C++
        b.sequence(b.optional(attributeSpecifierSeq), iterationStatement), // C++
        b.sequence(b.optional(attributeSpecifierSeq), jumpStatement), // C++
        declarationStatement, // C++
        b.sequence(b.optional(attributeSpecifierSeq), tryBlock), // C++
        emptyStatement // todo: not C++ => remove
    )
    );

    b.rule(emptyStatement).is(";"); // todo: not C++

    b.rule(initStatement).is(
      b.firstOf(
        expressionStatement, // C++
        simpleDeclaration // C++
      )
    );

    b.rule(condition).is( // todo C++17
      b.firstOf(
        b.sequence(b.optional(attributeSpecifierSeq), conditionDeclSpecifierSeq, declarator, b.firstOf(b.sequence("=", initializerClause), bracedInitList)), // C++
        expression // C++ todo: wrong order
      )
    );
        
    b.rule(labeledStatement).is(
      b.firstOf(
        b.sequence(b.optional(attributeSpecifierSeq), IDENTIFIER, ":", statement), // C++
        b.sequence(b.optional(attributeSpecifierSeq), CxxKeyword.CASE, constantExpression, ":", statement), // C++     
        b.sequence(b.optional(attributeSpecifierSeq), CxxKeyword.CASE, constantExpression, "...", constantExpression, ":", statement), // EXTENSION: gcc's case range
        b.sequence(b.optional(attributeSpecifierSeq), CxxKeyword.DEFAULT, ":", statement) // C++
      )
    );

    b.rule(expressionStatement).is(b.optional(expression), ";"); // C++

    b.rule(compoundStatement).is("{", b.optional(statementSeq), "}"); // C++

    b.rule(statementSeq).is(b.oneOrMore(statement)); // C++

    b.rule(selectionStatement).is(
      b.firstOf(
        b.sequence(CxxKeyword.IF, b.optional(CxxKeyword.CONSTEXPR), "(", b.optional(initStatement), condition, ")", statement, b.optional(CxxKeyword.ELSE, statement)), // C++
        b.sequence(CxxKeyword.SWITCH, "(", b.optional(initStatement), condition, ")", statement)
      )
    );

    b.rule(conditionDeclSpecifierSeq).is( // todo decl-specifier-seq
      b.oneOrMore(
        b.nextNot(b.sequence(declarator, b.firstOf("=", "{"))),
        declSpecifier
      ),
      b.optional(attributeSpecifierSeq)
    );

    b.rule(iterationStatement).is(
      b.firstOf(
        b.sequence(CxxKeyword.WHILE, "(", condition, ")", statement), // C++
        b.sequence(CxxKeyword.DO, statement, CxxKeyword.WHILE, "(", expression, ")", ";"), // C++
        b.sequence(CxxKeyword.FOR, "(", initStatement, b.optional(condition), ";", b.optional(expression), ")", statement), // C++
        b.sequence(CxxKeyword.FOR, "(", forRangeDeclaration, ":", forRangeInitializer, ")", statement), // C++
        b.sequence(CxxKeyword.FOR, "each", "(", forRangeDeclaration, "in", forRangeInitializer, ")", statement) // ???
      )
    );

    b.rule(forRangeDeclaration).is(
      b.optional(attributeSpecifierSeq), forRangeDeclSpecifierSeq, declarator // C++
    );

    b.rule(forRangeDeclSpecifierSeq).is( // todo decl-specifier-seq
      b.oneOrMore(
        b.nextNot(b.sequence(b.optional(declarator), b.firstOf(":", "in"))),
        declSpecifier,
        b.optional(attributeSpecifierSeq)
      )
    );

    b.rule(forRangeInitializer).is(
      b.firstOf(
        expression, // C++
        bracedInitList // C++
      )
    );

    b.rule(jumpStatement).is(
      b.firstOf(
        b.sequence(CxxKeyword.BREAK, ";"), // C++
        b.sequence(CxxKeyword.CONTINUE, ";"), // C++
        b.sequence(CxxKeyword.RETURN, b.optional(expression), ";"), // C++
        b.sequence(CxxKeyword.RETURN, bracedInitList, ";"), // C++
        b.sequence(CxxKeyword.GOTO, IDENTIFIER, ";") // C++
      )
    );

    b.rule(declarationStatement).is(blockDeclaration); // C++
  }

  // A.6 Declarations
  //
  private static void declarations(LexerfulGrammarBuilder b) {
    b.rule(declarationSeq).is(b.oneOrMore(declaration)); // C++

    b.rule(declaration).is(
      b.firstOf(
        blockDeclaration, // C++
        functionDefinition, // C++
        templateDeclaration, // C++
        cliGenericDeclaration, // CLI
        explicitInstantiation, // C++
        explicitSpecialization, // C++
        linkageSpecification, // C++
        namespaceDefinition, // C++
        emptyDeclaration, // C++
        attributeDeclaration, // C++
        vcAtlDeclaration // Attributted-ATL
      )
    );

    b.rule(blockDeclaration).is(
      b.firstOf(
        simpleDeclaration, // C++
        asmDefinition, // C++
        namespaceAliasDefinition, // C++
        usingDeclaration, // C++
        usingDirective, // C++
        staticAssertDeclaration, // C++
        aliasDeclaration, // C++
        opaqueEnumDeclaration // C++
      )
    );

    b.rule(aliasDeclaration).is(CxxKeyword.USING, IDENTIFIER, b.optional(attributeSpecifierSeq), "=", typeId); // C++

    b.rule(simpleDeclaration).is(
      b.firstOf(
        b.sequence(b.optional(simpleDeclSpecifierSeq), b.optional(initDeclaratorList), ";"), // C++
        b.sequence(b.optional(cliAttributes), b.optional(attributeSpecifierSeq), b.optional(simpleDeclSpecifierSeq), b.optional(initDeclaratorList), ";") // C++
      )
    );

    b.rule(staticAssertDeclaration).is(
      CxxKeyword.STATIC_ASSERT, "(", constantExpression, b.optional(",", STRING), ")", ";" // C++
    );

    b.rule(emptyDeclaration).is(";"); // C++

    b.rule(attributeDeclaration).is(attributeSpecifierSeq, ";"); // C++

    b.rule(declSpecifier).is(
      b.firstOf(
        storageClassSpecifier, // C++
        typeSpecifier, // C++
        functionSpecifier, // C++
        CxxKeyword.FRIEND, // C++
        CxxKeyword.TYPEDEF, // C++
        CxxKeyword.CONSTEXPR, // C++
        CxxKeyword.INLINE // C++
      )
    );

    b.rule(simpleDeclSpecifierSeq).is( // todo is decl-specifier-seq
      b.oneOrMore(
        b.nextNot(b.sequence(b.optional(initDeclaratorList), ";")),
        declSpecifier,
        b.optional(attributeSpecifierSeq)
      )
    );

    b.rule(storageClassSpecifier).is(
      b.firstOf(
        CxxKeyword.REGISTER, // C++11, C++14, deprecated with C++17
        CxxKeyword.STATIC, // C++
        CxxKeyword.THREAD_LOCAL, // C++
        CxxKeyword.EXTERN, // C++
        CxxKeyword.MUTABLE // C++
      )
    );

    b.rule(functionSpecifier).is(
      b.firstOf(
        CxxKeyword.VIRTUAL, // C++
        CxxKeyword.EXPLICIT // C++
      )
    );

    b.rule(typedefName).is(IDENTIFIER); // C++

    b.rule(typeSpecifier).is(
      b.firstOf(
        classSpecifier, // C++
        enumSpecifier, // C++
        trailingTypeSpecifier // C++ (todo should be first one)
      )
    );

    b.rule(trailingTypeSpecifier).is(
      b.firstOf(
        simpleTypeSpecifier, // C++
        elaboratedTypeSpecifier, // C++
        typenameSpecifier, // C++
        cvQualifier, // C++
        cliDelegateSpecifier // CLI
      )
    );

    b.rule(typeSpecifierSeq).is(
      b.oneOrMore(
        b.sequence(typeSpecifier, b.optional(attributeSpecifierSeq)) // C++
      )
    );

    b.rule(trailingTypeSpecifierSeq).is(
      b.oneOrMore(
        b.sequence(trailingTypeSpecifier, b.optional(attributeSpecifierSeq)) // C++
      )
    );

    b.rule(simpleTypeSpecifier).is(
      b.firstOf(
        b.sequence(b.optional(nestedNameSpecifier), typeName), // C++
        b.sequence(nestedNameSpecifier, CxxKeyword.TEMPLATE, simpleTemplateId), // C++
        CxxKeyword.CHAR, // C++
        CxxKeyword.CHAR16_T, // C++
        CxxKeyword.CHAR32_T, // C++
        CxxKeyword.WCHAR_T, // C++
        CxxKeyword.BOOL, // C++
        CxxKeyword.SHORT, // C++
        CxxKeyword.INT, // C++
        CxxKeyword.LONG, // C++
        CxxKeyword.SIGNED, // C++
        CxxKeyword.UNSIGNED, // C++
        CxxKeyword.FLOAT, // C++
        CxxKeyword.DOUBLE, // C++
        CxxKeyword.VOID, // C++
        CxxKeyword.AUTO, // C++
        decltypeSpecifier // C++
      )
    );

    b.rule(typeName).is(
      b.firstOf(
        className, // C++
        enumName, // C++
        typedefName, // C++
        simpleTemplateId // C++
      )
    );

    b.rule(decltypeSpecifier).is(
      b.firstOf(
        b.sequence(CxxKeyword.DECLTYPE, "(", expression, ")"), // C++
        b.sequence(CxxKeyword.DECLTYPE, "(", CxxKeyword.AUTO, ")") // C++
      )
    );

    b.rule(elaboratedTypeSpecifier).is(
      b.firstOf( // todo wrong order     
        b.sequence(b.optional(cliAttributes), classKey, templateId), // C++
        b.sequence(b.optional(cliAttributes), classKey, nestedNameSpecifier, b.optional(CxxKeyword.TEMPLATE), simpleTemplateId), // C++
        b.sequence(b.optional(cliAttributes), classKey, b.optional(attributeSpecifierSeq), b.optional(nestedNameSpecifier), IDENTIFIER), // C++
        b.sequence(b.optional(cliAttributes), CxxKeyword.ENUM, b.optional(nestedNameSpecifier), IDENTIFIER) // C++
      )
    );

    b.rule(enumName).is(IDENTIFIER); // C++

    b.rule(enumSpecifier).is(
      b.firstOf(
        b.sequence(enumHead, "{", b.optional(enumeratorList), "}"), // C++
        b.sequence(enumHead, "{", enumeratorList, ",", "}") // C++
      )
    );

    b.rule(enumHead).is(b.optional(vcAtlAttribute), b.optional(cliTopLevelVisibility), enumKey, b.optional(attributeSpecifierSeq), b.firstOf(b.sequence(nestedNameSpecifier, IDENTIFIER), b.optional(IDENTIFIER)), b.optional(enumBase)); // C++

    b.rule(opaqueEnumDeclaration).is(enumKey, b.optional(attributeSpecifierSeq), IDENTIFIER, b.optional(enumBase), ";"); // C++

    b.rule(enumKey).is(CxxKeyword.ENUM, b.optional(b.firstOf(CxxKeyword.CLASS, CxxKeyword.STRUCT))); // C++

    b.rule(enumBase).is(":", typeSpecifierSeq); // C++

    b.rule(enumeratorList).is(enumeratorDefinition, b.zeroOrMore(",", enumeratorDefinition)); // C++

    b.rule(enumeratorDefinition).is(enumerator, b.optional("=", constantExpression)); // C++

    b.rule(enumerator).is(IDENTIFIER, b.optional(attributeSpecifierSeq)); // C++

    b.rule(namespaceName).is(
      b.firstOf(
        IDENTIFIER, // C++
        namespaceAlias // C++
      )
    );
    
    b.rule(namespaceDefinition).is(
      b.firstOf(
        namedNamespaceDefinition, // C++
        unnamedNamespaceDefinition, // C++
        nestedNamespaceDefinition // C++
      )
    );

    b.rule(namedNamespaceDefinition).is(
      b.optional(CxxKeyword.INLINE), CxxKeyword.NAMESPACE, b.optional(attributeSpecifierSeq), IDENTIFIER, "{", namespaceBody, "}" // C++
    );

    b.rule(unnamedNamespaceDefinition).is(
      b.optional(CxxKeyword.INLINE), CxxKeyword.NAMESPACE, b.optional(attributeSpecifierSeq), "{", namespaceBody, "}" // C++
    );

    b.rule(nestedNamespaceDefinition).is(
      CxxKeyword.NAMESPACE, enclosingNamespaceSpecifier, "::", IDENTIFIER, "{", namespaceBody, "}" // C++
    );
    
    b.rule(enclosingNamespaceSpecifier).is(
      IDENTIFIER, b.zeroOrMore(b.sequence("::", IDENTIFIER, b.nextNot("{"))) // C++
    );
           
    b.rule(namespaceBody).is(b.optional(declarationSeq)); // C++

    b.rule(namespaceAlias).is(IDENTIFIER); // C++

    b.rule(namespaceAliasDefinition).is(CxxKeyword.NAMESPACE, IDENTIFIER, "=", qualifiedNamespaceSpecifier, ";"); // C++

    b.rule(qualifiedNamespaceSpecifier).is(b.optional(nestedNameSpecifier), namespaceName); // C++

    b.rule(usingDeclaration).is(
      CxxKeyword.USING, b.optional(CxxKeyword.TYPENAME), nestedNameSpecifier, unqualifiedId, ";"
    );

    b.rule(usingDirective).is(
      b.optional(attributeSpecifierSeq), CxxKeyword.USING, CxxKeyword.NAMESPACE, b.optional(nestedNameSpecifier), namespaceName, ";" // C++
    );

    b.rule(asmDefinition).is(
      b.firstOf(
        b.sequence(CxxKeyword.ASM, "(", STRING, ")", ";"), // C++
        b.sequence(CxxKeyword.ASM, "{", b.oneOrMore(b.nextNot(b.firstOf("}", EOF)), b.anyToken()), "}", b.optional(";")), // VS
        b.sequence(CxxKeyword.ASM, b.oneOrMore(b.nextNot(b.firstOf(";", EOF)), b.anyToken()), ";") // VS
      ));

    b.rule(linkageSpecification).is(CxxKeyword.EXTERN, STRING, b.firstOf(b.sequence("{", b.optional(declarationSeq), "}"), declaration)); // C++

    b.rule(attributeSpecifierSeq).is(b.oneOrMore(attributeSpecifier)); // C++

    b.rule(attributeSpecifier).is(
      b.firstOf(
        b.sequence("[", "[", attributeList, "]", "]"), // C++
        cliAttributes, // CLI: todo: check if attributeSpecifierSeq followed by cliAttributes, not needed
        alignmentSpecifier // C++
      ));

    b.rule(alignmentSpecifier).is(
      b.firstOf(
        b.sequence(CxxKeyword.ALIGNAS, "(", typeId, b.optional("..."), ")"), // C++
        b.sequence(CxxKeyword.ALIGNAS, "(", constantExpression, b.optional("..."), ")") // C++
      ));

    b.rule(attributeList).is(b.optional(attribute), b.zeroOrMore(",", attribute)); // todo

    b.rule(attribute).is(attributeToken, b.optional(attributeArgumentClause)); // C++

    b.rule(attributeToken).is(
      b.firstOf(
        IDENTIFIER, // C++
        attributeScopedToken // C++
      )
    );

    b.rule(attributeScopedToken).is(attributeNamespace, "::", IDENTIFIER); // C++

    b.rule(attributeNamespace).is(IDENTIFIER); // C++

    b.rule(attributeArgumentClause).is(balancedTokenSeq); // todo (...) missing?

    b.rule(balancedTokenSeq).is(b.zeroOrMore(balancedToken)); // C++

    b.rule(balancedToken).is(
      b.firstOf(
        b.sequence("(", balancedTokenSeq, ")"), // C++
        b.sequence("{", balancedTokenSeq, "}"), // C++
        b.sequence("[", balancedTokenSeq, "]"), // C++
        b.oneOrMore(b.nextNot(b.firstOf("(", ")", "{", "}", "[", "]", EOF)), b.anyToken()) // C++
      ));
  }

  // A.7 Declarators
  //
  private static void declarators(LexerfulGrammarBuilder b) {
    b.rule(initDeclaratorList).is(initDeclarator, b.zeroOrMore(",", initDeclarator)); // C++

    b.rule(initDeclarator).is(declarator, b.optional(initializer)); // C++

    b.rule(declarator).is(
      b.firstOf(
        ptrDeclarator, // C++
        b.sequence(noptrDeclarator, parametersAndQualifiers, trailingReturnType) // C++
      )
    );

    b.rule(ptrDeclarator).is(
      b.firstOf(
        b.sequence(ptrOperator, ptrDeclarator), // C++
        noptrDeclarator // C++
      )
    );

    b.rule(noptrDeclarator).is( // todo
      b.firstOf(
        b.sequence(declaratorId, b.optional(attributeSpecifierSeq)), // C++
        b.sequence("(", ptrDeclarator, ")") // C++
      ),
      b.zeroOrMore( // todo wrong?
        b.firstOf(
          parametersAndQualifiers,
          b.sequence("[", b.optional(constantExpression), "]", b.optional(attributeSpecifierSeq))
        )
      )
    );

    b.rule(parametersAndQualifiers).is(
      "(", parameterDeclarationClause, ")", // C++
      b.optional(attributeSpecifierSeq), // C++ todo wrong position
      b.optional(cvQualifierSeq), // C++
      b.optional(cliFunctionModifiers), // C++/CLI
      b.optional(refQualifier), // C++
      b.optional(exceptionSpecification), // C++
      b.optional(trailingReturnType) // todo wrong?
    );

    b.rule(trailingReturnType).is(
      "->", trailingTypeSpecifierSeq, b.optional(abstractDeclarator) // C++
    );

    b.rule(ptrOperator).is(
      b.firstOf(
        b.sequence("*", b.optional(attributeSpecifierSeq), b.optional(cvQualifierSeq)), // C++
        b.sequence("&", b.optional(attributeSpecifierSeq)), // C++
        b.sequence("&&", b.optional(attributeSpecifierSeq)), // C++
        b.sequence(nestedNameSpecifier, "*", b.optional(attributeSpecifierSeq), b.optional(cvQualifierSeq)),
        //CLI handle reference
        b.sequence("^", b.optional(attributeSpecifierSeq), b.optional(cvQualifierSeq)), // CLI
        b.sequence("%", b.optional(attributeSpecifierSeq)), // CLI
        // CLI tracking reference
        b.sequence("^%", b.optional(attributeSpecifierSeq)) // CLI
      )
    );

    b.rule(cvQualifierSeq).is(b.oneOrMore(cvQualifier)); // C++

    b.rule(cvQualifier).is(
      b.firstOf(CxxKeyword.CONST, CxxKeyword.VOLATILE) // C++
    );

    b.rule(refQualifier).is(
      b.firstOf("&", "&&") // C++
    );

    b.rule(declaratorId).is(
      b.firstOf(
        b.sequence(b.optional(nestedNameSpecifier), className), // todo wrong?
        b.sequence(b.optional("..."), idExpression) // C++
      )
    );

    b.rule(typeId).is(typeSpecifierSeq, b.optional(abstractDeclarator)); // C++

    b.rule(abstractDeclarator).is(
      b.firstOf(
        ptrAbstractDeclarator, // C++
        b.sequence(b.optional(noptrAbstractDeclarator), parametersAndQualifiers, trailingReturnType), // C++
        abstractPackDeclarator // C++
      )
    );

    b.rule(ptrAbstractDeclarator).is(
      b.oneOrMore(
        b.firstOf(noptrAbstractDeclarator, ptrOperator) // C++
      )
    );

    b.rule(noptrAbstractDeclarator).is(
      b.oneOrMore(
        b.firstOf(
          parametersAndQualifiers, // C++
          b.sequence("[", b.optional(constantExpression), "]", b.optional(attributeSpecifierSeq)), // C++
          b.sequence("(", ptrAbstractDeclarator, ")") // C++
        )
      )
    );

    b.rule(abstractPackDeclarator).is(
      b.firstOf(
        noptrAbstractPackDeclarator, // C++
        b.sequence(ptrOperator, abstractPackDeclarator) // C++
      )
    );

    b.rule(noptrAbstractPackDeclarator).is(
      b.oneOrMore(
        b.firstOf(
          parametersAndQualifiers, // C++
          b.sequence("[", b.optional(constantExpression), "]", b.optional(attributeSpecifierSeq)), // C++
          "..." // C++  todo not correct
        )
      )
    );

    b.rule(parameterDeclarationClause).is(
      b.firstOf(
        b.sequence(parameterDeclarationList, ",", "..."), // C++
        b.sequence(b.optional(parameterDeclarationList), b.optional("...")), // C++
        cliParameterArray // CLI
      )
    );

    b.rule(cliParameterArray).is(b.optional(attribute), "...", parameterDeclaration);

    b.rule(parameterDeclarationList).is(parameterDeclaration, b.zeroOrMore(",", parameterDeclaration)); // C++

    b.rule(parameterDeclaration).is(
      b.firstOf(
        b.sequence(b.optional(attributeSpecifierSeq), b.optional(vcAtlAttribute), parameterDeclSpecifierSeq, declarator, b.optional("=", initializerClause)), // C++
        b.sequence(b.optional(attributeSpecifierSeq), parameterDeclSpecifierSeq, b.optional(abstractDeclarator), b.optional("=", initializerClause))) // C++
    );

    b.rule(parameterDeclSpecifierSeq).is( // todo is decl-specifier-seq
      b.zeroOrMore(
        b.nextNot(b.sequence(b.optional(declarator), b.firstOf("=", ")", ","))),
        b.sequence(declSpecifier, b.optional("..."))
      ),
      b.optional(attributeSpecifierSeq)
    );

    b.rule(functionDefinition).is(b.optional(attributeSpecifierSeq), // C++
      b.optional(functionDeclSpecifierSeq), // todo is decl-specifier-seq
      declarator, //C++
      b.optional(virtSpecifierSeq), // C++
      functionBody); // C++

    b.rule(functionDeclSpecifierSeq).is( // todo is decl-specifier-seq
      b.oneOrMore(
        b.nextNot(b.sequence(declarator, b.optional(virtSpecifierSeq), functionBody)),
        declSpecifier
      ),
      b.optional(attributeSpecifierSeq)
    );

    b.rule(functionBody).is(
      b.firstOf(
        b.sequence(b.optional(ctorInitializer), compoundStatement), // C++
        functionTryBlock, // C++
        b.sequence("=", CxxKeyword.DELETE, ";"), // C++
        b.sequence("=", CxxKeyword.DEFAULT, ";") // C++
      )
    );

    b.rule(initializer).is(
      b.firstOf(
        braceOrEqualInitializer, // C++
        b.sequence("(", expressionList, ")") // C++
      )
    );

    b.rule(braceOrEqualInitializer).is(
      b.firstOf(
        b.sequence("=", initializerClause), // C++
        bracedInitList // C++
      )
    );

    b.rule(initializerClause).is(
      b.sequence(
        // C-COMPATIBILITY: C99 designated initializers
        b.optional(
          b.firstOf(
            b.sequence(b.zeroOrMore("[", constantExpression, "]"), b.zeroOrMore(".", IDENTIFIER), "="), // C99
            b.sequence("[", constantExpression, "...", constantExpression, "]", "=") // EXTENSION: gcc's designated initializers range
          )
        ),
        b.firstOf(
          assignmentExpression, // C++
          bracedInitList // C++
        )
      )
    );

    b.rule(initializerList).is(initializerClause, b.optional("..."), b.zeroOrMore(",", initializerClause, b.optional("..."))); // C++

    b.rule(bracedInitList).is(
      b.firstOf(
        b.sequence("{", initializerList, b.optional(","), "}"), // C++
        b.sequence("{", "}") // C++
      )
    );
  }

  // A.8 Classes
  //
  private static void classes(LexerfulGrammarBuilder b) {
    b.rule(className).is(
      b.firstOf(
        simpleTemplateId, // C++
        IDENTIFIER // C++
      )
    );

    b.rule(classSpecifier).is(b.optional(vcAtlAttribute), classHead, "{", b.optional(memberSpecification), "}"); // C++

    b.rule(classHead).is(
      b.firstOf(
        b.sequence(b.optional(cliTopLevelVisibility), b.optional(vcAtlAttribute), classKey, b.optional(attributeSpecifierSeq), classHeadName, b.optional(classVirtSpecifier), b.optional(baseClause), b.optional(attributeSpecifierSeq)), // C++
        b.sequence(b.optional(cliTopLevelVisibility), b.optional(vcAtlAttribute), classKey, b.optional(attributeSpecifierSeq), b.optional(baseClause)) // C++
      )
    );

    b.rule(classHeadName).is(b.optional(nestedNameSpecifier), className); // C++

    b.rule(cliTopLevelVisibility).is(
      b.firstOf(
        CxxKeyword.PUBLIC,
        CxxKeyword.PRIVATE
      )
    );

    b.rule(classVirtSpecifier).is(
      b.firstOf("final", // C++
        "sealed", "abstract" // CLI
      )
    );

    b.rule(classKey).is(
      b.firstOf(b.sequence(b.optional(b.firstOf("ref", "value", "interface")), CxxKeyword.CLASS), // C++, CLI
        b.sequence(b.optional(b.firstOf("ref", "value", "interface")), CxxKeyword.STRUCT), // C++, CLI
        CxxKeyword.UNION // C++, CLI
      )
    );

    b.rule(memberSpecification).is(
      b.oneOrMore(
        b.firstOf(
          memberDeclaration, // C++
          b.sequence(accessSpecifier, ":") // C++
        )
      )
    );

    b.rule(memberDeclaration).is( // todo
      b.firstOf(
        b.sequence(
          functionDefinition,
          b.optional(emptyStatement)),
        b.sequence(
          b.optional(attributeSpecifierSeq),
          b.optional(b.firstOf(cliAttributes, vcAtlAttribute)),
          b.optional(b.firstOf("initonly", "literal")),
          b.optional(memberDeclSpecifierSeq),
          b.optional(memberDeclaratorList),
          emptyStatement),

        b.sequence(
          b.optional("::"),
          nestedNameSpecifier,
          b.optional(CxxKeyword.TEMPLATE),
          unqualifiedId,
          emptyStatement),
        usingDeclaration,
        staticAssertDeclaration,
        templateDeclaration,
        aliasDeclaration,
        cliGenericDeclaration,
        cliDelegateSpecifier,
        cliEventDefinition,
        cliPropertyDefinition
      )
    );

    b.rule(cliDelegateDeclSpecifierSeq).is(
      b.oneOrMore(
        b.nextNot(b.sequence(b.optional(declarator), emptyStatement)),
        declSpecifier
      )
    );

    b.rule(cliDelegateSpecifier).is(b.optional(cliAttributes), b.optional(cliTopLevelVisibility), "delegate", cliDelegateDeclSpecifierSeq, declarator, emptyStatement);

    b.rule(memberDeclSpecifierSeq).is(
      b.oneOrMore(
        b.nextNot(b.sequence(b.optional(memberDeclaratorList), emptyStatement)),
        declSpecifier
      ),
      b.optional(attributeSpecifierSeq) // todo wrong position
    );

    b.rule(memberDeclaratorList).is(memberDeclarator, b.zeroOrMore(",", memberDeclarator)); // C++

    b.rule(memberDeclarator).is( // C++
      b.firstOf(
        b.sequence(declarator, braceOrEqualInitializer), // todo braceOrEqualInitializer is optional
        b.sequence(b.optional(IDENTIFIER), b.optional(attributeSpecifierSeq), ":", constantExpression), // C++
        b.sequence(declarator, b.optional(virtSpecifierSeq), b.optional(cliFunctionModifiers), b.optional(pureSpecifier)), // C++
        declarator
      )
    );

    b.rule(virtSpecifierSeq).is(b.oneOrMore(virtSpecifier)); // C++

    b.rule(virtSpecifier).is(
      b.firstOf("override", "final") // C++
    );

    b.rule(pureSpecifier).is(b.sequence("=", "0")); // C++
    
    b.rule(cliFunctionModifiers).is(b.oneOrMore(cliFunctionModifier)); // C++/CLI

    b.rule(cliFunctionModifier).is(
      b.firstOf("abstract", CxxKeyword.NEW, "sealed") // C++/CLI
    );
  }

  // A.9 Derived classes
  //
  private static void derivedClasses(LexerfulGrammarBuilder b) {
    b.rule(baseClause).is(":", baseSpecifierList); // C++

    b.rule(baseSpecifierList).is(baseSpecifier, b.optional("..."), b.zeroOrMore(",", baseSpecifier, b.optional("..."))); // C++

    b.rule(baseSpecifier).is(
      b.firstOf(
        b.sequence(b.optional(attributeSpecifierSeq), baseTypeSpecifier), // C++
        b.sequence(b.optional(attributeSpecifierSeq), CxxKeyword.VIRTUAL, b.optional(accessSpecifier), baseTypeSpecifier), // C++
        b.sequence(b.optional(attributeSpecifierSeq), accessSpecifier, b.optional(CxxKeyword.VIRTUAL), baseTypeSpecifier) // C++
      )
    );

    b.rule(classOrDecltype).is(
      b.firstOf(
        b.sequence(b.optional(nestedNameSpecifier), className), // C++
        decltypeSpecifier // C++
      )
    );

    b.rule(baseTypeSpecifier).is(classOrDecltype); // C++

    b.rule(accessSpecifier).is(
      b.firstOf(
        b.sequence(CxxKeyword.PROTECTED, CxxKeyword.PUBLIC), // ???
        b.sequence(CxxKeyword.PUBLIC, CxxKeyword.PROTECTED), // ???
        b.sequence(CxxKeyword.PROTECTED, CxxKeyword.PRIVATE), // ???
        b.sequence(CxxKeyword.PRIVATE, CxxKeyword.PROTECTED), // ???
        CxxKeyword.PRIVATE, // C++
        CxxKeyword.PROTECTED, // C++
        CxxKeyword.PUBLIC, // C++
        "internal" // ???
      )
    );
  }

  // A.10 Special member functions
  //
  private static void specialMemberFunctions(LexerfulGrammarBuilder b) {
    b.rule(conversionFunctionId).is(CxxKeyword.OPERATOR, conversionTypeId); // C++

    b.rule(conversionTypeId).is(typeSpecifierSeq, b.optional(conversionDeclarator)); // C++

    b.rule(conversionDeclarator).is(b.oneOrMore(ptrOperator)); // C++

    b.rule(ctorInitializer).is(":", memInitializerList); // C++

    b.rule(memInitializerList).is(memInitializer, b.optional("..."), b.zeroOrMore(",", memInitializer, b.optional("..."))); // C++

    b.rule(memInitializer).is(
      b.firstOf(
        b.sequence(memInitializerId, "(", b.optional(expressionList), ")"), // C++
        b.sequence(memInitializerId, bracedInitList) // C++
      )
    );

    b.rule(memInitializerId).is(
      b.firstOf(
        classOrDecltype, // C++
        IDENTIFIER // C++
      )
    );
  }

  // A.11 Overloading
  //
  private static void overloading(LexerfulGrammarBuilder b) {
    b.rule(operatorFunctionId).is(CxxKeyword.OPERATOR, operator); // C++

    b.rule(operator).is( // C++
      b.firstOf(
        b.sequence(CxxKeyword.NEW, "[", "]"),
        b.sequence(CxxKeyword.DELETE, "[", "]"),
        CxxKeyword.NEW, CxxKeyword.DELETE,
        "+", "-", "*", "/", "%", "^", "&", "|", "~",
        "!", "=", "<", ">", "+=", "-=", "*=", "/=", "%=",
        "^=", "&=", "|=", "<<", ">>", ">>=", "<<=", "==", "!=",
        "<=", ">=", "&&", "||", "++", "--", ",", "->*", "->",
        b.sequence("(", ")"),
        b.sequence("[", "]")
      )
    );

    b.rule(literalOperatorId).is(CxxKeyword.OPERATOR, "\"\"", IDENTIFIER);
  }

  private static void properties(LexerfulGrammarBuilder b) {
    b.rule(cliPropertyOrEventName).is(b.firstOf(IDENTIFIER, CxxKeyword.DEFAULT));

    b.rule(cliPropertyDeclSpecifierSeq).is(
        b.oneOrMore(
            b.nextNot(b.sequence(declarator, b.optional(cliPropertyBody), b.optional(";"))),
            typeSpecifier
            )
        );

    b.rule(cliPropertyDefinition).is(
        b.optional(cliAttributes), 
        b.optional(cliPropertyModifiers), 
        "property", 
        b.firstOf(cliPropertyDeclSpecifierSeq, b.sequence(b.optional(nestedNameSpecifier), b.optional(cliPropertyOrEventName))), 
        declarator,
        b.firstOf(
            emptyStatement, 
            cliPropertyBody)
        );

    b.rule(cliPropertyBody).is(b.optional(cliPropertyIndexes), "{", cliAccessorSpecification, "}");

    b.rule(cliPropertyModifiers).is(b.oneOrMore(b.firstOf(CxxKeyword.VIRTUAL, CxxKeyword.STATIC)));

    b.rule(cliPropertyIndexes).is("[", cliPropertyIndexParameterList,"]");

    b.rule(cliPropertyIndexParameterList).is(typeId, b.zeroOrMore(",", typeId));

    b.rule(cliAccessorSpecification).is(b.zeroOrMore(b.optional(b.sequence(accessSpecifier, ":")), cliAccessorDeclaration));

    b.rule(cliAccessorDeclaration).is(
        b.firstOf(
            functionDefinition,
            b.sequence(b.optional(attribute), b.optional(simpleDeclSpecifierSeq), b.optional(memberDeclaratorList), ";")
            )
            );

    b.rule(cliEventDefinition).is(
        b.optional(cliAttributes), 
        b.optional(cliEventModifiers),
        "event",
        cliEventType, 
        IDENTIFIER,
        b.firstOf(emptyStatement, b.sequence("{", cliAccessorSpecification,"}"))
        );

    b.rule(cliEventModifiers).is(b.oneOrMore(b.firstOf(CxxKeyword.VIRTUAL, CxxKeyword.STATIC)));
    b.rule(cliEventType).is(
        b.firstOf(
            b.sequence(b.optional("::"), b.optional(nestedNameSpecifier), typeName, b.optional("^")),
            b.sequence(b.optional("::"), b.optional(nestedNameSpecifier), CxxKeyword.TEMPLATE, templateId, "^")
        )
        );
  }

  // A.12 Templates
  //
  private static void templates(LexerfulGrammarBuilder b) {
    b.rule(templateDeclaration).is(CxxKeyword.TEMPLATE, templateParameterListEnclosed, declaration); // C++

    b.rule(templateParameterListEnclosed).is( // syntax sugar C++
      b.firstOf(
        b.sequence("<", templateParameterList, ">"), // C++
        b.sequence("<", b.zeroOrMore(templateParameter, ","), innerTypeParameter, ">>") // syntax sugar C++
      )
    );

    b.rule(templateParameterList).is(templateParameter, b.zeroOrMore(",", templateParameter)); // C++

    b.rule(templateParameter).is(
      b.firstOf(
        typeParameter, // C++
        parameterDeclaration // C++
      )
    );

    b.rule(typeParameter).is(
      b.firstOf(        
        b.sequence(typeParameterKey, nestedNameSpecifier, "type", b.optional("=", initializerClause)), // C++ special case to handle ::type (not part of standard)
        b.sequence(typeParameterKey, b.optional(IDENTIFIER), "=", typeId), // C++
        b.sequence(typeParameterKey, b.optional("..."), b.optional(IDENTIFIER)), // C++                
        b.sequence(CxxKeyword.TEMPLATE, templateParameterListEnclosed, typeParameterKey, b.optional(IDENTIFIER), "=", idExpression), // C++    
        b.sequence(CxxKeyword.TEMPLATE, templateParameterListEnclosed, typeParameterKey, b.optional("..."), b.optional(IDENTIFIER)) // C++             
      )
    );

    b.rule(innerTypeParameter).is(
      b.firstOf(
        b.sequence(typeParameterKey, b.optional(IDENTIFIER), "=", innerTypeId),
        b.sequence(CxxKeyword.TEMPLATE, templateParameterListEnclosed, CxxKeyword.CLASS, b.optional(IDENTIFIER), "=", innerTypeId)
      )
    );

    b.rule(typeParameterKey).is(
      b.firstOf(CxxKeyword.CLASS, CxxKeyword.TYPENAME) // C++
    );

    b.rule(simpleTemplateId).is(
      b.firstOf(
        b.sequence(templateName, "<", b.optional(templateArgumentList), ">"), // C++
        b.sequence(templateName, "<", innerTemplateId, ">>") // syntax sugar C++
      )
    );

    b.rule(innerTemplateId).is(
      b.sequence(b.zeroOrMore(b.nextNot(b.sequence(innerTypeId, ">>")), templateArgument, b.optional("..."), ","),
        innerTypeId)
    );

    b.rule(innerTypeId).is(
      b.firstOf(
        innerTrailingTypeSpecifier,
        b.sequence(b.oneOrMore(typeSpecifier), innerTrailingTypeSpecifier),
        b.sequence(typeSpecifierSeq, b.optional(noptrAbstractDeclarator), parametersAndQualifiers, "->", innerTrailingTypeSpecifier)
      )
    );

    b.rule(innerTrailingTypeSpecifier).is(
      b.firstOf(
        // simpleTypeSpecifier:
        b.sequence(b.optional("::"), b.optional(nestedNameSpecifier), innerSimpleTemplateId),
        b.sequence(b.optional("::"), b.optional(nestedNameSpecifier), CxxKeyword.TEMPLATE, innerSimpleTemplateId),
        // elaboratedTypeSpecifier:
        b.sequence(b.optional(cliAttributes), classKey, b.optional(nestedNameSpecifier), b.optional(CxxKeyword.TEMPLATE), innerSimpleTemplateId),
        // typenameSpecifier:
        b.sequence(CxxKeyword.TYPENAME, nestedNameSpecifier, CxxKeyword.TEMPLATE, innerSimpleTemplateId)
      // cvQualifier, cliDelegateSpecifier: never end with a template
      )
    );

    b.rule(innerSimpleTemplateId).is(templateName, "<", innerTemplateArgumentList);

    b.rule(templateId).is(
      b.firstOf(
        simpleTemplateId, // C++
        b.sequence(operatorFunctionId, "<", b.optional(templateArgumentList), ">"), // C++
        b.sequence(literalOperatorId, "<", b.optional(templateArgumentList), ">"), // C++
        b.sequence(operatorFunctionId, "<", innerTemplateId, ">>"), // syntax sugar C++
        b.sequence(literalOperatorId, "<", innerTemplateId, ">>") // syntax sugar C++
      )
    );

    b.rule(templateName).is(IDENTIFIER); // C++

    b.rule(templateArgumentList).is(templateArgument, b.optional("..."), b.zeroOrMore(",", templateArgument, b.optional("..."))); // C++

    b.rule(innerTemplateArgumentList).is(innerTemplateArgument, b.optional("..."), b.zeroOrMore(",", innerTemplateArgument, b.optional("...")));

    b.rule(templateArgument).is(
      b.firstOf(
        b.sequence(typeId, b.next(b.firstOf(">", ",", "..."))), // C++
        b.sequence(typenameSpecifier, b.next(b.firstOf(">", ",", "..."))), // seen in gnu system headers
        // FIXME: workaround to parse stuff like "carray<int, 10>"
        // actually, it should be covered by the next rule (constantExpression)
        // but it doesnt work because of ambiguity template syntax <--> relationalExpression
        b.sequence(shiftExpression, b.next(b.firstOf(">", ","))),
        b.sequence(constantExpression, b.next(b.firstOf(">", ","))) // C++
      //        idExpression
      )
    );

    b.rule(innerTemplateArgument).is(
      b.firstOf(
        b.sequence(typeId, b.next(b.firstOf(">>", ",", "..."))),
        b.sequence(typenameSpecifier, b.next(b.firstOf(">>", ",", "..."))), // seen in gnu system headers
        // FIXME: workaround to parse stuff like "carray<int, 10>", see above
        b.sequence(additiveExpression, b.next(b.firstOf(">>", ","))),
        b.sequence(constantExpression, b.next(b.firstOf(">>", ",")))
      //        idExpression
      )
    );

    b.rule(typenameSpecifier).is(
      b.firstOf(
        b.sequence(CxxKeyword.TYPENAME, nestedNameSpecifier, b.optional(CxxKeyword.TEMPLATE), simpleTemplateId), // C++
        b.sequence(CxxKeyword.TYPENAME, nestedNameSpecifier, IDENTIFIER), // C++
        b.sequence(CxxKeyword.TYPENAME, IDENTIFIER), // todo
        IDENTIFIER // todo
      )
    );

    b.rule(explicitInstantiation).is(b.optional(CxxKeyword.EXTERN), CxxKeyword.TEMPLATE, declaration); // C++

    b.rule(explicitSpecialization).is(CxxKeyword.TEMPLATE, "<", ">", declaration); // C++
  }

  private static void generics(LexerfulGrammarBuilder b) {
    b.rule(cliGenericDeclaration).is("generic", "<", cliGenericParameterList, ">", b.optional(cliConstraintClauseList), declaration);

    b.rule(cliGenericParameterList).is(cliGenericParameter, b.zeroOrMore(",", cliGenericParameter));

    b.rule(cliGenericParameter).is(b.optional(attribute), b.firstOf(CxxKeyword.CLASS, CxxKeyword.TYPENAME), IDENTIFIER );

    b.rule(cliGenericId).is(cliGenericName, "<", cliGenericArgumentList, ">");

    b.rule(cliGenericName).is(b.firstOf(IDENTIFIER, operatorFunctionId));

    b.rule(cliGenericArgumentList).is(cliGenericArgument, b.zeroOrMore(",", cliGenericArgument));

    b.rule(cliGenericArgument).is(typeId);

    b.rule(cliConstraintClauseList).is(cliConstraintClause, b.zeroOrMore(cliConstraintClause));

    b.rule(cliConstraintClause).is("where", IDENTIFIER, ":", cliConstraintItemList);

    b.rule(cliConstraintItemList).is(cliConstraintItem, b.zeroOrMore(",", cliConstraintItem));

    b.rule(cliConstraintItem).is(b.firstOf(
        typeId, 
        b.sequence(b.firstOf("ref", "value") , b.firstOf(CxxKeyword.CLASS,CxxKeyword.STRUCT)),
        CxxKeyword.GCNEW
        ));
  }

  // A.13 Exception handling 
  //
  private static void exceptionHandling(LexerfulGrammarBuilder b) {
    b.rule(tryBlock).is(
      b.firstOf(
        b.sequence(CxxKeyword.TRY, compoundStatement, handlerSeq, cliFinallyClause), // CLI
        b.sequence(CxxKeyword.TRY, compoundStatement, handlerSeq), // C++
        b.sequence(CxxKeyword.TRY, compoundStatement, cliFinallyClause) // CLI
      )
    );

    b.rule(functionTryBlock).is(
      b.firstOf(
        b.sequence(CxxKeyword.TRY, b.optional(ctorInitializer), compoundStatement, handlerSeq, cliFinallyClause), // CLI
        b.sequence(CxxKeyword.TRY, b.optional(ctorInitializer), compoundStatement, handlerSeq), // C++
        b.sequence(CxxKeyword.TRY, b.optional(ctorInitializer), compoundStatement, cliFinallyClause) // CLI
      )
    );

    b.rule(handlerSeq).is(b.oneOrMore(handler)); // C++

    b.rule(cliFinallyClause).is("finally", compoundStatement); // CLI

    b.rule(handler).is(CxxKeyword.CATCH, "(", exceptionDeclaration, ")", compoundStatement); // C++

    b.rule(exceptionDeclaration).is(
      b.firstOf(
        b.sequence(b.optional(attributeSpecifierSeq), typeSpecifierSeq, declarator), // C++
        b.sequence(b.optional(attributeSpecifierSeq), typeSpecifierSeq, b.optional(abstractDeclarator)), // C++
        "..."
      )
    );

    b.rule(exceptionSpecification).is(
      b.firstOf(
        dynamicExceptionSpecification, // C++
        noexceptSpecification // C++
      )
    );

    b.rule(dynamicExceptionSpecification).is(CxxKeyword.THROW, "(", b.optional(typeIdList), ")"); // C++

    b.rule(typeIdList).is(
        b.firstOf(
          b.sequence(typeId, b.optional("..."), b.zeroOrMore(",", typeId, b.optional("..."))), // C++ 
          "..."// Microsoft extension
        )
      ); 

    b.rule(noexceptSpecification).is(
      b.firstOf(
        b.sequence(CxxKeyword.NOEXCEPT, "(", constantExpression, ")"), // C++
        CxxKeyword.NOEXCEPT // C++
      )
    );

  }
  
  private static void cliAttributes(LexerfulGrammarBuilder b) {
    b.rule(cliAttributes).is(b.oneOrMore(cliAttributeSection));

    b.rule(cliAttributeSection).is("[", b.optional(cliAttributeTargetSpecifier), cliAttributeList,"]");

    b.rule(cliAttributeTargetSpecifier).is(cliAttributeTarget, ":");

    b.rule(cliAttributeTarget).is(
        b.firstOf(
           "assembly", CxxKeyword.CLASS, "constructor", "delegate", CxxKeyword.ENUM, "event", "field",
           "interface", "method", "parameter", "property", "returnvalue", CxxKeyword.STRUCT
        ));

    b.rule(cliAttributeList).is(cliAttribute, b.zeroOrMore(",", cliAttribute));
    b.rule(cliAttribute).is(b.optional(nestedNameSpecifier), typeName, b.optional(cliAttributeArguments));
    b.rule(cliAttributeArguments).is("(", 
        b.firstOf(
            b.optional(cliPositionArgumentList),
            b.sequence(cliPositionArgumentList, ",", cliNamedArgumentList),
            cliNamedArgumentList
            ),
        ")");

    b.rule(cliPositionArgumentList).is(cliPositionArgument, b.zeroOrMore(",", cliPositionArgument));
    b.rule(cliPositionArgument).is(cliAttributeArgumentExpression);

    b.rule(cliNamedArgumentList).is(cliNamedArgument, b.zeroOrMore(",", cliNamedArgument));
    b.rule(cliNamedArgument).is(IDENTIFIER, "=", cliAttributeArgumentExpression);
    
    b.rule(cliAttributeArgumentExpression).is(assignmentExpression);
    
  }
}
