package in.arod.addressNormalizer.service.impl;

import in.arod.addressNormalizer.service.Algorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class JavaLangAlgorithm implements Algorithm {
    @Override
    public float compare(String s1, String s2) {
       return (float) StringUtils.getJaroWinklerDistance(s1, s2);
    }
}
