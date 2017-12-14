package fr.inria.diverse.k3.al.annotationprocessor

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@Retention(RetentionPolicy::RUNTIME)
@Target(ElementType.TYPE)
annotation Customizable {
	
}