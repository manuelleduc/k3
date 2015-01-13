package fr.inria.diverse.melange.algebra

import com.google.inject.ImplementedBy

import fr.inria.diverse.melange.metamodel.melange.Metamodel
import fr.inria.diverse.melange.metamodel.melange.Aspect
import fr.inria.diverse.melange.metamodel.melange.ModelType

/**
 * Just to simplify some common operations for now
 *
 * Should then be defined only in terms of metamodels/modeltypes
 * which will require important modifications to the metalanguage
 */
@ImplementedBy(EmfCompareAlgebra)
interface ModelTypeAlgebra
{
	def boolean isSubtypeOf(ModelType mt1, ModelType mt2)
	def boolean isTypedBy(Metamodel mm, ModelType mt)
	def void weaveAspect(Metamodel mm, Aspect aspect)
}
