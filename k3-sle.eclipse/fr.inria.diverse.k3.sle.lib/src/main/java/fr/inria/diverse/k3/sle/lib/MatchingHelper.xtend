package fr.inria.diverse.k3.sle.lib

import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EDataType
import org.eclipse.emf.ecore.EOperation
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EParameter
import org.eclipse.emf.ecore.EReference

import java.util.List
import java.util.Map
import java.util.HashMap
import java.util.Stack

import fr.inria.diverse.k3.sle.lib.Logger

class MatchingHelper
{
	EPackage pkgA
	EPackage pkgB

	Map<Pair<String, String>, Boolean> matches
	Stack<String> currentMatching

	def boolean match(EPackage a, EPackage b) {
		pkgA = a
		pkgB = b

		if (pkgA === null || pkgB === null) {
			println("This is null BRO")
			return false
		}

		matches = new HashMap<Pair<String, String>, Boolean>
		currentMatching = new Stack<String>

		pkgB.EClassifiers.filter(EClass).forall[clsB |
			pkgA.EClassifiers.filter(EClass).exists[clsA |
				clsA.match(clsB)
			]
		]
	}

	def boolean match(EClass clsA, EClass clsB) {
		if (matches.containsKey(clsA.name -> clsB.name))
			return matches.get(clsA.name -> clsB.name)
		else if (!currentMatching.contains(clsB.name)) {
				currentMatching.push(clsB.name)

				val ret =
				    clsA.name == clsB.name
				&&  clsB.EOperations.forall[opB |
						clsA.EOperations.exists[opA | opA.match(opB)]
					]
				&&  clsB.EAttributes.forall[attrB |
						clsA.EAttributes.exists[attrA | attrA.match(attrB)]
					]
				&&  clsB.EReferences.forall[refB |
						clsA.EReferences.exists[refA | refA.match(refB)]
					]

				currentMatching.pop
				matches.put(clsA.name -> clsB.name, ret)

				return ret
		} else
			return true
	}

	def boolean match(EOperation opA, EOperation opB) {
		val ret =
		    opA.name == opB.name
			// FIXME: Just a hack for now
		&&  if (opA.EType instanceof EDataType || opB.EType instanceof EDataType || !pkgA.EClassifiers.contains(opA.EType))
				opA.EType?.name == opB.EType?.name
			else
				(
					   pkgA.EClassifiers.contains(opA.EType)
					&& pkgB.EClassifiers.contains(opB.EType)
					&& (opA.EType as EClass).match(opB.EType as EClass)
				) || (
					opA.EType === null && opB.EType === null
				) || (
					   opA.EType instanceof EClass
					&& (opA.EType as EClass).EAllSuperTypes.contains(opB.EType)
				)
		&&  opA.EParameters.match(opB.EParameters)
		&&  opA.EExceptions.forall[excA |
				opB.EExceptions.exists[excB |
					if (excA instanceof EDataType || excB instanceof EDataType)
						excA.name == excB.name
					else
						(
							   pkgA.EClassifiers.contains(excA)
							&& pkgB.EClassifiers.contains(excB)
							&& (excA as EClass).match(excB as EClass)
						) || (
							(excA as EClass).EAllSuperTypes.contains(excB)
						)
				]
			]

		if (opA.name == opB.name && !ret)
			Logger.log(opA + "doesn't match")

		return ret
	}

	def boolean match(List<EParameter> paramsA, List<EParameter> paramsB) {
		var rank = 0

		for (paramB : paramsB) {
			if (rank >= paramsA.size)
				return false

			val paramA = paramsA.get(rank)

			if (paramA.EType instanceof EDataType || paramB.EType instanceof EDataType)
				if (paramA.EType.name != paramB.EType.name)
					return false
			else if (pkgA.EClassifiers.contains(paramA.EType)
					&& pkgB.EClassifiers.contains(paramB.EType))
				if (!(paramA.EType as EClass).match(paramB.EType as EClass))
					return false
			else
				if (!(paramA.EType as EClass).EAllSuperTypes.contains(paramB.EType))
					return false

			if (
				   paramA.lowerBound != paramB.lowerBound
				|| paramA.upperBound != paramB.upperBound
				|| paramA.unique != paramB.unique
				|| paramA.ordered && !paramB.ordered
			)
				return false

			rank = rank + 1
		}

		true
	}

	def boolean match(EAttribute attrA, EAttribute attrB) {
		val ret =
		    attrA.name == attrB.name
		&&  (attrA.changeable || !attrB.changeable)
		&&  (attrA.unique == attrB.unique)
		&&  (!attrA.ordered || attrB.ordered)
		&&  if (attrA.EType instanceof EDataType && attrB.EType instanceof EDataType)
				attrA.EType.name == attrB.EType.name
			else if (attrA.EType instanceof EClass && attrB.EType instanceof EClass)
				(
					   pkgA.EClassifiers.contains(attrA.EType)
					&& pkgB.EClassifiers.contains(attrB.EType)
					&& (attrA.EType as EClass).match(attrB.EType as EClass)
				) || (
					   (attrA.EType as EClass).EAllSuperTypes.contains(attrB.EType)
					&& !attrA.changeable
				)
			else false
		&&  (attrA.lowerBound == attrB.lowerBound)
		&&  (attrA.upperBound == attrB.upperBound)

		if (attrA.name == attrB.name && !ret)
			Logger.log(attrA + "doesn't match")

		return ret
	}

	def boolean match(EReference refA, EReference refB) {
		val ret =
		    refA.name == refB.name
		&&  (refA.changeable || !refB.changeable)
		&&  (refA.containment == refB.containment)
		&&  (refA.unique == refB.unique)
		&&  (!refA.ordered || refB.ordered)
		&&  (refA.lowerBound == refB.lowerBound)
		&&  (refA.upperBound == refB.upperBound)
		&&  (!(refA.EOpposite !== null) || (refB.EOpposite !== null && refA.EOpposite.name == refB.EOpposite.name))

		if (refA.name == refB.name && !ret)
			Logger.log(refA + "doesn't match")

		return ret
	}
}
