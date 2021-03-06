package com.webcohesion.enunciate.modules.jaxws.api.impl;

import com.webcohesion.enunciate.api.Styles;
import com.webcohesion.enunciate.api.datatype.DataTypeReference;
import com.webcohesion.enunciate.api.services.Parameter;
import com.webcohesion.enunciate.javac.javadoc.JavaDoc;
import com.webcohesion.enunciate.modules.jaxb.api.impl.DataTypeReferenceImpl;
import com.webcohesion.enunciate.modules.jaxws.model.WebParam;

import javax.lang.model.element.AnnotationMirror;
import java.util.Map;
import java.util.Set;

/**
 * @author Ryan Heaton
 */
public class ParameterImpl implements Parameter {

  private final WebParam param;

  public ParameterImpl(WebParam param) {
    this.param = param;
  }

  @Override
  public String getName() {
    String name = this.param.getSimpleName().toString();
    if (!name.equals(param.getBaseParamName())) {
      name += " (" + param.getBaseParamName() + ")";
    }
    return name;
  }

  @Override
  public String getDescription() {
    return this.param.getDocValue();
  }

  @Override
  public DataTypeReference getDataType() {
    return new DataTypeReferenceImpl(this.param.getXmlType(), false);
  }

  @Override
  public Map<String, AnnotationMirror> getAnnotations() {
    return this.param.getAnnotations();
  }

  @Override
  public JavaDoc getJavaDoc() {
    return this.param.getJavaDoc();
  }

  @Override
  public Set<String> getStyles() {
    return Styles.gatherStyles(this.param, this.param.getContext().getContext().getConfiguration().getAnnotationStyles());
  }
}
