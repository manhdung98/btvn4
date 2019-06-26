package com.topica.process;


	import java.util.Set;
	 
	import javax.annotation.processing.AbstractProcessor;
	import javax.annotation.processing.Messager;
	import javax.annotation.processing.ProcessingEnvironment;
	import javax.annotation.processing.RoundEnvironment;
	import javax.annotation.processing.SupportedAnnotationTypes;
	import javax.annotation.processing.SupportedSourceVersion;
	import javax.lang.model.SourceVersion;
	import javax.lang.model.element.Element;
	import javax.lang.model.element.ElementKind;
	import javax.lang.model.element.ExecutableElement;
	import javax.lang.model.element.TypeElement;
	import javax.lang.model.type.TypeMirror;
	import javax.tools.Diagnostic.Kind;
	 
	@SupportedAnnotationTypes({ "com.topica.ann.MustInt" })
	@SupportedSourceVersion(SourceVersion.RELEASE_6)
	public class MustIntProcessor extends AbstractProcessor {
	 
	   private Messager messager;
	 
	   @Override
	   public void init(ProcessingEnvironment env) {
	       env.getFiler();
	       messager = env.getMessager();
	   }
	 
	   @Override
	   public boolean process(Set<? extends TypeElement> annotations,
	           RoundEnvironment env) {
	       
	 
	       for (TypeElement ann : annotations) {
	            
	           Set<? extends Element> e2s = env.getElementsAnnotatedWith(ann);

	           for (Element e2 : e2s) {
	               if (e2.getKind() != ElementKind.METHOD) {
	                   messager.printMessage(Kind.ERROR, "@MustInt using for method only ",
	                           e2);
	               } else {
	                   ExecutableElement method = (ExecutableElement) e2;
	                   TypeMirror retType = method.getReturnType();
	                   if (!Integer.class.getName().equals(retType.toString()) && !"int".equals(retType.toString())) {
	                       messager.printMessage(Kind.ERROR,
	                               "Method using @MustInt must return Integer", e2);
	                   }
	               }
	           }
	       }
	       return true;
	   }
	 
}
