package it.yobibit.jaxb;

import com.sun.codemodel.JDefinedClass;

import java.lang.annotation.Annotation;
import java.util.function.Consumer;

public class LombokCommand extends Command {

    private final Class<? extends Annotation>[] lombokAnnotations;

    public LombokCommand(String name, Class<? extends Annotation> ... lombokAnnotations) {
        super(name, "add Lombok @" + name + " annotation");
        this.lombokAnnotations = lombokAnnotations;
    }

    @Override
    public void editGeneratedClass(JDefinedClass generatedClass) {
        for (Class<? extends Annotation> lombokAnnotation : lombokAnnotations) {
            generatedClass.annotate(lombokAnnotation);
        }
    }

    public static LombokCommand with(String name, Class<? extends Annotation> lombokAnnotation, final Consumer<JDefinedClass> consumer) {
        return new LombokCommand(name, lombokAnnotation) {
            @Override
            public void editGeneratedClass(JDefinedClass generatedClass) {
                consumer.accept(generatedClass);
            }
        };
    }
}
