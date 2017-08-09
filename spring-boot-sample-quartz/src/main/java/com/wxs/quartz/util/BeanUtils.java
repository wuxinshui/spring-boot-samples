package com.wxs.quartz.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BeanUtils extends org.springframework.beans.BeanUtils{
	public static <S, D> D copy(S source, Class<D> clazz) {
		if (source == null) {
			return null;
		}

		try {
			D target = clazz.newInstance();
			copyProperties(source, target);
			return target;
		} catch (Throwable e) {
			throw new RuntimeException("BeanUtils copy error", e);
		}
	}

	public static <S, D> D copy(S source, Class<D> clazz, String... ignoreProperties) {
		if (source == null) {
			return null;
		}

		try {
			D target = clazz.newInstance();
			copyProperties(source, target, ignoreProperties);
			return target;
		} catch (Throwable e) {
			throw new RuntimeException("BeanUtils copy error", e);
		}
	}

	public static <S, D> List<D> copyList(List<S> sourceList, Class<D> clazz) {
		if (sourceList == null) {
			return null;
		}

		List<D> targetList = new ArrayList<>();
		try {
			for (S source : sourceList) {
				D target = clazz.newInstance();

				copyProperties(source, target);

				if (!targetList.contains(target)) {
					targetList.add(target);
				}
			}
		} catch (Throwable e) {
			throw new RuntimeException("BeanUtils copyList error", e);
		}
		return targetList;
	}

	public static <S, D> List<D> copyList(List<S> sourceList, Class<D> clazz, String... ignoreProperties) {
		if (sourceList == null) {
			return null;
		}

		List<D> targetList = new ArrayList<>();
		try {
			for (S source : sourceList) {
				D target = clazz.newInstance();
				copyProperties(source, target, ignoreProperties);
				if (!targetList.contains(target)) {
					targetList.add(target);
				}
			}
		} catch (Throwable e) {
			throw new RuntimeException("BeanUtils copyList error", e);
		}
		return targetList;
	}

	public static void copyProperties(Object source, Object target, String... ignoreProperties) throws BeansException {
		copyProperties(source, target, null, ignoreProperties);
	}

	public static void copyProperties(Object source, Object target) throws BeansException {
		copyProperties(source, target, null, (String[]) null);
	}

	private static void copyProperties(Object source, Object target, Class<?> editable, String... ignoreProperties)
			throws BeansException {

		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");

		Class<?> actualEditable = target.getClass();
		if (editable != null) {
			if (!editable.isInstance(target)) {
				throw new IllegalArgumentException("Target class [" + target.getClass().getName() +
						"] not assignable to Editable class [" + editable.getName() + "]");
			}
			actualEditable = editable;
		}
		PropertyDescriptor[] targetPds = org.springframework.beans.BeanUtils
				.getPropertyDescriptors(actualEditable);
		List<String> ignoreList = (ignoreProperties != null) ? Arrays.asList(ignoreProperties) : null;

		for (PropertyDescriptor targetPd : targetPds) {
			Method writeMethod = targetPd.getWriteMethod();
			if (writeMethod != null && (ignoreProperties == null || (!ignoreList.contains(targetPd.getName())))) {
				PropertyDescriptor sourcePd = org.springframework.beans.BeanUtils
						.getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null) {
					Method readMethod = sourcePd.getReadMethod();
					if (readMethod != null &&
							(ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType()) ||
									readMethod.getReturnType().isEnum() || writeMethod.getParameterTypes()[0].isEnum())) {
						try {
							if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
								readMethod.setAccessible(true);
							}
							Object value = readMethod.invoke(source);
							if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
								writeMethod.setAccessible(true);
							}

							// enum特殊处理 null 空字符串排除
							if (readMethod.getReturnType().isEnum() && writeMethod.getParameterTypes()[0].isAssignableFrom(String.class)) {
								if (value != null) {
									value = value.toString();
								}
							} else if (readMethod.getReturnType().isAssignableFrom(String.class) && writeMethod.getParameterTypes()[0].isEnum()) {
								if (value == null || StringUtils.isBlank(value.toString())) {
									continue;
								}
								value = Enum.valueOf((Class<Enum>) writeMethod.getParameterTypes()[0], "" + value);
							}

							writeMethod.invoke(target, value);
						} catch (Throwable ex) {
							throw new FatalBeanException(
									"Could not copy property '" + targetPd.getName() + "' from source to target", ex);
						}
					}
				}
			}
		}
	}
}
