package in.arod.addressNormalizer.service.impl;

import in.arod.addressNormalizer.service.Algorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service(value = "JavaLangAlgorithm")
public class JavaLangAlgorithm implements Algorithm {
    public float max;
    public float min;
    @Override
    public float compare(String s1, String s2) {
       return (float) StringUtils.getJaroWinklerDistance(s1, s2);
    }
}
