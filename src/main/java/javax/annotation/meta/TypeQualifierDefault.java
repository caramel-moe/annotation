package javax.annotation.meta;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This qualifier is applied to an annotation to denote that the annotation
 * defines a default type qualifier that is visible within the scope of the
 * element it is applied to.
 *
 * @see <a href="https://code.google.com/archive/p/jsr-305/">from jsr-305</a>
 */

@Documented
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TypeQualifierDefault {
    ElementType[] value() default {};
}
