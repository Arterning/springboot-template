package cn.ning.springboot.starter.config;

import cn.ning.springboot.starter.annotation.RequestDto;
import org.dozer.DozerBeanMapper;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;

public class WebArgumentConfig extends RequestResponseBodyMethodProcessor {

    private static final DozerBeanMapper modelMapper = new DozerBeanMapper();

    public WebArgumentConfig() {
        super(Collections.singletonList(new MappingJackson2HttpMessageConverter()));
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestDto.class);
    }

    @Override
    protected void validateIfApplicable(WebDataBinder binder, MethodParameter parameter) {
        super.validateIfApplicable(binder, parameter);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) throws Exception {
        Object dto = super.resolveArgument(parameter, mavContainer, webRequest, binderFactory);

        return modelMapper.map(dto, parameter.getParameterType());
    }

    @Override
    protected Object readWithMessageConverters(
            HttpInputMessage inputMessage,
            MethodParameter parameter,
            Type targetType
    ) throws IOException, HttpMediaTypeNotSupportedException, HttpMessageNotReadableException {
        for (Annotation ann : parameter.getParameterAnnotations()) {
            RequestDto dtoType = AnnotationUtils.getAnnotation(ann, RequestDto.class);

            if (dtoType != null) {
                return super.readWithMessageConverters(inputMessage, parameter, dtoType.value());
            }
        }

        throw new RuntimeException();
    }
}
