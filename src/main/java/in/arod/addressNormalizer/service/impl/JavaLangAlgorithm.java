package in.arod.addressNormalizer.service.impl;

import in.arod.addressNormalizer.service.Algorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service(value = "JavaLangAlgorithm")
public class JavaLangAlgorithm implements Algorithm {
    @Value("#{'${algorithmes.JavaLangAlgorithm.max}'}")
    public float max;
    @Value("#{'${algorithmes.JavaLangAlgorithm.min}'}")
    public float min;

    @Override
    public float compare(String s1, String s2) {
        return (float) StringUtils.getJaroWinklerDistance(s1, s2);
    }

    @Override
    public float getMax() {
        return max;
    }

    @Override
    public float getMin() {
        return min;
    }
}
