package org.example.Annotation;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationParse {


    @GetMapping(path = "/test")
    @PutMapping(path = "/test")
    @DeleteMapping(path = "/test")
    @PostMapping(path = "")
    public void test(){}
    public static void main(String[] args) throws NoSuchMethodException {
        System.out.println(new AnnotationParse().isPostMapping());
        System.out.println(new AnnotationParse().isDeleteMapping());
        System.out.println(new AnnotationParse().isGetMapping());
        System.out.println(new AnnotationParse().isPutMapping());
    }


    public boolean isPostMapping() throws NoSuchMethodException {

      Method declaredMethod = new AnnotationParse().getClass().getDeclaredMethod("test");
        PostMapping annotation = declaredMethod.getAnnotation(PostMapping.class);
        Class<? extends Annotation> aClass = annotation.annotationType();
        RequestMapping annotation1 = (RequestMapping) aClass.getAnnotation(RequestMapping.class);
        RequestMethod method = annotation1.method();
        if(annotation!=null&&method.equals(RequestMethod.POST)){
         if (annotation.path() != null) {
             return true;
         } else {
             return false;
         }
     }else {
         return false;
     }
    }
    public boolean isGetMapping() throws NoSuchMethodException {

        Method declaredMethod = new AnnotationParse().getClass().getDeclaredMethod("test");
        GetMapping annotation = declaredMethod.getAnnotation(GetMapping.class);
        Class<? extends Annotation> aClass = annotation.annotationType();
        RequestMapping annotation1 = (RequestMapping) aClass.getAnnotation(RequestMapping.class);
        RequestMethod method = annotation1.method();
        if(annotation!=null&&method.equals(RequestMethod.GET)) {
            if (annotation.path() != null) {
                return true;
            } else {
                return false;
            }
        }else {
            return false;
        }

    }
    public boolean isPutMapping() throws NoSuchMethodException {

        Method declaredMethod = new AnnotationParse().getClass().getDeclaredMethod("test");
        PutMapping annotation = declaredMethod.getAnnotation(PutMapping.class);
        Class<? extends Annotation> aClass = annotation.annotationType();
        RequestMapping annotation1 = (RequestMapping) aClass.getAnnotation(RequestMapping.class);
        RequestMethod method = annotation1.method();
        if(annotation!=null&&method.equals(RequestMethod.PUT)) {
            if (annotation.path() != null) {
                return true;
            } else {
                return false;
            }
        }else {
            return false;
        }
    }
    public boolean isDeleteMapping() throws NoSuchMethodException {

        Method declaredMethod = new AnnotationParse().getClass().getDeclaredMethod("test");
        DeleteMapping annotation = declaredMethod.getAnnotation(DeleteMapping.class);
        Class<? extends Annotation> aClass = annotation.annotationType();
        RequestMapping annotation1 = (RequestMapping) aClass.getAnnotation(RequestMapping.class);
        RequestMethod method = annotation1.method();
        if(annotation!=null&&method.equals(RequestMethod.DELETE)) {
            if (annotation.path() != null) {
                return true;
            } else {
                return false;
            }
        }else {
            return false;
        }

    }
}
