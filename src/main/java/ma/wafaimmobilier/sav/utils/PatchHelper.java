package ma.wafaimmobilier.sav.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import ma.wafaimmobilier.sav.exceptions.BadRequestException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Map;
import java.util.Set;

@Component
public class PatchHelper {
    private final ObjectMapper mapper;

    public PatchHelper() {
        this.mapper = JsonMapper.builder().enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
                .serializationInclusion(JsonInclude.Include.NON_NULL).build();

    }

    public <T> T mergePatch(Map<String, Object> mapBen, T targetBean, Class<T> classBean) {
        T mergedBean = merge(mapBen, targetBean, classBean);
        validate(mergedBean);
        return mergedBean;
    }

    @SuppressWarnings("unchecked")
    private <T> T merge(Map<String, Object> mapBean, T targetBean, Class<T> beanClass) {
        try {
            Map<String, Object> map = mapper.convertValue(targetBean, Map.class);
            map.putAll(mapBean);
            return mapper.convertValue(map, beanClass);
        } catch (RuntimeException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    private <T> void validate(T bean) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(bean);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

}
